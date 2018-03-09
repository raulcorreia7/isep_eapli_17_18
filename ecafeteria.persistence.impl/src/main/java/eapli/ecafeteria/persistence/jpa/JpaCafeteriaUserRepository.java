package eapli.ecafeteria.persistence.jpa;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaCafeteriaUserRepository extends JpaAutoTxRepository<CafeteriaUser, MecanographicNumber>
        implements CafeteriaUserRepository {

    public JpaCafeteriaUserRepository(TransactionalContext autoTx) {
        super(autoTx);
    }

    public JpaCafeteriaUserRepository(String puname) {
        super(puname);
    }

    @Override
    public Optional<CafeteriaUser> findByUsername(Username name) {
        final Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return matchOne("e.systemUser.username=:name", params);
    }

    @Override
    public Optional<CafeteriaUser> findByMecanographicNumber(MecanographicNumber number) {
        final Map<String, Object> params = new HashMap<>();
        params.put("number", number);
        return matchOne("e.mecanographicNumber=:number", params);
    }

    @Override
    public Iterable<CafeteriaUser> findAllActive() {
        return match("e.systemUser.active = true");
    }
}
