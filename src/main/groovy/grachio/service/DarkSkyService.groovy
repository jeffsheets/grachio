package grachio.service

import groovy.util.logging.Slf4j
import io.micronaut.context.annotation.Value
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.Client
import io.micronaut.http.client.RxHttpClient

import javax.inject.Inject

@Slf4j
class DarkSkyService {
    //location of my Rachio weather station (Millard Airport)
    static final String myWeatherLocation = '41.1951011,-96.1157208'

    //Use env var like DARKSKY_APIKEY=xyz123
    @Value('${darksky.apikey}')
    String key

    @Client('https://api.darksky.net/forecast/')
    @Inject
    RxHttpClient httpClient

    Map getWeather(Long time) {
        log.debug "myWeatherLocation is ${myWeatherLocation}"
        log.debug "key last 4 is ${key[-4..-1]}"

        Map data = httpClient.retrieve(HttpRequest.GET("${key}/${myWeatherLocation},${time/1000}?exclude=hourly"), Map).blockingFirst()

        log.debug('Dark Sky result is {}', data)
        data
    }
}
