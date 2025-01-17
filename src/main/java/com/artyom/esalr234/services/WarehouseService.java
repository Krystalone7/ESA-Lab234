package com.artyom.esalr234.services;

import com.artyom.esalr234.model.Warehouse;
import com.artyom.esalr234.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> getAll(){
        return warehouseRepository.findAll();
    }
}
