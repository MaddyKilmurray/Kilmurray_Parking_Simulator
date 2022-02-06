package com.kilmurray.trigg_java_developer_challenge.controller;

import com.kilmurray.trigg_java_developer_challenge.dao.Car;
import com.kilmurray.trigg_java_developer_challenge.dto.EntryFormDto;
import com.kilmurray.trigg_java_developer_challenge.dto.ExitFormDto;
import com.kilmurray.trigg_java_developer_challenge.service.ParkingService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
@CrossOrigin(origins = "*")
public class ParkingController {

    final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> getAllRecords() {
        return parkingService.getAllRecords();
    }

    @PostMapping("/entry")
    @ResponseStatus(HttpStatus.OK)
    public void enterParkingLot(@RequestBody EntryFormDto entryFormDto) {
        parkingService.enterParkingLot(entryFormDto);
    }

    @PostMapping("/exit")
    @ResponseStatus(HttpStatus.OK)
    public boolean verifyParkingCharge(@RequestBody ExitFormDto exitFormDto) {
        return parkingService.verifyParkingCharge(exitFormDto);
    }
}
