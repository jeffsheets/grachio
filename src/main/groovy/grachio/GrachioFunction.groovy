package grachio

import grachio.service.SheetsService
import groovy.transform.Field
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.inject.Inject

@Field static final Logger log = LoggerFactory.getLogger('grachio.GrachioFunction')

@Field @Inject SheetsService sheetsService

Map<String, Object> grachio() {
  log.debug 'starting grachio function'

  def result = [
    rachioData: sheetsService.getRachioData().takeRight(10)
  ]

  log.debug('result is {}', result)
  result
}