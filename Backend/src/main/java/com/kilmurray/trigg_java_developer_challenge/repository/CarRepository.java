package com.kilmurray.trigg_java_developer_challenge.repository;

import com.kilmurray.trigg_java_developer_challenge.dao.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByQrCode(String qrCode);
}
