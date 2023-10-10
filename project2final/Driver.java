package project2;

import java.io.File;
import java.io.IOException;

public class Driver {

    public static void main(String[] args) throws IOException {
        File dictionaryFile = new File("C:\\Users\\coler\\OneDrive\\Documents\\csci232\\projects\\project2\\src\\project2\\words.txt");
        File processingFile = new File("C:\\Users\\coler\\OneDrive\\Documents\\csci232\\projects\\project2\\src\\project2\\test.txt");
        DocCheck.spellCheck(processingFile, dictionaryFile);
        DocCheck.wordCount(processingFile);
    }
}