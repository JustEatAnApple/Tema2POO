package outputlists;

import auxiliaryentities.Consumer;
import outputentities.OutputConsumer;

import java.util.ArrayList;

public final class OutputConsumerList {
    private ArrayList<OutputConsumer> list = new ArrayList<>();

    public OutputConsumerList() {
    }

    /**
     * Method that adds a consumer to the list of consumers to be shown
     * @param c Consumer to be added
     */
    public void addOutputConsumer(final Consumer c) {
        OutputConsumer aux = new OutputConsumer();
        aux.setId(c.getId());
        aux.setIsBankrupt(c.isBankrupt());
        aux.setBudget(c.getBudget());
        list.add(aux);
    }

    public ArrayList<OutputConsumer> getList() {
        return list;
    }

}
