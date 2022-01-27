package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com")); //valid

        // must have exactly one @ character
        assertFalse(BankAccount.isEmailValid("")); //empty string
        assertFalse(BankAccount.isEmailValid("a@b@c.com")); //2 or more @symbols

        //prefix conditions
        assertTrue(BankAccount.isEmailValid("abc-d@c.com"));
        assertFalse(BankAccount.isEmailValid("abc-@c.com")); // does not have domain in email
        assertFalse(BankAccount.isEmailValid("abc-@c.com")); // ends with dash
        assertFalse(BankAccount.isEmailValid("abc.-d@c.com")); //two non-alpha numeric in a row
        assertFalse(BankAccount.isEmailValid("abc#d@c.com")); //illegal character

        //domain
        assertTrue(BankAccount.isEmailValid("abc@c-d.com"));
        assertFalse(BankAccount.isEmailValid("abc@c.c")); //domain too short
        assertFalse(BankAccount.isEmailValid("abc@c_d.com")); // invalid character
        assertFalse(BankAccount.isEmailValid("abc@c..com")); //two periods in a row
        assertFalse(BankAccount.isEmailValid("abc@ccom")); //no period
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));

      
    }

}