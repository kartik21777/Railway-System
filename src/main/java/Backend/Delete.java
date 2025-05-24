package Backend;
import application.*;
import java.time.LocalTime;
import static Backend.Models.*;
public class Delete {
    public static Platform deletePlatform(Platform plat){
        int f = 0;
        if(MIN==0)
        {
            System.out.println("No platform available to delete");
            return null;
        }
        for(Platform p : platformHeap)
        {
            if(p.getId()==plat.getId())
            {
                f++;
            }
        }
        if(f==0)
        {
            System.out.println("No such platform available to delete");
            return null;
        }
        f = 0;
        for(int i = 0;i<processedList.size();++i)
        {
            Train t = processedList.get(i);
            if(t.getPlatformId()==plat.getId())
            {
                platformHeap.remove(plat);
                plat.setNextFree(t.getDepartureTime());
                f++;
            }
        }
        if(f==0)
        {
            platformHeap.remove(plat);
            plat.setNextFree(LocalTime.of(0,0));
        }
        for(int i = 0;i<waitingList.size();++i) {
            Train t = waitingList.get(i);
            if (t.getPlatformId() == plat.getId()) {
                Allocation.allocate(t);
            }
        }
        return plat;
    }
    public static Train deleteTrain(Train train){
        int f = 0;
        for(int i = 0;i<processedList.size();++i) {
            Train t = processedList.get(i);
            if (t.getId() == train.getId()) {
                f++;
                for(Platform plat: platformHeap)
                {
                    if(plat.getId()==train.getId())
                    {
                        plat.setNextFree(train.getDepartureTime());
                    }
                }
            }
        }
        if(f==0)
        {
            train.setDepartureTime(LocalTime.of(0,0));
            for(Platform plat: platformHeap)
            {
                if(plat.getId()==train.getId())
                {
                    plat.setNextFree(LocalTime.of(0,0));
                }
            }
            waitingList.remove(train);
        }

        return train;
    }
}
