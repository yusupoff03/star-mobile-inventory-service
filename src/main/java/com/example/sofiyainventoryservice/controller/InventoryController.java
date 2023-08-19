package com.example.sofiyainventoryservice.controller;

import com.example.sofiyainventoryservice.dto.InventoryCreateDto;
import com.example.sofiyainventoryservice.entity.InventoryEntity;
import com.example.sofiyainventoryservice.exception.RequestValidationException;
import com.example.sofiyainventoryservice.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
     private final InventoryService inventoryService;

     @PostMapping("/add")
    public ResponseEntity<InventoryEntity> add(
           @Valid @RequestBody InventoryCreateDto inventoryCreateDto,
           BindingResult bindingResult
             ){
         if(bindingResult.hasErrors()){
             List<ObjectError> allErrors = bindingResult.getAllErrors();
             throw new RequestValidationException(allErrors);
         }
         return ResponseEntity.ok(inventoryService.add(inventoryCreateDto));
     }

    @GetMapping("/get-all")
    public ResponseEntity<List<InventoryEntity>> getAll(
            @RequestParam int size,
            @RequestParam int page

    ){
        return ResponseEntity.status(200).body(inventoryService.getAll(size, page));
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<InventoryEntity> update(
           @Valid @PathVariable UUID productId,
            @RequestParam UUID inventoryId,
            @RequestBody InventoryCreateDto inventoryCreateDto,
             BindingResult bindingResult
            ){
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
         return ResponseEntity.ok(inventoryService.update(inventoryCreateDto,inventoryId,productId));
    }
    @DeleteMapping("/{inventoryId}/delete")
    public ResponseEntity delete(
            @PathVariable UUID inventoryId
    ){
         inventoryService.deleteByInventoryId(inventoryId);
         return ResponseEntity.status(204).build();
    }
    @DeleteMapping("/{productId}/delete")
    public ResponseEntity deleteByProductId(
            @PathVariable UUID productId
    ){
        inventoryService.deleteByProductId(productId);
        return ResponseEntity.status(204).build();
    }


}
