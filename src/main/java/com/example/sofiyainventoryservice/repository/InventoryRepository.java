package com.example.sofiyainventoryservice.repository;

import com.example.sofiyainventoryservice.entity.InventoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<InventoryEntity, UUID> {
    List<InventoryEntity> searchInventoryEntitiesByProductIdContainingIgnoreCase(UUID productId, Pageable pageable);
}
