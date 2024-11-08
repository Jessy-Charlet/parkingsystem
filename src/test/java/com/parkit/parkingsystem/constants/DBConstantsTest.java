package com.parkit.parkingsystem.constants;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.service.FareCalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class DBConstantsTest {

    @Test
    public void fareBikeTest(){
        double bike = Fare.BIKE_RATE_PER_HOUR;
        Assertions.assertEquals(bike , 1.0);
    }

    @Test
    public void fareCarTest(){
        double bike = Fare.CAR_RATE_PER_HOUR;
        Assertions.assertEquals(bike , 1.5);
    }
}