import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
  private static Map<String, Center> centers = new HashMap<>();
  private static Map<String, User> users = new HashMap<>();
  private static List<Booking> bookings = new ArrayList<>();
  private static int nextSlotId = 1;

  public static void main(String[] args) {
    // Sample Test cases
    addCenter("bellandur", "bangalore", Arrays.asList("monday", "sunday"), 5);
    addWorkOutType("bellandur", "weights");
    addWorkOutType("bellandur", "cardio");
    addWorkOutType("bellandur", "yoga");
    addSlots("bellandur", "weights", "06:00", 2);
    addSlots("bellandur", "yoga", "08:00", 1);

    registerUser("Vivek", 16, "bangalore");
    registerUser("Pavan", 20, "bangalore");
    registerUser("Varun", 22, "bangalore");

    System.out.println(getAvailableSlot("bellandur", LocalDate.of(2021, 5, 28)));

    bookSlot("bellandur", "1", 1);

    System.out.println(viewUserBooking("1", LocalDate.of(2021, 5, 28)));

    cancelSlot("bellandur", "1", 1);

    System.out.println(viewUserBooking("1", LocalDate.of(2021, 5, 28)));
  }

  public static void addCenter(String centerName, String cityName, List<String> daysClosed, int numberOfSlots) {
    Center center = new Center(centerName, cityName, daysClosed, numberOfSlots, new ArrayList<>());
    centers.put(centerName, center);
  }

  public static void addWorkOutType(String centerName, String workoutType) {
    Center center = centers.get(centerName);
    if (center != null) {
      // No specific action needed for workout type in this simplified version
    }
  }

  public static void addSlots(String centerName, String workoutType, String startTime, int numberOfSeats) {
    Center center = centers.get(centerName);
    if (center != null) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
      LocalTime time = LocalTime.parse(startTime, formatter);
      Slot slot = new Slot(nextSlotId++, workoutType, time, numberOfSeats, numberOfSeats, 2);
      center.getSlots().add(slot);
    }
  }

  public static void registerUser(String name, int age, String city) {
    String userId = String.valueOf(users.size() + 1);
    User user = new User(userId, name, age, city);
    users.put(userId, user);
  }

  public static String getAvailableSlot(String centerName, LocalDate date) {
    Center center = centers.get(centerName);
    if (center != null) {
      StringBuilder result = new StringBuilder();
      for (Slot slot : center.getSlots()) {
        result.append(slot.getId()).append(",").append(centerName).append(",").append(slot.getWorkoutType()).append(",").append(slot.getStartTime()).append(",").append(slot.getAvailableSeats()).append("\n");
      }
      return result.toString();
    }
    return "No slots available";
  }

  public static void bookSlot(String centerName, String userId, int slotId) {
    Center center = centers.get(centerName);
    User user = users.get(userId);
    if (center != null && user != null) {
      for (Slot slot : center.getSlots()) {
        if (slot.getId() == slotId) {
          if (slot.getAvailableSeats() > 0) {
            slot.setAvailableSeats(slot.getAvailableSeats() - 1);
            slot.getBookedUsers().add(user);
            bookings.add(new Booking(userId, slotId, LocalDate.now()));
          } else if (slot.getWaitlist().size() < slot.getWaitlistCapacity()) {
            slot.getWaitlist().add(user);
            System.out.println("User " + user.getName() + " added to waitlist for slot " + slot.getId());
          } else {
            throw new RuntimeException("Slot is fully booked and waitlist is full");
          }
          break;
        }
      }
    }
  }

  public static String viewUserBooking(String userId, LocalDate date) {
    StringBuilder result = new StringBuilder();
    for (Booking booking : bookings) {
      if (booking.getUserId().equals(userId) && booking.getDate().equals(date)) {
        for (Center center : centers.values()) {
          for (Slot slot : center.getSlots()) {
            if (slot.getId() == booking.getSlotId()) {
              result.append(slot.getId()).append(",").append(center.getName()).append(",").append(slot.getWorkoutType()).append(",").append(slot.getStartTime()).append("\n");
            }
          }
        }
      }
    }
    return result.length() > 0 ? result.toString() : "No booking for the date";
  }

  public static void cancelSlot(String centerName, String userId, int slotId) {
    Center center = centers.get(centerName);
    User user = users.get(userId);
    if (center != null && user != null) {
      for (Slot slot : center.getSlots()) {
        if (slot.getId() == slotId) {
          Booking bookingToRemove = null;
          for (Booking booking : bookings) {
            if (booking.getUserId().equals(userId) && booking.getSlotId() == slotId) {
              bookingToRemove = booking;
              break;
            }
          }
          if (bookingToRemove != null) {
            bookings.remove(bookingToRemove);
            slot.setAvailableSeats(slot.getAvailableSeats() + 1);
            slot.getBookedUsers().remove(user);

            // Promote a user from the waitlist if available
            if (!slot.getWaitlist().isEmpty()) {
              User promotedUser = slot.getWaitlist().remove(0);
              slot.setAvailableSeats(slot.getAvailableSeats() - 1);
              slot.getBookedUsers().add(promotedUser);
              bookings.add(new Booking(promotedUser.getId(), slot.getId(), LocalDate.now()));
              System.out.println("User " + promotedUser.getName() + " promoted from waitlist to booked for slot " + slot.getId());
            }
          }
          break;
        }
      }
    }
  }
}