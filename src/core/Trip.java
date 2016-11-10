package core;

import java.util.Date;

public class Trip {
    public final int personID;
    public final Date arrivalDate;
    public final Date departureDate;
    public final String city;

    public Trip(int ID, Date arrivalDate, Date departureDate, String city) {
        this.personID = ID;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.city = city;
    }
}