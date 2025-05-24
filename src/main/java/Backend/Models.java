package Backend;
import java.time.Duration;
import java.time.LocalTime;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.Platform;
import application.Train;

public class Models {
    public static int MIN;
    public static PriorityQueue<Platform> platformHeap;
    public static List<Train> waitingList;
    public static List<Train> processedList;
    public static int MAX;

    static {
        platformHeap = new PriorityQueue<>(
                Comparator.comparing(Platform::getNextFree)
                        .thenComparing(Platform::getId)
        );
        waitingList = new ArrayList<>();
        processedList = new ArrayList<>();
    }
    public static void setMAX(int n)
    {
        MAX = n;
    }
    public static void enqueueTrain(Train train) {
        waitingList.add(train);
        Collections.sort(waitingList, Comparator
                .comparing(Train::getArrivalTime)
                .thenComparing(Train::getPriority)
        );
    }
}