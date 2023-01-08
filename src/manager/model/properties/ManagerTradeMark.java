package manager.model.properties;

import iinterface.ICrud;
import iinterface.ISort;
import manager.action.MyFileBinary;
import manager.action.MyRegex;
import manager.model.ac.ManagerProduct;
import model.customer.AC.Customer;
import model.product.abs.Product;
import model.product.ac.TradeMark;
import model.product.properties.TradeMarkShoe;
import model.product.properties.TradeMarkTiVi;

import java.util.ArrayList;
import java.util.Scanner;

public class ManagerTradeMark implements ICrud<TradeMark>, ISort<TradeMark> {
    Scanner scanner = new Scanner(System.in);
    MyRegex myRegex = new MyRegex();
    ManagerProduct managerProduct;
    MyFileBinary<TradeMark> myFileBinary = new MyFileBinary<>();
    private int autoId;
    private ArrayList<TradeMark> listTrademark;

    public ManagerTradeMark() {
        listTrademark = new ArrayList<>();
        listTrademark = ((ArrayList<TradeMark>) myFileBinary.inputStream(myFileBinary.getPathTradeMark()));
        if (!listTrademark.isEmpty()) {
            autoId = (listTrademark.get(listTrademark.size() - 1).getId()) + 1;
        } else {
            autoId = 1;
        }
    }


    public ArrayList<TradeMark> getListTrademark() {
        return listTrademark;
    }

    public void setListTrademark(ArrayList<TradeMark> listTrademark) {
        this.listTrademark = listTrademark;
    }

    @Override
    public TradeMark findById(Scanner scanner, String pattern) {
        TradeMark tradeMark = null;
        int id = 0;
        String input;
        boolean checkRegex = false;
        if (listTrademark.isEmpty()) {
            System.out.println("                     No Product");
        } else {
            do {
                display();
                System.out.print("Enter  Id : ");
                input = scanner.nextLine();
                if (myRegex.regex(input, pattern)) {
                    checkRegex = true;
                    id = Integer.parseInt(input);
                } else {
                    System.err.println("Malformed ID");
                    System.out.println("\nId Contains Only Numbers\n");
                }
            }
            while (!checkRegex);
            if (checkIdToList(id, listTrademark)) {
                for (TradeMark c : listTrademark) {
                    if (c.getId() == id) {
                        tradeMark = c;
                        tradeMark.display();
                        break;
                    }
                }
            } else {
                System.err.println("                    Id Does Not Exist");
            }
        }
        return tradeMark;
    }

    @Override
    public boolean checkIdToList(int id, ArrayList<TradeMark> list) {
        boolean check = false;
        for (TradeMark p : list) {
            if (p.getId() == id) {
                check = true;
                break;
            }
        }
        return check;
    }

    public void title() {
        System.out.printf("%-10s%s",
                "ID", "NAME\n");
        System.out.println("-----------------");
    }

    @Override
    public void display() {
        title();
        for (TradeMark tradeMark : listTrademark) {
            tradeMark.display();
        }

    }
    public void display(String nameTradeMark) {
        if (nameTradeMark.equals("Shoe")) {
            title();
            for (TradeMark tr : listTrademark) {
                if (tr instanceof TradeMarkShoe) {
                    TradeMarkShoe tradeMarkShoe = (TradeMarkShoe) tr;

                    tradeMarkShoe.display();
                }
            }
            System.out.println("-------------------");
        }
        if (nameTradeMark.equals("TiVi")) {
            title();
            for (TradeMark tr : listTrademark) {
                if (tr instanceof TradeMarkTiVi) {
                    TradeMarkTiVi tradeMarkTiVi = (TradeMarkTiVi) tr;
                    tradeMarkTiVi.display();
                }
            }
            System.out.println("------------------");
        }
    }


    @Override
    public TradeMark create(Scanner scanner) {
        TradeMark trademark;
        String choiceName = choiceProductTradeMark(scanner);
        boolean check;
        String name;
        System.out.println("\nCreate New TradeMark\n");
        System.out.println("TradeMark : (3-15) characters ");
        do {
            System.out.print("Enter Name : ");
            name = scanner.nextLine();
            if (myRegex.regex(name, myRegex.getPatternName())) {
                check = true;
            } else {
                System.err.println("Malformed Name");
                System.out.println("\nTradeMark : (3-15) characters ");
                check = false;
            }
        } while (!check);

        if (choiceName.equals("Shoe")) {
            trademark = new TradeMarkShoe(autoId, name);
            System.out.println("                    Create Successful....!");
            title();
            trademark.display();
            return trademark;
        } else {
            trademark = new TradeMarkTiVi(autoId, name);
            System.out.println("                    Create Successful....!");
            title();
            trademark.display();
            return trademark;
        }
    }

    public TradeMark create(Scanner scanner, String choiceProductTradeMark) {
        TradeMark trademark;
        boolean check;
        String name;
        System.out.println("\nCreate New TradeMark\n");
        System.out.println("TradeMark : (3-15) characters ");
        do {
            System.out.print("Enter Name : ");
            name = scanner.nextLine();
            if (myRegex.regex(name, myRegex.getPatternName())) {
                check = true;
            } else {
                System.err.println("Malformed Name");
                System.out.println("\nTradeMark : (3-15) characters ");
                check = false;
            }
        } while (!check);

        if (choiceProductTradeMark.equals("Shoe")) {
            trademark = new TradeMarkShoe(autoId, name);
            System.out.println("                    Create Successful....!");
            title();
            trademark.display();
            return trademark;
        } else {
            trademark = new TradeMarkTiVi(autoId, name);
            System.out.println("                    Create Successful....!");
            title();
            trademark.display();
            return trademark;
        }
    }

    public String choiceProductTradeMark(Scanner scanner) {
        String pattern = "^[0-2]$";
        boolean check = false;
        String input;
        int choice = -1;
        do {
            do {
                System.out.println("MENU:");
                System.out.println("1. Shoe");
                System.out.println("2. TiVi");
                System.out.println("-------------------");
                System.out.println("0.Back Menu");
                System.out.println("--------------------");
                input = scanner.nextLine();
                if (myRegex.regex(input, pattern)) {
                    choice = Integer.parseInt(input);
                    check = true;
                } else {
                    System.err.println("                          incorrect choice, please re-enter");
                    System.out.println("\n");
                }
            } while (!check);
            switch (choice) {
                case 1:
                    return "Shoe";
                case 2:
                    return "TiVi";
            }

        } while (choice != 0);

        return null;
    }


    @Override
    public void add(TradeMark tradeMark) {
        listTrademark.add(tradeMark);
        autoId++;

    }

    @Override
    public void update(TradeMark tradeMark) {
        boolean check;
        String name;
        System.out.println("\nCreate New TradeMark\n");
        System.out.println("TradeMark : (3-15) characters ");
        do {
            System.out.print("Enter Name : ");
            name = scanner.nextLine();
            if (myRegex.regex(name, myRegex.getPatternName())) {
                check = true;
            } else {
                System.err.println("Malformed Name");
                System.out.println("\nTradeMark : (3-15) characters ");
                check = false;
            }
        } while (!check);
        tradeMark.setName(name);
        System.out.println("                    Update Successful....!");


    }


    @Override
    public void delete(TradeMark tradeMark) {
        listTrademark.remove(tradeMark);
        System.out.println("\n                                  Delate Successful...! ");
    }


}
