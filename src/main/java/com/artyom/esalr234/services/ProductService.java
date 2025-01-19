package com.artyom.esalr234.services;

import com.artyom.esalr234.message.Producer;
import com.artyom.esalr234.model.ChangeLog;
import com.artyom.esalr234.model.Product;
import com.artyom.esalr234.repositories.ChangeLogRepository;
import com.artyom.esalr234.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ChangeLogRepository changeLogRepository;
    private final Producer producer;

    @Autowired
    public ProductService(ProductRepository productRepository, ChangeLogRepository changeLogRepository, Producer producer) {
        this.productRepository = productRepository;
        this.changeLogRepository = changeLogRepository;
        this.producer = producer;
    }

    public Product saveProduct(Product product){
        ChangeLog changeLog = new ChangeLog();
        changeLog.setChangeType("create");
        changeLog.setEntityClass("product");
        changeLog.setChangeTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        changeLog.setChangeDetails("Create product: " + product.toString());
        changeLogRepository.save(changeLog);
        producer.produce(changeLog);

        return productRepository.save(product);
    }

    public Product updateProduct(Product product){
        ChangeLog changeLog = new ChangeLog();
        changeLog.setChangeType("update");
        changeLog.setEntityClass("product");
        changeLog.setChangeTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        changeLog.setChangeDetails("Update product: " + product.toString());
        changeLogRepository.save(changeLog);
        producer.produce(changeLog);
        return productRepository.save(product);
    }
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id){
        return productRepository.findProductById(id);
    }

    public void deleteById(Long id){
        ChangeLog changeLog = new ChangeLog();
        changeLog.setChangeType("delete");
        changeLog.setEntityClass("product");
        changeLog.setChangeTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        changeLog.setChangeDetails("Delete product: " + findById(id).toString());
        changeLogRepository.save(changeLog);
        producer.produce(changeLog);
        productRepository.deleteById(id);
    }

}
