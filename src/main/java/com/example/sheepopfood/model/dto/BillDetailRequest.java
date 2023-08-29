package com.example.sheepopfood.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillDetailRequest {
    private String product;
    private String quantity;
    private String total;
}
