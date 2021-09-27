package com.example.servletBankAccountProject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class User{

    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private List<CreditCard> cards;
    private List<Payment> payments;

    private List<CardRequest> unblockRequests;

    private Role role;

    private State state = State.ACTIVE;
    public enum State {
        ACTIVE,
        BLOCKED
    }

    public void setRole(String role) {
        switch (role) {
            case "ROLE_ADMIN":
                this.role = Role.ROLE_ADMIN;
                break;
            case "ROLE_USER":
            default:
                this.role = Role.ROLE_USER;
        }
    }

    public void setState(String state) {
        switch (state) {
            case "ACTIVE":
                this.state = State.ACTIVE;
                break;
            case "BLOCKED":
            default:
                this.state = State.BLOCKED;
        }

    }
}
