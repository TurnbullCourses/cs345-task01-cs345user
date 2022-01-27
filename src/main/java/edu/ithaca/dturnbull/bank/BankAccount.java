package edu.ithaca.dturnbull.bank;


public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }

    private static boolean hasDoubleSymbol(String fullStr){
        // using simple for-loop
        for (int i = 0; i < fullStr.length()-1; i++) {
            String s = fullStr.substring(i, i+2);
            if (s.matches("^[_.-]*$")){
                return true;
            }
        }
        return false;
    }

    private static boolean isPrefixValid(String prefix){
        if (prefix.length() == 0){
            return false;
        }
        boolean validFirstChar = Character.toString(prefix.charAt(0)).matches("^[a-zA-Z0-9]*$");
        boolean validAllChars = prefix.matches("^[a-zA-Z0-9_.-]*$");
        boolean validLastChar = Character.toString(prefix.charAt(prefix.length()-1)).matches("^[a-zA-Z0-9]*$");
        boolean validChars =  validAllChars && validFirstChar && validLastChar ;
        if (validChars ==false){
            return false;
        }

        if (hasDoubleSymbol(prefix) == true){
            return false;
        }
        return true;

    }

    private static boolean isDomainValid(String domain){
        if (domain.indexOf('.') == -1){
            return false;
        }

        if (domain.lastIndexOf('.') + 2 >= domain.length()){
            return false;
        }
        boolean validChars = domain.matches("^[a-zA-Z0-9.-]*$");
        if (validChars == false){
            return false;
        }

        if (hasDoubleSymbol(domain) == true){
            return false;
        }
        return true;

    }


    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1){      //no @ symbol
            return false;   
        }
        if (email.indexOf('@') != email.lastIndexOf('@')) { // two or more @ symbols
            return false;
        }
        
        String[] parts = email.split("@");
        if (parts.length != 2){
            return false;
        }

        if ( isPrefixValid(parts[0]) && isDomainValid(parts[1])){
            return true;
        } else {
            return false;
        }
        
      
    }

    public static void main(String[] args) {
        BankAccount.isEmailValid("a@b.co");
    }
}
