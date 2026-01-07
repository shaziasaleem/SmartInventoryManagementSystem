package inventory.inventoryService.mapper;

import inventory.inventoryService.dtos.ProductRequestDTO;
import inventory.inventoryService.dtos.ProductResponseDTO;
import inventory.inventoryService.model.ProductEntity;

public class ProductMapper {

    public static ProductEntity toEntity(ProductRequestDTO dto) {
        if (dto == null) return null;
        ProductEntity e = new ProductEntity();
        e.setSku(dto.getSku());
        e.setName(dto.getName());
        e.setDescription(dto.getDescription());
        e.setQuantity(dto.getQuantity() == null ? 0 : dto.getQuantity());
        e.setPrice(dto.getPrice());
        e.setActive(dto.getActive() == null ? Boolean.TRUE : dto.getActive());
        return e;
    }

    public static ProductResponseDTO toDto(ProductEntity e) {
        if (e == null) return null;
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(e.getId());
        dto.setSku(e.getSku());
        dto.setName(e.getName());
        dto.setDescription(e.getDescription());
        dto.setQuantity(e.getQuantity());
        dto.setPrice(e.getPrice());
        dto.setActive(e.getActive());
        dto.setCreatedAt(e.getCreatedAt());
        dto.setUpdatedAt(e.getUpdatedAt());
        dto.setVersion(e.getVersion());
        return dto;
    }
}
