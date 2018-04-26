package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.pos.POSOpeningController;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author Class 2DC
 */
public class POSOpeningUI extends AbstractUI {

    private final POSOpeningController theController;

    public POSOpeningUI() throws DataConcurrencyException, DataIntegrityViolationException {
        this.theController = new POSOpeningController();
    }

    @Override
    protected boolean doShow() {
        System.out.println(String.format("Creating Delivery Meal Session...\n", (Object) null));
        if (theController.checkPoSState()) {
            theController.createDeliveryMealSession();
            return true;
        }
        System.out.println(String.format("Couldn't open point of sale as it was already opened."));
        return false;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }
}
