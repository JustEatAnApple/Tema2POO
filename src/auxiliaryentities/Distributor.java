package auxiliaryentities;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import static auxiliaryentities.Constants.MAGICNUMBER;

public final class Distributor implements Observer {
    private int id;
    private int contractLength;
    private int budget;
    private int initialBudget;
    private int initialInfrastructureCost;
    private boolean isBankrupt;
    private ArrayList<Consumer> subscribedconsumers = new ArrayList<>();
    private ArrayList<Producer> subscribedproducers = new ArrayList<>();
    private int contractPrice;
    private int energyNeededKW;
    private String producerStrategy;

    public Distributor() {
    }

    public Distributor(final int id, final int contractLength, final int initialBudget,
                       final int initialInfrastructureCost, final int energyNeededKW,
                       final String producerStrategy) {
        this.id = id;
        this.contractLength = contractLength;
        this.budget = initialBudget;
        this.initialBudget = initialBudget;
        this.initialInfrastructureCost = initialInfrastructureCost;
        this.isBankrupt = false;
        this.contractPrice = 0;
        this.energyNeededKW = energyNeededKW;
        this.producerStrategy = producerStrategy;
    }

    @Override
    public void update(final Observable o, final Object obs) {
        o.deleteObserver(this);
    }

    public ArrayList<Producer> getSubscribedproducers() {
        return subscribedproducers;
    }

    public void setSubscribedproducers(final ArrayList<Producer> subscribedproducers) {
        this.subscribedproducers = subscribedproducers;
    }

    /**
     * Method to add a producer to the list of subscriptions
     * @param p Producer to be added
     */
    public void addSubscribedproducer(final Producer p) {
        this.subscribedproducers.add(p);
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getContractLength() {
        return contractLength;
    }

    public void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public void setInitialInfrastructureCost(final int initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    /**
     * Checker for the boolean value of the bankruptcy
     * @return State of bankruptcy
     */
    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public ArrayList<Consumer> getSubscribedconsumers() {
        return subscribedconsumers;
    }

    /**
     * Method to add a consumer to the distributor
     * @param c Consumer to be added
     */
    public void addSubscribedconsumer(final Consumer c) {
        this.subscribedconsumers.add(c);
    }

    public int getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(final int contractPrice) {
        this.contractPrice = contractPrice;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }

    /**
     * Function to add funds to the distributor
     * @param budgettoadd to be added
     */
    public void addBudget(final int budgettoadd) {
        this.budget += budgettoadd;
    }

    /**
     * Method to calculate the new Production cost formula
     * @return The production cost
     */
    public double retrieveProductionCost() {
        double productionCost = 0;
        for (Producer p : subscribedproducers) {
            productionCost += (p.getPriceKW() * p.getEnergyPerDistributor());
        }
        return Math.round(Math.floor((float) productionCost / MAGICNUMBER));
    }

    /**
     * Method that reduces the budget of the distributor at the end of the round
     */
    public void reduceBudget() {
        this.budget -= (this.initialInfrastructureCost
                + retrieveProductionCost() * this.subscribedconsumers.size());
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(final int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(final String producerStrategy) {
        this.producerStrategy = producerStrategy;
    }
}
