package com.shivamsatija.wikisearch.ui.base

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.shivamsatija.wikisearch.di.component.ActivityComponent
import com.shivamsatija.wikisearch.WikiSearchApplication
import com.shivamsatija.wikisearch.di.component.DaggerActivityComponent
import com.shivamsatija.wikisearch.di.module.ActivityModule

abstract class BaseActivity : AppCompatActivity(), MvpView {

    @LayoutRes
    abstract fun getLayoutResource(): Int

    abstract fun performDependencyInjection(activityComponent: ActivityComponent)

    abstract fun setup(state: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        val activityComponent = DaggerActivityComponent.builder()
            .applicationComponent((application as WikiSearchApplication).getApplicationComponent())
            .activityModule(ActivityModule(this))
            .build()
        performDependencyInjection(activityComponent)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())

        setup(savedInstanceState)
    }

    override fun showToast(message: String?) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(this, message!!, Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}