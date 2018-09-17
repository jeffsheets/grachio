package grachio

import io.micronaut.function.client.FunctionClient
import io.reactivex.Single

import javax.inject.Named

@FunctionClient
interface GrachioClient {

    @Named("grachio")
    Single<Map<String, String>> grachio()
}
