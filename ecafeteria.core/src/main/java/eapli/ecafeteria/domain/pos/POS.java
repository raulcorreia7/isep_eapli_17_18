package eapli.ecafeteria.domain.pos;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */

//Entity that represents a session start in a point of sale

@Entity
public class POS implements AggregateRoot<Long>, Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDPOS")
    private long idPOS; 
    
    @OneToOne
    @JoinColumn(name ="POSUser")
    private CafeteriaUser posUser;
    
    @Transient
    private long identification;
    
    @Transient
    private boolean open;

    protected POS () {
        //for ORM only
    }
    
    public POS (CafeteriaUser posUser) {
        this.posUser = posUser;
        this.identification = 1;
    }

    /**
     * Compares this object with another
     * @param other
     * @return 
     */
    @Override
    public boolean sameAs(Object other) {
        
        boolean finalResult = false;
        
        if(other.getClass() == this.getClass()) { //check if "other" is a POS entity aswell
           //checks if it has same ID (only atribute of entity that will be unique)
           POS p = (POS)other;
           if(p.id() == this.id()) {
               finalResult = true;
           }
        }
        
        return finalResult;
    }

    @Override
    public Long id() {
       return this.idPOS;
    }

    public boolean isClosed() {
        return this.open == false;
    }

    public void changeState() {
        this.open = !this.open;
    }
 }
