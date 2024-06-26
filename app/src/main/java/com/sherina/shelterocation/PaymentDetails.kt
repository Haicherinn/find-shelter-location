package com.sherina.shelterocation

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONException
import org.json.JSONObject


class PaymentDetails : AppCompatActivity() {
    var txtId: TextView? = null
    var txtAmount: TextView? = null
    var txtStatus: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_details)
        txtId = findViewById<View>(R.id.txtId) as TextView
        txtAmount = findViewById<View>(R.id.txtAmount) as TextView
        txtStatus = findViewById<View>(R.id.txtStatus) as TextView
        val intent = intent
        try {
            val jsonObject = JSONObject(intent.getStringExtra("PaymentDetails"))
            showDetails(
                jsonObject.getJSONObject("response"),
                intent.getStringExtra("PaymentAmount")
            )
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun showDetails(response: JSONObject, paymentAmount: String?) {
        try {
            txtId!!.text = response.getString("id")
            txtAmount!!.text = response.getString("amount")
            txtStatus!!.text = response.getString(String.format("$", paymentAmount))
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }


}