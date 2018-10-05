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

    // Create an employee list variable.
    public static List<Employee> employees;
    
    // This is the count variable for counting the number of reports.
    public static int count;
    
    
    /**
     * The main method for program execution.
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        
        // Create an EmployeePortfolios object.
        EmployeeManager em = new EmployeeManager();
    
        /*************************************************************
            employee.txt is stored in main project file so that it can
            be accessed without a specific file path for simplicity.
        *************************************************************/
        // Load employee data from text file.
        employees = em.loadEmployeePortfolios("employee.txt");
        
        // Display the list of Employee Instances.
        em.display(employees);
        
        // Add a line of separation between file contents and employee counts.
        System.out.println("\nDirect and Indirect Reports:"); 
        
        // Count and display the number of direct and indirect
        // reports for each employee.
        for (Employee name : employees) {
            count = 0; // Recursive exit condition if no reports are found.
            count = em.countEmployeesUnder(name.getName());
            System.out.println(name.getName() + " manages " + 
                                count + " employee(s).");
        }
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
        String line = br.readLine(); // Reads in file header which isn't used.
        line = br.readLine(); // Read first line of data after header.
        
        // Write all buffered lines into Employee Instances and write to List.
        while (line != null) {
            
            // Create an instance of Employee.
            Employee emp = new Employee();
            
            // Split out name and manager from line.
            // -1 is needed since the Veronica line has no second name
            // after the comma in the text file.
            String[] lineEM = line.split(",",-1); 
            
            // Set instance fields using array created above.
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
     * The countEmployeesUnder method will use recursion to count the number
     * of direct and indirect employees under the input manager.
     * @param employeeName
     * @return The number of direct and indirect employees.
     */
    private int countEmployeesUnder(String employeeName) {
               
        for (Employee employee : employees) {
            if (employee.getManager().equals(employeeName))
                count = 1 + countEmployeesUnder(employee.getName());
        }
        return count; // Returns the preset 0 if no reports are found.
    }
}
