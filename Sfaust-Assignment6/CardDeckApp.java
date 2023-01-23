/*Name: Scott Faust
 * Class: 22_WI_INFO_1521_WW
 * Date: 1/17/2023
 * Resources: https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/ (ShuffleDeck method)
 *            Class lecture videos
 * 
 * Description: This program creates a deck of cards, shuffles them, and then deals a hand of cards. 
 */
 

public class CardDeckApp {

    public static void main(String[] args) {
        System.out.println("DECK");
        String[] deck = getDeck();
        displayCards(deck);

        System.out.println("SHUFFLED DECK");
        shuffleDeck(deck);
        displayCards(deck);

        int count = 2;
        System.out.println("HAND OF " + count + " CARDS");
        String[] hand = dealCards(deck, count);
        displayCards(hand);
    }

    private static String[] getDeck() {
        String[] deck = new String[52];
        // add code that creates deck here
        String suits[] = {"Hearts", "Diamonds", "Spades", "Clubs"};
        String ranks[] = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                deck[i * ranks.length + j] = ranks[j] + " of " + suits[i];
            }
        }

        return deck;
    }

    private static void displayCards(String[] cards) {
        // add code that displays cards here
        String deck = "|";
        for (int i = 0; i < cards.length; i++) {
            deck += cards[i] + "|";
        }
        System.out.println(deck);
    }

    private static void shuffleDeck(String[] deck) { // Fisher-Yates shuffle algorithm (https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/)
        for (int i = deck.length - 1; i > 0; i--) { // This starts at the end of the deck and works its way to the beginning eliminating the need to shuffle the cards that have already been shuffled
            int randomIndex = (int) (Math.random() * (i + 1)); // I first used the other method, but I was getting the same two cards every time
            String temp = deck[i];
            deck[i] = deck[randomIndex];
            deck[randomIndex] = temp;
        }
    }
    

    private static String[] dealCards(String[] deck, int count) {
        String[] hand = new String[count];
        // add code that deals cards here
        for (int i = 0; i < count; i++) {
            hand[i] = deck[i];
        }
        return hand;
    }

}


