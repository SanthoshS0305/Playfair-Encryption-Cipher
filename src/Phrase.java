/** Wrapped queue of Bigrams that gets decrypted or encrypted using Playfair Encryption
 * @author Santhosh Senthil, 115871889, R01
 */
import java.util.LinkedList;

public class Phrase extends LinkedList implements Queue  {
    private LinkedList<Bigram> q = new LinkedList<Bigram>();

    /**
     * Default constructor
     */
    public Phrase(){}

    /**
     * Builds phrase object from input string
     * @param s string that acts as input to get converted into phrase
     * @return Phrase object that reformats the string into a queue of Bigrams
     */
    public static Phrase buildPhraseFromString(String s){
        Phrase newPhrase = new Phrase();
        char[] temp=s.replaceAll("[^a-zA-Z]", "").toUpperCase().toCharArray();
        for (int i=0;i<(temp.length%2==0?temp.length:temp.length-1);i+=2){
            newPhrase.enqueue(new Bigram(temp[i], temp[i+1]));
        }
        if (temp.length%2!=0)
            newPhrase.enqueue(new Bigram(temp[temp.length-1],'X' ));
        return newPhrase;
    }

    /**
     * Encrypts the Phrase using Playfair Encryption
     * @param key key from which the Encryption is based
     * @return Encrypted phrase object
     */
    public Phrase encrypt(KeyTable key){
        if (key==null)
            throw new IllegalArgumentException();
        Phrase temp = new Phrase();
        Phrase unique = this.consecutive();
        while (!unique.isEmpty()){
            Bigram b = unique.dequeue();
            if (key.findRow(b.getFirst())==key.findRow(b.getSecond())){
                b.setFirst(key.getKeyTable()[key.findRow(b.getFirst())][(key.findCol(b.getFirst())+1)%5]);
                b.setSecond(key.getKeyTable()[key.findRow(b.getSecond())][(key.findCol(b.getSecond())+1)%5]);
            }
            if (key.findCol(b.getFirst())==key.findCol(b.getSecond())){
                b.setFirst(key.getKeyTable()[(key.findRow(b.getFirst())+1)%5][key.findCol(b.getFirst())]);
                b.setSecond(key.getKeyTable()[(key.findRow(b.getSecond())+1)%5][key.findCol(b.getSecond())]);
            }
            if ((key.findCol(b.getFirst())!=key.findCol(b.getSecond()))&&(key.findRow(b.getFirst())!=key.findRow(b.getSecond()))){
                char first=b.getFirst();
                b.setFirst(key.getKeyTable()[key.findRow(b.getFirst())][key.findCol(b.getSecond())]);
                b.setSecond(key.getKeyTable()[key.findRow(b.getSecond())][key.findCol(first)]);
            }
            temp.enqueue(b);
        }
        return temp;
    }

    /**
     * helper method that places an 'X' between consecutive and identical letter,
     * and at the end of a phrase if the length is odd, according to Playfair Encryption specifications
     * @return Modified Phrase object that meets above specifications
     */
    private Phrase consecutive(){
        StringBuilder s = new StringBuilder();
        s.append(this.toString().charAt(0));
        for (char i : this.toString().replace(" ","").substring(1).toCharArray()) {
            if (s.charAt(s.length() - 1) == i)
                s.append('X');
            s.append(i);
        }
        return buildPhraseFromString(s.toString());
    }

    /**
     * Decrypts a Phrase that has been encrypted using Playfair Encryption
     * @param key key from which encryption was derived, and using which decryption will occur
     * @return Decrypted phrase
     */
    public Phrase decrypt(KeyTable key){
        if (key==null)
            throw new IllegalArgumentException();
        Phrase temp = new Phrase();
        while (!this.isEmpty()){
            Bigram b = this.dequeue();
            if (key.findRow(b.getFirst())==key.findRow(b.getSecond())){
                b.setFirst(key.getKeyTable()[key.findRow(b.getFirst())][(key.findCol(b.getFirst())<=0)?4:(key.findCol(b.getFirst())-1)]);
                b.setSecond(key.getKeyTable()[key.findRow(b.getSecond())][(key.findCol(b.getSecond())<=0)?4:(key.findCol(b.getSecond())-1)]);
            }
            if (key.findCol(b.getFirst())==key.findCol(b.getSecond())){
                b.setFirst(key.getKeyTable()[(key.findRow(b.getFirst())<=0)?4:(key.findRow(b.getFirst())-1)][key.findCol(b.getFirst())]);
                b.setSecond(key.getKeyTable()[(key.findRow(b.getSecond())<=0)?4:(key.findRow(b.getSecond())-1)][key.findCol(b.getSecond())]);
            }
            if ((key.findCol(b.getFirst())!=key.findCol(b.getSecond()))&&(key.findRow(b.getFirst())!=key.findRow(b.getSecond()))){
                char first=b.getFirst();
                b.setFirst(key.getKeyTable()[key.findRow(b.getFirst())][key.findCol(b.getSecond())]);
                b.setSecond(key.getKeyTable()[key.findRow(b.getSecond())][key.findCol(first)]);
            }
            temp.enqueue(b);
        }
        return temp;
    }

    /**
     * Overridden toString method
     * @return Formatted Phrase object as a String
     */
    public String toString(){
        StringBuilder s = new StringBuilder();
        for (int i=0;i<this.size();i++){
                s.append(this.get(i).toString());
                if (i!=this.size()-1)
                    s.append(" ");
        }
        return s.toString();
    }

    /**
     * Method from Custom Queue interface: adds a Bigram onto the rear of Phrase
     * @param b Bigram to be added to end of Queue
     */
    @Override
    public void enqueue(Bigram b) {
        this.add(b);
    }

    /**
     * Method from Custom Queue interface: removes a Bigram onto the front of Phrase
     * @return removed Bigram
     */
    @Override
    public Bigram dequeue() {
        return (Bigram) this.removeFirst();
    }

    /**
     * Method from Custom Queue interface: returns at Bigram at the front of Phrase without removing
     * @return Bigram that was at the front of the Phrase
     */
    @Override
    public Bigram peek() {
        return (Bigram) this.getFirst();
    }
}
