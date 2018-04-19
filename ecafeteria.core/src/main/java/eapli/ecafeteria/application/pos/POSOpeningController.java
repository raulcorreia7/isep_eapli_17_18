package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.persistence.DeliveryMealSessionRepository;
import java.util.Optional;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Class 2DC 
 */
public class POSOpeningController {
    
    private final CafeteriaUserService service = new CafeteriaUserService();
    private final DeliveryMealSessionRepository jpaDMS = PersistenceContext.repositories().deliveryMealRepository();
    private POS pointofsale;
    private final Optional<CafeteriaUser> user;
    
    public POSOpeningController(){
            user = service.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().username());
            if(!user.isPresent()) return;
            pointofsale = new POS(user.get());
    }
    
    public boolean checkPoSState(){
        return pointofsale.isClosed();
    }

    public void createDeliveryMealSession() {
        try {
            DeliveryMealSession dms = new DeliveryMealSession(pointofsale);
            if(!user.isPresent()) return;    
            jpaDMS.save(dms);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(POSOpeningController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
}