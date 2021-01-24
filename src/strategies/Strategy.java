package strategies;

import auxiliaryentities.Producer;

import java.util.ArrayList;

public interface Strategy {
    /**
     * Method that determines the sorting of the list
     * @param prod list to be sorted
     */
    void sorter(ArrayList<Producer> prod);
}
