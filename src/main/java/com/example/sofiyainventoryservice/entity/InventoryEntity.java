package com.example.sofiyainventoryservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.UUID;

@Entity(name = "inventory")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InventoryEntity extends BaseEntity{

    private UUID productId;

    private Integer productCount;

}
