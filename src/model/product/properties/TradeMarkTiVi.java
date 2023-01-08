package model.product.properties;

import model.product.ac.TradeMark;

import java.io.Serializable;

public class TradeMarkTiVi extends TradeMark implements Serializable {
    public TradeMarkTiVi() {
    }

    public TradeMarkTiVi(int id, String name) {
        super(id, name);
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
    public String toString() {
        return super.toString();
    }

    @Override
    public void display() {
        super.display();
    }
}
