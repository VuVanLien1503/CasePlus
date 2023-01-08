package main;

import manager.action.ManagerMenu;

import java.util.Scanner;

public class Main {
   static ManagerMenu managerMenu=new ManagerMenu();
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        managerMenu.begin(scanner);
    }
}
