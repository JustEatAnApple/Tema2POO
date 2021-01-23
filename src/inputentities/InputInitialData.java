package inputentities;

import java.util.ArrayList;

public final class InputInitialData {
    private ArrayList<InputConsumer> consumers;
    private ArrayList<InputDistributor> distributors;
    private ArrayList<InputProducer> producers;

    private InputInitialData() {
    }

    public ArrayList<InputConsumer> getConsumers() {
        return consumers;
    }

    public ArrayList<InputDistributor> getDistributors() {
        return distributors;
    }

    public ArrayList<InputProducer> getProducers() {
        return producers;
    }
}
