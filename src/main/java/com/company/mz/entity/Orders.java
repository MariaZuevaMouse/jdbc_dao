package com.company.mz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private int id;
    private LocalDateTime date;
    private int guestId;

}
