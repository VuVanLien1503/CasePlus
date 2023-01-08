package model.product.ac;

import model.product.abs.Product;

import java.io.Serializable;

public class TiVi extends Product implements Serializable {
    public TiVi() {
        super();
    }

    public TiVi(int id, String name, int size, int quantity, TradeMark tradeMark, double price, String describe) {
        super(id, name, size, quantity, tradeMark, price, describe);
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public int getSize() {
        return super.getSize();
    }

    @Override
    public void setSize(int size) {
        super.setSize(size);
    }

    @Override
    public int getQuantity() {
        return super.getQuantity();
    }

    @Override
    public void setQuantity(int quantity) {
        super.setQuantity(quantity);
    }

    @Override
    public TradeMark getTradeMark() {
        return super.getTradeMark();
    }

    @Override
    public void setTradeMark(TradeMark tradeMark) {
        super.setTradeMark(tradeMark);
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public void setPrice(double price) {
        super.setPrice(price);
    }

    @Override
    public String getDescribe() {
        return super.getDescribe();
    }

    @Override
    public void setDescribe(String describe) {
        super.setDescribe(describe);
    }

    @Override
    public void display() {
        super.display();
    }
}
