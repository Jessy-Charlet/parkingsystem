package com.parkit.parkingsystem.dao;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicketDAOTest extends Ticket {
    private static TicketDAO ticketDAO;
    private Ticket ticket;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeEach
    public void setUp() {
        ticket = new Ticket();
        ticketDAO = new TicketDAO();
        this.ticket.setInTime(new Date());
        this.ticket.setOutTime(new Date());
        ParkingSpot parkingSpot = new ParkingSpot(3, ParkingType.CAR, true);
        this.ticket.setParkingSpot(parkingSpot);
        this.ticket.setVehicleRegNumber("ABCDEF");
        System.out.println(ticket);
    }


    @AfterEach
    public void cleanDataBase() throws SQLException, ClassNotFoundException {
            DataBaseConfig dataBaseConfig = new DataBaseConfig();
            Connection con = null;
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE ticket FROM ticket where VEHICLE_REG_NUMBER=?");
            ps.setString(1,this.ticket.vehicleRegNumber);
            ps.execute();
            dataBaseConfig.closeConnection(con);

    }

    @Test
    public void testSaveAndGetTicket () {
        ticketDAO.saveTicket(ticket);
        assertEquals(ticketDAO.getTicket(this.ticket.getVehicleRegNumber()).vehicleRegNumber, ticket.vehicleRegNumber);
    }

    @Test
    public void testUpdateTicket() {
        ticketDAO.saveTicket(ticket);
        ticketDAO.updateTicket(ticket);
    }

}