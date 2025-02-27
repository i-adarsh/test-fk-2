
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Slot {
  private int id;
  private String workoutType;
  private LocalTime startTime;
  private int numberOfSeats;
  private int availableSeats;
  private List<User> bookedUsers;
  private List<User> waitlist;
  private int waitlistCapacity;

  public Slot(int id, String workoutType, LocalTime startTime, int numberOfSeats, int availableSeats, int waitlistCapacity) {
    this.id = id;
    this.workoutType = workoutType;
    this.startTime = startTime;
    this.numberOfSeats = numberOfSeats;
    this.availableSeats = availableSeats;
    this.bookedUsers = new ArrayList<>();
    this.waitlist = new ArrayList<>();
    this.waitlistCapacity = waitlistCapacity;
  }

  // Getters and setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getWorkoutType() {
    return workoutType;
  }

  public void setWorkoutType(String workoutType) {
    this.workoutType = workoutType;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }

  public int getNumberOfSeats() {
    return numberOfSeats;
  }

  public void setNumberOfSeats(int numberOfSeats) {
    this.numberOfSeats = numberOfSeats;
  }

  public int getAvailableSeats() {
    return availableSeats;
  }

  public void setAvailableSeats(int availableSeats) {
    this.availableSeats = availableSeats;
  }

  public List<User> getBookedUsers() {
    return bookedUsers;
  }

  public void setBookedUsers(List<User> bookedUsers) {
    this.bookedUsers = bookedUsers;
  }

  public List<User> getWaitlist() {
    return waitlist;
  }

  public void setWaitlist(List<User> waitlist) {
    this.waitlist = waitlist;
  }

  public int getWaitlistCapacity() {
    return waitlistCapacity;
  }

  public void setWaitlistCapacity(int waitlistCapacity) {
    this.waitlistCapacity = waitlistCapacity;
  }
}
