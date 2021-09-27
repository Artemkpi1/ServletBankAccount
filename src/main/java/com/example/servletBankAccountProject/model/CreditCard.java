package com.example.servletBankAccountProject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreditCard {
    private Long id;
    private String cardName;
    private String cardNumber;

    private Status status;
    private BigDecimal balance = new BigDecimal(1000);
    private Long ownerId;

    private Request request;

    public enum Request {
        REQUESTED,
        NOT_REQUESTED
    }


    public enum Status {
        ACTIVE,
        BLOCKED
    }

    public void setRequest(String request) {
        switch (request) {
            case "NOT_REQUESTED":
                this.request = Request.NOT_REQUESTED;
                break;
            case "REQUESTED":
            default:
                this.request = Request.REQUESTED;
        }
    }

    public void setStatus(String status) {
        switch (status) {
            case "ACTIVE":
                this.status = Status.ACTIVE;
                break;
            case "BLOCKED":
            default:
                this.status = Status.BLOCKED;
        }

    }
}
