package com.example.servletBankAccountProject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Payment {

    private Long id;
    private Long senderId;
    private Long receiverId;
    private BigDecimal sum;
    private Date date;
    private Long ownerId;
    private State state;
    public enum State {
        PREPARED,
        SENT
    }

    public void setState(String state) {
        switch (state) {
            case "PREPARED":
                this.state = State.PREPARED;
                break;
            case "SENT":
            default:
                this.state = State.SENT;
        }

    }
}
