package auxiliaryentities;

public final class Consumer {
    private int id;
    private int budget;
    private int initialBudget;
    private int monthlyIncome;
    private boolean isBankrupt;
    private int toPay;
    private int monthstoPay;
    private int idofDist;
    private int duepayment;

    public Consumer() {
    }

    public Consumer(final int id, final int initialBudget, final int monthlyIncome) {
        this.id = id;
        this.budget = initialBudget;
        this.initialBudget = initialBudget;
        this.monthlyIncome = monthlyIncome;
        this.isBankrupt = false;
        this.toPay = 0;
        this.monthstoPay = 0;
        this.idofDist = -1;
        this.duepayment = 0;
    }


    public int getIdofDist() {
        return idofDist;
    }


    public void setIdofDist(final int idofDist) {
        this.idofDist = idofDist;
    }


    public int getId() {
        return id;
    }


    public void setId(final int id) {
        this.id = id;
    }


    public int getInitialBudget() {
        return initialBudget;
    }


    public void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }


    public int getMonthlyIncome() {
        return monthlyIncome;
    }


    public void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }


    public boolean isBankrupt() {
        return isBankrupt;
    }


    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }


    public int getToPay() {
        return toPay;
    }


    public void setToPay(final int toPay) {
        this.toPay = toPay;
    }


    public int getMonthstoPay() {
        return monthstoPay;
    }


    public void setMonthstoPay(final int monthstoPay) {
        this.monthstoPay = monthstoPay;
    }

    /**
     * Method to ensure that a payment has been made (reduces the number of months
     * remaining in a contract )
     * Or a due payment created
     */
    public void reduceMonths() {
        this.monthstoPay--;
    }



    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }

    /**
     * Method to add funds to the budget of the consumer
     * @param budgettobeadded (funds) to be added
     */
    public void addtoBudget(final int budgettobeadded) {
        this.budget += budgettobeadded;
    }

    /**
     * Getter for the due payment of the consumer
     * If it returns 0, the user has no due payments
     * @return Due payment
     */
    public int getDuepayment() {
        return duepayment;
    }

    /**
     * Setter for the due payment of the consumer
     * (Adds a due payment to the consumer )
     * @param duepayment to be set
     */
    public void setDuepayment(final int duepayment) {
        this.duepayment = duepayment;
    }
}
