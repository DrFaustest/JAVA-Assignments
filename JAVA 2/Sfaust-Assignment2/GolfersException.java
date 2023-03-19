    //extend from the Exceptions class 
    //have an empty default constructor 
    //have a constructor that takes on a String as parameter and sends it to the super class
    /*Lastly, you now need to modify your method definition at the top so that they "throws NEWEXCEPTIONHERE". Then you will need to go to the main and adjust the switch statement so that you use a try/catch and simply print out the getMessage() of each of your custom exceptions. */


public class GolfersException extends Exception {
    public GolfersException() {
        super();
    }
    public GolfersException(String message) {
        super(message);
    }
}




