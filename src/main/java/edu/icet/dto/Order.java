package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

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
