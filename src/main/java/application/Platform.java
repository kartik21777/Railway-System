package application;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Platform {
    private int id;
    private String platformName;
    private LocalTime nextFree;
    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("HH:mm");
    public Platform(int id, String platformName) {
        this.id = id;
        this.platformName = platformName;
        this.nextFree = LocalTime.of(0, 0);
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

    // Setters

    public void setNextFree(LocalTime nextFree) {
        this.nextFree = nextFree;
    }

}
