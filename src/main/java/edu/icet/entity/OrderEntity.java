package edu.icet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   Integer  orderID;
    private  String customerName;
    private Integer packageID;
    private  String email;
    private Double total;
    private Integer qty;
    private LocalDate checkIn;
    private  LocalDate checkOut;
    private String category;
}
