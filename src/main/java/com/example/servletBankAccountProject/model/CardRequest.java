package com.example.servletBankAccountProject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CardRequest {

    private Long id;
    private Long ownerId;
    private Long creditCardId;
    private String creditCardState;

}
