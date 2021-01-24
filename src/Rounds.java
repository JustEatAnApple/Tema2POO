
import auxiliaryentities.Consumer;
import auxiliaryentities.Distributor;
import auxiliaryentities.DistributorChanges;
import auxiliaryentities.Producer;
import auxiliaryentities.ProducerChanges;
import lists.ConsumerList;
import lists.DistributorList;
import lists.MonthlyUpdateList;
import lists.ProducerList;
import outputentities.MonthlyStats;
import strategies.GreenStrategy;
import strategies.PriceStrategy;
import strategies.QuantityStrategy;
import strategies.Strategy;

import java.util.ArrayList;

import static auxiliaryentities.Constants.DUECOEFF;

public final class Rounds {
    private final ConsumerList listOfConsumers;
    private final DistributorList listOfDistributors;
    private final ProducerList listOfProducers;
    private final MonthlyUpdateList listOfUpdates;
    private final int nrofturns;

    public Rounds(final ConsumerList listOfConsumers,
                  final DistributorList listOfDistributors,
                  final MonthlyUpdateList listOfMonthlyUpdates,
                  final ProducerList listOfProducers,
                  final int nrofturns) {
        this.listOfConsumers = listOfConsumers;
        this.listOfDistributors = listOfDistributors;
        this.listOfProducers = listOfProducers;
        this.listOfUpdates = listOfMonthlyUpdates;
        this.nrofturns = nrofturns;
    }

    /**
     * Function that makes changes according to the events of the first round (round 0)
     */
    public void makeFirstRound() {
        Strategy tactics = null;
        ArrayList<Producer> auxProducerList = new ArrayList<>(listOfProducers.getList());
        for (Distributor dst : listOfDistributors.getList()) {
            if (dst.getSubscribedproducers().isEmpty()) {
                int auxNeededPower = dst.getEnergyNeededKW();
                if (dst.getProducerStrategy().equals("GREEN")) {
                    tactics = new GreenStrategy();
                } else if (dst.getProducerStrategy().equals("PRICE")) {
                    tactics = new PriceStrategy();
                } else if (dst.getProducerStrategy().equals("QUANTITY")) {
                    tactics = new QuantityStrategy();
                }
                tactics.sorter(auxProducerList);
                for (Producer p : auxProducerList) {
                    if (!(p.getMaxDistributors() == p.getCurrDistributors())) {
                        dst.addSubscribedproducer(p);
                        p.addDistIDs(dst.getId());
                        p.addObserver(dst);
                        p.setCurrDistributors(p.getCurrDistributors() + 1);
                        auxNeededPower -= p.getEnergyPerDistributor();
                        if (auxNeededPower < 0) {
                            break;
                        }
                    }
                }
            }
        }

        Distributor bestDist = listOfDistributors.getBestDistinRound();

        for (Consumer auxcons : listOfConsumers.getList()) {
            auxcons.setBudget(auxcons.getInitialBudget() + auxcons.getMonthlyIncome());
            auxcons.setIdofDist(bestDist.getId());
            auxcons.setMonthstoPay(bestDist.getContractLength());
            auxcons.setToPay(bestDist.getContractPrice());
            bestDist.addSubscribedconsumer(auxcons);
            if (auxcons.getBudget() >= auxcons.getToPay()) {
                auxcons.addtoBudget(-auxcons.getToPay());
                auxcons.reduceMonths();
                bestDist.addBudget(auxcons.getToPay());
            } else {
                auxcons.setDuepayment(auxcons.getToPay());
                auxcons.reduceMonths();
            }
            if (auxcons.getMonthstoPay() == 0) {
                auxcons.setIdofDist(-1);
            }
        }

        for (Distributor d : listOfDistributors.getList()) {
            if (d.isBankrupt()) {
                continue;
            }
            d.reduceBudget();
            if (d.getBudget() < 0) {
                d.setBankrupt(true);
            }
        }
    }

    /**
     * Function that acts according to the new requirements
     * @param i the round in question
     */
    public void roundsAdjustments(final int i) {
        // Remove the contracts with the consumers who have gone bankrupt
        for (Distributor dist : listOfDistributors.getList()) {
            if (dist.isBankrupt()) {
                continue;
            }
            dist.getSubscribedconsumers().removeIf(c -> c.isBankrupt());
        }
        // This list will consist of the ids of the distributors who have had at least one
        // producer changed
        ArrayList<Integer> distsLeftHanging = new ArrayList<>();

        // Producer changes
        if (listOfUpdates.getList().get(i).getProducerChanges().size() != 0) {
            for (ProducerChanges x : listOfUpdates.getList().get(i).getProducerChanges()) {
                listOfProducers.grabProducerbyID(x.getId()).setEnergyPerDistributor(
                        x.getEnergyPerDistributor());
                listOfProducers.grabProducerbyID(x.getId()).setHasChanged(true);
                for (Integer itr : listOfProducers.grabProducerbyID(x.getId()).getDistIDs()) {
                    if (!distsLeftHanging.contains(itr)) {
                        distsLeftHanging.add(itr);
                    }
                }
            }
        }

        distsLeftHanging.sort(Integer::compareTo);

        // We notify the observers
        for (Producer p : listOfProducers.getList()) {
            p.roundCheck();
        }

        // Implementing a variable that will sort the list of producers according to the strategies
        Strategy tactics = null;
        ArrayList<Producer> auxProducerList = new ArrayList<>(listOfProducers.getList());

        // We iterate through the previously mentioned list to remove the bonds
        for (Integer integer : distsLeftHanging) {
            Distributor dst = listOfDistributors.grabDistributorbyID(integer);
            int auxNeededPower = dst.getEnergyNeededKW();
            for (Producer prdcr : dst.getSubscribedproducers()) {
                prdcr.setCurrDistributors(prdcr.getCurrDistributors() - 1);
                prdcr.getDistIDs().remove(integer);
                prdcr.deleteObserver(dst);
            }
            dst.getSubscribedproducers().clear();
            // Afterwards, we give the distribuitors new producers according to their strategy
            if (dst.getProducerStrategy().equals("GREEN")) {
                tactics = new GreenStrategy();
            } else if (dst.getProducerStrategy().equals("PRICE")) {
                tactics = new PriceStrategy();
            } else if (dst.getProducerStrategy().equals("QUANTITY")) {
                tactics = new QuantityStrategy();
            }
            tactics.sorter(auxProducerList);

            for (Producer p : auxProducerList) {
                if (!(p.getMaxDistributors() == p.getCurrDistributors())) {
                    dst.addSubscribedproducer(p);
                    p.addDistIDs(dst.getId());
                    p.addObserver(dst);
                    p.setCurrDistributors(p.getCurrDistributors() + 1);
                    auxNeededPower -= p.getEnergyPerDistributor();
                    if (auxNeededPower <= 0) {
                        break;
                    }
                }
            }
        }

        for (Producer prd : listOfProducers.getList()) {
            if (prd.getMonthlyStats().size() <= i) {
                MonthlyStats cleaning = new MonthlyStats();
                prd.getMonthlyStats().add(cleaning);
            }
            prd.getMonthlyStats().get(i).setMonth(i + 1);
            prd.getDistIDs().sort(Integer::compareTo);
            for (Integer itg : prd.getDistIDs()) {
                prd.getMonthlyStats().get(i).addDistId(itg);
            }
        }
    }

    /**
     * Function that starts the simulation, making the according changes for each
     * and every round while also actualising the lists
     */
    public void makeRounds() {
        for (int i = 0; i < nrofturns; i++) {
            int check = 0;
            // If all distributors are bankrupt we intrrerupt the simulation
            for (Distributor d : listOfDistributors.getList()) {
                if (d.isBankrupt()) {
                    continue;
                }
                check = 1;
                break;
            }
            if (check == 0) {
                break;
            }
            // Auxiliary variable that will point to the best deal for the consumers
            Distributor bestDist;
            // Making the updates at the start of each month
            if (listOfUpdates.getList().get(i).getNewConsumers().size() != 0) {
                for (Consumer x : listOfUpdates.getList().get(i).getNewConsumers()) {
                    listOfConsumers.getList().add(x);
                }
            }
            if (listOfUpdates.getList().get(i).getDistributorChanges().size() != 0) {
                for (DistributorChanges x : listOfUpdates.getList().get(
                        i).getDistributorChanges()) {
                    listOfDistributors.grabDistributorbyID(
                            x.getId()).setInitialInfrastructureCost(x.getInfrastructureCost());
                }
            }

            // Checking if the producers have changed their costs asta ar trb sa fie update

            // Making the variable point to the best deal in the current round
            // while also calculating the contract price for each distributor
            bestDist = listOfDistributors.getBestDistinRound();
            // Removing the link between consumers and distributors when the contract has ended
            for (Distributor d : listOfDistributors.getList()) {
                if (d.isBankrupt()) {
                    // Case in which we delete the due payment of the consumer if
                    // its current distributor goes bankrupt
                    for (Consumer cons : listOfConsumers.getList()) {
                        if (cons.getIdofDist() == d.getId()) {
                            cons.setDuepayment(0);
                            cons.setMonthstoPay(0);
                            cons.setIdofDist(-1);
                        }
                    }
                    continue;
                }
                d.getSubscribedconsumers().removeIf(
                        c -> c.isBankrupt() || c.getMonthstoPay() <= 0);
            }
            // Giving the non-bankrupt consumers their monthly income and getting the ones
            // without a contract a deal (the best one)
            for (Consumer c : listOfConsumers.getList()) {
                if (c.isBankrupt()) {
                    continue;
                }
                c.addtoBudget(c.getMonthlyIncome());
                if (c.getMonthstoPay() <= 0 || c.getIdofDist() == -1) {
                    c.setIdofDist(bestDist.getId());
                    c.setMonthstoPay(bestDist.getContractLength());
                    c.setToPay(bestDist.getContractPrice());
                    bestDist.addSubscribedconsumer(c);
                }
            }
            // Making the monthly payments for the non-bankrupt consumers
            for (Consumer entity : listOfConsumers.getList()) {
                if (entity.isBankrupt()) {
                    continue;
                }
                // If the consumer has no due payment we check if he can pay the current rate
                if (entity.getDuepayment() == 0) {
                    // If he can, do just that
                    if (entity.getBudget() >= entity.getToPay()) {
                        entity.addtoBudget(-entity.getToPay());
                        listOfDistributors.grabDistributorbyID(entity.getIdofDist()).addBudget(
                                entity.getToPay());
                        entity.reduceMonths();
                        // Contract has ended
                        if (entity.getMonthstoPay() <= 0) {
                            entity.setIdofDist(-1);
                        }
                    } else {
                        // He cannot pay so he gets a due payment
                        entity.setDuepayment(entity.getToPay());
                        entity.reduceMonths();
                    }
                } else {
                    // The consumer has a due payment
                    if (entity.getMonthstoPay() == 0) {
                        // He has one to a distributor with whom he has ended the contract so
                        // he must make the due payment and the one to the new distributor
                        int aux = (int) Math.round(Math.floor(DUECOEFF * entity.getDuepayment()));
                        int idAux = entity.getIdofDist();
                        entity.setIdofDist(bestDist.getId());
                        entity.setToPay(bestDist.getContractPrice());
                        entity.setMonthstoPay(bestDist.getContractLength());
                        bestDist.addSubscribedconsumer(entity);
                        // He is able to
                        if (entity.getBudget() >= (aux + entity.getToPay())) {
                            entity.addtoBudget(-(aux + entity.getToPay()));
                            listOfDistributors.grabDistributorbyID(idAux).addBudget(
                                    aux);
                            listOfDistributors.grabDistributorbyID(entity.getIdofDist()).addBudget(
                                    entity.getToPay());
                            entity.reduceMonths();
                        } else {
                            // He has insufficient funds
                            entity.setBankrupt(true);
                        }
                    } else {
                        // His due payment is at the same distributor he has to make the monthly
                        // payments
                        // He can do the payments
                        if (entity.getBudget()
                                >= (int) Math.round(Math.floor(DUECOEFF * entity.getDuepayment())
                                + entity.getToPay())) {
                            entity.addtoBudget(-(int) Math.round(Math.floor(DUECOEFF
                                    * entity.getDuepayment()) + entity.getToPay()));
                            listOfDistributors.grabDistributorbyID(entity.getIdofDist()).addBudget(
                                    (int) Math.round(Math.floor(DUECOEFF * entity.getDuepayment())
                                            + entity.getToPay()));
                            entity.reduceMonths();
                        } else {
                            // He cannot do the payments
                            entity.setBankrupt(true);
                        }
                    }
                }
            }
            // The non-bankrupt distributors pay their monthly rates
            for (Distributor d : listOfDistributors.getList()) {
                if (d.isBankrupt()) {
                    continue;
                }
                d.reduceBudget();
                // Those who cannot, go bankrupt
                if (d.getBudget() < 0) {
                    d.setBankrupt(true);
                }
            }

            this.roundsAdjustments(i);
        }
    }
}
