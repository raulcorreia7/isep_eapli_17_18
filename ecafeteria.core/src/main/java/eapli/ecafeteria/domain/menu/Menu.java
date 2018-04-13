/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menu;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class Menu {

    /**
     * Menu state of this Menu It has two possible By default when a menu is
     * created, it is unpublished
     * <p>
     * # Published
     * <p>
     * # Unpublished
     *
     */
    private MenuState menuState;

    /**
     * Planned period of a menu, starting day is on a Monday and ending day is
     * on a Sunday. Seven working days total planned
     */
    private Period period;

    /**
     *
     * @author Raúl Correia
     * @param startingDayOfWeek Starting working day of a week in format
     * dd-MM-yyyy
     * @param endingDayOfWeek Starting working day of a week in format
     * dd-MM-yyyy
     */
    public Menu(final String startingDayOfWeek, final String endingDayOfWeek) throws IllegalArgumentException {
        menuState = MenuState.UNPUBLISHED;
        setPeriod(startingDayOfWeek, endingDayOfWeek);
    }

    /**
     * Method that creates a period and saves as a member variable Throws
     * IllegalArgumentException in case something goes wrong
     *
     * @author Raúl Correia
     * @param startingDayOfWeek
     * @param endingDayOfWeek
     */
    private void setPeriod(final String startingDayOfWeek, final String endingDayOfWeek) throws IllegalArgumentException {
        period = new Period(startingDayOfWeek, endingDayOfWeek);
    }

    /**
     * Method that returns if the menu is critical or not
     *
     * @author Raúl Correia
     * @return true if critical by business logic , false ifnot
     */
    public boolean isCritical() {
        return period.isCritical();
    }

    /**
     * Method that changes the state of a menu to published
     *
     * @author Raúl Correia
     */
    public void publish() {
        menuState = MenuState.PUBLISHED;
    }
}
