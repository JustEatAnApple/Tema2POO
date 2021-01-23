package auxiliaryentities;

public final class DistributorChanges {
    private int id;
    private int infrastructureCost;

    public DistributorChanges(final int id, final int infrastructureCost) {
        this.id = id;
        this.infrastructureCost = infrastructureCost;
    }

    public int getId() {
        return id;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

}
