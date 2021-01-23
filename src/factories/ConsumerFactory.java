package factories;

import auxiliaryentities.Consumer;

public final class ConsumerFactory {
    private static ConsumerFactory instance = null;

    public ConsumerFactory() {
    }

    /**
     * Implementing a singleton Factory for consumers
     * @return Instance of the run
     */
    public static ConsumerFactory getInstance() {
        if (instance == null) {
            instance = new ConsumerFactory();
        }
        return instance;
    }

    /**
     * Method that creates a new consumer
     * @param id of consumer to be created
     * @param initialBudget of consumer to be created
     * @param monthlyIncome of consumer to be created
     * @return The new consumer
     */
    public Consumer makeConsumer(final int id, final int initialBudget, final int monthlyIncome) {
        return new Consumer(id, initialBudget, monthlyIncome);
    }
}
