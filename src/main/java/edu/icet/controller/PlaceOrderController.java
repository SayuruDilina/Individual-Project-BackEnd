package edu.icet.controller;

import edu.icet.dto.Order;
import edu.icet.service.PlaceOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order")
@RequiredArgsConstructor
public class PlaceOrderController {

    private  final PlaceOrderService service;

    //Total changed as double
    @PostMapping("/place-order")
    public void placeOrder(@RequestBody Order order){
        service.placeOrder(order);
    }
    @GetMapping("/get-order-by-customer")
    public List<Order> getOrdersByCustomer(@RequestParam("email") String email){
        return service.getOrderByCustomer(email);

    }
    @GetMapping("get-all-orders")
    public List<Order> getAll(){
        return service.getAll();
    }

    @GetMapping("/get-detail-report")
    public ResponseEntity<InputStreamResource> getOrderDetailReport(){
         ByteArrayInputStream stream=service.pdfReportGenerate();
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-Deposition","inline;filename=OrderDetails.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(stream));
    }
}
