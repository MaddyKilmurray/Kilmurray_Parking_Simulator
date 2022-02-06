package com.kilmurray.trigg_java_developer_challenge.service;

import com.kilmurray.trigg_java_developer_challenge.dao.Car;
import com.kilmurray.trigg_java_developer_challenge.dto.EntryFormDto;
import com.kilmurray.trigg_java_developer_challenge.dto.ExitFormDto;
import com.kilmurray.trigg_java_developer_challenge.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {

    final CarRepository carRepository;

    public ParkingService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public void enterParkingLot(EntryFormDto entryFormDto) {
        if (entryFormDto.getLicencePlate() == "" || entryFormDto.getQrCode() == "" || entryFormDto.getEntranceDate() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"All values required. Please try again.");
        }
        Car newRecord = new Car(entryFormDto.getLicencePlate(), entryFormDto.getEntranceDate(), entryFormDto.getQrCode());
        carRepository.save(newRecord);
    }

    public boolean verifyParkingCharge(ExitFormDto exitFormDto) {
        Optional<Car> foundCar = carRepository.findByQrCode(exitFormDto.getQrCode());
        if (foundCar.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Parking record could not be found.");
        }
        foundCar.get().setExitDate(LocalDateTime.now());
        carRepository.save(foundCar.get());
        long parkedHours = ChronoUnit.HOURS.between(foundCar.get().getEntranceDate(), foundCar.get().getExitDate());
        if (parkedHours >= 2) {
            return true;
        }
        return false;
    }

    public List<Car> getAllRecords() {
        return carRepository.findAll();
    }
}
