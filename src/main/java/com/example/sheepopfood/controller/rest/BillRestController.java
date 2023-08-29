package com.example.sheepopfood.controller.rest;

import com.example.sheepopfood.model.Bill;
import com.example.sheepopfood.model.Status;
import com.example.sheepopfood.model.dto.BillRequest;
import com.example.sheepopfood.service.BillService;
import com.example.sheepopfood.ulti.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bills")
@AllArgsConstructor
public class BillRestController {
    private final BillService billService;
    @PostMapping
    public ResponseEntity<?> checkOut(@RequestBody BillRequest billRequest){
        Bill bill = AppUtils.mapper.map(billRequest,Bill.class);
        bill.setStatus(Status.NOTACCEPTED);
        billService.save(bill);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
