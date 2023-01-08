package manager.action;


import manager.model.ac.ManagerCustomer;
import manager.model.ac.ManagerProduct;
import manager.model.properties.ManagerRole;
import manager.model.properties.ManagerTradeMark;
import model.customer.AC.Customer;
import model.customer.Properties.Role;
import model.product.abs.Product;
import model.product.ac.TradeMark;

import java.util.ArrayList;
import java.util.Scanner;

public class ManagerMenu {
    Scanner scanner = new Scanner(System.in);
    MyFileBinary myFileBinary = new MyFileBinary<>();
    ManagerRole managerRole = new ManagerRole();

    MyRegex myRegex = new MyRegex();

    ManagerProduct managerProduct;
    ManagerTradeMark managerTrademark = new ManagerTradeMark();
    ManagerCustomer managerCustomer = new ManagerCustomer();
    Customer customer;
    ManagerLogin login = new ManagerLogin(managerCustomer);


    public ManagerMenu() {
        managerProduct = new ManagerProduct(managerTrademark);
//        Role role = managerRole.getListRole().get(0);
//        Customer customer = new Customer(0, "Lien", 33, "NamDinh", "0987654321", "admin@gmail.com", "123456", role);
//        managerCustomer.getListCustomer().add(customer);
        managerProduct.setListProduct((ArrayList<Product>) myFileBinary.inputStream(myFileBinary.getPathProduct()));
        managerCustomer.setListCustomer((ArrayList<Customer>) myFileBinary.inputStream(myFileBinary.getPathCustomer()));

    }

    public void begin(Scanner scanner) {
        boolean check = false;
        int choice = -1;
        String input;
        do {
            do {
                System.out.println("MENU:");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("----------------");
                System.out.println("0. Exit :");
                System.out.println("----------------");
                System.out.println("Enter Choice Menu From 0->2 ");
                System.out.print("Enter Choice: ");
                input = scanner.nextLine();
                if (myRegex.regex(input, myRegex.getPatternBegin())) {
                    choice = Integer.parseInt(input);
                    check = true;
                } else {
                    System.err.println("                         Incorrect Choice, Please Re-Enter");
                    System.out.println("\n");
                }
            } while (!check);

            switch (choice) {
                case 1:
                    customer = login.checkLogin(managerCustomer.getListCustomer(), scanner);
                    if (customer != null) {
                        if (login.isAdmin(customer)) {
                            menuAdmin(scanner);
                        } else {
                            menuCustomer();
                        }
                    }
                    break;
                case 2:
                    Customer newCustomer = managerCustomer.create(scanner);
                    managerCustomer.add(newCustomer);
                    myFileBinary.outPutStream(myFileBinary.getPathCustomer(), managerCustomer.getListCustomer());
                    break;
            }
        } while (choice != 0);
    }

    public void menuAdmin(Scanner scanner) {
        String pattern = "^[0-2]$";
        boolean check = false;
        String input;
        int choice = -1;
        do {
            do {
                System.out.println("MENU:");
                System.out.println("1. Action Customer");
                System.out.println("2. Action Product");
                System.out.println("------------------");
                System.out.println("0. Back Login :");
                System.out.println("------------------");
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
                    System.out.println("ACTION-CUSTOMER");
                    actionCustomer(scanner);
                    break;
                case 2:
                    System.out.println("ACTION-PRODUCT");
                    actionProduct(scanner);
                    break;
            }
        } while (choice != 0);

    }

    public void actionProduct(Scanner scanner) {
        String pattern = "^[0-5]$";
        boolean check = false;
        String input;
        int choice = -1;
        do {
            do {
                System.out.println("MENU:");
                System.out.println("1. Display Product");
                System.out.println("2. Create New Product");
                System.out.println("3. Update ProductById");
                System.out.println("4. Delete ProductById");
                System.out.println("5. Action TradeMark");
                System.out.println("----------------------");
                System.out.println("0. Back Login : ");
                System.out.println("---------------------");
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
                    System.out.println("ShowListProduct:");
                    managerProduct.display();
                    System.out.println("-----------------------------------------------------------------------------------------------------------");
                    break;
                case 2:
                    System.out.println("CREATE NEW PRODUCT:");
                    Product product = managerProduct.create(scanner);
                    managerProduct.add(product);
                    myFileBinary.outPutStream(myFileBinary.getPathProduct(), managerProduct.getListProduct());


                    myFileBinary.outPutStream(myFileBinary.getPathTradeMark(), managerTrademark.getListTrademark());
                    break;
                case 3:
                    System.out.println("UPDATE PRODUCT BY ID");
                    Product productUpdate = managerProduct.findById(scanner, myRegex.getPatternNumber());
                    managerProduct.update(productUpdate);
                    myFileBinary.outPutStream(myFileBinary.getPathProduct(), managerProduct.getListProduct());
                    myFileBinary.outPutStream(myFileBinary.getPathTradeMark(), managerTrademark.getListTrademark());
                    break;
                case 4:
                    System.out.println("DELETE PRODUCT BY ID");
                    Product productDelete = managerProduct.findById(scanner, myRegex.getPatternNumber());
                    managerProduct.delete(productDelete);
                    myFileBinary.outPutStream(myFileBinary.getPathProduct(), managerProduct.getListProduct());
                    break;
                case 5:
                    System.out.println("ACTION TRADEMARK");
                    actionTradeMark();
                    break;
            }
        } while (choice != 0);
    }

    public void actionCustomer(Scanner scanner) {
        String pattern = "^[0-4]$";
        boolean check = false;
        String input;
        int choice = -1;
        do {
            do {
                System.out.println("MENU:");
                System.out.println("1. Display Customer");
                System.out.println("2. Delete Customer");
                System.out.println("3. Transaction history");
                System.out.println("4. Update Role Customer");
                System.out.println("-------------------------");
                System.out.println("0. Back Login  : ");
                System.out.println("-------------------------");
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
                    System.out.println("Show List Customer:");
                    managerCustomer.display();
                    break;
                case 2:
                    System.out.println("DELETE CUSTOMER:");


                    break;
                case 3:
                    System.out.println("TRANSACTION HISTORY");
                    break;
                case 4:
                    System.out.println("UPDATE ROLE CUSTOMER");
                    updateRole();
                    myFileBinary.outPutStream(myFileBinary.getPathCustomer(), managerCustomer.getListCustomer());
                    break;
            }
        } while (choice != 0);

    }

    public void actionTradeMark() {
        String pattern = "^[0-4]$";
        boolean check = false;
        String input;
        int choice = -1;
        do {
            do {
                System.out.println("MENU:");
                System.out.println("1. Display TradeMark");
                System.out.println("2. Create New TradeMark");
                System.out.println("3. Update TradeMark By Id");
                System.out.println("4. Delete TradeMark By Id");
                System.out.println("-----------------------");
                System.out.println("0.  Back Login : ");
                System.out.println("----------------------");
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
                    System.out.println("Show List TradeMark");
                    managerTrademark.display();
                    choiceTradeMark();
                    break;
                case 2:
                    System.out.println("Create New TradeMark");
                    TradeMark tradeMark= managerTrademark.create(scanner);
                    managerTrademark.add(tradeMark);
                    myFileBinary.outPutStream(myFileBinary.getPathTradeMark(), managerTrademark.getListTrademark());
                    break;
                case 3:
                    System.out.println("Update TradeMark By Id");
                    UpdateProductByTradeMark();
                    myFileBinary.outPutStream(myFileBinary.getPathTradeMark(), managerTrademark.getListTrademark());
                    break;
                case 4:
                    System.out.println("Delete Trade Mark By Id");
                    deleteProductByTradeMark();
                    myFileBinary.outPutStream(myFileBinary.getPathTradeMark(), managerTrademark.getListTrademark());
                    myFileBinary.outPutStream(myFileBinary.getPathProduct(),managerProduct.getListProduct() );
                    break;
            }
        } while (choice != 0);
    }
    public void deleteProductByTradeMark() {
        String pattern = "^[YN]$";
        boolean check = false;
        String confirm;
        TradeMark trademark = managerTrademark.findById(scanner,myRegex.getPatternNumber());
       managerTrademark.delete(trademark);
        boolean checkList = false;
        for (Product p : managerProduct.getListProduct()) {
            if (p.getTradeMark().getName().equals(trademark.getName())) {
                checkList = true;
                break;
            }
        }
        if (checkList) {
            System.out.println("List Product TradeMark Name = " + trademark.getName() + "\n");
            managerProduct.title();
            for (Product p : managerProduct.getListProduct()) {
                if (p.getTradeMark().getName().equals(trademark.getName())) {
                    p.display();
                }
            }
            System.out.println("                                Delete Trademark = " + trademark.getName() + " With All Products \n");
        } else {
            System.out.println("                                No Product Under TradeMark Name = " + trademark.getName() + "\n");
        }

        do {
            System.out.print("Enter Y (Agree) / N (disagree) TradeMark Name = " + trademark.getName() + " : ");
            confirm = scanner.nextLine();
            if (myRegex.regex(confirm, pattern)) {
                if (confirm.equals("Y")) {
                    ArrayList<Product> listDelete = new ArrayList<>();
                    for (Product p : managerProduct.getListProduct()) {
                        if (p.getTradeMark().getName().equals(trademark.getName())) {
                            listDelete.add(p);
                        }
                    }
                    for (Product p :
                            listDelete) {
                        managerProduct.getListProduct().remove(p);
                    }
                   managerTrademark.getListTrademark().remove(trademark);
                } else if (confirm.equals("N")) {
                    System.err.println("                        Delete Failed, Back MENU :");
                }
                check = true;
            } else {
                System.err.println("                          incorrect choice, please re-enter");
                System.out.println("\n");
            }
        } while (!check);

    }
    public void UpdateProductByTradeMark() {
        String pattern = "^[YN]$";
        boolean check = false;
        String confirm;
        TradeMark trademark = managerTrademark.findById(scanner,myRegex.getPatternNumber());
        String nameTradeMark=trademark.getName();
        boolean checkList = false;
        for (Product p : managerProduct.getListProduct()) {
            if (p.getTradeMark().getName().equals(nameTradeMark)) {
                checkList = true;
                break;
            }
        }
        if (checkList) {
            System.out.println("List Product TradeMark Name = " + nameTradeMark + "\n");
            managerProduct.title();
            for (Product p : managerProduct.getListProduct()) {
                if (p.getTradeMark().getName().equals(nameTradeMark)) {
                    p.display();
                }
            }
            System.out.println("                                Update Trademark = " + nameTradeMark + " With All Products \n");
        } else {
            System.out.println("                                No Product Under TradeMark Name = " + trademark + "\n");
        }

        do {
            System.out.print("Enter Y (Agree) / N (disagree) ");
            confirm = scanner.nextLine();
            if (myRegex.regex(confirm, pattern)) {
                if (confirm.equals("Y")) {
                    System.out.print("Enter Name TradeMark "+nameTradeMark+" UPDATE---> ");
                    trademark.setName(scanner.nextLine());
                    String newName=trademark.getName();
                    ArrayList<Product> listDelete = new ArrayList<>();
                    for (Product p : managerProduct.getListProduct()) {
                        if (p.getTradeMark().getName().equals(nameTradeMark)) {
                            p.getTradeMark().setName(newName);
                        }
                    }
                } else if (confirm.equals("N")) {
                    System.err.println("                        Update Failed, Back MENU :");
                }
                check = true;
            } else {
                System.err.println("                          incorrect choice, please re-enter");
                System.out.println("\n");
            }
        } while (!check);

    }
    public void updateRole() {
        if (customer.getRole().getId() == 0) {
            System.out.println("                               Choice Customer\n");
            Customer customer = managerCustomer.findById(scanner, myRegex.getPatternNumber());
            if (customer != null) {
                System.out.println("\n                           Choice Role");
                Role role = managerRole.findById(scanner, myRegex.getPatternNumber());
                customer.setRole(role);
            }
        } else {
            System.err.println("                         Không Có Quyền Hạn");
        }


    }

    public void choiceTradeMark() {
        String pattern = "^[0-2]$";
        boolean check = false;
        String input;
        int choice = -1;
        do {
            do {
                System.out.println("MENU:");
                System.out.println("1. Display TradeMark Shoe");
                System.out.println("2. Display TradeMark TiVi");
                System.out.println("-----------------------------");
                System.out.println("0.  Back Login  : ");
                System.out.println("-----------------------------");
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
                    System.out.println("Show List TradeMark Shoe\n");
                    managerTrademark.display("Shoe");
                    break;
                case 2:
                    System.out.println("Show List TradeMarkTiVi\n");
                    managerTrademark.display("TiVi");
                    break;
            }
        } while (choice != 0);
    }


    public void menuCustomer() {
        String pattern = "^[0-3]$";
        boolean check = false;
        String input;
        int choice = -1;
        do {
            do {
                System.out.println("MENU:");
                System.out.println("1. Buy Product");
                System.out.println("2. Update Information");
                System.out.println("3. Purchase History");
                System.out.println("-------------------");
                System.out.println("0. Back Login  :");
                System.out.println("-------------------");
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

                    break;
                case 2:
                    managerCustomer.update(customer);
                    myFileBinary.outPutStream(myFileBinary.getPathCustomer(), managerCustomer.getListCustomer());
                    break;

            }
        } while (choice != 0);

    }
}
