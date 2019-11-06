package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        ReadFile obj = new ReadFile();
        obj.openFile();
        obj.readPrintTotalWordCount();
        obj.printTop10Words();
        obj.findTheTopSentence();
        obj.closeFile();

    }
}



