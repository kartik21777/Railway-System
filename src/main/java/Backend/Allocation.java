package Backend;
import application.*;
import static Backend.Models.*;

public class Allocation {
    public static void allocate(Train train){
        for(Platform plat : platformHeap)
        {
            if(plat.getNextFree().isBefore(train.getArrivalTime()))
            {
                plat.setNextFree(train.getDepartureTime());
                train.setPlatformId(plat.getId());
                break;
            }
        }
        System.out.println("No platform available");
    }
    public static void addPlatform(Platform platform, Train train, int minPlatforms){
        platformHeap.add(platform);
        minPlatforms++;

    }
}
