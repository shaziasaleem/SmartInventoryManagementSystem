package inventory.inventoryService.service;

import java.util.UUID;

import inventory.inventoryService.dtos.ProductRequestDTO;
import inventory.inventoryService.dtos.ProductResponseDTO;

public interface InventoryService {

    ProductResponseDTO createProduct(ProductRequestDTO request);

    ProductResponseDTO updateProduct(UUID id, ProductRequestDTO request);

    void deleteProduct(UUID id);
}
