package com.example.sofiyainventoryservice.service;


import com.example.sofiyainventoryservice.dto.InventoryCreateDto;
import com.example.sofiyainventoryservice.entity.InventoryEntity;
import com.example.sofiyainventoryservice.exception.DataNotFoundException;
import com.example.sofiyainventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InventoryServiceImplTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAdd() {
        // Mock the input DTO
        InventoryCreateDto inventoryCreateDto = new InventoryCreateDto();
        // Set necessary fields in the DTO

        // Mock the entity that will be saved in the repository
        InventoryEntity savedEntity = new InventoryEntity();
        // Set necessary fields in the entity

        when(modelMapper.map(inventoryCreateDto, InventoryEntity.class)).thenReturn(savedEntity);
        when(inventoryRepository.save(savedEntity)).thenReturn(savedEntity);

        InventoryEntity result = inventoryService.add(inventoryCreateDto);

        // Verify that modelMapper.map() and inventoryRepository.save() were called with the correct arguments
        verify(modelMapper, times(1)).map(inventoryCreateDto, InventoryEntity.class);
        verify(inventoryRepository, times(1)).save(savedEntity);

        // Assert the returned result
        assertEquals(savedEntity, result);
    }

    @Test
    public void testGetAll() {
        // Mock the size and page parameters
        int size = 10;
        int page = 0;

        // Mock the list of entities that will be returned from the repository
        List<InventoryEntity> entityList = new ArrayList<>();
        // Add some entities to the list

        when(inventoryRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(entityList));

        List<InventoryEntity> result = inventoryService.getAll(size, page);

        // Verify that inventoryRepository.findAll() was called with the correct Pageable argument
        verify(inventoryRepository, times(1)).findAll(any(Pageable.class));

        // Assert the returned result
        assertEquals(entityList, result);
    }

    @Test
    public void testDeleteById() {
        // Mock the UUID for the entity to be deleted
        UUID id = UUID.randomUUID();

        // Mock the entity that will be returned from the repository
        InventoryEntity entityToDelete = new InventoryEntity();
        // Set necessary fields in the entity

        when(inventoryRepository.findById(id)).thenReturn(Optional.of(entityToDelete));

        inventoryService.deleteByInventoryId(id);

        // Verify that inventoryRepository.findById() was called with the correct UUID
        verify(inventoryRepository, times(1)).findById(id);

        // Verify that inventoryRepository.deleteByInventoryId() was called with the correct UUID
        verify(inventoryRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteByIdEntityNotFound() {
        // Mock a UUID that won't be found in the repository
        UUID id = UUID.randomUUID();

        when(inventoryRepository.findById(id)).thenReturn(Optional.empty());

        // Call deleteByInventoryId with the non-existent UUID and expect a DataNotFoundException to be thrown
        assertThrows(DataNotFoundException.class, () -> inventoryService.deleteByInventoryId(id));

        // Verify that inventoryRepository.findById() was called with the correct UUID
        verify(inventoryRepository, times(1)).findById(id);

        // Verify that inventoryRepository.deleteByInventoryId() was not called since the entity was not found
        verify(inventoryRepository, never()).deleteById(any());
    }

    @Test
    public void testUpdateValid() {
        // Mock the UUIDs for the entity and product
        UUID id = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        // Mock the input DTO
        InventoryCreateDto updateDto = new InventoryCreateDto();
        // Set necessary fields in the DTO

        // Mock the entity that will be returned from the repository
        InventoryEntity entityToUpdate = new InventoryEntity();
        entityToUpdate.setProductId(productId);
        // Set necessary fields in the entity

        when(inventoryRepository.findById(id)).thenReturn(Optional.of(entityToUpdate));
        when(inventoryRepository.save(entityToUpdate)).thenReturn(entityToUpdate);

        InventoryEntity result = inventoryService.update(updateDto, id, productId);

        // Verify that inventoryRepository.findById() was called with the correct UUID
        verify(inventoryRepository, times(1)).findById(id);

        // Verify that modelMapper.map() was called with the correct arguments
        verify(modelMapper, times(1)).map(updateDto, entityToUpdate);

        // Verify that inventoryRepository.save() was called with the correct entity
        verify(inventoryRepository, times(1)).save(entityToUpdate);

        // Assert the returned result
        assertEquals(entityToUpdate, result);
    }

    @Test
    public void testUpdateEntityNotFound() {
        // Mock a UUID that won't be found in the repository
        UUID id = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        // Mock the input DTO
        InventoryCreateDto updateDto = new InventoryCreateDto();
        // Set necessary fields in the DTO

        when(inventoryRepository.findById(id)).thenReturn(Optional.empty());

        // Call update with the non-existent UUID and expect a DataNotFoundException to be thrown
        assertThrows(DataNotFoundException.class, () -> inventoryService.update(updateDto, id, productId));

        // Verify that inventoryRepository.findById() was called with the correct UUID
        verify(inventoryRepository, times(1)).findById(id);

        // Verify that modelMapper.map() and inventoryRepository.save() were not called since the entity was not found
        verify(modelMapper, never()).map(any(), any());
        verify(inventoryRepository, never()).save(any());
    }

    @Test
    public void testUpdateProductNotFound() {
        // Mock the UUIDs for the entity and product
        UUID id = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        // Mock the input DTO
        InventoryCreateDto updateDto = new InventoryCreateDto();
        // Set necessary fields in the DTO

        // Mock the entity that will be returned from the repository
        InventoryEntity entityToUpdate = new InventoryEntity();
        entityToUpdate.setProductId(UUID.randomUUID()); // Set a different product ID in the entity

        when(inventoryRepository.findById(id)).thenReturn(Optional.of(entityToUpdate));

        // Call update with a different product ID and expect a DataNotFoundException to be thrown
        assertThrows(DataNotFoundException.class, () -> inventoryService.update(updateDto, id, productId));

        // Verify that inventoryRepository.findById() was called with the correct UUID
        verify(inventoryRepository, times(1)).findById(id);

        // Verify that modelMapper
    }
}