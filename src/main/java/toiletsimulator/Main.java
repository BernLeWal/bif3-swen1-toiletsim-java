package toiletsimulator;

import toiletsimulator.consumer.Toilet;
import toiletsimulator.interfaces.ToiletQueueInterface;
import toiletsimulator.producer.PeopleGenerator;
import toiletsimulator.queues.SimpleQueue;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ToiletQueueInterface queue = new SimpleQueue();

        int randomSeed = new Random().nextInt();
        Random random = new Random(randomSeed);

        PeopleGenerator[] producers = new PeopleGenerator[Parameters.PRODUCERS];
        for (int i = 0; i < producers.length; i++) {
            producers[i] = new PeopleGenerator("People Generator " + i, queue, random);
        }

        Toilet[] consumers = new Toilet[Parameters.CONSUMERS];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Toilet("Toilet " + i, queue);
        }

        for (PeopleGenerator producer : producers) {
            producer.produce();
        }
        for (Toilet consumer : consumers) {
            consumer.consume();
        }

        for (Toilet consumer : consumers) {
            consumer.join();
        }

        System.out.println();
    }
}
