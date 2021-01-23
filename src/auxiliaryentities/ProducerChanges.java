package auxiliaryentities;

public final class ProducerChanges {
    private int id;
    private int energyPerDistributor;

    public ProducerChanges() {
    }

    public ProducerChanges(final int id, final int energyPerDistributor) {
        this.id = id;
        this.energyPerDistributor = energyPerDistributor;
    }

    public int getId() {
        return id;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }
}
