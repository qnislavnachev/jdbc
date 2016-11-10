package cardealertest;

import carshop.Car;
import carshop.CarDealer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CarDealerTest {
    private Connection connection;
    private CarDealer carDealer;

    @Before
    public void setUp() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "iani");
        carDealer = new CarDealer(connection);
        createCarsTable();
        createHistoryTable();
        createTrigger();
    }

    @After
    public void tearDown() throws Exception {
        dropTables();
    }

    @Test
    public void gettingHistoryOfUpdatedCars() throws Exception {
        Car car1 = new Car("Opel", "Astra", "Red");
        Car car2 = new Car("Audi", "A3", "Black");
        Car car3 = new Car("BMW", "330", "Grey");

        carDealer.addCar(car1);
        carDealer.addCar(car2);
        carDealer.addCar(car3);

        carDealer.updateCarByRegNum(1, "Subaru", "Impreza", "Red");
        carDealer.updateCarByRegNum(2, "Audi", "Q8", "Black");
        carDealer.updateCarByRegNum(3, "Mercedes", "Benz", "Blue");

        List<Car> carList = carDealer.getHistory(2, 1);

        assertThat(carList.size(), is(2));
        assertThat(carList.contains(car1), is(false));
    }

    private void createHistoryTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS CarsHistory(\n" +
                "    RegistrationNum          int(10)         NOT NULL,\n" +
                "    Name                     varchar(20)     NOT NULL,\n" +
                "    Model                    varchar(20)     NOT NULL,\n" +
                "    Color                    varchar(20)     NOT NULL,\n" +
                "    PRIMARY KEY(RegistrationNum)\n" +
                ");";
        connection.createStatement().executeUpdate(query);
    }

    private void createCarsTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS Cars (\n" +
                "    RegistrationNum          int(10)         NOT NULL        AUTO_INCREMENT,\n" +
                "    Name                     varchar(20)     NOT NULL,\n" +
                "    Model                    varchar(20)     NOT NULL,\n" +
                "    Color                    varchar(20)     NOT NULL,\n" +
                "    PRIMARY KEY(RegistrationNum)\n" +
                ");";
        connection.createStatement().executeUpdate(query);
    }

    private void createTrigger() throws SQLException {
        String query = "CREATE TRIGGER updateCars BEFORE UPDATE ON Cars\n" +
                "    FOR EACH ROW\n" +
                "    INSERT INTO CarsHistory\n" +
                "    VALUES (OLD.RegistrationNum, OLD.Name, OLD.Model, OLD.Color);";
        connection.createStatement().executeUpdate(query);
    }

    private void dropTables() throws SQLException {
        String query = "drop table Cars";
        connection.createStatement().executeUpdate(query);
        query = "drop table CarsHistory";
        connection.createStatement().executeUpdate(query);
    }
}
