package carshop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDealer implements CarShop {
    private Connection connection;

    public CarDealer(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Car addCar(Car car) {
        String query = "insert into Cars (Name, Model, Color) values (?, ?, ?)";
        executeCarInsert(car, query);
        return car;
    }

    @Override
    public void updateCarByRegNum(int regNum, String name, String model, String color) {
        String query = "update Cars set Name = ?, Model = ?, Color = ? where RegistrationNum = ? ";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, model);
            statement.setString(3, color);
            statement.setInt(4, regNum);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Car> getHistory(int limit, int offset) {
        String query = "select * from CarsHistory limit ? offset ?";
        return getCarList(limit, offset, query);
    }

    private List<Car> getCarList(int limit, int offset, String query) {
        List<Car> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new Car(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void executeCarInsert(Car car, String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, car.name);
            statement.setString(2, car.model);
            statement.setString(3, car.color);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}