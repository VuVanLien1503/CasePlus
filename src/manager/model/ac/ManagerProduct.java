package manager.model.ac;

import iinterface.ICrud;
import iinterface.ISort;
import manager.action.MyRegex;
import manager.model.properties.ManagerTradeMark;
import model.product.abs.Product;
import model.product.ac.Shoe;
import model.product.ac.TiVi;
import model.product.ac.TradeMark;
import model.product.properties.TradeMarkShoe;
import model.product.properties.TradeMarkTiVi;

import java.util.ArrayList;
import java.util.Scanner;

public class ManagerProduct implements ICrud<Product>, ISort<Product> {
    MyRegex myRegex = new MyRegex();
    ManagerTradeMark managerTradeMark;

    private int autoId;
    private ArrayList<Product> listProduct;

    public ManagerProduct(ManagerTradeMark managerTrademark) {
        this.managerTradeMark = managerTrademark;
        listProduct = new ArrayList<>();
        if (listProduct.size() > 0) {
            autoId = (listProduct.get(listProduct.size() - 1).getId()) + 1;
        } else {
            autoId = 1;
        }
    }

    public ManagerTradeMark getManagerTradeMark() {
        return managerTradeMark;
    }

    public void setManagerTradeMark(ManagerTradeMark managerTradeMark) {
        this.managerTradeMark = managerTradeMark;
    }

    public ArrayList<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(ArrayList<Product> listProduct) {
        this.listProduct = listProduct;
    }

    @Override
    public Product findById(Scanner scanner, String pattern) {
        Product product = null;
        int id = 0;
        String input;
        boolean checkRegex = false;
        if (listProduct.isEmpty()) {
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
            if (checkIdToList(id, listProduct)) {
                for (Product c : listProduct) {
                    if (c.getId() == id) {
                        product = c;
                        product.display();
                        break;
                    }
                }
            } else {
                System.err.println("                    Id Does Not Exist");
            }
        }
        return product;


    }

    @Override
    public boolean checkIdToList(int id, ArrayList<Product> list) {
        boolean check = false;
        for (Product p : list) {
            if (p.getId() == id) {
                check = true;
                break;
            }
        }
        return check;
    }


    public void title() {
        System.out.printf("%-5s%-15s%-10s%-15s%-15s%-20s%-25s%s",
                "| ID", "|   NAME", "|   SIZE", "|   PRICE", "|   QUANTITY", "|   TRADEMARK", "|   DESCRIBE", " |\n");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    @Override
    public void display() {
        title();
        for (Product product : listProduct) {
            if (product instanceof Shoe) {
                Shoe shoe = (Shoe) product;
                shoe.display();
            } else {
                if (product instanceof TiVi) {
                    TiVi tiVi = (TiVi) product;
                    tiVi.display();
                }
            }
        }
    }


    @Override
    public Product create(Scanner scanner) {
        Product product;
        String productTradeMark = managerTradeMark.choiceProductTradeMark(scanner);
        if (productTradeMark==null){
            System.out.println("MENU ACTION PRODUCT");
        }
        else {
            boolean check;
            String name;
            int size;
            int quantity;
            TradeMark trademark;
            double price;
            String describe;
            System.out.println("\nCreate New Product...!");
            System.out.println("\nname : (3-15) characters ");
            do {
                System.out.print("Enter Name : ");
                name = scanner.nextLine();
                if (myRegex.regex(name, myRegex.getPatternName())) {
                    check = true;
                } else {
                    System.err.println("Malformed Name");
                    System.out.println("\nname : (3-15) characters ");
                    check = false;
                }
            } while (!check);
            check = true;
            do {
                System.out.print("Enter Size : ");
                size = Integer.parseInt(scanner.nextLine());
                if (myRegex.regex(String.valueOf(size), myRegex.getPatternNumber())) {
                    check = true;
                } else {
                    System.err.println("Malformed Size:");
                    System.out.println("\nSize :contains only numbers\n ");
                    check = false;
                }
            } while (!check);
            check = true;
            do {
                System.out.print("Enter Quantity : ");
                quantity = Integer.parseInt(scanner.nextLine());
                if (myRegex.regex(String.valueOf(quantity), myRegex.getPatternDouble())) {
                    check = true;
                } else {
                    System.err.println("Malformed Quantity:");
                    System.out.println("\nSize :contains only numbers\n ");
                    check = false;
                }
            } while (!check);
            check = true;
            do {
                System.out.print("Enter Price : ");
                price = Double.parseDouble(scanner.nextLine());
                if (myRegex.regex(String.valueOf(price), myRegex.getPatternDouble())) {
                    check = true;
                } else {
                    System.err.println("Malformed Price:");
                    System.out.println("\nPrice :contains only numbers\n ");
                    check = false;
                }
            } while (!check);
            check = true;
            do {
                System.out.print("Enter Describe : ");
                describe = scanner.nextLine();
            } while (!check);
            if (productTradeMark.equals("Shoe")) {
                trademark = choiceTradeMark(scanner, productTradeMark);
                product = new Shoe(autoId, name, size, quantity, trademark, price, describe);
                System.err.println("                                              Create Successful...! \n");
                title();
                product.display();
                return product;
            } else {
                trademark = choiceTradeMark(scanner, productTradeMark);
                product = new Shoe(autoId, name, size, quantity, trademark, price, describe);
                System.err.println("                                                Create Successful...! \n");
                title();
                product.display();
                return product;
            }
        }
        return product=null;
    }

    public TradeMark choiceTradeMark(Scanner scanner, String productTradeMark) {
        TradeMark trademark = null;
        boolean check = false;
        StringBuilder patternIdShoe;
        int number = 0;
        String choice;
        boolean checkChoice = false;

        switch (productTradeMark) {
            case "Shoe":
                if (managerTradeMark.getListTrademark().isEmpty()) {
                    check = true;
                } else {
                    do {
                        patternIdShoe = new StringBuilder("[0");
                        System.out.println("List TradeMark " + productTradeMark);
                        System.out.println("--------------------------");
                        for (TradeMark shoe : managerTradeMark.getListTrademark()) {
                            if (shoe instanceof TradeMarkShoe) {
                                TradeMarkShoe tradeMarkShoe = (TradeMarkShoe) shoe;
                                patternIdShoe.append(tradeMarkShoe.getId());
                                tradeMarkShoe.display();
                            }
                        }
                        patternIdShoe.append("]$");
                        System.out.println("--------------------------");
                        System.out.println("0.    Add  New  TradeMark:");
                        System.out.println("--------------------------");
                        System.out.println("Enter ID Choice Trademark " + patternIdShoe);
                        choice = scanner.nextLine();
                        if (myRegex.regex(String.valueOf(choice), patternIdShoe.toString())) {
                            number = Integer.parseInt(choice);
                            checkChoice = true;
                        } else {
                            System.err.println("                               Malformed ID Choice Mark");
                            System.out.println("\n                                 Please re-enter");
                        }
                    } while (!checkChoice);
                    switch (number) {
                        case 0:
                            trademark = managerTradeMark.create(scanner, productTradeMark);
                            managerTradeMark.add(trademark);
                            return trademark;
                        default:
                            for (TradeMark tr : managerTradeMark.getListTrademark()) {
                                if (tr.getId() == number) {
                                    trademark = tr;
                                    return trademark;
                                }
                            }
                    }
                }
                break;
            case "TiVi":
                if (managerTradeMark.getListTrademark().isEmpty()) {
                    check = true;
                } else {
                    do {
                        patternIdShoe = new StringBuilder("[0");
                        System.out.println("List TradeMark " + productTradeMark);
                        System.out.println("--------------------------");
                        for (TradeMark tiVi : managerTradeMark.getListTrademark()) {
                            if (tiVi instanceof TradeMarkTiVi) {
                                TradeMarkTiVi tradeMarkTiVi = (TradeMarkTiVi) tiVi;
                                patternIdShoe.append(tradeMarkTiVi.getId());
                                tradeMarkTiVi.display();
                            }
                        }
                        patternIdShoe.append("]$");
                        System.out.println("--------------------------");
                        System.out.println("0.    Add  New  TradeMark:");
                        System.out.println("--------------------------");
                        System.out.println("Enter ID Choice Trademark " + patternIdShoe);
                        choice = scanner.nextLine();
                        if (myRegex.regex(String.valueOf(choice), patternIdShoe.toString())) {
                            number = Integer.parseInt(choice);
                            checkChoice = true;
                        } else {
                            System.err.println("                               Malformed ID Choice Mark");
                            System.out.println("\n                                 Please re-enter");
                        }
                    } while (!checkChoice);
                    switch (number) {
                        case 0:
                            trademark = managerTradeMark.create(scanner, productTradeMark);
                            managerTradeMark.add(trademark);
                            return trademark;
                        default:
                            for (TradeMark tr : managerTradeMark.getListTrademark()) {
                                if (tr.getId() == number) {
                                    trademark = tr;
                                    return trademark;
                                }
                            }
                    }
                }
                break;
            default:
                System.err.println("                                  No Product TradeMark\n");

        }
        if (check) {
            trademark = managerTradeMark.create(scanner, productTradeMark);
            managerTradeMark.add(trademark);
            return trademark;
        } else {
            return trademark;
        }
    }


    @Override
    public void add(Product product) {
        if (product!=null){
            listProduct.add(product);
            autoId++;
        }
    }

    @Override
    public void update(Product product) {
        Scanner scanner = new Scanner(System.in);
        boolean check;
        String name;
        int size;
        int quantity;
        double price;
        String describe;
        System.out.println("\nUpdate Product...!\n");
        System.out.println("name : (3-15) characters ");
        do {
            System.out.println("UPDATE NAME : ");
            System.out.print(product.getName() + " UPDATE --> ");
            name = scanner.nextLine();
            if (myRegex.regex(name, myRegex.getPatternName())) {
                check = true;
            } else {
                System.err.println("Malformed Name");
                System.out.println("\nname : (3-15) characters ");
                check = false;
            }
        } while (!check);

        product.setName(name);

        check = true;
        do {
            System.out.print("UPDATE SIZE ");
            System.out.print(product.getSize() + " UPDATE --> ");
            size = Integer.parseInt(scanner.nextLine());
            if (myRegex.regex(String.valueOf(size), myRegex.getPatternNumber())) {
                check = true;
            } else {
                System.err.println("Malformed Size:");
                System.out.println("\nSize :contains only numbers\n ");
                check = false;
            }
        } while (!check);

        product.setSize(size);

        check = true;
        do {
            System.out.print("UPDATE QUANTITY  ");
            System.out.print(product.getQuantity() + " UPDATE --> ");
            quantity = Integer.parseInt(scanner.nextLine());
            if (myRegex.regex(String.valueOf(quantity), myRegex.getPatternDouble())) {
                check = true;
            } else {
                System.err.println("Malformed Quantity:");
                System.out.println("\nSize :contains only numbers\n ");
                check = false;
            }
        } while (!check);

        product.setQuantity(quantity);

        check = true;

        do {
            System.out.print("UPDATE PRICE ");
            System.out.print(product.getPrice() + " UPDATE --> ");
            price = Double.parseDouble(scanner.nextLine());
            if (myRegex.regex(String.valueOf(price), myRegex.getPatternDouble())) {
                check = true;
            } else {
                System.err.println("Malformed Price:");
                System.out.println("\nPrice :contains only numbers\n ");
                check = false;
            }
        } while (!check);

        product.setPrice(price);

        check = true;
        System.out.print("UPDATE DESCRIBE ");
        System.out.print(product.getDescribe() + " UPDATE --> ");
        describe = scanner.nextLine();
        product.setDescribe(describe);
        String choiceProductTradeMark = managerTradeMark.choiceProductTradeMark(scanner);
        TradeMark trademark = choiceTradeMark(scanner, choiceProductTradeMark);
        product.setTradeMark(trademark);
        System.out.println("\n                                  Create Successful...! ");

    }

    @Override
    public void delete(Product product) {
        listProduct.remove(product);
        System.out.println("\n                                  Delate Successful...! ");
    }
}
