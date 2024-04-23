package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Retired {
    private String name;
    private String employeeCode;
    private String position;
    private String status;

    // Constructor
    public Retired(String name, String employeeCode, String position, String status) {
        this.name = name;
        this.employeeCode = employeeCode;
        this.position = position;
        this.status = status;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public String getPosition() {
        return position;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                ", Employee Code: " + employeeCode +
                ", Position: " + position +
                ", Status: " + status;
    }
}

public class Programm2 extends Nomin {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        List<Darkan> darkanEmployees = new ArrayList<>();
        List<Retired> retiredEmployees = new ArrayList<>();

        try {
            File file = new File("C:\\Users\\user\\Downloads\\ajilchid.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    String employeeCode = parts[1];
                    String position = parts[2];
                    String status = parts[3];

                    
                    Employee employee = new Employee(name, employeeCode, position, status);
                    employees.add(employee);
                } else {
                    System.out.println("Invalid data: " + line);
                }
            }
            scanner.close();

            
            System.out.println("Бүх ажилтаны мэдээлэл:");
            printEmployeeInformation(employees);

            
            for (Employee employee : employees) {
                darkanEmployees.add(new Darkan(employee.getName(), employee.getEmployeeCode(), employee.getPosition(), employee.getStatus()));
            }

            
            for (Darkan darkanEmployee : darkanEmployees) {
                if (darkanEmployee.getStatus().equalsIgnoreCase("тэтгэвэрт гарсан")) {
                    Retired retiredEmployee = new Retired(darkanEmployee.getName(), darkanEmployee.getEmployeeCode(), darkanEmployee.getPosition(), darkanEmployee.getStatus());
                    retiredEmployees.add(retiredEmployee);
                    
                    
            
                }
            }
            System.out.println("Дарханы нөөц сервер лүү шилжсэн бүх ажилтануудын мэдээлэл");
            printDarkanInformation(darkanEmployees);
            darkanEmployees.removeIf(darkanEmployee -> darkanEmployee.getStatus().equalsIgnoreCase("тэтгэвэрт гарсан"));

            
            employees.clear();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
       
        

        Scanner inputScanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Цэс:");
            System.out.println("1. Номин ажилчидын мэдээлэл");
            System.out.println("2. Дархан серверт хадгалагдсан ажилчдын мэдээлэл");
        
            System.out.println("3. Гарах");
            System.out.print("Сонголтоо оруулна уу: ");
            choice = inputScanner.nextInt();
            switch (choice) {
                case 1:
                    printEmployeeInformation(employees);
                    break;
                case 2:
                    printDarkanInformation(darkanEmployees);
                    printretiredEmployees(retiredEmployees);
                    break;
                
                case 3:
                	System.out.println("Системээс гарлаа.....");
                    break;
                default:
                    System.out.println("Буруу сонголт! Зөв сонголт оруулна уу.");
                    break;
            }
        } while (choice != 3);
    }

    private static void printEmployeeInformation(List<? extends Employee> employees) {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-15s | %-20s | %-10s |\n", "Нэр", "Ажилтаны код", "Мэргэжил", "Төлөв");
        System.out.println("---------------------------------------------------------------------------------");
        for (Employee employee : employees) {
            System.out.printf("| %-20s | %-15s | %-20s | %-10s |\n", employee.getName(), employee.getEmployeeCode(), employee.getPosition(), employee.getStatus());
        }
        System.out.println("---------------------------------------------------------------------------------");
    }

    private static void printDarkanInformation(List<Darkan> darkanEmployees) {
    	System.out.println("|Энгийн ажилчид|");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-15s | %-20s | %-10s |\n", "Нэр", "Ажилтаны код", "Мэргэжил", "Төлөв");
        System.out.println("---------------------------------------------------------------------------------");
        for (Darkan darkanEmployee : darkanEmployees) {
            System.out.printf("| %-20s | %-15s | %-20s | %-10s |\n", darkanEmployee.getName(), darkanEmployee.getEmployeeCode(), darkanEmployee.getPosition(), darkanEmployee.getStatus());
        }
        System.out.println("---------------------------------------------------------------------------------");
    }
    private static void printretiredEmployees(List<Retired> RetiredEmployees) {
    	 System.out.println("|Тэтгэвэрт гарсан ажилчидын мэдээлэл|");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-15s | %-20s | %-10s |\n", "Нэр", "Ажилтаны код", "Мэргэжил", "Төлөв");
        System.out.println("---------------------------------------------------------------------------------");
        for (Retired retiredEmployee : RetiredEmployees) {
            System.out.printf("| %-20s | %-15s | %-20s | %-10s |\n", retiredEmployee.getName(), retiredEmployee.getEmployeeCode(), retiredEmployee.getPosition(), retiredEmployee.getStatus());
        }
        System.out.println("---------------------------------------------------------------------------------");
    }
}

