package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    private static final TicketDAO ticketDAO = new TicketDAO();

    public void calculateFare(Ticket ticket){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }
        float price = 1F;
        float inHour = (float) ticket.getInTime().getTime() / 60000;
        float outHour = (float) ticket.getOutTime().getTime() / 60000;
        float duration = (outHour - inHour) / 60;

       if(ticketDAO.countRecurrence(ticket.vehicleRegNumber)){ // -5% if it is recurring
            price = 0.95f;
           System.out.println("-5% loyalty discount");
        }

        if (duration <= 0.5){         //Free if parked less than 30 minutes
            ticket.setPrice(0);
        } else {
            switch (ticket.getParkingSpot().getParkingType()){
                case CAR: {
                    ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR * price);
                    break;
                }
                case BIKE: {
                    ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR * price);
                    break;
                }
                default: throw new IllegalArgumentException("Unkown Parking Type");
            }
        }
    }
}