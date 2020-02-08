package com.arctouch.codechallenge.splash

import android.content.Intent
import android.os.Bundle
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.base.BaseActivity
import com.arctouch.codechallenge.views.activities.HomeActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        startActivity(Intent(this, HomeActivity::class.java))

//        api.genres()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                Cache.cacheGenres(it.genres)
//                startActivity(Intent(this, HomeActivity::class.java))
//                finish()
//            }
    }
}
