package com.clouway.core;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class Stock {
  public final String name;
  public final Double price;
  public final Double quantity;

  public Stock(String name, Double price, Double quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "Stock{" +
            "name='" + name + '\'' +
            ", price=" + price +
            ", quantity=" + quantity +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Stock stock = (Stock) o;

    if (name != null ? !name.equals(stock.name) : stock.name != null) return false;
    if (price != null ? !price.equals(stock.price) : stock.price != null) return false;
    return quantity != null ? quantity.equals(stock.quantity) : stock.quantity == null;

  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (price != null ? price.hashCode() : 0);
    result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
    return result;
  }
}
