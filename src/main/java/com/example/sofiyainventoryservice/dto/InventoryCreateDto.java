package com.example.sofiyainventoryservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InventoryCreateDto {
    @NotBlank(message = "product id not entered")
    private UUID productId;

    @NotBlank(message = "Product count not entered")
    private Integer productCount;
}
