package com.lbaburic.learning.cardproducer.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CardRequestDTO {

    private String firstName;
    private String lastName;
    private String status;
    private String oib;

}
