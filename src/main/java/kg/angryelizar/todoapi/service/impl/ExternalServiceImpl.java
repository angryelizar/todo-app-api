package kg.angryelizar.todoapi.service.impl;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kg.angryelizar.todoapi.model.Data;
import kg.angryelizar.todoapi.model.Item;
import kg.angryelizar.todoapi.service.ExternalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalServiceImpl implements ExternalService {
    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<String> test() throws IOException {
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        WebClient webClient = webClientBuilder.build();
        String url = "https://api.restful-api.dev/objects";
        log.info("Make a request to url {}", url);
        Mono<String> result = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
        log.info("Got response");
        List<Item> items = readJson(result.block());
        log.error("Items: {}", items);
        return result;
    }


    private List<Item> readJson(String json) throws IOException {
        return objectMapper.readValue(json,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Item.class));
    }
}
