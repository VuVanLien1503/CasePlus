package manager.model.properties;


import manager.action.MyRegex;
import model.customer.AC.Customer;
import model.customer.Properties.Role;

import java.util.ArrayList;
import java.util.Scanner;

public class ManagerRole {
    private ArrayList<Role> listRole;
    MyRegex myRegex = new MyRegex();

    public ManagerRole() {
        listRole = new ArrayList<>();
//        listRole.add(new Role(0, "ADMIN"));
        listRole.add(new Role(1, "USER"));
        listRole.add(new Role(2, "VIP-1"));
        listRole.add(new Role(3, "VIP-2"));

    }

    public ArrayList<Role> getListRole() {
        return listRole;
    }

    public void setListRole(ArrayList<Role> listRole) {
        this.listRole = listRole;
    }

    public Role findById(Scanner scanner, String patternNumber) {
        Role role = null;
        int id = -1;
        String input;
        boolean checkRegex = false;
        if (listRole.isEmpty()) {
            System.out.println("                     No Role");
        } else {
            do {
                display();
                System.out.print("Enter Id : ");
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
            if (checkIdToList(id, listRole)) {
                for (Role r : listRole) {
                    if (r.getId() == id && id != 0) {
                        role = r;
                        role.display();
                        break;
                    }
                }
            } else {
                System.err.println("                    Id Does Not Exist");
            }
        }
        return role;
    }

    public boolean checkIdToList(int id, ArrayList<Role> list) {
        boolean check = false;
        for (Role role :
                list) {
            if (role.getId() == id) {
                check = true;
                break;
            }
        }
        return check;
    }

    public void title() {
        System.out.printf("%-5s%s",
                "ID", "NAME\n");
        System.out.println("------------");
    }

    public void display() {
        title();
        for (Role r :
                listRole) {
            if (r.getId() != 0) {
                r.display();
            }
        }
    }


}
