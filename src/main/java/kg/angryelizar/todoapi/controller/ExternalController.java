package kg.angryelizar.todoapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.angryelizar.todoapi.service.ExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("/external")
@RequiredArgsConstructor
@Tag(name = "External contoller", description = "Controller for receiving a response from an external API https://api.restful-api.dev/objects")
public class ExternalController {
    private final ExternalService externalService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Make a request",
            description = "Returns request, make logging while operation"
    )
    public Mono<String> external() throws IOException {
        return externalService.test();
    }
}
