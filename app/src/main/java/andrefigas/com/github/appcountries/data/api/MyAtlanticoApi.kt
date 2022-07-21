package andrefigas.com.github.appcountries.data.api

import andrefigas.com.github.appcountries.data.model.AreaResponse
import retrofit2.Response
import retrofit2.http.GET

interface MyAtlanticoApi {

    @GET("v7/SignUp/GetAreaCodes")
    suspend fun getAreaCodes() : Response<List<AreaResponse>>

}