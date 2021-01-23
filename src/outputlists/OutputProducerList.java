package outputlists;

import auxiliaryentities.Producer;
import outputentities.OutputProducer;

import java.util.ArrayList;

public final class OutputProducerList {
    private ArrayList<OutputProducer> list = new ArrayList<>();

    public OutputProducerList() {
    }

    /**
     * Method that adds a producer to the list of consumers to be shown
     * @param p Producer to be added
     */
    public void addOutputProducer(final Producer p) {
        OutputProducer aux = new OutputProducer();
        aux.setId(p.getId());
        aux.setEnergyPerDistributor(p.getEnergyPerDistributor());
        aux.setEnergyType(p.getEnergyType());
        aux.setMaxDistributors(p.getMaxDistributors());
        aux.setPriceKW(p.getPriceKW());
        aux.setMonthlyStats(p.getMonthlyStats());
        list.add(aux);
    }

    public ArrayList<OutputProducer> getList() {
        return list;
    }
}
