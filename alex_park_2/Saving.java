
package alex_park_2;

/**
 *   Submitted by: Alex Yeji Park
 *
 *   Honor: I have completed this assignment on my own.
 *          In researching the assignment I got help/ideas from http://stackoverflow.com/ 
 *
 *   File name: Saving.java 
 *   
 *   Description: Saving class is inheriting from abstract Account class.
 *              This class creates a saving account with initial deposit and interest rate,
 *              implemented withdraw method and overridden deposit method from Account class. 
 *
 *   @author Alex Yeji Park
 */


public class Saving extends Account
{

    /////////////////////// FIELDS //////////////////////
    
    private final int MAX_FREE_WITHDRAW = 5;
    private int freeWithdraw = 2;
    private double interestRate;
    
    ////////////// CONSTRUCTORS /////////////
    
    /**
    * Constructor to create an saving instance with amount and interestRate
    * calls on the superclass constructor with the initial deposit.
    *
    * @param amount - initial deposit into account (> $0)
    * @param interestRate - interest rate of created saving account
    * @throws IllegalArgumentException - thrown when interest rate is not 0.5% - 3%
    */
    public Saving(double amount, double interestRate) throws IllegalArgumentException
    {
        super(amount);
        setInterestRate(interestRate);
    }
    
    ////////////////////////////// Methods ////////////////////////////
    
    
    public final void setInterestRate(double interestRate) throws IllegalArgumentException
    {
        // only if the interestRate is between 0.5 to 3.0, assign the value to this.interestRate
        if(interestRate >= 0.5 && interestRate <= 3.0)
        {
            this.interestRate = interestRate;
        }
        // unless throw an exception
        else
        {
            throw new IllegalArgumentException("[Error]The interest rate munt be between 0.5% to 3% incl.");
        }
    }

    
    public double getInterestRate() 
    {
        return interestRate;
    }
    
    
    // implement abstract withdraw method from Account class
    @Override
    protected void withdraw(double amount) throws IllegalArgumentException
    {
        // temporary balance before actually assigning the result of withdrawing to the new balance 
        double tmpBalance = balance;
        
        // if this saving account does not have free withdraw anymore, charge the withdraw fee
        if(freeWithdraw==0)
        {
            tmpBalance -= (amount + WITHDRAW_FEE);
        }
        else
        {
            tmpBalance -= amount;
        }
        
        // if the result of withdrawing is below 0 or the amount to withdraw is below 0, throw an exception
        if(tmpBalance < 0 || amount < 0)
        {
            throw new IllegalArgumentException("Transaction was not successful.");
        }
        
        // beacuase withdrawing 0 is not an exception, but still shouldn't affect the balance and freeWithdraw
        if(amount!=0)
        {
            // if this account has greater than 0 of free withdraw, decrease the number of it by 1
            if(freeWithdraw > 0)
            {
                freeWithdraw--;
            }
            // assign the computed temporary balance to the actual balance
            balance = tmpBalance;
        }
    }
    
    // override deposit method from super class   
    @Override
    public void deposit(double amount) throws IllegalArgumentException
    {
        // only if the amount is greater than 0, add it to the current balance
        if(amount > 0)
        {
            balance += amount;
            // if the current freeWithdraw is less than Max number(=5), increase it by 1
            if(freeWithdraw < MAX_FREE_WITHDRAW)
            {
                freeWithdraw++;
            }
        }
        // or throw an exception
        else
        {
            throw new IllegalArgumentException("[Error]The amount of deposit must be > 0");
        }
    }
    
    @Override
    public String toString()
    {
        return String.format("Saving Account created on %s has a current balance of $%.2f "
                + "and interest rate of %.1f%%", getDateCreated(), getBalance(), getInterestRate());
    }
}
