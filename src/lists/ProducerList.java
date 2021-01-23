package lists;

import auxiliaryentities.Producer;
import factories.ProducerFactory;
import inputentities.InputProducer;

import java.util.ArrayList;

public final class ProducerList {
    private ArrayList<Producer> list = new ArrayList<>();

    public ProducerList() {
    }

    /**
     * Function to copy the entities initialised in the input while also adding them
     * in a new list
     * @param p List of input producers
     */
    public void getInputProducers(final ArrayList<InputProducer> p) {
        if (p.size() != 0) {
            for (InputProducer inpp : p) {
                Producer aux = ProducerFactory.getInstance().makeProducer(inpp.getId(),
                        inpp.getEnergyType(), inpp.getMaxDistributors(), inpp.getPriceKW(),
                        inpp.getEnergyPerDistributor());
                list.add(aux);
            }
        }
    }

    public ArrayList<Producer> getList() {
        return list;
    }


    /**
     * Function to grab a producer by its ID
     * @param id ID of producer to be grabbed
     * @return Producer needed
     */
    public Producer grabProducerbyID(final int id) {
        for (Producer x : list) {
            if (x.getId() == id) {
                return x;
            }
        }
        return null;
    }
}
