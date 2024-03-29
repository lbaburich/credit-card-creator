package com.lbaburic.learning.cardcreator.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "CardResponse",
        description = "Schema for card response infomation"
)
public class CardResponseDTO {

    @Schema(
            description = "Fist Name of Customer", example = "Ivan"
    )
    private String firstName;

    @Schema(
            description = "Last Name of Customer", example = "Horvat"
    )
    private String lastName;

    @Schema(
            description = "Card Status", example = "DONE"
    )
    private String status;
}
