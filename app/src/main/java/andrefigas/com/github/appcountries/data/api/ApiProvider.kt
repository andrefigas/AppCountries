package andrefigas.com.github.appcountries.data.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiProvider {

    private val builder = Retrofit.Builder()
        .client(UnsafeClient.getUnsafeOkHttpClient())
        .addConverterFactory(MoshiConverterFactory.create())

    fun <API> createService(apiClass: Class<API>): API {
        return builder.baseUrl(getEndPoint(apiClass)).build().create(apiClass)
    }

    private fun <API> getEndPoint(apiClass: Class<API>): String {
        return when (apiClass) {
            MyAtlanticoApi::class.java -> "https://myatlanticoapi-quality.appdev.p.azurewebsites.net/"
            WikipediaApi::class.java -> "https://en.wikipedia.org/"
            else -> throw IllegalArgumentException("${apiClass.simpleName} is not mapped")
        }
    }

}