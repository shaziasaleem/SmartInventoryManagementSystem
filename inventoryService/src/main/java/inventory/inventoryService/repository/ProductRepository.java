package inventory.inventoryService.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import inventory.inventoryService.model.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    Optional<ProductEntity> findBySku(String sku);

    boolean existsBySku(String sku);

    Optional<ProductEntity> findById(UUID id);

    List<ProductEntity> findAll();

    ProductEntity save(ProductEntity prod);

    void deleteById(UUID id);
}
