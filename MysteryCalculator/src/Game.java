import java.util.ArrayList;

class Game {
    private int maxNum;
    private int cardNum;
    private Card[] cards;
    private ArrayList<Integer> cards_with_x = new ArrayList<>();
    private int gameScore;

    public Game(int MaxNum) {
        this.maxNum = MaxNum; //assigns maxNum passed in from the menu selection
        this.cardNum = (int) (Math.log(maxNum + 1) / Math.log(2)); //calculates the number of cards needed for this max number based on binary principals
        generate_cards(); //creates the cards
    }
    public void generate_cards() {
        //creates the array of cards
        cards = new Card[cardNum];
        for (int i = 0; i < cardNum; i++) {
            cards[i] = new Card(i, maxNum, cardNum); //uses card class to calulate the numbers on each card
        }
    }

    public void get_cards_with_x(ArrayList<Integer> cards) {
        cards_with_x = cards;
        calculate_x();
    }

    public void calculate_x() {
        int x = 0;
        //adds together the first number from each card selected: this will be the users number
        for (int cardnum : cards_with_x) {
            x += cards[cardnum - 1].return_first();
        }
        gameScore = x;
    }

    public int get_game_score(){
        return gameScore;
    }

    public int get_card_num(){
        return cardNum;
    }

    public String get_numbers(int cardNumber){
        return cards[cardNumber].print_card();
    }
}
