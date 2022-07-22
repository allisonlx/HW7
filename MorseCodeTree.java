package com.edu.miracosta.cs113;

import java.awt.desktop.SystemEventListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> {

    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.


    //CONSTRUCTOR
    public MorseCodeTree(){
        this.root = new Node<>(' ');
        String filepath = "/Users/allisonlane/CS113/Polynomial/src/edu/HW7/src/com/edu/miracosta/cs113/code.txt";
        try {
            Scanner in = new Scanner(new File(filepath));
            while (in.hasNext()) {
                String line = in.nextLine();
                char letter = line.charAt(0);
                String code = line.substring(2);
                code.trim();
                Node<Character> newNode = new Node<>(letter);
                Node temp = this.root;

                for (int i = 0; i < code.length(); i++){
                    if (code.charAt(i) == '*'){
                        if (temp.left == null) {
                            temp.left = newNode;
                        }
                        else {
                            temp = temp.left;
                        }
                    }
                    else {
                        if (temp.right == null) {
                            temp.right = newNode;
                        }
                        else temp = temp.right;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }


    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) throws Exception {
        String[] splitCodes = morseCode.split(" ");
        String output = "";
        for (String s : splitCodes){
            System.out.println(s);
        }

        //ERROR CATCHING
        for (String s : splitCodes) {
            if (s.length() > 4) {
                throw new Exception("Code exceeds maximum length.");
            }
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '*' && s.charAt(i) != '-') {
                    throw new Exception("Code contains invalid characters.");
                }
            }
        }

        for (String code : splitCodes){
            output += readMorseCodeTree(code);
        }


        return output;
    }



    public char readMorseCodeTree(String code){
        BinaryTree<Character> bt = new MorseCodeTree();
        int i = 0;
        while (i < code.length()){
            if (code.charAt(i) == '*'){
                bt = bt.getLeftSubtree();
            }
            else bt = bt.getRightSubtree();
            i++;
        }
        return bt.getData();
    }

} // End of class MorseCodeTree