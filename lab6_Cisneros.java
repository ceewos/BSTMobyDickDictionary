/*Carlos Cisneros
 *For this lab we will create a Binary Search tree, that works as a dictionary for Moby Dick we will first start by sanitizing the text getting rid of everything that
 * Is not a word so symbols and numbers would be erased, next we will import this sanitized text word by word into a binary search tree, the binary search tree will work by using Moby
 * As the root and every dictionary text value less than Moby will go to the right and everything greater to the left, this allows for quick binary search of words in
 * Moby Dick, we will also add methods in the NODE class that delete from the tree, and input into the tree. we will also create a text file with 100 words 10 of
 * Which can't be found in Moby Dick to test adding those into the dictionary and removing the other 90 from the Dictionary. We will also take the time it takes to run
 * certain functions as well as the levels to calculate the efficiency.
 * 
 * levels in BST                      = 37 Levels
 * time it took to create BST         = 8375 Nano Seconds 
 * time it takes to add 50 values     = 3526041 Nanoseconds
 * time it takes to delete 50 values  = 4116583 Nanoseconds 
 * time it takes to search 50 values  = 2906125 Nanoseconds
 * 
 * time it takes to search 100 values = 6618167 Nanoseconds
 * */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class lab6_Cisneros{
    public static void main(String[] args) throws FileNotFoundException {
        //First part we call a method that sanitizes the string then we populate it into a binary tree
        System.out.print("Sanitizing String ...");
        String sanitizedString = sanitizeString("the_whale.txt");
        Scanner scanner = new Scanner(sanitizedString);
        Node root = new Node(scanner.next());
        while(scanner.hasNext()){
            Node node = new Node(scanner.next());
            root.createTree(node, root);
        }
        //create and array of the 100 words we will look for in the BST from a file

        long start = System.nanoTime();
        while(scanner.hasNext()){
            Node node = new Node(scanner.next());
            root.createTree(node, root);
        }
        long end = System.nanoTime();
        System.out.println("\n\nTime it took to create BST = "+(end-start)+" Nano Seconds");
        
        Scanner scnr = new Scanner(System.in);
        boolean valid = false;
        while(!valid){
            System.out.println("\nWhat do you wish to do[Please enter a value from 1-6]\n1 = search Dictionary\n2 = Insert new word to Dictionary\n3 = Delete word from Dictionary\n"+
            "4 = view in Order Dictionary\n5 = find height of tree\n6 = EXIT");
            int userInput = scnr.nextInt();
            if(userInput == 1){
                System.out.print("Enter a word to search in dictionary\n>");
                String wordIn = scnr.next();
                root.search(wordIn, root);
            }else if(userInput == 2){
                System.out.print("Enter a word to insert in dictionary\n>");
                String wordIn = scnr.next();
                root.insert(wordIn, root);
            }else if(userInput == 3){
                System.out.print("Enter a word to delete in dictionary\n>");
                String wordIn = scnr.next();
                root.delete(new Node(wordIn), root);
            }else if(userInput == 4){
                root.inOrder(root);
            }else if(userInput == 5){
                System.out.println("Height of tree is = "+root.getHeight(root));
            }else if(userInput == 6){
                System.out.println("ENDING");
                valid = true;
            }else{
                System.out.println("INVALID INPUT");
            }
            
        }


    }
    public static String sanitizeString(String fileName) throws FileNotFoundException{ // sanitizes string to be put in dictionary
        String sanitized = "";
        //read file from src directory
        File file = new File(fileName);
        Scanner input = new Scanner(file);

        while (input.hasNext()) {
            //remove punctuation
            String word  = input.next().replaceAll("\\p{Punct}|“|‘|’|”", "");

            //remove "-" from words and split into multiple
            if(word.contains("—")) {
                word = word.replaceAll("—", " ");
                word = word.replaceAll("  ", " ");
               
            }
            //replace "æ" with "a"
            if(word.contains("æ")) word = word.replace("æ", "a");

            //dont count words with digits or other symbols
            if( !(word.matches("^[a-zA-Z]*$"))) continue;

            sanitized += word.toLowerCase()+" ";//Lower cases String
        }
        return sanitized;
    }
}