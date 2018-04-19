/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.kitchen.RegisterMealController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class MealBootstrapper implements Action {

    @Override
    public boolean execute() {
        final DishRepository dishRepo = PersistenceContext.repositories().dishes();
        final Dish dish1 = dishRepo.findByName(Designation.valueOf("tofu grelhado")).get();
        final Dish dish2 = dishRepo.findByName(Designation.valueOf("picanha")).get();
        final Dish dish3 = dishRepo.findByName(Designation.valueOf("costeleta à salsicheiro")).get();
        final Dish dish4 = dishRepo.findByName(Designation.valueOf("bacalhau à braz")).get();
        final Dish dish5 = dishRepo.findByName(Designation.valueOf("lentilhas salteadas")).get();
        final Dish dish6 = dishRepo.findByName(Designation.valueOf("lagosta suada")).get();
        //06-05-2018
        register(dish1, MealType.LUNCH, DateTime.parseDate("06-05-2018"));
        register(dish3, MealType.LUNCH, DateTime.parseDate("06-05-2018"));
        register(dish6, MealType.LUNCH, DateTime.parseDate("06-05-2018"));
        register(dish1, MealType.DINNER, DateTime.parseDate("06-05-2018"));
        register(dish3, MealType.DINNER, DateTime.parseDate("06-05-2018"));
        register(dish6, MealType.DINNER, DateTime.parseDate("06-05-2018"));
        //07-05-2018
        register(dish1, MealType.LUNCH, DateTime.parseDate("07-05-2018"));
        register(dish3, MealType.LUNCH, DateTime.parseDate("07-05-2018"));
        register(dish6, MealType.LUNCH, DateTime.parseDate("07-05-2018"));
        register(dish1, MealType.DINNER, DateTime.parseDate("07-05-2018"));
        register(dish3, MealType.DINNER, DateTime.parseDate("07-05-2018"));
        register(dish6, MealType.DINNER, DateTime.parseDate("07-05-2018"));
        //08-05-2018
        register(dish2, MealType.LUNCH, DateTime.parseDate("08-05-2018"));
        register(dish4, MealType.LUNCH, DateTime.parseDate("08-05-2018"));
        register(dish5, MealType.LUNCH, DateTime.parseDate("08-05-2018"));
        register(dish2, MealType.DINNER, DateTime.parseDate("08-05-2018"));
        register(dish4, MealType.DINNER, DateTime.parseDate("08-05-2018"));
        register(dish5, MealType.DINNER, DateTime.parseDate("08-05-2018"));
        //09-05-2018
        register(dish2, MealType.LUNCH, DateTime.parseDate("09-05-2018"));
        register(dish4, MealType.LUNCH, DateTime.parseDate("09-05-2018"));
        register(dish5, MealType.LUNCH, DateTime.parseDate("09-05-2018"));
        register(dish2, MealType.DINNER, DateTime.parseDate("09-05-2018"));
        register(dish4, MealType.DINNER, DateTime.parseDate("09-05-2018"));
        register(dish5, MealType.DINNER, DateTime.parseDate("09-05-2018"));
        //10-05-2018
        register(dish1, MealType.LUNCH, DateTime.parseDate("10-05-2018"));
        register(dish3, MealType.LUNCH, DateTime.parseDate("10-05-2018"));
        register(dish6, MealType.LUNCH, DateTime.parseDate("10-05-2018"));
        register(dish1, MealType.DINNER, DateTime.parseDate("10-05-2018"));
        register(dish3, MealType.DINNER, DateTime.parseDate("10-05-2018"));
        register(dish6, MealType.DINNER, DateTime.parseDate("10-05-2018"));
        //11-05-2018
        register(dish1, MealType.LUNCH, DateTime.parseDate("11-05-2018"));
        register(dish3, MealType.LUNCH, DateTime.parseDate("11-05-2018"));
        register(dish6, MealType.LUNCH, DateTime.parseDate("11-05-2018"));
        register(dish1, MealType.DINNER, DateTime.parseDate("11-05-2018"));
        register(dish3, MealType.DINNER, DateTime.parseDate("11-05-2018"));
        register(dish6, MealType.DINNER, DateTime.parseDate("11-05-2018"));
        //12-05-2018
        register(dish2, MealType.LUNCH, DateTime.parseDate("12-05-2018"));
        register(dish4, MealType.LUNCH, DateTime.parseDate("12-05-2018"));
        register(dish5, MealType.LUNCH, DateTime.parseDate("12-05-2018"));
        register(dish2, MealType.DINNER, DateTime.parseDate("12-05-2018"));
        register(dish4, MealType.DINNER, DateTime.parseDate("12-05-2018"));
        register(dish5, MealType.DINNER, DateTime.parseDate("12-05-2018"));
        return true;
    }
    
    private void register(Dish dish, MealType mealType, Calendar cal) {
        final RegisterMealController controller = new RegisterMealController();
        try {
            controller.registerMeal(dish, mealType, cal);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstrapper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }
}