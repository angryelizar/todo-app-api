package kg.angryelizar.todoapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info information = new Info()
                .title("To Do API")
                .version("1.0")
                .description("To Do API - test task ")
                .contact(new Contact().email("conovalov.elizar@gmail.com").name("Elizar"));
        return new OpenAPI().info(information);
    }
}
