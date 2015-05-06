package com.ford.gux.tests.selenium.Utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by alewi143 on 28/04/2015.
 */
public class TestOutput {

    public static void printToFile (String text){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(System.getProperty("user.dir")+ "/target/test-results/test-results.txt","UTF-8");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println(text);
        writer.close();
    }


        public static void printConsoleToFile (String text){
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(System.getProperty("user.dir")+ "/target/test-results/test-results.txt","UTF-8");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            writer.println(text);
            writer.close();
        }

}
