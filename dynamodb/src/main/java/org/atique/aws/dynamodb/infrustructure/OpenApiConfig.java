package org.atique.aws.dynamodb.infrustructure;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

/**
 * @author atiQue
 * @since 16'Jun 2023 at 10:10 PM
 */

@OpenAPIDefinition(
        info = @Info(
                title = "DynamoDB Service API-DOC",
                description = "DynamoDB CRUD APIs",
                version = "1.0.0",
                contact = @Contact(
                        name = "Atiqur Rahman",
                        email = "atiqur.rahman6069@gmail.com"
                )
        ),
        tags = {
                @Tag(name = "DynamoDB CRUD", description = "DynamoDB CRUD"),
        }
)
@Configuration
public class OpenApiConfig {

}
