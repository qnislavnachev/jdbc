package core;

import java.sql.Date;

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

    @Override
    public String toString() {
        return "Trip: PersonID= " + personID +
                ", ArrivalDate" + arrivalDate +
                ", DepartureDate= " + departureDate +
                ", City= " + city;
    }
}