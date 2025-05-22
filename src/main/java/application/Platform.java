package application;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Platform {
    private int id;
    private int f;
    private String platformName;
    private LocalTime nextFree;
    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("HH:mm");
    public Platform(int id, String platformName) {
        this.id = id;
        this.platformName = platformName;
        this.nextFree = LocalTime.of(0, 0);
        this.f = 0;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getPlatformName() {
        return platformName;
    }

    public LocalTime getNextFree() {
        return nextFree;
    }

    public int getFlag(){return f;}

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public void setFlag(int f){this.f = f;}

    public void setNextFree(LocalTime nextFree) {
        this.nextFree = nextFree;
    }
    public String toString() {
        return String.format("Platform{id=%d, name='%s', nextFree='%s', f = %d}",
                id, platformName, nextFree.format(formatter),f);
    }
}
