package com.lbaburic.learning.cardcreator.controller;


import com.lbaburic.learning.cardcreator.dto.request.CardRequestDTO;
import com.lbaburic.learning.cardcreator.dto.response.CardResponseDTO;
import com.lbaburic.learning.cardcreator.dto.response.ErrorResponseDTO;
import com.lbaburic.learning.cardcreator.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "REST API for Credit Card Generator",
        description = "REST APIs to CREATE, FETCH AND DELETE card details"
)
@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardRestController {

    private final CardService cardService;


    @Operation(
            summary = "Fetch Card REST API",
            description = "REST API to fetch card details by OIB"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = CardResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{oib}")
    public ResponseEntity<CardResponseDTO> getCard(
            @PathVariable("oib")
            @Pattern(regexp="(^$|[0-9]{11})",message = "OIB must contain 11 digits")
            String oib
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardService.getCardByOib(oib));
    }


    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create new card"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity createCard(
            @Validated @RequestBody CardRequestDTO card
    ) {
        cardService.createNewCard(card);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @Operation(
            summary = "Delete Card REST API",
            description = "REST API to delete a card by OIB"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    })
    @DeleteMapping("/{oib}")
    public ResponseEntity deleteCard(
            @PathVariable("oib")
            @Pattern(regexp="(^$|[0-9]{11})",message = "OIB must contain 11 digits")
            String oib
    ) {
        cardService.deleteCardByOib(oib);
        return new ResponseEntity(HttpStatus.OK);
    }


}
