package inputentities;

import java.util.ArrayList;

public final class InputMonthlyUpdate {
    private ArrayList<InputConsumer> newConsumers;
    private ArrayList<InputDistributorChanges> distributorChanges;
    private ArrayList<InputProducerChanges> producerChanges;

    private InputMonthlyUpdate() {
    }

    public ArrayList<InputConsumer> getNewConsumers() {
        return newConsumers;
    }

    public ArrayList<InputDistributorChanges> getDistributorChanges() {
        return distributorChanges;
    }

    public ArrayList<InputProducerChanges> getProducerChanges() {
        return producerChanges;
    }
}
