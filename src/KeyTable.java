/** KeyTable object that holds the key to the encryption as a 5x5 character matrix
 * @author Santhosh Senthil, 115871889, R01
 */
import java.lang.StringBuilder;
import java.util.LinkedList;

public class KeyTable{
    private char[][] key = new char[5][5];

    /**
     * Default Constructor
     */
    public KeyTable(){}

    /**
     * Method that builds 5x5 Keytable from key phrase
     * @param phrase string that the key is derived from
     * @return 5x5 matrix table that holds the key to encryption
     * @throws IllegalArgumentException throws exception if input phrase is null
     */
    public static KeyTable buildFromString(String phrase) throws IllegalArgumentException{
        LinkedList<Character> uniqueChar=new LinkedList<>();
        LinkedList<Character> usedChar=new LinkedList<>();
        for (int i=65;i<91;i++){
            usedChar.addLast((char) i);
        }
        if (phrase==null)
            throw new IllegalArgumentException("String is null");
        for (char i : phrase.toUpperCase().toCharArray()){
            if (usedChar.removeFirstOccurrence(i)){
                if (i=='J'){
                    uniqueChar.addLast('I');
                }
                else
                    uniqueChar.addLast(i);
            }
        }
        while (!usedChar.isEmpty()){
            uniqueChar.addLast(usedChar.removeFirst());
        }
        uniqueChar.removeFirstOccurrence('J');
        return tabler(uniqueChar.toString().replace(", ", "").replace("[","").replace("]",""));
    }

    /**
     * Helper method that organizes key from linear string into 5x5 matrix KeyTable
     * @param str key as a linear string, to be reformatted as a table
     * @return KeyTable object that holds key as a 5x5 matrix
     */
    private static KeyTable tabler(String str){
        KeyTable newKey=new KeyTable();
        int count=0;
        for (char s : str.toCharArray()){
            newKey.key[count/5][count++%5]=s;
        }
        return newKey;
    }

    /**
     * getter method for matrix in KeyTable object
     * @return char[][] 5x5 char matrix that holds key
     */
    public char[][] getKeyTable(){
        return key;
    }

    /**
     * method that finds what row specified character is in
     * @param c character to be found
     * @return index of row that character is in
     * @throws IllegalArgumentException throws exception if char not in table
     */
    public int findRow(char c) throws IllegalArgumentException{
        for (int i=0;i<5;i++){
            for (int j=0;j<5;j++){
                if (key[i][j]==c)
                    return i;
            }
        }
        throw new IllegalArgumentException("Char not in KeyTable");
    }

    /**
     * method that finds what column specified character is in
     * @param c character to be found
     * @return index of column that character is in
     * @throws IllegalArgumentException throws exception if char not in table
     */
    public int findCol(char c) throws IllegalArgumentException{
        for (int i=0;i<5;i++){
            for (int j=0;j<5;j++){
                if (key[j][i]==c)
                    return i;
            }
        }
        throw new IllegalArgumentException("Char not in KeyTable");
    }

    /**
     * Overridden toString method for KeyTable
     * @return Formatted String that returns KeyTable in a 5x5 tabular format
     */
    public String toString(){
        return key[0][0]+" "+key[0][1]+" "+key[0][2]+" "+key[0][3]+" "+key[0][4]+"\n"
                +key[1][0]+" "+key[1][1]+" "+key[1][2]+" "+key[1][3]+" "+key[1][4]+"\n"
                +key[2][0]+" "+key[2][1]+" "+key[2][2]+" "+key[2][3]+" "+key[2][4]+"\n"
                +key[3][0]+" "+key[3][1]+" "+key[3][2]+" "+key[3][3]+" "+key[3][4]+"\n"
                +key[4][0]+" "+key[4][1]+" "+key[4][2]+" "+key[4][3]+" "+key[4][4];
    }
}