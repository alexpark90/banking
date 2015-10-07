
package alex_park_2;

/**
 *   Submitted by: Alex Yeji Park
 *   Date: Feb. 18. 2015
 * 
 *   Honor: I have completed this assignment on my own.
 *          In researching the assignment I got help/ideas from http://stackoverflow.com/ 
 *
 *   File name: Checking.java 
 *
 *   Description: Checking class is inheriting from abstract Account class.
 *              This class creates a checking account with initial deposit and overdraft amount,
 *              implemented withdraw method and inheriting some methods from Account class.
 *
 *   @author Alex Yeji Park
 */


public class Checking extends Account
{
    /////////////////////// FIELDS //////////////////////  
    
    private float overdraft;
    
    ////////////// CONSTRUCTORS /////////////
    
    /**
    * Constructor to create an checking instance with initDeposit and overdraft
    * calls on the superclass constructor with the initial deposit.
    *
    * @param initDeposit - initial deposit into account (> $0)
    * @param overdraft - amount that would be a debt when the account balance below $0
    * @throws IllegalArgumentException - thrown when interest rate is not $100 - $1000
    */ 
    public Checking(double initDeposit, double overdraft) throws IllegalArgumentException
    {
        super(initDeposit);
        setOverdraft(overdraft);
    }
    
    ////////////////////////////// Methods ////////////////////////////
    
    public final void setOverdraft(double overdraft) throws IllegalArgumentException
    {
        // only if overdraft is between 100 to 1000, assign the value to this.overdraft
        if(overdraft >= 100 && overdraft <= 1000)
        {
            this.overdraft = (float)overdraft;
        }
        // or throw an exception
        else
        {
            throw new IllegalArgumentException("[Error]Overdraft must be between $100 to $1000 incl.");
        }
    }
    
    
    public float getOverdraft() 
    {
        return overdraft;
    }
    
    
    // implement abstract method from Account class   
    @Override
    protected void withdraw(double amount) throws IllegalArgumentException
    {
        // if the amount is greater than or equal to 0 
        // and the total subtracting amount(amount+withdraw fee)  is less than or equal to (balace+overdraft) 
        if(amount >= 0 && (amount+WITHDRAW_FEE) <= (balance+overdraft))
        {
            this.balance -= amount;
            
            // charge the withdraw fee only if the result of withdrawing is below 0 and the amount is not 0
            if(balance < 0 && amount !=0)
            {
                balance -= WITHDRAW_FEE;
            }
        }
        // or throw an exception
        else
        {
            throw new IllegalArgumentException("Transaction was not successful.");
        }       
    }
    
    @Override
    public String toString()
    {
        return String.format("Checking Account created on %s has a current balance of $%.2f "
                + "and overdraft of $%.2f", getDateCreated(), getBalance(), getOverdraft());
    }
}
