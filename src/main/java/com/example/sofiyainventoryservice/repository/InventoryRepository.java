package com.example.sofiyainventoryservice.repository;

import com.example.sofiyainventoryservice.entity.InventoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, UUID> {
    //    List<InventoryEntity> searchInventoryEntitiesByProductIdContainingIgnoreCase(UUID productId, Pageable pageable);
    void deleteInventoryEntityByProductIdContaining(UUID productId);

}
