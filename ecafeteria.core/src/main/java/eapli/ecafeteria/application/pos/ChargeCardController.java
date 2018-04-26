/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.CreditTransaction.CreditRecharge;
import eapli.ecafeteria.domain.CreditTransaction.Transaction;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.TransactionRepository;
import eapli.framework.application.Controller;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Currency;
import java.util.Optional;

/**
 *
 * @author MarioDias
 */
public class ChargeCardController implements Controller {

    private final CafeteriaUserService service = new CafeteriaUserService();
    private final TransactionRepository tr = PersistenceContext.repositories().transactioRepository();
    private Transaction t;
    private CafeteriaUser user;

    public String findCafeteriaUserByMecanographicNumber(String mecanographicNumber) {
        Optional<CafeteriaUser> user = service.findCafeteriaUserByMecNumber(mecanographicNumber);
        return this.user.cafeteriaUserNameAndCurrentBalance();
    }

    public boolean chargeCafeteriaUserCard(double tempCredits) throws DataConcurrencyException, DataIntegrityViolationException {
        Money credits = new Money(tempCredits, Currency.getInstance("EUR"));
        this.t = new CreditRecharge(this.user, credits);
        saveTransaction(t);
        if (!this.user.addCredits(credits)) {
            throw new IllegalArgumentException("Error adding credits");
        }
        return true;
    }

    public String saveCafeteriaUser() {
        CafeteriaUser tempuser = service.save(this.user);
        if (tempuser == null) {
            throw new IllegalStateException("Error Updating cafeteria user");
        }
        return tempuser.cafeteriaUserNameAndCurrentBalance();
    }

    private void saveTransaction(Transaction t) throws DataConcurrencyException, DataIntegrityViolationException {
        if (this.tr.save(t) == null) {
            throw new IllegalArgumentException("Error Saving transaction");
        }
    }

}
