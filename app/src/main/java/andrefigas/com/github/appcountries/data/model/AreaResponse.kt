package andrefigas.com.github.appcountries.data.model

data class AreaResponse(val id : String, val iso3 : String, val areaCode : String, val areaName : String){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AreaResponse

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
