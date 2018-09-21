package grachio

import grachio.service.SheetsService
import groovy.transform.Field

import javax.inject.Inject

@Field @Inject SheetsService sheetsService

Map<String, Object> grachio() {
  [
    rachioData: sheetsService.rachioData
  ]
}