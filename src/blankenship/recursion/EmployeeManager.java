/*
 * COSC 2436 Programming Fundamentals III with Java
 * Java Recursion Assignment to count employees.
 * Create an Employee Class containing employee name and manager name.
 * Create an EmployeeManager class to read a file containing a list of
 * employee names and their managers name. Then write a method called
 * countEmployeesUnder(employeeName) to count the number of employees
 * report either directly or indirectly to employeeName. Display the 
 * results.
 */

package blankenship.recursion;

import java.io.*;
import java.util.*;
        
/**
 * EmployeeManager Class contains the main method for executing this 
 * program. it Also contains the a countEmployeesUnder and display
 * methods.
 * @author Karl Blankenship
 */
public class EmployeeManager {

    /**
     * The main method for program execution.
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        
        // Create an EmployeePortfolios object.
        EmployeeManager em = new EmployeeManager();
    
        // Create an employee list variable.
        List<Employee> employees;
        
        /*
            employeeData.txt is stored in main project file so that it can
            be accessed without a specific file path for simplicity.
        */
        // Load employee data.
        employees = em.loadEmployeePortfolios("employee.txt");
        em.display(employees);
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
     * @return employeeList A list of employee objects.
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
        line = br.readLine(); // Read first line of data after header.
        
        // Write all buffered lines into Employee Instances and write to List.
        while (line != null) {
            // Create an instance of Employee.
            Employee emp = new Employee();
            // Split out name and manager from line.
            String[] lineEM = line.split(",",-1); 
            // Set instance fields.
            emp.setName(lineEM[0]);
            emp.setManager(lineEM[1]);
            // Add the employee instance to the List.
            employeeList.add(emp);
            // Read next line of file.
            line = br.readLine();
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
