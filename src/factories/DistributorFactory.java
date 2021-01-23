package factories;

import auxiliaryentities.Distributor;

public final class DistributorFactory {
    private static DistributorFactory instance = null;

    public DistributorFactory() {
    }

    /**
     * Implementing a singleton Factory for distributors
     * @return Instance of the run
     */
    public static DistributorFactory getInstance() {
        if (instance == null) {
            instance = new DistributorFactory();
        }
        return instance;
    }

    /**
     * Method that creates a new Distributor
     * @param id of the new distributor
     * @param contractLength of the new distributor
     * @param initialBudget of the new distributor
     * @param initialInfrastructureCost of the new distributor
     * @param energyNeededKW of the new distributor
     * @param producerStrategy of the new distributor
     * @return The new Distributor entity
     */
    public Distributor makeDistributor(final int id, final int contractLength,
                                    final int initialBudget, final int initialInfrastructureCost,
                                       final int energyNeededKW, final String producerStrategy) {
        return new Distributor(id, contractLength, initialBudget,
                initialInfrastructureCost, energyNeededKW, producerStrategy);
    }
}
