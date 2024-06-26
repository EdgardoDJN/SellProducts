package com.example.SellProducts.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detail_shippings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DetailShipping {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String transporter;
    private String trackingNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

}
