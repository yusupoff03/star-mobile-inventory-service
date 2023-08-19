package com.example.sofiyainventoryservice.controller;


import com.example.sofiyainventoryservice.dto.InventoryCreateDto;
import com.example.sofiyainventoryservice.entity.InventoryEntity;
import com.example.sofiyainventoryservice.exception.RequestValidationException;
import com.example.sofiyainventoryservice.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class InventoryControllerTest {

    //Test Add

    @Mock
    private InventoryService inventoryService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private InventoryController inventoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddValidInventory() {
        // Mock your inventoryCreateDto
        InventoryCreateDto inventoryCreateDto = new InventoryCreateDto();
        // Add necessary fields to inventoryCreateDto

        // Mock the inventoryService.add() method
        InventoryEntity mockedInventory = new InventoryEntity();
        when(inventoryService.add(inventoryCreateDto)).thenReturn(mockedInventory);

        // Call the add method in the controller
        ResponseEntity<InventoryEntity> responseEntity = inventoryController.add(inventoryCreateDto, bindingResult);

        // Verify that the inventoryService.add() method was called once
        verify(inventoryService, times(1)).add(inventoryCreateDto);

        // Assert the response status and the returned entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockedInventory, responseEntity.getBody());
    }

    @Test
    void testAddInvalidInventory() {
        // Mock your inventoryCreateDto
        InventoryCreateDto inventoryCreateDto = new InventoryCreateDto();
        // Add necessary fields to inventoryCreateDto

        // Set up the binding result to have errors
        when(bindingResult.hasErrors()).thenReturn(true);
        List<ObjectError> errors = new ArrayList<>();
        // Add some validation errors to the errors list
        errors.add(new ObjectError("field", "Some error message"));
        when(bindingResult.getAllErrors()).thenReturn(errors);

        // Call the add method in the controller and expect a RequestValidationException to be thrown
        try {
            inventoryController.add(inventoryCreateDto, bindingResult);
        } catch (RequestValidationException ex) {
            // Verify that the exception was thrown and contains the expected errors
            assertEquals(errors, ex.getMessage());
        }

        // Verify that the inventoryService.add() method was not called
        verify(inventoryService, never()).add(inventoryCreateDto);
    }

    @Test
    void testGetAll() {
        // Mock the size and page parameters
        int size = 10;
        int page = 1;

        // Mock the inventoryService.getAll() method
        List<InventoryEntity> mockedInventoryList = new ArrayList<>();
        // Add some inventory items to the mocked list

        when(inventoryService.getAll(size, page)).thenReturn(mockedInventoryList);

        // Call the getAll method in the controller
        ResponseEntity<List<InventoryEntity>> responseEntity = inventoryController.getAll(size, page);

        // Verify that the inventoryService.getAll() method was called once with the correct parameters
        verify(inventoryService, times(1)).getAll(size, page);

        // Assert the response status and the returned list of entities
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockedInventoryList, responseEntity.getBody());
    }

    @Test
    void testUpdateValidInventory() {
        // Mock your inventoryCreateDto
        InventoryCreateDto inventoryCreateDto = new InventoryCreateDto();
        // Add necessary fields to inventoryCreateDto

        // Mock the productId and inventoryId
        UUID productId = UUID.randomUUID();
        UUID inventoryId = UUID.randomUUID();

        // Mock the inventoryService.update() method
        InventoryEntity mockedInventory = new InventoryEntity();
        when(inventoryService.update(inventoryCreateDto, inventoryId, productId)).thenReturn(mockedInventory);

        // Call the update method in the controller
        ResponseEntity<InventoryEntity> responseEntity = inventoryController.update(productId, inventoryId, inventoryCreateDto, bindingResult);

        // Verify that the inventoryService.update() method was called once with the correct parameters
        verify(inventoryService, times(1)).update(inventoryCreateDto, inventoryId, productId);

        // Assert the response status and the returned entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockedInventory, responseEntity.getBody());
    }

    @Test
    void testUpdateInvalidInventory() {
        // Mock your inventoryCreateDto
        InventoryCreateDto inventoryCreateDto = new InventoryCreateDto();
        // Add necessary fields to inventoryCreateDto

        // Mock the productId and inventoryId
        UUID productId = UUID.randomUUID();
        UUID inventoryId = UUID.randomUUID();

        // Set up the binding result to have errors
        when(bindingResult.hasErrors()).thenReturn(true);
        List<ObjectError> errors = new ArrayList<>();
        // Add some validation errors to the errors list
        errors.add(new ObjectError("field", "Some error message"));
        when(bindingResult.getAllErrors()).thenReturn(errors);

        // Call the update method in the controller and expect a RequestValidationException to be thrown
        try {
            inventoryController.update(productId, inventoryId, inventoryCreateDto, bindingResult);
        } catch (RequestValidationException ex) {
            // Verify that the exception was thrown and contains the expected errors
            assertEquals(errors, ex.getMessage());
        }

        // Verify that the inventoryService.update() method was not called
        verify(inventoryService, never()).update(inventoryCreateDto, inventoryId, productId);
    }

    @Test
    void testDelete() {
        // Mock the inventoryId
        UUID inventoryId = UUID.randomUUID();

        // Call the delete method in the controller
        ResponseEntity responseEntity = inventoryController.delete(inventoryId);

        // Verify that the inventoryService.deleteByInventoryId() method was called once with the correct parameter
        verify(inventoryService, times(1)).deleteByInventoryId(inventoryId);

        // Assert the response status
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}




