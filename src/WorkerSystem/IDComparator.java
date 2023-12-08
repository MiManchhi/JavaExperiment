package WorkerSystem;

import WorkerManager.Worker;

import java.util.Comparator;
//升序
public class IDComparator implements Comparator<Worker> {
    @Override
    public int compare(Worker o1, Worker o2) {
        return Integer.compare(o1.GetID(),o2.GetID());
    }
}
