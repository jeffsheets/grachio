package grachio

import grachio.service.DarkSkyService
import grachio.service.SheetsService
import groovy.transform.Field
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.inject.Inject

@Field static final Logger log = LoggerFactory.getLogger('grachio.GrachioFunction')

@Field @Inject SheetsService sheetsService
@Field @Inject DarkSkyService darkSkyService

Map<String, Object> grachio() {
  log.debug 'starting grachio function'

  List rachioData = sheetsService.getRachioData().takeRight(5)

  def result = [
    rachioData: rachioData.collect {
      [
        date: it.date,
        waterMinutes: it.waterMinutes,
        weather: darkSkyService.getWeather(it.date)?.daily?.data[0]
      ]
    }
  ]

  log.debug('result is {}', result)
  result
}