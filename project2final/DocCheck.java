package project2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.*;

public class DocCheck {

    public static void spellCheck(File processingFile, File dictionaryFile) throws IOException {
        HashSet<String> dictionary = new HashSet<>();
        FileWriter w = new FileWriter("C:\\Users\\coler\\OneDrive\\Documents\\csci232\\projects\\project2\\src\\project2\\test_spellChecked.txt");
        BufferedReader dr = new BufferedReader(new FileReader(dictionaryFile));
        String word;
        while ((word = dr.readLine()) != null) {
            dictionary.add(word);
        }
        Scanner pr = new Scanner(processingFile);
        String check;       
        while (pr.hasNext()){
            check = pr.next();
            
            if(check.matches("[a-zA-Z]+"))   
                if(dictionary.contains(check.toLowerCase())){
                    w.write(check + " ");
                }
                else{
                    check = ("<" + check + "> ");
                    w.write(check);
                }
            else{
                String arr[] = check.split("((?=[^a-zA-Z])|(?<=[^a-zA-Z]))");
                for(int i = 0; i < arr.length; i++){
                    if(arr[i].matches("[a-zA-Z]+")){
                        if(!dictionary.contains(arr[i].toLowerCase())){
                            check = ("<" + arr[i] + ">");
                            if(i == arr.length - 1){
                                w.write(check + " ");
                            }
                            else{
                                w.write(check);
                            }
                        }
                        else{
                            if(i == arr.length - 1){
                                w.write(arr[i] + " ");
                            }
                            else{
                                w.write(arr[i]);
                            }
                        }
                    }
                    else{
                        if(i == arr.length - 1){
                            w.write(arr[i] + " ");
                        }
                        else{
                            w.write(arr[i]);
                        }
                    }
                }
            }
        }
        w.close();    
        pr.close();    
    }

    public static void wordCount(File processingFile) throws IOException {
        HashMap<String, Integer> countPerWord = new HashMap<>();
        HashMap<Integer, HashSet<String>> wordsPerCount = new HashMap<>();
        
        FileWriter l = new FileWriter("C:\\Users\\coler\\OneDrive\\Documents\\csci232\\projects\\project2\\src\\project2\\test_wordcount.txt");
        
        l.write("Alphabetic order word count:\n");
        
        Scanner pr = new Scanner(processingFile);
        String word;
        while (pr.hasNext()){
            word = pr.next();
            if(word.matches("[a-zA-Z]+")){
                if(!countPerWord.containsKey(word)){
                    countPerWord.put(word.toLowerCase(), 1);
                }
                else{
                    countPerWord.replace(word, countPerWord.get(word) + 1);
                }
            }
            else{
                String arr[] = word.split("((?=[^a-zA-Z])|(?<=[^a-zA-Z]))");
                for(int i = 0; i < arr.length; i++){
                    if(arr[i].matches("[a-zA-Z]+")){
                        if(!countPerWord.containsKey(arr[i])){
                            countPerWord.put(arr[i].toLowerCase(), 1);
                        }
                        else{
                            countPerWord.replace(arr[i], countPerWord.get(arr[i]) + 1);
                        }
                    }
                }
            }
        }
        TreeMap<String, Integer> sort = new TreeMap<>(countPerWord);
        for(String key : sort.keySet()){
            l.write(key + ": " + sort.get(key) + "\n");
        }       
        l.write("\nBy occurrence word count: \n");
        for(String key : countPerWord.keySet()){
            if(!wordsPerCount.containsKey(countPerWord.get(key))){
                wordsPerCount.put(countPerWord.get(key), new HashSet<>());
            }
            wordsPerCount.get(countPerWord.get(key)).add(key);
        }
        TreeMap<Integer, HashSet<String>> num_sort = new TreeMap<>(Collections.reverseOrder());
        num_sort.putAll(wordsPerCount);
        for(int key : num_sort.keySet()){
            l.write(key + ": " + num_sort.get(key).toString().replace("[", "").replace("]", "") + "\n");
        }
        l.close(); 
        pr.close();
    }
}
