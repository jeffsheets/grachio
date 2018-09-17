package grachio

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.Specification

class GrachioFunctionSpec extends Specification {

    void "test grachio function"() {
        given:
        EmbeddedServer server = ApplicationContext.run(EmbeddedServer)
        GrachioClient client = server.getApplicationContext().getBean(GrachioClient)

        expect:
        client.grachio().blockingGet() == [grachio: 'jeff sheets']

        cleanup:
        if(server != null) server.stop()
    }
}
