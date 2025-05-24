package Backend;
import application.*;
import java.time.LocalTime;
import static Backend.Models.*;
public class Allocation {
    public static void allocate(Train train, Platform plat){
        Platform p = platformHeap.peek();
        if(p.getNextFree().isBefore(train.getArrivalTime()))
        {
            platformHeap.poll();
            p.setNextFree(train.getDepartureTime());
            train.setPlatformId(p.getId());
            platformHeap.add(p);
        }
        else if(MIN<MAX)
        {
            MIN++;
            plat.setNextFree(train.getDepartureTime());
            train.setPlatformId(plat.getId());
            platformHeap.add(plat);
        }
        else
        {
            System.out.println("No platform available");
            waitingList.remove(train);
        }
    }
    public static void addPlatform(Platform platform, LocalTime now){
        if(MIN<MAX)
        {
            platform.setNextFree(now);
            platformHeap.add(platform);
            MIN++;
        }
        else
        {
            System.out.println("Cannot add more platforms");
        }
    }
}
