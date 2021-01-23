package factories;

import auxiliaryentities.MonthlyUpdate;
import inputentities.InputConsumer;
import inputentities.InputDistributorChanges;
import inputentities.InputProducerChanges;

import java.util.ArrayList;

public final class MonthlyUpdateFactory {
    private static MonthlyUpdateFactory instance = null;

    public MonthlyUpdateFactory() {
    }

    /**
     * Implementing a singleton Factory for the monthly updates
     * @return Instance of the run
     */
    public static MonthlyUpdateFactory getInstance() {
        if (instance == null) {
            instance = new MonthlyUpdateFactory();
        }
        return instance;
    }

    /**
     * Method that creates a monthly update that stores the round's changes
     * @param c customer changes
     * @param cc cost changes
     * @param ccc producer changes
     * @return The monthly update entity
     */
    public MonthlyUpdate makeMonthlyUpdate(final ArrayList<InputConsumer> c,
                                           final ArrayList<InputDistributorChanges> dc,
                                           final ArrayList<InputProducerChanges> pc) {

        return new MonthlyUpdate(c, dc, pc);
    }
}
