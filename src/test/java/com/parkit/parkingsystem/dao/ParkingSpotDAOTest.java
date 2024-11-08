package com.parkit.parkingsystem.dao;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import org.junit.jupiter.api.Test;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSpotDAOTest {

    @Test
    public void testGetNextAvailableSlot() {
        ParkingSpotDAO parking = new ParkingSpotDAO();
        assertEquals(1, parking.getNextAvailableSlot(ParkingType.CAR));
    }

    @Test
    public void testUpdateParking() {
        ParkingSpotDAO parking = new ParkingSpotDAO();
        ParkingSpot spot = new ParkingSpot(1, ParkingType.CAR, true);
        assertEquals(true, parking.updateParking(spot));
    }
}