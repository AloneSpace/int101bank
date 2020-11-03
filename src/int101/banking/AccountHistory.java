package int101.banking;

import java.util.Arrays;

public class AccountHistory {
    private AccountTransaction history[];
    private int count;

    public AccountHistory(int size) {
        history = new AccountTransaction[size>0 ? size :100];
    }
    
    public AccountHistory append(AccountTransaction trx) {
        if (count < history.length) {
            history[count++] = trx;
            return this;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < count; i++) {
            stringBuilder.append(history[i]).append("\n");
        }
        return stringBuilder.toString();
    }
}
