package outputlists;

import outputentities.OutputConsumer;
import outputentities.OutputDistributor;
import outputentities.OutputProducer;

import java.util.ArrayList;

public final class Output {
    private ArrayList<OutputConsumer> consumers;
    private ArrayList<OutputDistributor> distributors;
    private ArrayList<OutputProducer> energyProducers;

    public Output(final OutputConsumerList consumers,
                  final OutputDistributorList distributors,
                  final OutputProducerList producers) {
        this.consumers = consumers.getList();
        this.distributors = distributors.getList();
        this.energyProducers = producers.getList();
    }

    public ArrayList<OutputConsumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(final ArrayList<OutputConsumer> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<OutputDistributor> getDistributors() {
        return distributors;
    }

    public void setDistributors(final ArrayList<OutputDistributor> distributors) {
        this.distributors = distributors;
    }

    public ArrayList<OutputProducer> getEnergyProducers() {
        return energyProducers;
    }

    public void setEnergyProducers(ArrayList<OutputProducer> energyProducers) {
        this.energyProducers = energyProducers;
    }
}
