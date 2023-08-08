package com.camp.campingapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.facebook.CallbackManager

import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton

import com.facebook.CallbackManager.Factory.create
import com.facebook.FacebookSdk


/**
 * Demonstrate Firebase Authentication using a Facebook access token.
 */
class FacebookLoginActivity : Activity() {
    private var callbackManager = create()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_facebook_login)
        FacebookSdk.sdkInitialize(applicationContext);

        val loginButton: LoginButton = findViewById(R.id.login_button)

        // Set the initial permissions to request from the user while logging in
        loginButton.permissions = listOf(EMAIL, USER_POSTS)
        loginButton.authType = AUTH_TYPE

        // Register a callback to respond to the user
        loginButton.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    setResult(RESULT_OK)
                    finish()
                }

                override fun onCancel() {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                override fun onError(error: FacebookException) {
                    // Handle exception
                }
            })
    }

    companion object {
        private const val EMAIL = "email"
        private const val USER_POSTS = "user_posts"
        private const val AUTH_TYPE = "rerequest"
    }
}