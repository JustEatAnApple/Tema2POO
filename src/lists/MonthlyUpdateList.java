package lists;

import auxiliaryentities.MonthlyUpdate;
import inputentities.InputMonthlyUpdate;
import factories.MonthlyUpdateFactory;

import java.util.ArrayList;

public final class MonthlyUpdateList {
    private ArrayList<MonthlyUpdate> list = new ArrayList<>();

    public MonthlyUpdateList() {
    }

    /**
     * Function to copy the monthly updates initialised in the input while also adding them
     * in a new list
     * @param update List of input monthly updates
     */
    public void getInputMonthlyUpdates(final ArrayList<InputMonthlyUpdate> update) {
        if (update.size() != 0) {
            for (InputMonthlyUpdate inpmu : update) {
                MonthlyUpdate aux = MonthlyUpdateFactory.getInstance().makeMonthlyUpdate(
                        inpmu.getNewConsumers(), inpmu.getDistributorChanges(),
                        inpmu.getProducerChanges());
                list.add(aux);
            }
        }
    }

    public ArrayList<MonthlyUpdate> getList() {
        return list;
    }

}
