/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Version;
import eapli.ecafeteria.domain.cafeteriauser.*;
import eapli.ecafeteria.domain.meal.*;
import javax.persistence.EmbeddedId;
import javax.persistence.OneToOne;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
@Entity
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;
    
    
    @EmbeddedId
    private int idBooking;
    @OneToOne
     private Meal meal; 
     private BookingState bookingState;
     private CafeteriaUser cafeteriauser;

     
    public Booking(int idBooking, Meal meal, BookingState bookingState, CafeteriaUser cafeteriauser) {
        this.idBooking = idBooking;
        this.meal = meal;
        this.bookingState = bookingState;
        this.cafeteriauser = cafeteriauser;
    }
     
     
    protected Booking() {
        // for ORM only
    }
    
    

}
