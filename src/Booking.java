import java.time.LocalDate;

public class Booking {
  private String userId;
  private int slotId;
  private LocalDate date;

  public Booking(String userId, int slotId, LocalDate date) {
    this.userId = userId;
    this.slotId = slotId;
    this.date = date;
  }

  // Getters and setters
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int getSlotId() {
    return slotId;
  }

  public void setSlotId(int slotId) {
    this.slotId = slotId;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
}
