
package alex_park_2;

import java.util.Scanner;

/**
 *   Submitted by: Alex Yeji Park
 *
 *   Honor: I have completed this assignment on my own.
 *          In researching the assignment I got help/ideas from http://stackoverflow.com/ 
 *
 *   File name: TestAccount.java 
 *
 *   Description: This is the main class to test other classes in the package, 
 *              which are Checking and Saving and create loops asking a user what type of account to make 
 *              and what type of transaction to be done. This class has static variables and static methods  
 *              to take user inputs, validate inputs, control the stream of the loop, and call instance method of Account,  
 *
 *   @author Alex Yeji Park
 */


public class TestAccount 
{
    /////////////////////// FIELDS //////////////////////
    
    // constant to check user inputs 
    final static String NUMBER_PATTERN = "[-+]?\\d+(\\.\\d+)?";
    
    // to control the loops
    static String option = null;
    static String innerOption = null;
    
    // declare an Account type variable
    static Account account;
    
    // create a Scanner
    static Scanner scan = new Scanner(System.in);
    
    // Main method containing console based program to create
    // a new savings or chequing account and to take actions regarding these accounts.
    public static void main(String[] args) 
    {
        // outside loop to ask the action 
        //(1 for saving, 2 for checkng, 0 for exiting the program)    
        do 
        {
            System.out.print("\nWhat type of account do you want to create?\n"
                    + "1 for Savings; 2 for Checking (0 to quit): ");
            
            // take the user input as the option. 
            // I used next() instead of nextInt() just in case the user enter non-integer values.  
            option = scan.next();
            
            switch (option)
            {
                // when the user select 0 to quit the program
                case "0":
                    scan.close();
                    System.out.println("Thank you for using. Good Bye!");
                    break;
                
                // when the user select 1 to create a saving account    
                case "1": 
                    {
                        // take user inputs for deposit and rate
                        // assign them to temporary vars to validate them
                        System.out.print("Enter initial deposit: ");
                        String tmp1 = scan.next();
                        System.out.print("Enter interest rate: ");
                        String tmp2 = scan.next();
                        
                        // check if the user inputs are numeric values or not
                        if(tmp1.matches(NUMBER_PATTERN) && tmp2.matches(NUMBER_PATTERN))
                        {
                            double amount = Double.parseDouble(tmp1);
                            double rate = Double.parseDouble(tmp2);
                            
                            // try to create a saving account using user inputs
                            try
                            {
                                account = new Saving(amount, rate);
                                System.out.println(account);
                                
                                // call innerLoop to ask the type of transaction that the user wants
                                invokeTransactionLoop();
                            }
                            catch (IllegalArgumentException e)
                            {
                                System.out.println(e.getMessage());
                            }
                        }
                        // when the user input is not the numeric value
                        else
                        {
                            System.out.println("[Error] Invalid Input. Plase Enter Numeric Value.");
                        }       break;
                    }
                
                // when user select 2 to create a checking account
                case "2":
                    {
                        // take user inputs for deposit and overdraf
                        // assign them to temporary vars to validate them
                        System.out.print("Enter initial deposit: ");
                        String tmp1 = scan.next();
                        System.out.print("Enter overdraft amount: ");
                        String tmp2 = scan.next();
                        
                        // check if the user inputs are numeric values or not
                        if(tmp1.matches(NUMBER_PATTERN) && tmp2.matches(NUMBER_PATTERN))
                        {
                            double amount = Double.parseDouble(tmp1);
                            double overdraft = Double.parseDouble(tmp2);
                            
                            // try to create a checking  account using user inputs
                            try
                            {
                                account = new Checking(amount, overdraft);
                                System.out.println(account);
                                
                                // call innerLoop to ask the type of transaction that the user wants
                                invokeTransactionLoop();
                            }
                            catch (IllegalArgumentException e)
                            {
                                System.out.println(e.getMessage());
                            }
                        }
                        // if the user input is not the numeric value
                        else
                        {
                            System.out.println("[Error] Invalid Input. Plase Enter Numeric Value.");
                        }       break;
                    }
                
                // when the user entered the value except 1, 2, or 0
                default:
                    System.out.println("[Error] Invalid Input. Please Enter 1, 2 or 0");
                    break;
            }
            
        }while(!option.equals("0"));
    }
    
    ///////////////////////// HELPER METHODS /////////////////////////
    
    /**
     * invokeTransactionLoop Static method 
     * to invoke the inner loop to ask desired transaction
     * (withdraw , deposit, or quit) multiple times
     * 
     * @param 
     * @return 
     */
    private static void invokeTransactionLoop()
    {
        do 
        {
            System.out.print("\nWithdrawal (1) or deposit (2) (0 to quit): ");
            // take the user input for the option. 
            innerOption = scan.next();

            // when the user select 1 or 2 to withdraw or deposit
            if(innerOption.equals("1") || innerOption.equals("2"))
            {
                System.out.print("Enter transaction amount: ");
                String tmp = scan.next();
        
                // check if the user input is numeric value or not
                if(tmp.matches(NUMBER_PATTERN))
                {
                    double amount = Double.parseDouble(tmp);

                    // try to call the withdraw or deposit method with the amount the user entered
                    try 
                    {
                        if(innerOption.equals("1"))
                        {
                            account.withdraw(amount);
                        }
                        else if(innerOption.equals("2"))
                        {
                            account.deposit(amount);
                        }
                        System.out.println("Transaction was successful.\n" + account);
                    }
                    catch(IllegalArgumentException e) 
                    {
                        System.out.println(e.getMessage() + "\n" + account);
                    }
                }
                // when the user entered non-numeric values for the amount
                else
                {
                    System.out.println("[Error]Plase Enter Numeric Value.");
                }
            }
            // when the user select 0 to quit
            else if(innerOption.equals("0"))
            {
                break;
            }
            // when the user entered the input other than 1,2, or 0
            else
            {
                System.out.println("[Error] Invalid Input. Please Enter 1, 2 or 0");
            }            
        }while(!innerOption.equals("0"));       
    }  
}