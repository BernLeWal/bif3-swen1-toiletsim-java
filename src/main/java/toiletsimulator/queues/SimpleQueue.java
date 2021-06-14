package toiletsimulator.queues;

import toiletsimulator.interfaces.JobInterface;

public class SimpleQueue extends ToiletQueue {

    @Override
    public void enqueue(JobInterface job) {
        synchronized (this) {
            queue.add(job);
        }
    }

    @Override
    public JobInterface tryDequeue() {
        synchronized (this) {
            if (queue.size() > 0) {
                return queue.remove(0);
            } else {
                return null;
            }
        }
    }
}