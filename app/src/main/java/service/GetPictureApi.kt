package service

//import retrofit2.*

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url
import rx.Observable


interface GetPictureApi {
    @GET
    fun downloadPicture(@Url fileUrl: String): Observable<ResponseBody>;
}