package grachio

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.Specification

class GrachioFunctionSpec extends Specification {

    void "functional test grachio against real Google Sheets api endpoint"() {
        given:
        EmbeddedServer server = ApplicationContext.run(EmbeddedServer)
        GrachioClient client = server.getApplicationContext().getBean(GrachioClient)

        when:
        def result = client.grachio().blockingGet()

        then:
        result.rachioData[0].date == 1466951160000
        result.rachioData[0].waterMinutes == '174'
        result.rachioData[0].prettyDate == 'Sun Jun 26 09:26:00 CDT 2016'

        cleanup:
        if(server != null) server.stop()
    }
}
