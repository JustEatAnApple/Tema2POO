package outputentities;

import java.util.ArrayList;

public final class MonthlyStats {
    private int month;
    private ArrayList<Integer> distributorsIds = new ArrayList<>();

    public MonthlyStats() {
    }

    /**
     * Method that adds a distributor's id to the list
     * @param id to be added
     */
    public void addDistId(final int id) {
        this.distributorsIds.add(id);
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public ArrayList<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    public void setDistributorsIds(ArrayList<Integer> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }
}
