package com.kilmurray.trigg_java_developer_challenge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kilmurray.trigg_java_developer_challenge.controller.ParkingController;
import com.kilmurray.trigg_java_developer_challenge.dao.Car;
import com.kilmurray.trigg_java_developer_challenge.dto.EntryFormDto;
import com.kilmurray.trigg_java_developer_challenge.dto.ExitFormDto;
import com.kilmurray.trigg_java_developer_challenge.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ParkingServiceTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ParkingController parkingController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Car testCar1;
    private Car testCar2;
    private EntryFormDto testEntryForm1;
    private EntryFormDto testEntryForm2;
    private ExitFormDto testExitForm1;
    private ExitFormDto testExitForm2;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        testCar1 = new Car("YY123 42G", LocalDateTime.of(2022,2,1,10,0,0), "1111111");
        testCar2 = new Car("ZZ123 42G", LocalDateTime.of(2022,2,2,10,0,0), "2222222");

        testEntryForm1 = new EntryFormDto("PP123 42G", LocalDateTime.of(2022,2,3,11,30,0), "333333");
        testEntryForm2 = new EntryFormDto("BB123 42G", LocalDateTime.of(2022,2,3,11,0,0), "444444");

        testExitForm1 = new ExitFormDto("1111111");

        carRepository.save(testCar1);
        carRepository.save(testCar2);
    }

    @AfterEach
    public void tearDown() {
        carRepository.deleteAll();
    }

    @Test
    @DisplayName("Test 1: Parking record successfully saved")
    public void ParkingController_SaveNewRecord_Success() {
        parkingController.enterParkingLot(testEntryForm1);
        parkingController.enterParkingLot(testEntryForm2);

        assertTrue(carRepository.findByQrCode(testEntryForm1.getQrCode()).isPresent());
        assertTrue(carRepository.findByQrCode(testEntryForm2.getQrCode()).isPresent());
    }

    @Test
    @DisplayName("Test 2: Parking record not saved")
    public void ParkingController_SaveNewRecord_Fail() throws Exception {
        testEntryForm1.setEntranceDate(null);

        String body1 = objectMapper.writeValueAsString(testEntryForm1);

        ResultActions mvcResult1 = mockMvc.perform( MockMvcRequestBuilders.post("/api/parking/entry/").content(body1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException));
    }

//    @Test
//    @DisplayName("Test 3: Parking Charge Verified, Under 2 hours")
//    public void ParkingController_VerifyParkingCharge_Under2hrs() {
//        carRepository.save(testCar1);
//
//        assertFalse(parkingController.verifyParkingCharge(testExitForm1));
//    }

    @Test
    @DisplayName("Test 4: Parking Charge Verified, Over 2 hours")
    public void ParkingController_VerifyParkingCharge_Over2hrs() {
        carRepository.save(testCar1);

        assertTrue(parkingController.verifyParkingCharge(testExitForm2));
    }
}
