package com.crocobet.customer.notification.system.model;

import lombok.Data;

@Data
public class CustomerDTO {

    private Long id;

    private String name;
    private String email;
    private String phone;
}
