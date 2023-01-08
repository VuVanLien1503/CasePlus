package manager.model.ac;

import iinterface.ICrud;
import iinterface.ISort;
import manager.action.MyRegex;
import model.customer.AC.Customer;
import model.customer.Properties.Role;

import java.util.ArrayList;
import java.util.Scanner;

public class ManagerCustomer implements ICrud<Customer>, ISort<Customer> {
    Scanner scanner = new Scanner(System.in);
    MyRegex myRegex = new MyRegex();
    private int autoId;
    private ArrayList<Customer> listCustomer;

    public ManagerCustomer() {
        listCustomer = new ArrayList<>();
        if (listCustomer.size() > 0) {
            autoId = (listCustomer.get(listCustomer.size() - 1).getId()) + 1;
        } else {
            autoId = 1;
        }
    }

    public ArrayList<Customer> getListCustomer() {
        return listCustomer;
    }

    public void setListCustomer(ArrayList<Customer> listCustomer) {
        this.listCustomer = listCustomer;
    }
    @Override
    public Customer findById(Scanner scanner, String patternNumber) {
        Customer customer = null;
        int id = 0;
        String input;
        boolean checkRegex = false;
        if (listCustomer.isEmpty()) {
            System.out.println("                     No Product");
        } else {
            do {
                display();
                System.out.print("Enter  Id : ");
                input = scanner.nextLine();
                if (myRegex.regex(input, patternNumber)) {
                    checkRegex = true;
                    id = Integer.parseInt(input);
                } else {
                    System.err.println("Malformed ID");
                    System.out.println("\nId Contains Only Numbers\n");
                }
            }
            while (!checkRegex);
            if (checkIdToList(id, listCustomer)) {
                for (Customer c : listCustomer) {
                    if (c.getId() == id) {
                        customer = c;
                        customer.display();
                        break;
                    }
                }
            } else {
                System.err.println("                    Id Does Not Exist");
            }
        }
        return customer;
    }

    @Override
    public boolean checkIdToList(int id, ArrayList<Customer> list) {
        boolean check = false;
        for (Customer p :
                list) {
            if (p.getId() == id) {
                check = true;
                break;
            }
        }
        return check;
    }

    public void title() {
        System.out.printf("%-5s%-10s%-10s%-10s%-15s%-25s%s",
                "ID", "NAME", "AGE", "ADDRESS", "PHONE", "EMAIL", "PASSWORD\n");
        System.out.println("-----------------------------------------------------------------------------------");
    }


    @Override
    public void display() {
        title();
        for (Customer c : listCustomer) {
            if (!c.getRole().getName().equals("ADMIN")) {
                c.display();
            }
        }
    }

    @Override
    public Customer create(Scanner scanner) {
        boolean check;
        String name;
        String email;
        String passWord;
        System.out.println("\nCreate New Customer...!\n");
        System.out.println("name : (3-15) characters ");
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
        System.out.print("Enter Email : ");
        do {
            email = scanner.nextLine();
            if (myRegex.regex(email, myRegex.getPatternEmail())) {
                check = true;
            } else {
                System.err.println("Malformed Email");
                System.out.print("\nEnter Email : ");
                check = false;
            }
        } while (!check);
        check = true;
        do {
            System.out.print("Enter PassWord : ");
            passWord = scanner.nextLine();
            if (myRegex.regex(passWord, myRegex.getPatternPassWord())) {
                check = true;
            } else {
                System.err.println("Malformed PassWord");
                System.out.println("\nPassWord (3-15) characters");
                check = false;
            }
        } while (!check);
        Customer customer;
        if (listCustomer.isEmpty()) {
            Role role = new Role(0, "ADMIN");
            customer = new Customer(0, name, email, passWord, role);
            System.err.println("                                  Create Successful...! ");
            return customer;

        } else {
            customer = new Customer(autoId, name, email, passWord);
            System.err.println("                                  Create Successful...! ");
            return customer;
        }

    }

    @Override
    public void add(Customer customer) {
        listCustomer.add(customer);
        autoId++;
    }

    @Override
    public void update(Customer customer) {
        System.out.println("\n                                             Update Customer...!\n");
        title();
        customer.display();
        String name;
        int age;
        String address;
        String phone;
        String email;
        String passWord;
        boolean check;
        System.out.println("name : (3-15) characters ");
        do {
            System.out.println("UPDATE NAME : ");
            System.out.print(customer.getName() + " UPDATE --> ");
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
            System.out.print("UPDATE AGE ");
            System.out.print(customer.getAge() + " UPDATE --> ");
            age = Integer.parseInt(scanner.nextLine());
            if (myRegex.regex(String.valueOf(age), myRegex.getPatternNumber())) {
                check = true;
            } else {
                System.err.println("Malformed Size:");
                System.out.println("\nSize :contains only numbers\n ");
                check = false;
            }
        } while (!check);
        check = true;
        System.out.println("Address : (3-15) characters ");
        do {
            System.out.print("UPDATE ADDRESS : ");
            System.out.print(customer.getAddress() + " UPDATE --> ");
            address = scanner.nextLine();
            if (myRegex.regex(address, myRegex.getPatternName())) {
                check = true;
            } else {
                System.err.println("Malformed Name");
                System.out.println("\nAddress : (3-15) characters ");
                check = false;
            }
        } while (!check);
        check = true;
        System.out.print("Phone : (10) characters ");
        do {
            System.out.println("UPDATE PHONE : ");
            System.out.print(customer.getPhone() + " UPDATE --> ");
            phone = scanner.nextLine();
            if (myRegex.regex(phone, myRegex.getPatternPhone())) {
                check = true;
            } else {
                System.err.println("Malformed Name");
                System.out.println("\nPhone : (3-15) characters ");
                check = false;
            }
        } while (!check);
        check = true;
        System.out.println("EMail : (3-15) characters ");
        do {
            System.out.println("UPDATE EMAIL : ");
            System.out.print(customer.getEmail() + " UPDATE --> ");
            email = scanner.nextLine();
            if (myRegex.regex(email, myRegex.getPatternEmail())) {
                check = true;
            } else {
                System.err.println("Malformed Name");
                System.out.println("\nAddress : (3-15) characters ");
                check = false;
            }
        } while (!check);
        check = true;
        System.out.println("PassWord : (6-15) characters ");
        do {
            System.out.println("UPDATE PASSWORD : ");
            System.out.print(customer.getPassWord() + " UPDATE --> ");
            passWord = scanner.nextLine();
            if (myRegex.regex(passWord, myRegex.getPatternPassWord())) {
                check = true;
            } else {
                System.err.println("Malformed Name");
                System.out.println("\nAddress : (3-15) characters ");
                check = false;
            }
        } while (!check);
        check = true;
        customer.setName(name);
        customer.setAge(age);
        customer.setAddress(address);
        customer.setPhone(phone);
        customer.setEmail(email);
        customer.setPassWord(passWord);
        System.out.println("UPDATE SUCCESSFUL");
        title();
        customer.display();

    }

    @Override
    public void delete(Customer customer) {
        listCustomer.remove(customer);
        System.out.println("\n                                  Delate Successful...! ");
    }
}
