package com.cleanarchitecture.main

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cleanarchitecture.R
import com.cleanarchitecture.entity.User
import com.jakewharton.rxbinding3.view.clicks
import dagger.android.AndroidInjection
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    @Inject
    lateinit var factory: MainPresenter.Factory
    lateinit var presenter: MainPresenter

    override val loginClicksWithPin: Observable<String>
        get() = loginButton.clicks().map { pinEditText.text.toString() }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = ViewModelProviders.of(this, factory).get(MainPresenter::class.java)
    }

    override fun showUser(user: User) {
        loginButton.text = "Success"
    }

    override fun showError(message: String) {
        errorTextView.text = message
        errorTextView.visibility = View.VISIBLE
    }

    override fun hideError() {
        errorTextView.visibility = View.GONE
    }
}
