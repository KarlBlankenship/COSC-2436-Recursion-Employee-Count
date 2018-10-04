/*
 * COSC 2436 Programming Fundamentals III with Java
 * Java Recap Assignment 1
 * Create an Employee Class containing employee name and manager name.
 * Create an EmployeePortfolios class to read a file containing a list of
 * employee names and their managers name. Then write to methods to sort the
 * employee objects by name and by manager name and display the results.
 */

package blankenship.recursion;

import java.io.*;
import java.util.*;
        
/**
 * EmployeePortfolios Class containing the main class for executing this 
 * program. Also contains the NameComparator and Manager Comparator classes
 * as well and the methods for sorting by name, sorting by manager and
 * displaying the results.
 * @author Karl Blankenship
 */
public class EmployeeManager {

    /**
     * Main method for program execution.
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        
        // Create an EmployeePortfolios object.
        EmployeeManager ef = new EmployeeManager();
    
        // Create an employee list variable.
        List<Employee> employees;
        
        /*
            employeeData.txt is stored in main project file so that it can
            be accessed without a specific file path for simplicity.
        */
        // Load employee data.
        employees = ef.loadEmployeePortfolios("employeeData.txt");
        
        // Display unsorted employee instances
        //System.out.println("Unsorted list of employees.");
        //ef.display(employees);
        
        // Display list of employees sorted by name.
        //System.out.println("List of employees sorted by name.");
        //ef.display(ef.sortByEmployeeName(employees));
        
        // Display list of employees sorted by manager.
        //System.out.println("List of employees sorted by manager.");
        //ef.display(ef.sortByManagerName(employees));
    }
    
    /**
     * loadEmployeePortfolios Method will read the text file, create 
     * employee instances, write text data to instance fields and add
     * instances to the output list.
     * @param filename
     * @return employeeList
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private List<Employee> loadEmployeePortfolios(String filename) 
            throws FileNotFoundException, IOException {
        
        // Create List to hold Employee objects and for return.
        List<Employee> employeeList = new ArrayList();
        
        // Create FileReader object and pass in file.
        FileReader fr = new FileReader(filename);
        
        // Read file into a buffer.
        BufferedReader br = new BufferedReader(fr);
        
        // Create String var to hold each buffered line and burn file header.
        String line = br.readLine();    // Reads in file header.
        System.out.println("Header: " + line);
        line = br.readLine(); // Read first line of data after header.
        System.out.println("First line: " + line);
        
        // Write all buffered lines into Employee Instances and write to List.
        while (line != null) {
            // Create an instance of Employee.
            Employee em = new Employee();
            // Split out name and manager from line.
            String[] lineEM = line.split(","); 
            // Set instance fields.
            System.out.println("lineEM[0]: " +lineEM[0]);
            System.out.println("lineEM[1]: " +lineEM[1]);
            //em.setName(lineEM[0]);
            //em.setManager(lineEM[1]);
            // Add the employee instance to the List.
            employeeList.add(em);
            // Read next line of file.
            line = br.readLine();
            System.out.println("next line: " + line);
        }
        
        return employeeList; // Return employee list.
    }
    
    /**
     * display Method will accept a list of employees and display the list to
     * the terminal.
     * @param inputList 
     */
    private void display(List<Employee> inputList) {
        
        // Print output header.
        System.out.println("Employee\t" + "Manager");
        // Call each object in the employee list and print fields.
        for (Employee employee : inputList) {    
            System.out.println(employee.getName() + "\t\t" +
                    employee.getManager());
        }
    }
    
    /**
     * sortByEmployeeName Method will accept a List of Employee objects and 
     * return the list sorted alphabetically by employee name utilizing the
     * NameComparator Class as defined below.
     * @param inputEmployee
     * @return 
     */
    private List<Employee> sortByEmployeeName(List<Employee> inputEmployee) {
        Collections.sort(inputEmployee, new NameComparator());
        return inputEmployee;
    }
    
    /**
     * sortByManagerName Method will accept a List of Employee objects and 
     * return the list sorted alphabetically by employees manager utilizing
     * the ManagerComparator Class as defined below.
     * @param inputEmployee
     * @return 
     */
    private List<Employee> sortByManagerName(List<Employee> inputEmployee) {
        Collections.sort(inputEmployee, new ManagerComparator());
        return inputEmployee;
    }

    /**
     * Name Comparator class implements the Comparator class for Employee
     * objects and contains an override method to compare objects.
     */
    private class NameComparator implements Comparator<Employee> {

        @Override
        public int compare(Employee e1, Employee e2) {
            String name1 = e1.getName();
            String name2 = e2.getName();
            return name1.compareTo(name2);
        }
    }

    /**
     * Name Comparator class implements the Comparator class for Employee
     * objects and contains an override method to compare objects.
     */
    private class ManagerComparator implements Comparator<Employee> {

        @Override
        public int compare(Employee e1, Employee e2) {
            String name1 = e1.getManager();
            String name2 = e2.getManager();
            return name1.compareTo(name2);
        }
    }
}
