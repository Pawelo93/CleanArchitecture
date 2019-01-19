package com.cleanarchitecture

import com.cleanarchitecture.entity.User
import com.cleanarchitecture.interactors.services.UserFriendlyExceptionService
import com.cleanarchitecture.interactors.useCases.VerifyPinAndGetUserUseCase
import com.cleanarchitecture.main.MainPresenter
import com.cleanarchitecture.main.MainView
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test


class MainPresenterTest {

    private lateinit var mainPresenter: MainPresenter

    private val view: MainView = mock()
    private val verifyPinAndGetUserUseCase: VerifyPinAndGetUserUseCase = mock()
    private val userFriendlyExceptionService: UserFriendlyExceptionService = mock()

    private val loginClicks = PublishSubject.create<String>()

    @Before
    fun setUp() {
        whenever(view.loginClicksWithPin).thenReturn(loginClicks)
        mainPresenter = MainPresenter(
            verifyPinAndGetUserUseCase,
            userFriendlyExceptionService,
            view,
            TestTransformer()
        )
    }

    @Test
    fun `loginClicks load user when pin is verified`() {
        val pin = "1111"
        val user = User("Test")
        whenever(verifyPinAndGetUserUseCase(pin)).thenReturn(Single.just(user))
        loginClicks.onNext(pin)
        verify(view).showUser(user)
    }

    @Test
    fun `loginClicks shows user friendly error message when pin not contains 4 digits`() {
        val pin = "111"
        val throwable = Throwable("Error")
        val errorMessage = "Ups, coś poszło nie tak"
        whenever(verifyPinAndGetUserUseCase(pin)).thenReturn(Single.error(throwable))
        whenever(userFriendlyExceptionService(throwable)).thenReturn(Single.just(errorMessage))
        loginClicks.onNext(pin)
        verify(view).showError(errorMessage)
    }

    @Test
    fun `loginClicks hides error`() {
        val pin = "1111"
        whenever(verifyPinAndGetUserUseCase(pin)).thenReturn(Single.just(User()))
        loginClicks.onNext(pin)
        verify(view).hideError()
    }
}