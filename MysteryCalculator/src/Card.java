import java.util.ArrayList;

class Card {
    private int cardID;
    private int maxNum;
    private int numCards;
    private ArrayList<Integer> numbers = new ArrayList<>();

    public Card(int cardID, int maxNum, int numCards){
        this.cardID = cardID;
        this.maxNum = maxNum;
        this.numCards = numCards;
        calculate_numbers();
    }

    private void calculate_numbers() {
        for(int i = 1; i <= maxNum; i++){
            String temp_binary = Integer.toBinaryString(i); //converts loop index to binary string
            temp_binary = String.format("%1$" + numCards + "s", temp_binary).replace(' ', '0');
            if (temp_binary.charAt(cardID) == '1'){ //if the value is 1 in the place of the card then add the number to the card
                numbers.add(i); //adds number to the list of numbers in this card
            }
        }
    }

    public String print_card() {
        return ("Card " + (cardID+1) + "  :  " + numbers);
    }

    public int return_first() {
        return numbers.get(0); //returns the first number in the card
    }
}
