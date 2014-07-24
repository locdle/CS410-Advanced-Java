package edu.pdx.cs410J.locle;

/**
 * Created by locle on 7/9/14.
 */
public class FizzBuzz {
    public static void main(String[] args) {

        for(int i = 1; i <= 100; ++i){
            convertFizzBuzz(i);
        }
        System.exit(0);

    }

    public static void convertFizzBuzz(int i){
        if(i%3 == 0 && i%5 ==0){
            System.out.println("FizzBuzz");
        }
        else if(i%5 == 0){
            System.out.println("Buzz");
        }
        else if(i%3 == 0) {
            System.out.println("Fizz");
        }
        else{
            System.out.println(i);
        }
    }
}
