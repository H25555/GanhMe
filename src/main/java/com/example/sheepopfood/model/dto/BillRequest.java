package com.example.sheepopfood.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillRequest {
    private String user;
    private List<BillDetailRequest> billDetail;
    private String description;
    private String total;
    private String shipAddress;
}
