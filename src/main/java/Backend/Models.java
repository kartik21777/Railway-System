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
import java.util.Arrays;

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
        );
    }
    public static void reset(List<Train> tail)
    {
        int f = 0;
        for(int i = 1;i<tail.size();++i)
        {
            Train t = tail.get(i);
            for(Platform p: platformHeap)
            {
                if(p.getId()==t.getPlatformId())
                {
                    platformHeap.remove(p);
                    p.setNextFree(LocalTime.of(0,0));
                    platformHeap.add(p);
                    t.setPlatformId(0);
                    f++;
                    break;
                }
            }
            if(f==0)
            {
                break;
            }
        }
        for(int i = 0;i<tail.size();++i)
        {
            Platform p = platformHeap.peek();
            Train t = tail.get(i);
            if(p.getNextFree().isBefore(t.getArrivalTime()))
            {
                platformHeap.poll();
                p.setNextFree(t.getDepartureTime());
                t.setPlatformId(p.getId());
                platformHeap.add(p);
            }
        }
    }


        public static int MinPlatform(LocalTime[] arr, LocalTime[] dep) {
            Arrays.sort(arr, Comparator.naturalOrder());
            Arrays.sort(dep, Comparator.naturalOrder());
            int platforms = 1, result = 1, i = 1, j = 0;
            while (i < arr.length && j < dep.length) {
                if (!arr[i].isAfter(dep[j])) {
                    platforms++;
                    i++;
                } else {
                    platforms--;
                    j++;
                }
                result = Math.max(result, platforms);
            }
            return result;
        }
    }


