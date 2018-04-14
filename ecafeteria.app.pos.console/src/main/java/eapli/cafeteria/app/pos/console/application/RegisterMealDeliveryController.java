/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.cafeteria.app.pos.console.application;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class RegisterMealDeliveryController {
        
    /** Construtor which shall receive a entity of a open session of a certain pos from the UC User Interface **/
    
    public RegisterMealDeliveryController() {
        
        
    }
    
    /**
     * Method that will focus on regiter a meal delivery
     * given by in the delivery Registry of the pos session given by the UI
     * @param idUser User which will consume the meal
     * @param idBooking Booking which will be delivered
     * @return 
     */    
    public boolean registerNewMealDelivery(long idUser, long idBooking) {
        
        //recordNewMealDelivery(pos, idBooking); //records the new delivery on the delivery registry of this session
        
        //code to fetch the BookingsRepository on the PersistenceContext
        
        //changeState(idBooking, bookingsRepo); //will change the state of the booking delivered
        
        return true;
    }
}
