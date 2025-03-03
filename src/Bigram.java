/** Bigram Object that holds two characters as a pair
 * @author Santhosh Senthil, 115871889, R01
 */

public class Bigram {
    private char first;
    private char second;

    /**
     * Default Constructor
     */
    public Bigram(){}

    /**
     * Constructor for Bigram that takes input of both indexes of the pair
     * @param first first char in pair
     * @param second second char in pair
     */
    public Bigram(char first, char second){
        this.first=first;
        this.second=second;
    }

    /**
     * getter method for first char
     * @return first char
     */
    public char getFirst() {
        return first;
    }

    /**
     * setter method for first char
     * @param first sets first char
     */
    public void setFirst(char first) {
        this.first = first;
    }

    /**
     * getter method for second char
     * @return second char
     */
    public char getSecond() {
        return second;
    }

    /**
     * setter method for second char
     * @param second sets second char
     */
    public void setSecond(char second) {
        this.second = second;
    }

    /**
     * Overridden toString method
     * @return returns first and second as string pair
     */
    @Override
    public String toString() {
        return ""+first+second;
    }
}
