package strategies;

import auxiliaryentities.Producer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public final class QuantityStrategy implements Strategy {
    @Override
    public void sorter(ArrayList<Producer> prod) {
        Comparator<Producer> idcomp = Comparator.comparing(Producer::getId);
        Collections.sort(prod, idcomp);
        Comparator<Producer> comparator = Comparator.comparing(Producer::getEnergyPerDistributor);
        Collections.sort(prod, comparator.reversed());
    }
}
