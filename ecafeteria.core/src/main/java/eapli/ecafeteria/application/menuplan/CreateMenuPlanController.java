/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menuplan;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.menus.MenuService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.menuplan.MenuPlan;
import eapli.ecafeteria.domain.menuplan.MenuPlanItem;
import eapli.ecafeteria.domain.menuplan.Quantity;
import eapli.ecafeteria.persistence.MenuPlanItemRepository;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import static eapli.framework.util.DateTime.beginningOfWeek;
import static eapli.framework.util.DateTime.currentWeekNumber;
import static eapli.framework.util.DateTime.endOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class CreateMenuPlanController implements Controller {

    private final MenuPlanRepository mpr = PersistenceContext.repositories().menuPlan();

    private final MenuPlanItemRepository mpir = PersistenceContext.repositories().menuPlanItem();

    private Menu m;

    private MenuPlan menuplan;

    private MenuPlanItem mpi;

    private Quantity q;

    public Menu getCurrentMenu() {

        m = MenuService.findLatestMenu().get();
        return m;
    }
    
    public MenuPlan getMenuPlanFromMenu(Menu m){
     
        return mpr.getMenuPlanFromMenu(m);
    }

    public Iterable<Meal> mealsFromMenuByDay(Calendar bDay, Menu me) {
        return MenuService.getMealsFromMenuByGivenDay(me, bDay);
    }

    public Quantity insertQuantity(int quantity) {
        q = new Quantity(quantity);
        return q;
    }

    public MenuPlanItem createMenuPlanItem(Meal currentMeal, Quantity q) {
        mpi = new MenuPlanItem(currentMeal, q);
        return mpi;

    }

    public MenuPlan createMenuPlan(List<MenuPlanItem> lista, Menu m) {

        menuplan = new MenuPlan(lista, m);

        return menuplan;
    }

//    public void saveMenuPlanItem(MenuPlanItem mpi) throws DataConcurrencyException, DataIntegrityViolationException {
//        mpir.save(mpi);
//    }

    public void saveMenuPlan(MenuPlan mp,List<MenuPlanItem>list) throws DataConcurrencyException, DataIntegrityViolationException {
        System.out.println("quantidade de MenuPlanItem: "+list.size());
        
        for (MenuPlanItem mpi:list) {
            System.out.println("menu plan item guardado: "+mpir.save(mpi));
        }
        
        mpr.save(mp);
    }
    
    public MenuPlan getActiveMenuPlan(){
        return mpr.getActiveMenuPlan();
    }
    
  

    public void editMenuPlan(int quantity, MenuPlanItem menu_plan_item) {
        

        menu_plan_item.getQuantityNumber().setQuantity(quantity);

    }
}
