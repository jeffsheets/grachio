package grachio.service

import groovy.util.logging.Slf4j
import io.micronaut.context.annotation.Value
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.Client
import io.micronaut.http.client.RxHttpClient

import javax.inject.Inject
import java.text.DateFormat
import java.text.SimpleDateFormat

@Slf4j
class SheetsService {
    static final DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy 'at' KK:mma")

    //Just the public id for the spreadsheet with my Rachio watering data
    static final String myRachioSpreadsheetId = '1RL6dWpxJUFxUirAHdAKApCGqiad3JLAr3CA4Q1IFBOk'

    //Use env var like GOOGLESHEETS_APIKEY=xyz123
    @Value('${googlesheets.apikey}')
    String key

    @Client('https://sheets.googleapis.com/v4/spreadsheets/')
    @Inject
    RxHttpClient httpClient

    List getRachioData() {
        log.debug "myRachioSpreadsheetId is ${myRachioSpreadsheetId}"
        log.debug "key last 4 is ${key[-4..-1]}"

        Map data = httpClient.retrieve(HttpRequest.GET("${myRachioSpreadsheetId}/values/B1:C1000?key=${key}"), Map).blockingFirst()

        //[[June 26, 2016 at 09:26AM, 174], [July 9, 2016 at 07:48AM, 74]]
        data.values.collect {
            Date date = dateFormat.parse(it[0])
            [date: date, waterMinutes: it[1], prettyDate: date.toString()]
        }
    }
}
