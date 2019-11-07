package com.company;

import jdk.swing.interop.SwingInterOpUtils;
import java.net.SocketTimeoutException;
import java.io.*;
import java.util.*;

public class ReadFile {

    private Scanner file;
    private BufferedReader reader;
    private ArrayList<String> words;
    private ArrayList<Integer> count;
    private int countWord = 0;
    private ArrayList<String> Top10words;
    private ArrayList<String> strWordLine;
    private ArrayList<String> Sentence;
    private ArrayList<Integer> topTen;

    //Open passage.txt
    public void openFile() {
        try {
            file = new Scanner(new File("passage.txt"));
            reader = new BufferedReader(new FileReader("passage.txt"));
            words = new ArrayList<String>();
            count = new ArrayList<Integer>();
            Top10words = new ArrayList<String>();
            strWordLine = new ArrayList<String>();
            Sentence = new ArrayList<String>();
            topTen = new ArrayList<Integer>();

            System.out.println("File Exist");

        } catch (Exception e) {
            System.out.println("File Not Exist");
        }
    }
    // Read through the file and count the words and finally Give a total word count
    public void readPrintTotalWordCount() {
        while (file.hasNext()) {
            // Get the next word
            String nextWord = file.next();
            //Determine if the word is in the ArrayList
            if (words.contains(nextWord)) {
                int index = words.indexOf(nextWord);
                count.set(index, count.get(index) + 1);
                countWord++;
            } else {
                words.add(nextWord);
                count.add(1);
                countWord++;
            }
        }
        System.out.println("\nThis File Has " + countWord + " Word");
    }
    //Identify the top 10 words used and display them in sorted order
    public void printTop10Words() {
        int Top10Max = 0;
        System.out.println("\nThe Top 10 Words is :");
        for (int i = 0; i < count.size(); i++) {
            if (Top10Max < 10) {
                Top10Max++;
                Object obj1 = Collections.max(count);
                int maxIndex = count.indexOf(obj1);
                System.out.println(String.format( words.get(maxIndex) + " " + obj1));
                Top10words.add(words.get(maxIndex));
                count.remove(Collections.max(count));
                words.remove(words.get(maxIndex));
            }
        }
    }
    // Find the top sentence
    public void findTheTopSentence() throws IOException {
        String strLine ;
        int counter = 0;
        while ((strLine = reader.readLine()) != null) {
            if (!(strLine.equals(""))) {
                // [!?.:  ]+ is the sentence delimiter in java
                String [] lineList = strLine.split("[.]+");
                for ( String strWordLine : lineList) {
                    for (int j = 0; j < Top10words.size(); j++) {
                        if (strWordLine.contains(Top10words.get(j)))
                            counter++;
                    }
                    Sentence.add(strWordLine);
                    topTen.add(counter);
                    counter = 0;
                }
            }
        }
        Object obj2 = Collections.max(topTen);
        int maxIndex = topTen.indexOf(obj2);
        System.out.println("The Sentence That Contains The Most Used Word is:\n"+Sentence.get(maxIndex));
    }
    //Close file
    public void closeFile() {
        file.close();
    }
}