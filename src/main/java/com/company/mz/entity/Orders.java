package com.company.mz.entity;

import lombok.Data;

import java.sql.Date;
import java.util.Objects;

@Data
public class Orders {
    private int id;
    private Date date;
    private int guestId;

}
