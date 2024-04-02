package com.example.SellProducts.dto.order;

import com.example.SellProducts.dto.orderItem.UpdateOrderItem2;
import com.example.SellProducts.entities.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
public class OrderDto2 {
    private Long id;
    private LocalDateTime dateOrder;
    private OrderStatus status;
    private Long customerId;
    private List<UpdateOrderItem2> orderItems;
}
