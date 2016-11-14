package carshop;

import java.util.List;

public interface CarShop {

    Car addCar(Car car);

    void updateCarByRegNum(int regNum, String name, String model, String color);

    List<Car> getHistory(int limit, int offset);
}
