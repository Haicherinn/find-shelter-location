package com.sherina.shelterocation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.SupportMapFragment
import com.paypal.android.sdk.payments.*
import com.sherina.shelterocation.Config.Config
import org.json.JSONException
import java.math.BigDecimal


class DonasiActivity : AppCompatActivity() {
    var btnPayNow: Button? = null
    var edtAmount: EditText? = null
    var amount = ""
    override fun onDestroy() {
        stopService(Intent(this, PayPalService::class.java))
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donasi)
        val intent = Intent(this, PayPalService::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, DonasiActivity.Companion.config)
        btnPayNow = findViewById<View>(R.id.btnPayNow) as Button
        edtAmount = findViewById<View>(R.id.edtAmount) as EditText
        btnPayNow!!.setOnClickListener { processPayment() }

    }

    private fun processPayment() {
        amount = edtAmount!!.text.toString()
        val payPalPayment = PayPalPayment(
            BigDecimal(amount), "USD",
            "Donate for Shelter", PayPalPayment.PAYMENT_INTENT_SALE
        )
        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, DonasiActivity.Companion.config)
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment)
        startActivityForResult(intent, DonasiActivity.Companion.PAYPAL_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DonasiActivity.Companion.PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                val confirmation =
                    data!!.getParcelableExtra<PaymentConfirmation>(PaymentActivity.EXTRA_RESULT_CONFIRMATION)
                if (confirmation != null) {
                    try {
                        val paymentDetails = confirmation.toJSONObject().toString(4)
                        startActivity(
                            Intent(this, PaymentDetails::class.java)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", amount)
                        )
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            } else Toast.makeText(this, "Cancle", Toast.LENGTH_SHORT).show()
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) Toast.makeText(
            this,
            "Invalid",
            Toast.LENGTH_SHORT
        ).show()
    }

    companion object {
        private const val PAYPAL_REQUEST_CODE = 7171
        private val config = PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}