package com.bai.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class User implements Serializable {

    private Integer id;

    private String name;

    private BigDecimal money;
}
