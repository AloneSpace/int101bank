package int101.banking;

import int101.base.Person;
import java.math.BigDecimal;

public class BankAccount {
    private static int nextAccountNo;
    private final int accountNo;
    private final String accountName;
    private final Person accountOwner;
    private AccountHistory accountHistory;
    private BigDecimal balance;

    public BankAccount(String accountName, Person accountOwner) {
        this.accountNo = nextAccountNo++;
        this.accountName = accountName;
        this.accountOwner = accountOwner;
        this.accountHistory = new AccountHistory(100);
        this.balance = new BigDecimal(0);
    }

    /* ToDo: 
       - call the above constructor to the the job.
       - use "firstname lastname" of accountOwner as the accountName;
    */
    public BankAccount(Person accountOwner) {
        this(accountOwner.getFirstname() + " " + accountOwner.getLastname(), accountOwner);
    }
    
    public BankAccount deposit(double amount, boolean isTransfer) {
        if (amount<=0) return null;
        BigDecimal amount2BigDecimal = new BigDecimal(amount);
        balance = balance.add(amount2BigDecimal);
        if(!isTransfer) accountHistory.append(new AccountTransaction(TransactionType.DEPOSIT, amount2BigDecimal));
        else accountHistory.append(new AccountTransaction(TransactionType.TRANSFER_IN, amount2BigDecimal));
        return this;
    }
    
    public BankAccount withdraw(double amount, boolean isTransfer) {
        if (amount<=0) return null;
        if (balance.doubleValue()<amount) return null;
        BigDecimal amount2BigDecimal = new BigDecimal(amount);
        balance = balance.subtract(new BigDecimal(amount));
        if(!isTransfer) accountHistory.append(new AccountTransaction(TransactionType.WITHDRAW, amount2BigDecimal));
        else accountHistory.append(new AccountTransaction(TransactionType.TRANSFER_OUT, amount2BigDecimal));
        return this;
    }
    
    /* ToDo:
       - try withdraw from this account first (call withdraw()); if fails, return null.
       - deposit to the other account (call deposit()); if fails, return null.
       - if everything is ok, return this (for method chaining).
    */
    public BankAccount transferTo(BankAccount to, double amount) {
        if(to == null) return null;
        if(withdraw(amount, true) == null) return null;
        to.deposit(amount, true);
        return this;
    }

    public Person getAccountOwner() { return accountOwner; }

    public AccountHistory getAccountHistory() {
        return accountHistory;
    }

    @Override
    public String toString() {
        return "BankAccount[" + accountNo + ":" + accountName + "=" + balance + ']';
    }
    
}
