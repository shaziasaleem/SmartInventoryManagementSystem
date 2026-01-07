package inventory.inventoryService.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import inventory.inventoryService.dtos.ProductRequestDTO;
import inventory.inventoryService.dtos.ProductResponseDTO;
import inventory.inventoryService.exception.DuplicateSkuException;
import inventory.inventoryService.exception.ProductNotFoundException;
import inventory.inventoryService.mapper.ProductMapper;
import inventory.inventoryService.model.ProductEntity;
import inventory.inventoryService.repository.ProductRepository;
import inventory.inventoryService.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final ProductRepository productRepository;

    public InventoryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO request) {
        // enforce unique SKU
        if (productRepository.existsBySku(request.getSku())) {
            throw new DuplicateSkuException("SKU already exists: " + request.getSku());
        }

        ProductEntity entity = ProductMapper.toEntity(request);
        ProductEntity saved = productRepository.save(entity);
        return ProductMapper.toDto(saved);
    }

    @Override
    @Transactional
    public ProductResponseDTO updateProduct(UUID id, ProductRequestDTO request) {
        ProductEntity existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        // if SKU changed, ensure uniqueness
        if (!existing.getSku().equals(request.getSku()) && productRepository.existsBySku(request.getSku())) {
            throw new DuplicateSkuException("SKU already exists: " + request.getSku());
        }

        existing.setSku(request.getSku());
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setQuantity(request.getQuantity());
        existing.setPrice(request.getPrice());
        existing.setActive(request.getActive() == null ? Boolean.TRUE : request.getActive());

        ProductEntity saved = productRepository.save(existing);
        return ProductMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteProduct(UUID id) {
        ProductEntity existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        productRepository.deleteById(existing.getId());
    }
}
