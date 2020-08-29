package com.ljt.katp.server

import com.google.gson.Gson
import fi.iki.elonen.NanoHTTPD

class HttpServer(hostname: String?, port: Int) : NanoHTTPD(hostname, port) {

    private val TAG = "HttpServer"
    private var count = 0 //用于记录请求为第几次
    private var mGson: Gson = Gson() //用于记录请求为第几次

    override fun serve(session: IHTTPSession?): Response {
        return dealWith(session);
    }

    private fun dealWith(session: IHTTPSession?): Response {

        if (Method.POST == session?.method) {
            //获取请求头数据
            val header = session.headers
            //获取传参参数
            val param = session.parameters

            return when(session.uri){

                HTTP_URI_SUM ->{
                    var b: Int = param["number"]?.get(0).toString().toInt()
                    for (i in 0..100){ b += 1 }
                    responseJsonString(200, b, "Success！")
                }

                HTTP_URI_HELLO -> responseJsonString(200, "Hello ${param["name"]?.get(0)} !", "Success！")

                else -> responseJsonString(404, "It's nothing!", "Success！")

            }


        }else if (Method.GET == session?.method){

            if(session.uri == HTTP_URI_SAYSOMETHING){
                count++
                val param = session.parameters
                return responseJsonString(200, "${param["name"]?.get(0)}, say somthing to me $count, ok?", "请求成功！")
            }

        }

        return responseJsonString(404, "", "Request not support!")
    }


    private fun <T: Any> responseJsonString(code: Int, data: T, msg: String): Response {
        val responser = Responser<T>(code, data, msg)
        return newFixedLengthResponse(mGson.toJson(responser))
    }


}