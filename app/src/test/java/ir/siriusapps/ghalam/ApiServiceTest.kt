package ir.siriusapps.ghalam

import com.google.gson.GsonBuilder
import ir.siriusapps.ghalam.data.Note
import ir.siriusapps.ghalam.data.source.remote.ApiService
import org.junit.Test

class ApiServiceTest {

    private val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
    private val apiService = ApiService(gson)

    @Test
    fun getNotesApiTest() {
        val call = apiService.getApis().getNotes()
        val response = call.execute()
        assert(response.isSuccessful)
    }
}
