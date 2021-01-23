import auxiliaryentities.Consumer;
import auxiliaryentities.Distributor;
import auxiliaryentities.Producer;
import inputentities.Input;
import com.fasterxml.jackson.databind.ObjectMapper;
import lists.ConsumerList;
import lists.DistributorList;
import lists.MonthlyUpdateList;
import lists.ProducerList;

import java.io.File;
import java.io.FileWriter;


public final class Main {

    private Main() {
    }

    /**
     * The simulation programme
     * @param args Files
     * @throws Exception Unforeseen events
     */
    public static void main(final String[] args) throws Exception {


        ObjectMapper objMapper = new ObjectMapper();
        Input input = objMapper.readValue(new File(args[0]), Input.class);

        ConsumerList listOfConsumers = new ConsumerList();
        listOfConsumers.getInputConsumers(input.getInitialData().getConsumers());

        DistributorList listOfDistributors = new DistributorList();
        listOfDistributors.getInputDistributors(input.getInitialData().getDistributors());

        ProducerList listOfProducers = new ProducerList();
        listOfProducers.getInputProducers(input.getInitialData().getProducers());

        MonthlyUpdateList listOfUpdates = new MonthlyUpdateList();
        listOfUpdates.getInputMonthlyUpdates(input.getMonthlyUpdates());

    }
}
