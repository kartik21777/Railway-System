package Backend;
import application.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static Backend.Models.*;
public class Delete {
    public static Platform deletePlatform(Platform plat){
        int f = 0;
        if(platformHeap.size()==0)
        {
            System.out.println("No platform availabe");
            return null;
        }
        for(int i = 0;i<processedList.size();++i)
        {
            Train t = processedList.get(i);
            if(t.getPlatformId()==plat.getId())
            {
                platformHeap.remove(plat);
                plat.setNextFree(t.getDepartureTime());
                f++;
                break;
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
                int idx = Models.waitingList.indexOf(t);
                List<Train> head = new ArrayList<>(Models.waitingList.subList(0, idx));
                List<Train> tail = new ArrayList<>(Models.waitingList.subList(idx, Models.waitingList.size()));
                reset(tail);
                waitingList.clear();
                waitingList.addAll(head);
                waitingList.addAll(tail);
                break;
            }
        }
        return plat;
    }
    public static Train deleteTrain(Train train) {
        int f = 0;
        for(int i= 0;i<processedList.size();++i)
        {
            if(processedList.get(i).getId()==train.getId())
            {
                System.out.println("Cannot delete Train");
                return null;
            }
        }
        for (int i = 0;i<waitingList.size();++i)
        {
            Train t = waitingList.get(i);
            if(t.getId()==train.getId())
            {
                f++;
                waitingList.remove(t);
                break;
            }
        }
        if(f==0)
        {
            System.out.println("No such train is there");
            return null;
        }
        if(train.getPlatformId()!=0)
        {
            for(Platform plat:platformHeap)
            {
                if(plat.getId()==train.getPlatformId())
                {
                    platformHeap.remove(plat);
                    plat.setNextFree(LocalTime.of(0,0));
                    platformHeap.add(plat);
                    break;
                }
            }
            for(int i = 0;i<waitingList.size();++i)
            {
                Train t = waitingList.get(i);
                Platform plat = platformHeap.peek();
                if(plat.getNextFree().isBefore(t.getArrivalTime()))
                {
                    plat.setNextFree(t.getDepartureTime());
                    t.setPlatformId(plat.getId());
                }
            }
        }
        int n = waitingList.size();
        LocalTime[] arr = new LocalTime[n];
        LocalTime[] dep = new LocalTime[n];
        for (int i = 0; i < n; ++i) {
            Train t = waitingList.get(i);
            arr[i] = t.getArrivalTime();
            dep[i] = t.getDepartureTime();
        }
        MAX = MinPlatform(arr, dep);
        return train;
    }
}
