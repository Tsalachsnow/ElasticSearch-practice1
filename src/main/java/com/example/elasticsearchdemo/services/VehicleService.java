package com.example.elasticsearchdemo.services;

import com.example.elasticsearchdemo.document.Vehicle;
import com.example.elasticsearchdemo.search.SearchRequestDTO;
import java.util.Date;
import java.util.List;
import org.elasticsearch.action.search.SearchRequest;
import org.springframework.stereotype.Service;

@Service
public interface VehicleService {
    List<Vehicle> search(final SearchRequestDTO dto);
    List<Vehicle> getAllVehiclesCreatedSince(final Date date);
    List<Vehicle> searchCreatedSince(final SearchRequestDTO dto, final Date date);
    List<Vehicle> searchInternal(final SearchRequest request);
    Boolean index(final Vehicle vehicle);
    Vehicle getById(final String vehicleId);
}
