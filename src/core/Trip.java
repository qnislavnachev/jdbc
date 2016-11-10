package core;

public class Trip {
    public final int personID;
    public final String arrivalDate;
    public final String departureDate;
    public final String city;

    public Trip(int ID, String arrivalDate, String departureDate, String city) {
        this.personID = ID;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.city = city;
    }
}