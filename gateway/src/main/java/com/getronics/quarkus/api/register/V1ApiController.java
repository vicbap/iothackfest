package com.getronics.quarkus.api.register;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-05-17T15:27:27.014+02:00[Europe/Madrid]")
@Controller
@RequestMapping("${openapi.qioTRegistrationService.base-path:}")
public class V1ApiController implements V1Api {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public V1ApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
