import java.util.List;

public class Center {
  private String name;
  private String city;
  private List<String> daysClosed;
  private int numberOfSlots;
  private List<Slot> slots;
  private List<String> workoutTypes; // Add this line

  public Center(String name, String city, List<String> daysClosed, int numberOfSlots, List<Slot> slots) {
    this.name = name;
    this.city = city;
    this.daysClosed = daysClosed;
    this.numberOfSlots = numberOfSlots;
    this.slots = slots;
  }

  // Getters and setters
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public List<String> getDaysClosed() {
    return daysClosed;
  }

  public void setDaysClosed(List<String> daysClosed) {
    this.daysClosed = daysClosed;
  }

  public int getNumberOfSlots() {
    return numberOfSlots;
  }

  public void setNumberOfSlots(int numberOfSlots) {
    this.numberOfSlots = numberOfSlots;
  }

  public List<Slot> getSlots() {
    return slots;
  }

  public void setSlots(List<Slot> slots) {
    this.slots = slots;
  }

  public List<String> getWorkoutTypes() {
    return workoutTypes;
  }

  public void setWorkoutTypes(List<String> workoutTypes) {
    this.workoutTypes = workoutTypes;
  }
}