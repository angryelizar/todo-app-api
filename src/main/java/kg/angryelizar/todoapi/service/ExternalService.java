package kg.angryelizar.todoapi.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public interface ExternalService {
    Mono<String> test() throws IOException;
}
