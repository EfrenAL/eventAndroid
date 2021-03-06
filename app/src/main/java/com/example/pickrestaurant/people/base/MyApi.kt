package com.example.pickrestaurant.people.base

import com.example.pickrestaurant.people.model.Event
import com.example.pickrestaurant.people.model.User
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by efren on 12/07/2018.
 */
interface MyApi {

    @POST("/user")
    fun signUpUser(@Body user: UserSignUpPostParameter): Observable<Response<User>>

    @POST("/user/login")
    fun loginUser(@Body user: UserLoginPostParameter): Observable<Response<User>>

    @POST("/user/facebook")
    fun loginSignUpUserFacebook(@Body user: UserLoginSignUpFbPostParameter): Observable<Response<User>>

    @PUT("/user")
    fun updateUser(@Header("Auth") auth: String, @Body user: UserUpdatePutParameter): Observable<Response<User>>

    @GET("/user/event/all")
    fun getEvents(@Header("Auth") auth: String): Observable<EventsResponse>

    @POST("/user/event/{eventCode}")
    fun postEvent(@Header("Auth") auth: String, @Path("eventCode") eventCode: String): Observable<ResponseBody>

    @GET("/event/user/{eventId}")
    fun getPeople(@Header("Auth") auth: String, @Path("eventId") eventId: String): Observable<Response<PeopleResponse>>

}

data class UserLoginPostParameter(var email: String, var password: String)

data class UserLoginSignUpFbPostParameter(var email: String, var name: String, var token: String, var pictureUrl: String)

data class UserSignUpPostParameter(var name: String, var email: String, var password: String)

data class UserUpdatePutParameter(var name: String?, var description: String?, var pictureUrl: String?)

data class EventsResponse(var user: User, var events: List<Event>)

data class PeopleResponse(var user: Event, var users: List<User>)