/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.deactivationreasons;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class LoginException extends RuntimeException {

    public LoginException(String message) {
        super(message);
    }

    public LoginException(DeactivationReasonType reason) {
        super(reason.toString());
    }

}
