package application;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Train {
    private int id;
    private String name;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private int platformId;
    private String color;
    private int priority;
    private LocalTime actualArrival;
    private LocalTime actualDeparture;
    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("HH:mm");
    public Train(int id, String name, String arrivalTime, String departureTime,
                 String color, int priority) {
        this.id = id;
        this.name = name;
        this.arrivalTime = LocalTime.parse(arrivalTime,formatter);
        this.departureTime = LocalTime.parse(departureTime,formatter);
        this.platformId = 0;
        this.color = color;
        this.priority = priority;
        this.actualArrival = LocalTime.of(0,0);
        this.actualDeparture = LocalTime.of(0,0);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public int getPlatformId() {
        return platformId;
    }

    public String getColor() {
        return color;
    }

    public int getPriority() {
        return priority;
    }

    public LocalTime getActualArrival() {
        return actualArrival;
    }

    public LocalTime getActualDeparture() {
        return actualDeparture;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setActualArrival(LocalTime actualArrival) {
        this.actualArrival = actualArrival;
    }

    public void setActualDeparture(LocalTime actualDeparture) {
        this.actualDeparture = actualDeparture;
    }
    public String toString() {
        return String.format("Train{id=%d, name='%s', arrivalTime='%s', departureTime='%s', actualArrival='%s', actualDeparture='%s', color='%s', priority=%d}",
                id, name, arrivalTime.format(formatter), departureTime.format(formatter),
                actualArrival.format(formatter), actualDeparture.format(formatter), color, priority);
    }
}
