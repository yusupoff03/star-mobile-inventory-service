package com.example.sofiyainventoryservice.service;


import com.example.sofiyainventoryservice.dto.InventoryCreateDto;
import com.example.sofiyainventoryservice.entity.InventoryEntity;

import java.util.List;
import java.util.UUID;

public interface InventoryService {
    InventoryEntity add(InventoryCreateDto inventory);
    List<InventoryEntity> getAll(int size, int page);
//    List<InventoryEntity> search(int page,int size,UUID productId);
    void deleteByInventoryId(UUID inventoryId);
    void deleteByProductId(UUID productId);
    InventoryEntity update(InventoryCreateDto update,UUID id,UUID productId);
}
