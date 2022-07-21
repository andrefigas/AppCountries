package andrefigas.com.github.appcountries.data.api

import andrefigas.com.github.appcountries.data.model.WikipediaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WikipediaApi {

    @GET("api/rest_v1/page/summary/{country}")
    suspend fun getSummary(@Path("country") country : String) : Response<WikipediaResponse>

}