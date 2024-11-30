package com.epam.concurrency;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Test {
    public static void main(String[] args) {

        HashMap<String,String> input = new HashMap<>();
        input.put("1","1");
        input.put("2","1");
        input.put("Bangalore","Sampath");
//        System.out.println(input);
//        System.out.println(input.size());
        

       for (Map.Entry<String,String> value : input.entrySet())
       {
         System.out.println(value.getKey().hashCode());
         //System.out.println(value.getKey().hashCode() %input.size());
        System.out.println(calculateHashIndex(value.getKey().hashCode(),input.size()));

       }
    }

    private static int calculateHashIndex(int hashCode, int capacity) {
        return (hashCode & (capacity - 1));
    }
}
