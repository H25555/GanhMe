package com.example.sheepopfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @OneToMany(mappedBy = "bill", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<BillDetail> billDetail;
    private String description;
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String shipAddress;

}
