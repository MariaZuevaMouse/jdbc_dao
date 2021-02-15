package com.company.mz.entity;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class Guest {
    private int id;
    private String name;

//    public Guest() {
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Guest guest = (Guest) o;
//        return id == guest.id &&
//                Objects.equals(name, guest.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name);
//    }
//
//    @Override
//    public String toString() {
//        return "Guest{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
}
