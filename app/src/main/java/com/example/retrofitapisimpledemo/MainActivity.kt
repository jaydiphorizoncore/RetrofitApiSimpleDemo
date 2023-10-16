package com.example.retrofitapisimpledemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitapisimpledemo.model.DataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var edName: EditText
    lateinit var edJob: EditText
    private lateinit var btnSend: Button
    lateinit var tvResponse: TextView
    lateinit var loadingProgress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        clickListener()

        val quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {

            val result = quotesApi.getQuotes()
            Log.d("Tag", result.body().toString())

        }
    }

    private fun initView() {
        edName = findViewById(R.id.edName)
        edJob = findViewById(R.id.edJob)
        btnSend = findViewById(R.id.btnSent)
        tvResponse = findViewById(R.id.tvResponce)
        loadingProgress = findViewById(R.id.loadingProgress)
    }

    private fun clickListener() {
        btnSend.setOnClickListener {
            if (edName.text.toString().isEmpty() && edJob.text.toString().isEmpty()) {
                Toast.makeText(
                    this@MainActivity,
                    "Please enter both the values",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            // calling a method to post the data and passing our name and job.

            postData(edName.text.toString(), edJob.text.toString());
        }
    }

    private fun postData(name: String, job: String) {

        val retrofit = RetrofitHelper.getInstance2().create(QuotesApi::class.java)

        // calling a method to create a post and passing our modal class.

        val call = retrofit.createPost(DataModel(name, job))

        call.enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                Toast.makeText(this@MainActivity, "Data Added in api", Toast.LENGTH_LONG).show()

                loadingProgress.visibility = View.GONE

                edName.setText("")
                edJob.setText("")
                edName.requestFocus()

                // we are getting response from our body
                // and passing it to our modal class.

                val responseFromAPI = response.body()
                val responseString =
                    "Response code : ${response.code()} \n Name : ${responseFromAPI?.name} \n Job : ${responseFromAPI?.job}"
                tvResponse.text = responseString
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                tvResponse.text = "Error Found is : ${t.message} "
            }
        })
    }
}