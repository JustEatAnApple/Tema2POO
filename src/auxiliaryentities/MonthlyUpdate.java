package auxiliaryentities;

import inputentities.InputConsumer;
import factories.ConsumerFactory;
import inputentities.InputDistributorChanges;
import inputentities.InputProducerChanges;

import java.util.ArrayList;

public final class MonthlyUpdate {
    private ArrayList<Consumer> newConsumers = new ArrayList<>();
    private ArrayList<DistributorChanges> distributorChanges = new ArrayList<>();
    private ArrayList<ProducerChanges> producerChanges = new ArrayList<>();
    private ConsumerFactory factory = new ConsumerFactory();

    public MonthlyUpdate() {
    }

    public MonthlyUpdate(final ArrayList<InputConsumer> c,
                         final ArrayList<InputDistributorChanges> cc,
                         final ArrayList<InputProducerChanges> ccc) {
        factory.getInstance();
        if (c.size() != 0) {
            for (InputConsumer inpc : c) {
                Consumer aux = factory.makeConsumer(inpc.getId(), inpc.getInitialBudget(),
                        inpc.getMonthlyIncome());
                newConsumers.add(aux);
            }
        }
        if (cc.size() != 0) {
            for (InputDistributorChanges inpcc : cc) {
                DistributorChanges auxc = new DistributorChanges(inpcc.getId(),
                        inpcc.getInfrastructureCost());
                distributorChanges.add(auxc);
            }
        }
        if (ccc.size() != 0) {
            for (InputProducerChanges inpccc : ccc) {
                ProducerChanges auxp = new ProducerChanges(inpccc.getId(),
                        inpccc.getEnergyPerDistributor());
                producerChanges.add(auxp);
            }
        }
    }

    public ArrayList<Consumer> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final ArrayList<Consumer> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public ArrayList<DistributorChanges> getDistributorChanges() {
        return distributorChanges;
    }

    public void setDistributorChanges(final ArrayList<DistributorChanges> costsChanges) {
        this.distributorChanges = costsChanges;
    }

    public ArrayList<ProducerChanges> getProducerChanges() {
        return producerChanges;
    }

    public void setProducerChanges(ArrayList<ProducerChanges> producerChanges) {
        this.producerChanges = producerChanges;
    }
}
