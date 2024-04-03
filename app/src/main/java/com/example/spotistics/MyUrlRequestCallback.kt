package com.example.spotistics

import android.net.http.HttpException
import android.net.http.UrlRequest
import android.net.http.UrlResponseInfo
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import java.nio.ByteBuffer

private const val TAG = "MyUrlRequestCallback"

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class MyUrlRequestCallback : UrlRequest.Callback {
    override fun onRedirectReceived(p0: UrlRequest, p1: UrlResponseInfo, p2: String) {
        Log.i(TAG, "onRedirectReceived method called.")
        // You should call the request.followRedirect() method to continue
        // processing the request.
        p0.followRedirect()
    }

    override fun onResponseStarted(p0: UrlRequest, p1: UrlResponseInfo) {
        Log.i(TAG, "onResponseStarted method called.")
        // You should call the request.read() method before the request can be
        // further processed. The following instruction provides a ByteBuffer object
        // with a capacity of 102400 bytes for the read() method. The same buffer
        // with data is passed to the onReadCompleted() method.
        p0.read(ByteBuffer.allocateDirect(102400))
    }

    override fun onReadCompleted(p0: UrlRequest, p1: UrlResponseInfo, byteBuffer: ByteBuffer) {
        Log.i(TAG, "onReadCompleted method called.")
        // You should keep reading the request until there's no more data.
        byteBuffer.clear()
        p0.read(byteBuffer)
    }

    override fun onSucceeded(p0: UrlRequest, p1: UrlResponseInfo) {
        Log.i(TAG, "onSucceeded method called.")
    }

    override fun onFailed(p0: UrlRequest, p1: UrlResponseInfo?, p2: HttpException) {
        TODO("Not yet implemented")
    }

    override fun onCanceled(p0: UrlRequest, p1: UrlResponseInfo?) {
        TODO("Not yet implemented")
    }
}
