package com.example.spotistics

import android.util.Log
import org.chromium.net.CronetException
import org.chromium.net.UrlRequest
import org.chromium.net.UrlResponseInfo
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets

private const val TAG = "MyUrlRequestCallback"

class UrlRequestCallback : UrlRequest.Callback() {
    private val responseData = StringBuilder()
    private var jsonResponse: String? = null

    override fun onRedirectReceived(request: UrlRequest?, info: UrlResponseInfo?, newLocationUrl: String?) {
        Log.i(TAG, "onRedirectReceived method called.")
        // You should call the request.followRedirect() method to continue
        // processing the request.
        request?.followRedirect()
    }

    override fun onResponseStarted(request: UrlRequest?, info: UrlResponseInfo?) {
        val httpStatusCode = info?.httpStatusCode
        val responseHeaders = info?.allHeaders

        if (httpStatusCode == 200) {
            // The request was fulfilled. Start reading the response.
            request?.read(ByteBuffer.allocateDirect(102400))
        } else if (httpStatusCode == 503) {
            // The service is unavailable. You should still check if the request
            // contains some data.
            request?.read(ByteBuffer.allocateDirect(102400))
        }
    }

    override fun onReadCompleted(request: UrlRequest?, info: UrlResponseInfo?, byteBuffer: ByteBuffer?) {
        Log.i(TAG, "onReadCompleted method called.")

        // Read the response body
        byteBuffer!!.flip()
        val bytes = ByteArray(byteBuffer.remaining())
        byteBuffer[bytes]
        val chunk = String(bytes, StandardCharsets.UTF_8)

        // Append chunk to responseData
        responseData.append(chunk)

        // Clear the buffer for the next read
        byteBuffer.clear()

        // Continue reading
        request!!.read(byteBuffer)
    }

    override fun onSucceeded(request: UrlRequest?, info: UrlResponseInfo?) {
        Log.i(TAG, "onSucceeded method called.")

        // Request completed successfully, parse responseData as JSON
        jsonResponse = responseData.toString()

        // Reset responseData for potential reuse
        responseData.setLength(0)
    }

    override fun onFailed(request: UrlRequest?, info: UrlResponseInfo?, error: CronetException?) {
        // The request has failed. If possible, handle the error.
        Log.e(TAG, "The request failed.", error)
    }

    fun getJsonResponse(): String? {
        return jsonResponse
    }
}