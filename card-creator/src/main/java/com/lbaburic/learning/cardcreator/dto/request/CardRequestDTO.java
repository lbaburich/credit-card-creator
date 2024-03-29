package com.lbaburic.learning.cardcreator.dto.request;


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
@Schema(name = "CardRequest",
        description = "Schema to hold Card information"
)
public class CardRequestDTO {

    @NotBlank(message = "Fist Name should not be blank")
    @Schema(
            description = "Fist Name of Customer", example = "Ivan"
    )
    private String firstName;

    @NotBlank(message = "Last Name should not be blank")
    @Schema(
            description = "Last Name of Customer", example = "Horvat"
    )
    private String lastName;

    @NotBlank(message = "Status should not be blank")
    @Schema(
            description = "Card Status", example = "DONE"
    )
    private String status;

    @NotBlank(message = "OIB should not be blank")
    @Pattern(regexp="(^$|[0-9]{11})",message = "OIB must be 11 digits")
    @Schema(
            description = "OIB of the customer", example = "50640346679"
    )
    private String oib;
}
