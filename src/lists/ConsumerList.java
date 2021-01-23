package lists;

import auxiliaryentities.Consumer;
import inputentities.InputConsumer;
import factories.ConsumerFactory;

import java.util.ArrayList;

public final class ConsumerList {
    private ArrayList<Consumer> list = new ArrayList<>();

    public ConsumerList() {
    }

    /**
     * Function to copy the entities initialised in the input while also adding them
     * in a new list
     * @param cons List of input consumers
     */
    public void getInputConsumers(final ArrayList<InputConsumer> cons) {
        if (cons.size() != 0) {
            for (InputConsumer inpc : cons) {
                Consumer aux = ConsumerFactory.getInstance().makeConsumer(inpc.getId(),
                        inpc.getInitialBudget(), inpc.getMonthlyIncome());
                list.add(aux);
            }
        }
    }

    public ArrayList<Consumer> getList() {
        return list;
    }

}
