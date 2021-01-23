package factories;

import auxiliaryentities.Producer;
import entities.EnergyType;

public final class ProducerFactory {
    private static ProducerFactory instance = null;

    public ProducerFactory() {
    }

    /**
     * Implementing a singleton factory for the producers
     * @return Instance of the run
     */
    public static ProducerFactory getInstance() {
        if (instance == null) {
            instance = new ProducerFactory();
        }
        return instance;
    }

    /**
     * Method that creates a new Producer
     * @param id of the new producer
     * @param energyType of the new producer
     * @param maxDistributors of the new producer
     * @param priceKW of the new producer
     * @param energyPerDistributor of the new producer
     * @return The new Producer entity
     */
    public Producer makeProducer(final int id, final EnergyType energyType,
                                 final int maxDistributors, final double priceKW,
                                 final int energyPerDistributor) {
        return new Producer(id, energyType, maxDistributors, priceKW, energyPerDistributor);
    }
}
