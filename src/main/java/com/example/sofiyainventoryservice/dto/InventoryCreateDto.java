package com.example.sofiyainventoryservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InventoryCreateDto {
    @NotNull(message = "product id not entered")
    private UUID productId;
    @NotNull(message = "Product count not entered")
    private Integer productCount;
    
}
