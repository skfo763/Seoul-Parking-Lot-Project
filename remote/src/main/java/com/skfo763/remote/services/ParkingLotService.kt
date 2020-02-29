package com.skfo763.remote.services

import com.skfo763.remote.RetroBuilder
import com.skfo763.remote.entites.ParkInfoEntity
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

object ParkingLotService {
    fun getParkingLotService(isDebug: Boolean): ParkingLotApi {
        return RetroBuilder.i.getRetrofit(isDebug)
            .create(ParkingLotApi::class.java)
    }
}

interface ParkingLotApi {
    companion object {
        const val CONTENT_TYPE = "json"
        const val SERVICE_TYPE = "GetParkInfo"
        const val BASE_ENDPOINT = "{serviceKey}/{type}/{service}/{startIndex}/{endIndex}"
    }

    /*
    KEY	            String(필수)     인증키	      OpenAPI 에서 발급된 인증키
    TYPE	        String(필수)	    요청파일타입	  xml : xml, xml파일 : xmlf, 엑셀파일 : xls, json파일 : json
    SERVICE	        String(필수)     서비스명	      GetParkInfo
    START_INDEX	    INTEGER(필수	    요청시작위치	  정수 입력 (페이징 시작번호 입니다 : 데이터 행 시작번호)
    END_INDEX	    INTEGER(필수)    요청종료위치	  정수 입력 (페이징 끝번호 입니다 : 데이터 행 끝번호)
    ADDR	        STRING(선택)	    주소	          주소 문자열
    PARKING_CODE	STRING(선택)	    주차장코드
    QUE_STATUS	    STRING(선택)	    주차현황        정보 제공여부
     */
    @GET("$BASE_ENDPOINT/{address}/{parkingCode}/{queueStatus}")
    fun getParkingLotData(
        @Path("serviceKey") serviceKey: String = ParkingLotApiConstant.API_KEY,
        @Path("type") type: String = CONTENT_TYPE,
        @Path("service") serviceType: String = SERVICE_TYPE,
        @Path("startIndex") startIndex: Int = 1,
        @Path("endIndex") endIndex: Int = 10,
        @Path("address", encoded = true) region: String = " ",
        @Path("parkingCode") parkingCode: String = " ",
        @Path("queueStatus") queueStatus: String = " "
    ): Flowable<ParkInfoEntity>
}