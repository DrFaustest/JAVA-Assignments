//main method in the project collects the account class, bank class and customer class so that they can work together.

// Path: src\main.java
 public class main {
     public static void main(String[] args) {

            Bank bank = new Bank();
    
            bank.addCustomer("John", "Doe", "1234 Main St", "123-456-7890");
            bank.addCustomer("Jane", "Doe", "1234 Main St", "123-456-7890");
    
            Account account = bank.searchCustomer("John", "Doe");
            account.deposit(1000);
            account.withdraw(500);
            System.out.println(account.calculateInterest(12));
        }

        //test the functionality of the program by calling the methods and manipulating the data

        public static void test() {
            Bank bank = new Bank();
    
            bank.addCustomer("John", "Doe", "1234 Main St", "123-456-7890");
            bank.addCustomer("Jane", "Doe", "1234 Main St", "123-456-7890");
    
            Account account = bank.searchCustomer("John", "Doe");
            account.deposit(1000);
            account.withdraw(500);
            System.out.println(account.calculateInterest(12));

        }
    }