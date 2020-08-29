package com.ljt.katp.server

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MainService : Service() {

    private var mHttpServer: HttpServer? = null

    override fun onCreate() {
        super.onCreate()
        mHttpServer = HttpServer(HTTP_IP, HTTP_PORT)
        try {
            mHttpServer?.start()
        }catch (e: Exception){
            e.printStackTrace()
            val service = Intent(this, MainService::class.java)
            startService(service)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        mHttpServer?.stop()
        super.onDestroy()
    }

}