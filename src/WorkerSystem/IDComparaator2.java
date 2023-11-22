package WorkerSystem;

import WorkerManager.Worker;

import java.util.Comparator;

public class IDComparaator2 implements Comparator<Worker> {
    @Override
    public int compare(Worker o1, Worker o2) {
        return Integer.compare(o2.GetID(),o1.GetID());
    }
}
