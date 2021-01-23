package outputlists;

import auxiliaryentities.Distributor;
import outputentities.OutputContract;
import outputentities.OutputDistributor;
import auxiliaryentities.Consumer;

import java.util.ArrayList;

public final class OutputDistributorList {
    private ArrayList<OutputDistributor> list = new ArrayList<>();

    public OutputDistributorList() {
    }

    /**
     * Method that adds a distributor to the list of consumers to be shown
     * @param d Distributor to be added
     */
    public void addOutputDistributor(final Distributor d) {
        OutputDistributor aux = new OutputDistributor();
        aux.setBudget(d.getBudget());
        aux.setId(d.getId());
        aux.setContractCost(d.getContractPrice());
        aux.setEnergyNeededKW(d.getEnergyNeededKW());
        aux.setIsBankrupt(d.isBankrupt());
        aux.setProducerStrategy(d.getProducerStrategy());
        for (Consumer c : d.getSubscribedconsumers()) {
            OutputContract ctr = new OutputContract();
            ctr.setConsumerId(c.getId());
            ctr.setPrice(c.getToPay());
            ctr.setRemainedContractMonths(c.getMonthstoPay());
            aux.getContracts().add(ctr);
        }
        list.add(aux);
    }

    public ArrayList<OutputDistributor> getList() {
        return list;
    }

}
