package lists;

import auxiliaryentities.Distributor;
import inputentities.InputDistributor;
import factories.DistributorFactory;

import java.util.ArrayList;

import static auxiliaryentities.Constants.PROFITCOEFF;

public final class DistributorList {
    private ArrayList<Distributor> list = new ArrayList<>();

    public DistributorList() {
    }

    /**
     * Function to copy the entities initialised in the input while also adding them
     * in a new list
     * @param dist List of input distributors
     */
    public void getInputDistributors(final ArrayList<InputDistributor> dist) {
        if (dist.size() != 0) {
            for (InputDistributor inpd : dist) {
                Distributor aux = DistributorFactory.getInstance().makeDistributor(inpd.getId(),
                        inpd.getContractLength(), inpd.getInitialBudget(),
                        inpd.getInitialInfrastructureCost(), inpd.getEnergyNeededKW(),
                        inpd.getProducerStrategy());
                list.add(aux);
            }
        }
    }

    public ArrayList<Distributor> getList() {
        return list;
    }

    /**
     * Function to grab a distributor by its ID
     * @param id ID of distributor to be grabbed
     * @return Distributor needed
     */
    public Distributor grabDistributorbyID(final int id) {
        for (Distributor x : list) {
            if (x.getId() == id) {
                return x;
            }
        }
        return null;
    }

    /**
     * Method that gets the prices of all contracts while also returning the most efficient
     * one for the consumers
     * @return Most efficient contract for the consumers
     */
    public Distributor getBestDistinRound() {
        int aux = Integer.MAX_VALUE;
        Distributor tobereturned = null;
        for (Distributor x : list) {
            if (x.isBankrupt()) {
                continue;
            }
            if (x.getSubscribedconsumers().size() >= 1) {
                x.setContractPrice((int) Math.round(Math.floor(x.getInitialInfrastructureCost()
                        / (float) x.getSubscribedconsumers().size())
                        + x.retrieveProductionCost()
                        + Math.round(Math.floor((PROFITCOEFF * x.retrieveProductionCost())))));
            } else {
                x.setContractPrice((int) Math.round(Math.floor(x.getInitialInfrastructureCost())
                        + x.retrieveProductionCost()
                        + Math.round(Math.floor((PROFITCOEFF * x.retrieveProductionCost())))));
            }
        }
        for (Distributor x : list) {
            if (x.isBankrupt()) {
                continue;
            }
            if (x.getContractPrice() < aux) {
                aux = x.getContractPrice();
                tobereturned = x;
            }
        }
        if (tobereturned != null) {
            return tobereturned;
        }
        return null;
    }

}
