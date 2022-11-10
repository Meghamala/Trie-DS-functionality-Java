import java.util.Scanner;

class TrieTest {
    /* this is for new test branch */
    /*
    This class contains all the parameters and methods
    related to trie node creation, insertion,
    search and print mechanisms

     Defining all the parameters for the list
    > alphabet of char type to store each character
    > nextParent of Trie type which is used for creating
    the list horizontally
    > child of Trie type which is used for creating list
    vertically
    > isEndOfString of Boolean type to specify the
    end of string
    > static Head of Trie type
    > static subString of String type to store
    substring
    */
    private final char character;
    private TrieTest nextParent,child;
    private Boolean isEndOfString;
    static String subString;

    /*
    Trie constructor for node initialization
    */
    TrieTest(char c) {
        this.character = c;
        isEndOfString = false;
        nextParent = child = null;
    }

    /*=======================================================
    METHOD:
    createNode: Trie constructor is called for initializing
    new node

    PARAMETERS:
    alphabet - char type to be initialized in node

    RETURN:
    Trie
    =====================================================*/
    protected static TrieTest createNode(char alphabet) {
        return new TrieTest(alphabet);
    }/*end createNode*/

    /*=========================================================
    METHOD:
    createWordsTrie

    PARAMETERS :
    Word - string type word to be added to Trie

    RETURN:
    None
    ====================================================*/
    protected void createWordsTrie(String word) {
        TrieTest ifFoundNode, childNode, node = this;
        char alphabet;
        /*---------------------------------------------*/
        if (word.length() == 0) { return; }

        /* this loop runs through each alphabet of the word
        and calls to SearchChar method to find if current
        alphabet already exists in the trie as child of
        current node.
        */
        for (int str_idx = 0; str_idx < word.length();
             str_idx++) {
            alphabet = word.charAt(str_idx);

            if ((ifFoundNode = searchCharNode
                    (alphabet, node)) == null) {
                /*if no child is found for node, create
                * new child node*/
                childNode = createNode(
                        Character.toLowerCase(alphabet));
                node.child = childNode;
                node = node.child;
            }
            else
                /* if not null, means node having alphabet
                * has been found, so pointer is moved to
                * this node*/
                node = ifFoundNode;
        }/*end for int str_idx =0 */
        node.isEndOfString = true;
    }/*end createWordsTrie*/

    /*=====================================================
    METHOD:
    searchCharNode

    PARAMETERS:
    alphabet - char type to be searched in node
    node - Trie type current node pointer

    RETURN:
    Trie
    ===================================================*/
    protected TrieTest searchCharNode(
            char alphabet, TrieTest node) {
        TrieTest next, prev, parentNode;
        /*---------------------------------------------*/

        /*If child node of passed node is not present,
        then null is returned to mark no nodes*/
        if (node.child == null) { return null; }

        next = node.child;
        prev = null;

        /*If there are child nodes present to current node
        then all the child node list is iterated to check
        and find alphabet */
        while (next != null) {
            if (next.character == Character.
                    toLowerCase(alphabet)) {
                /*return the node is match is found*/
                return next;
            } else {
                prev = next;
                next = next.nextParent;
            }
        }/*end while (next!= null)*/

        /*If nodes matching alphabet is not found, then a
        newParent node is created and linked to previous
        child node. This new node is returned*/
        parentNode = createNode(Character.
                toLowerCase(alphabet));
        prev.nextParent = parentNode;
        next = prev.nextParent;

        return next;
    }/*end searchCharNode*/

    /*========================================================
    METHOD:
    printTrieWords: traversal method is DFS

    PARAMETERS:
    current - Trie type used for trie traversal
    count - int type to keep track of each levels of node
    printString - character array to store each
    alphabet of node
    subString - string type to store substring
    printAll - boolean type to differentiate between
    print operations

    RETURN:
    none
     ===================================================*/
    public static void printTrieWords(
            TrieTest current,int count,char[] printString,
            Boolean printAll) {
        if (current.isEndOfString) {
            /*if true,this string needs to be printed and
            continue to see if there are child and
            nextParent of this node*/

            StringBuilder fullString = new StringBuilder();
            printString[count] = current.character;
            for (int i = 0; i <= count; i++) {
                fullString.append(printString[i]);
            }/* end for */
            if (printAll) {
                // To print all the words
                System.out.println("\""+ fullString.toString().trim()
                        +"\"");
            }
            else {
                /* printAll is False hence check if this
                string has the substring and print
                if true*/

                if (fullString.toString().contains(subString.toLowerCase())) {
                    System.out.println("\"" +
                            fullString.toString().trim() + "\"");
                }
            }
        }/*end if(current.isEndOfString) condition*/

        /* if child nodes are present,move to child
        node and recursively iterate */
        if (current.child != null) {
            printString[count] = current.character;
            count++;
            printTrieWords(
                    current.child,count,
                    printString,printAll);
        }

        /* if multiple parent nodes are present at
        this level, move to nextParent*/
        if (current.nextParent != null) {
            printTrieWords(
                    current.nextParent,count - 1,
                    printString,printAll);
        }
    }/*end printTrieWords*/
}/*end of Trie class*/

public class NewTrie extends TrieTest {

    /*
    This class inherits properties of Trie class and
    includes main method for creating Trie
    ----------------------------------------
    NewTrie constructor
    */
    NewTrie() {
        super(' ');
    }

    public static void main(String[] args) {
        char[] printString = new char[256];
        int n, count = 0;
        TrieTest root = createNode(' ');

        Scanner inputObject = new Scanner(System.in).
                useDelimiter("\n");
        System.out.println(
        "Enter the number of words to be " +
        "inserted");
        n = inputObject.nextInt();
        String[] words = new String[n];

        System.out.print(
        "Enter the words to be inserted: ");
        for (int i = 0; i < words.length; i++) {
            words[i] = inputObject.next();
            root.createWordsTrie(words[i]);
        }

        /*choice is added to either print all words
        * or only matching words to substring*/
        System.out.print("Enter 0 to print words "+
                "matching substring"+
                "or 1 to print all words: ");
        int value = inputObject.nextInt();
        if(value==0) {
            System.out.print(
                    "Enter the substring that needs "
                            +"to be searched: ");
            subString = inputObject.next();

            System.out.println(
                    "The words having substring "+"\""
                            +subString+"\""+" are: ");
            printTrieWords(root,count,printString,
                    false);
        }
        else {
            System.out.println("\nAll the words in " +
                    "trie are: ");
            printTrieWords(root,count,printString,
                        true);
        }
    }/*end main*/
}/*end NewTrie class*/