package eapli.ecafeteria.persistence;

import eapli.ecafeteria.dto.AlertBookingDTO;
import eapli.framework.persistence.repositories.DataRepository;


public interface AlertRepositoryBookings extends DataRepository<AlertBookingDTO, Long>{
    
    AlertBookingDTO getNOBookings();
    
}
