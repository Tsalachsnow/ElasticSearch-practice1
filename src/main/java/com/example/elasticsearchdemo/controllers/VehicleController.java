package com.example.elasticsearchdemo.controllers;

import com.example.elasticsearchdemo.document.Vehicle;
import com.example.elasticsearchdemo.search.SearchRequestDTO;
import com.example.elasticsearchdemo.services.VehicleService;
import com.example.elasticsearchdemo.services.helper.VehicleDummyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;
    private final VehicleDummyDataService dummyDataService;

    @Autowired
    public VehicleController(VehicleService vehicleService, VehicleDummyDataService dummyDataService) {
        this.vehicleService = vehicleService;
        this.dummyDataService = dummyDataService;
    }


    @PostMapping
    public void index(@RequestBody final Vehicle vehicle) {
        vehicleService.index(vehicle);
    }

    @PostMapping("/insertdummydata")
    public void insertDummyData() {
        dummyDataService.insertDummyData();
    }

    @GetMapping("/{id}")
    public Vehicle getById(@PathVariable final String id) {
        return vehicleService.getById(id);
    }

    @PostMapping("/search")
    public List<Vehicle> search(@RequestBody final SearchRequestDTO dto) {
        return vehicleService.search(dto);
    }

    @GetMapping("/search/{date}")
    public List<Vehicle> getAllVehiclesCreatedSince(
            @PathVariable
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            final Date date) {
        return vehicleService.getAllVehiclesCreatedSince(date);
    }

    @PostMapping("/searchcreatedsince/{date}")
    public List<Vehicle> searchCreatedSince(
            @RequestBody final SearchRequestDTO dto,
            @PathVariable
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            final Date date) {
        return vehicleService.searchCreatedSince(dto, date);
    }
}
