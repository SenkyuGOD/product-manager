package com.example.product_manager_v2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
}
