package hw4;

import java.util.Stack;

//Reza Choudhury

public class Tree {
  
	
	public char myChar;
    public Tree l;
    public Tree r;
    private static String stnd = "";
    /**
     * Represents a binary tree with a character associated with each node.
     * The tree is constructed based on the given encoding string.
     * The encoding string is a representation of the tree with '^' representing a null node.
     * Each non-null character n the string represents a node in the tree.
     * 
     * @param encodingString The encoding string used to construct the tree.
     *                       Should be in format with '^' representing null nodes.
     *                       Must be at least 2 characters long.
     */
    public Tree(String encodingString) {
        if (encodingString == null || encodingString.length() < 2) {
            return;
        }

        Stack<Tree> stack = new Stack<>();
        int index = 0;
        this.myChar = encodingString.charAt(index++);
        stack.push(this);
        Tree current = this;
        String last = "in";

        while (index < encodingString.length()) {
            Tree node;
            if (encodingString.charAt(index) == '^') {
                node = new Tree('^');
                index++;
            } else {
                node = new Tree(encodingString.charAt(index++));
            }

            if ("in".equals(last)) {
                current.l = node;
                if (node.myChar == '^') {
                    current = stack.push(node);
                    last = "in";
                } else {
                    if (!stack.isEmpty())
                        current = stack.pop();
                    last = "out";
                }
            } else { // last is out
                current.r = node;
                if (node.myChar == '^') {
                    current = stack.push(node);
                    last = "in";
                } else {
                    if (!stack.isEmpty())
                        current = stack.pop();
                    last = "out";
                }
            }
        }
    }

    /**
     * Constructor for node
     *
     * @param myChar
     */
    public Tree(char myChar) {
        this.myChar = myChar;
        this.l = null;
        this.r = null;
    }

    /**
     * Method to print characters and their binary codes
     *
     * @param root
     * @param code
     */
    public static void printCodes(Tree root, String code) {
        System.out.println("character code\n-------------------------");
        for (char ch : code.toCharArray()) {
            String stnd = "";
            getCode(root, ch, stnd);
            System.out.println("    " + (ch == '\n' ? "\\n" : ch + " ") + "    " + stnd);
        }
    }

    

    /**
     * Gets the code + calls itself setting the alphabet
     *
     * @param root
     * @param ch
     * @param path
     * @return
     */
    private static boolean getCode(Tree root, char c, String path) {
        if (root != null) {
            if (root.myChar == c) {
                stnd = path;
                return true;
            }
            return getCode(root.l, c, path + "0") || getCode(root.r, c, path + "1");
        }
        return false;
    }

    /**
     * Decodes message using the decoding process given
     *
     * @param codes
     * @param msg
     */
    public void decode(Tree codeTree, String encodedMessage) {
        System.out.println("Decoded message:");
        Tree current = codeTree;
        String decodedMessage = "";
        
        for (char bit : encodedMessage.toCharArray()) {
            current = (bit == '0') ? current.l : current.r;

            if (current.myChar != '^') {
                getCode(codeTree, current.myChar, stnd = ""); 
                decodedMessage += current.myChar;
                current = codeTree;
            }
        }

        System.out.println(decodedMessage);
        analyzeData(encodedMessage, decodedMessage);
    }

    /**
     * Extra credit statistics. Pulls the encoded and decoded strings data to print
     *
     * @param encode
     * @param decode
     */
    private void analyzeData(String encode, String decode) {
        System.out.println("Analysis of data:");
        System.out.println(String.format("Average bits per character:\t%.1f", (double) encode.length() / (double) decode.length()));
        System.out.println("Total Characters:\t" + decode.length());
        System.out.println(String.format("Space Utilization:\t%.1f%%", (1d - decode.length() / (double) encode.length()) * 100));
    }

}