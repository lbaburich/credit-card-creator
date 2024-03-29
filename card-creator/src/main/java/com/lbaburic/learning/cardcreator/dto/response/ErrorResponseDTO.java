package com.lbaburic.learning.cardcreator.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorResponseDTO {

    @Schema(
            example = "/v1/api/cards"
    )
    private String apiPath;


    @Schema(
            example = "400"
    )
    private HttpStatus errorCode;

    @Schema(
            example = "Error has occurred"
    )
    private  String errorMessage;

    private LocalDateTime time;
}
