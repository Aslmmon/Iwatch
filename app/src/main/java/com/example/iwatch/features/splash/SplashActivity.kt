package com.example.iwatch.features.splash

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.iwatch.MainActivity
import com.example.iwatch.R
import com.example.iwatch.common.Util.NetworkUtil
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit


class SplashActivity : AppCompatActivity() {

    companion object {
         lateinit var obs : Observer<Long>
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


       obs = getObserver()

        registerConnection()


    }

    private fun registerConnection() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val reciever = Reciever()
            val intent = IntentFilter()
            intent.addAction("android.net.conn.CONNECTIVITY_CHANGE")
            registerReceiver(reciever, intent)
        }
    }

    private fun getObserver(): Observer<Long> {
        return object : Observer<Long> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(s: Long) {
                Log.d("", "onNext: $s")
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onError(e: Throwable) {
                Log.e("", "onError: " + e.message)
            }

            override fun onComplete() {
                Log.d("", "onComplete")
            }
        }
    }

}

class Reciever : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val status = NetworkUtil.getConnectivityStatusString(context!!)

        if (intent != null) {
            if ("android.net.conn.CONNECTIVITY_CHANGE" == intent.action) {
                if (status == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED) {

                    Toast.makeText(
                        context,
                        "No internet",
                        Toast.LENGTH_SHORT
                    ).show()



                } else {
                    Observable.timer(3, TimeUnit.SECONDS).subscribe(SplashActivity.obs)

                    Toast.makeText(
                        context,
                        " Connected To internet" ,
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }


    }


}