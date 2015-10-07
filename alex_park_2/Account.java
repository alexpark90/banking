
package alex_park_2;
import java.util.Date;


/**
 *   Submitted by: Alex Yeji Park
 *
 *   Honor: I have completed this assignment on my own.
 *          In researching the assignment I got help/ideas from http://stackoverflow.com/ 
 *
 *   File name: Account.java 
 *
 *   Description: Account is an abstract class being a basic class for Checking and Saving class.
 *              This class has the information for balance, date created, and withdraw fee.
 *              Also, it has abstract withdraw method , concrete deposit method and so on.
 *
 *   @author Alex Yeji Park
 */


public abstract class Account 
{
    /////////////////////// FIELDS ////////////////////// 
    
    protected final float WITHDRAW_FEE = (float)2.00;   
    protected Date dateCreated;
    protected double balance;
    
    
    ////////////// CONSTRUCTORS /////////////
    /**
     * Account constructor to be called in children classes
     * 
     * @param initDeposit - initial deposit into account (> $0)
     * @throws IllegalArgumentException - thrown when deposit is less than $0
     */
    protected Account(double initDeposit) throws IllegalArgumentException
    {
        if(initDeposit <= 0)
        {
            throw new IllegalArgumentException("[Error]The amount of deposit must be > 0");  
        }
        this.balance = initDeposit;
        dateCreated = new Date();
    }
    // calls this class' another constructor with init deposit as $0 
    protected Account()
    {
        this(0.0);
    }
    
    
    ////////////////////////////// Methods ////////////////////////////
    
    //  withdraw method as abstract  
    protected abstract void withdraw(double amount);
    
    // concrete deposit method throwing Exception    
    protected void deposit(double amount) throws IllegalArgumentException 
    {
        // only if the amount is greater than 0, add it to the current balance
        if(amount > 0)
        {
            balance += amount;
        }
        // or throw an exception
        else
        {
            throw new IllegalArgumentException("[Error]The amount of deposit must be > 0");
        }
    }

    protected Date getDateCreated() 
    {
        return dateCreated;
    }

    protected double getBalance() 
    {
        return balance;
    }
}
