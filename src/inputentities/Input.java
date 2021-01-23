package inputentities;

import java.util.ArrayList;

public final class Input {
    private int numberOfTurns;
    private InputInitialData initialData;
    private ArrayList<InputMonthlyUpdate> monthlyUpdates;

    private Input() {
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public InputInitialData getInitialData() {
        return initialData;
    }

    public ArrayList<InputMonthlyUpdate> getMonthlyUpdates() {
        return monthlyUpdates;
    }
}
