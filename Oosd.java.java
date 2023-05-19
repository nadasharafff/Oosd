package javaapplication16;

import java.util.ArrayList;
import java.util.Scanner;

class MenuItem {
    String item;
    String type;
    double price;
    
    public MenuItem(String item, String type, double price) {
        this.item = item;
        this.type = type;
        this.price = price;
    }
}

class CoffeeShop {
    String name;
    ArrayList<MenuItem> menu;
    ArrayList<String> orders;
    
    public CoffeeShop(String name, ArrayList<MenuItem> menu) {
        this.name = name;
        this.menu = menu;
        this.orders = new ArrayList<String>();
    }
    
    public String addOrder(String item) {
        for (MenuItem menuItem : menu) {
            if (menuItem.item.equalsIgnoreCase(item)) {
                orders.add(item);
                return "Order added!";
            }
        }
        return "This item is currently unavailable!";
    }
    
    public String fulfillOrder() {
        if (!orders.isEmpty()) {
            String item = orders.get(0);
            orders.remove(0);
            return "The " + item + " is ready!";
        } else {
            return "All orders have been fulfilled!";
        }
    }
    
    public ArrayList<String> listOrders() {
        return orders;
    }
    
    public double dueAmount() {
        double total = 0.0;
        for (String order : orders) {
            for (MenuItem menuItem : menu) {
                if (menuItem.item.equalsIgnoreCase(order)) {
                    total += menuItem.price;
                }
            }
        }
        return total;
    }
    
    public String cheapestItem() {
        double minPrice = Double.MAX_VALUE;
        String cheapest = "";
        for (MenuItem menuItem : menu) {
            if (menuItem.price < minPrice) {
                minPrice = menuItem.price;
                cheapest = menuItem.item;
            }
        }
        return cheapest;
    }
    
    public ArrayList<String> drinksOnly() {
        ArrayList<String> drinkList = new ArrayList<String>();
        for (MenuItem menuItem : menu) {
            if (menuItem.type.equalsIgnoreCase("drink")) {
                drinkList.add(menuItem.item);
            }
        }
        return drinkList;
    }
    
    public ArrayList<String> foodOnly() {
        ArrayList<String> foodList = new ArrayList<String>();
        for (MenuItem menuItem : menu) {
            if (menuItem.type.equalsIgnoreCase("food")) {
                foodList.add(menuItem.item);
            }
        }
        return foodList;
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
        menu.add(new MenuItem("Latte", "Drink", 3.50));
        menu.add(new MenuItem("Cappuccino", "Drink", 3.00));
        menu.add(new MenuItem("Espresso", "Drink", 2.50));
        menu.add(new MenuItem("Croissant", "Food", 2.00));
        menu.add(new MenuItem("Bagel", "Food", 2.50));
        
        CoffeeShop coffeeShop = new CoffeeShop("My Coffee Shop", menu);
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Enter your order (or 'exit' to quit): ");
            String order = scanner.nextLine();
            if (order.equalsIgnoreCase("exit")) {
                break;
            }
            String response = coffeeShop.addOrder(order);
            System.out.println(response);
        }
        
        System.out.println(coffeeShop.listOrders());
        
        System.out.println("Total due: " + coffeeShop.dueAmount());
        
        System.out.println("Cheapest item: " + coffeeShop.cheapestItem());
        
       
    }
}