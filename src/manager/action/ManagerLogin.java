package manager.action;


import manager.model.ac.ManagerCustomer;
import model.customer.AC.Customer;

import java.util.List;
import java.util.Scanner;

public class ManagerLogin {
    MyRandom myRandom = new MyRandom();
    MyRegex myRegex = new MyRegex();
    ManagerCustomer managerCustomer;

    public ManagerCustomer getManagerCustomer() {
        return managerCustomer;
    }

    public void setManagerCustomer(ManagerCustomer managerCustomer) {
        this.managerCustomer = managerCustomer;
    }

    public ManagerLogin(ManagerCustomer managerCustomer) {
        this.managerCustomer = managerCustomer;
    }

    public String[] login(Scanner scanner) {
        System.out.println("\n                              WElCOME TO LOGIN : \n");
        boolean check = false;
        String inputEmail;
        String inputPassWord;
        System.out.print("Enter Email : ");
        do {
            inputEmail = scanner.nextLine();
            if (myRegex.regex(inputEmail, myRegex.getPatternEmail())) {
                check = true;
            } else {
                System.err.print("Email format is incorrect\nPlease re-enter : ");
            }
        } while (!check);
        System.out.print("Enter PassWord : ");
        inputPassWord = scanner.nextLine();
        return new String[]{inputEmail, inputPassWord};
    }

    public boolean isAdmin(Customer customer) {
        boolean checkAdmin = false;
        try {
            if (customer.getRole().getName().equals("ADMIN") || customer.getRole().getName().equals("VIP-1")) {
                checkAdmin = true;
            }
        } catch (NullPointerException e) {
            checkAdmin = false;
        }

        return checkAdmin;
    }

    public Customer checkLogin(List<Customer> listCustomer, Scanner scanner) {
        String[] login = login(scanner);
        Customer customer = null;
        String outPut;
        boolean check = false;
        for (Customer c : listCustomer) {
            if (c.getEmail().equals(login[0])) {
                if (c.getPassWord().equals(login[1])) {
                    if (isAdmin(c)) {
                        outPut = "\n                                   Welcome Back ADMIN " + c.getName() + " ....!";
                    } else {
                        outPut = "\n                                    Welcome Back " + c.getName() + " ....!";
                    }
                    System.out.println(outPut);
                    customer = c;
                    check = true;
                    break;
                } else {
                    System.err.println("wrong password\n");
                    System.out.println("\nyou have 3 times enter");
                    System.out.println("------------------------");
                    int count = 3;
                    while (count > 0) {
                        System.out.println("Incorrect password : ");
                        System.out.print("Enter password " + count + " times : ");
                        login[1] = scanner.nextLine();
                        System.out.println("------------------------");
                        if (c.getPassWord().equals(login[1])) {
                            outPut = "                    Welcome Back " + c.getName() + " ....!";
                            System.out.println(outPut);
                            check = true;
                            return c;
                        } else {
                            count--;
                        }
                    }
                    System.out.println("You forgot your Password");
                    System.out.println("Press y to get it back");
                    System.out.println("Press n to return to Login");
                    System.out.print("Enter Choice:");
                    String choice = scanner.nextLine();
                    if (choice.equals("y")) {
                        passwordRetrieval(scanner);
                        return checkLogin(listCustomer, scanner);
                    } else if (choice.equals("n")) {
                        return checkLogin(listCustomer, scanner);
                    }

                }
            } else {
                check = false;
            }
        }
        if (!check) {
            System.err.println("\n                                  Login Unsuccessful\n");
            System.out.println("\n                                E-mail does not exist yet");
            System.out.println("                                     PLEASE SIGN UP ");
            System.out.println("                              -------------------------------");
        }
        return customer;
    }

    public void passwordRetrieval(Scanner scanner) {
        String inputEmail;
        boolean check = false;
        boolean checkEmail = false;
        do {
            System.out.println("Enter  Registered Email ");
            inputEmail = scanner.nextLine();
            if (myRegex.regex(inputEmail, myRegex.getPatternEmail())) {
                check = true;
            } else {
                System.err.print("Email format is incorrect\nPlease re-enter : ");
            }
        } while (!check);
        Customer customer = null;
        for (Customer c : managerCustomer.getListCustomer()) {
            if (c.getEmail().equals(inputEmail)) {
                checkEmail = true;
                customer = c;
            }
        }
        if (checkEmail) {
            String random = myRandom.randomAlphaNumeric();
            System.out.println("a code is sent email");
            System.out.println("your code is : \n" + random);
            System.out.print("Enter code :");
            String code = scanner.nextLine();
            if (random.equals(code)) {
                System.out.println("PassWord your :\n" + customer.getPassWord() + "\n");
            }

        } else {
            System.out.println("                                    Email does not exist\n");
        }
    }
}
