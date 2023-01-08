package model.product.abs;

import iinterface.ICrud;
import model.product.ac.TradeMark;

import java.util.Scanner;

public abstract class Product {
    private int id;
    private String name;
    private int size;
    private int quantity;
    private TradeMark tradeMark;
    private double price;
    private String describe;

    public Product() {
    }

    public Product(int id, String name, int size, int quantity, TradeMark tradeMark, double price, String describe) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.quantity = quantity;
        this.tradeMark = tradeMark;
        this.price = price;
        this.describe = describe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public TradeMark getTradeMark() {
        return tradeMark;
    }

    public void setTradeMark(TradeMark tradeMark) {
        this.tradeMark = tradeMark;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void display() {
        System.out.printf("%-5s%-15s%-10s%-15s%-15s%-20s%-25s%s",
                "| "+this.getId(), "|   "+this.getName(), "|   "+this.getSize(),
                "|   "+this.getPrice(),"|   "+ this.getQuantity(),
                "|   "+this.getTradeMark(),"|   "+ this.getDescribe()," |" + "\n");
    }
}
