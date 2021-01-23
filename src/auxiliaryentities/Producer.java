package auxiliaryentities;

import entities.EnergyType;
import outputentities.MonthlyStats;

import java.util.ArrayList;
import java.util.Observable;

public final class Producer extends Observable {
    private int id;
    private EnergyType energyType;
    private int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;
    private boolean hasChanged;
    private int isRegenerable;
    private int currDistributors;
    private ArrayList<Integer> distIDs = new ArrayList<>();
    private ArrayList<MonthlyStats> monthlyStats = new ArrayList<>();

    public Producer() {
    }

    public Producer(final int id, final EnergyType energyType, final int maxDistributors,
                    final double priceKW, final int energyPerDistributor) {
        this.id = id;
        this.energyType = energyType;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
        this.hasChanged = false;
        if (energyType.isRenewable()) {
            this.isRegenerable = 1;
        } else {
            this.isRegenerable = -1;
        }
        this.currDistributors = 0;
    }

    public ArrayList<MonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(ArrayList<MonthlyStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

    /**
     * Method to add a distributor's id to the list of subscribed ones
     * @param distid of the distributor to be added
     */
    public void addDistIDs(final int distid) {
        this.distIDs.add(distid);
    }

    public ArrayList<Integer> getDistIDs() {
        return distIDs;
    }

    /**
     * Function that performs the changes to the Observers ( calls the update function )
     */
    public void roundCheck() {
        if (this.hasChanged) {
            setChanged();
            notifyObservers();
            this.hasChanged = false;
        }
    }

    public int getIsRegenerable() {
        return isRegenerable;
    }

    public void setHasChanged(boolean hasChanged) {
        this.hasChanged = hasChanged;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(double priceKW) {
        this.priceKW = priceKW;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public int getCurrDistributors() {
        return currDistributors;
    }

    public void setCurrDistributors(int currDistributors) {
        this.currDistributors = currDistributors;
    }
}
