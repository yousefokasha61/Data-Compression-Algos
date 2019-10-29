package com.company;

import java.util.*;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<MAP> arraymap = new ArrayList<>();
    public static ArrayList<String> dictionary =new ArrayList<>();


    public static void main(String[] args) {
        // write your code here
        //get message to compressed***********
        final String input ;
        input = scanner.nextLine();
        dictionary.add("");
        boolean success = compress(input) ;

        if(success)
            System.out.println(decompress(arraymap));
        //************************************

        for (MAP m : arraymap) {
            System.out.println("<" + m.getIndex() + "," + m.getSymbol()+ ">\n");
        }

        System.out.println("Printing  Dictionary: \n");

        for (int i = 0; i < dictionary.size(); i++) {
            System.out.println(" | " + i + " | " + " " + dictionary.get(i));
        }

    }

    private static String decompress(ArrayList<MAP> arraymap) {
        String originalMessage ="";
        for (MAP m : arraymap) {
            originalMessage+= dictionary.get(m.getIndex());
            if(!m.getSymbol().equals("null") )
                originalMessage+= m.getSymbol() ;
        }
        return originalMessage ;
    }

    private static boolean compress(String input){
        int numberOfTags=0;
        boolean found ;
        for(int i = 0 ; i < input.length() ; i++ ){
            MAP m = new MAP() ;
            String symb = input.charAt(i) + "" ;
            int index = dictionary.indexOf(symb); // da el makan el symbol bta3k
            if(index == -1 )
                found  = false ;
            else
                found  = true ;
            // da fe 7alet enha msh mwgooda fel dictionary
            if(!found){
                dictionary.add(symb+"");
                m.setSymbol(symb);
                m.setIndex(0);
                arraymap.add(m);
            }
            else{
                boolean isSaved = false ;
                while (i+1 < input.length()){
                    int updatedindex = dictionary.indexOf(symb) ;
                    symb+= input.charAt(++i) ;
                    if(dictionary.indexOf(symb)!= -1 ){
                        updatedindex = dictionary.indexOf(symb); // da el index bta3 a5r symbol mwgod fel dic
                    }
                    else{
                        char nextsymb = symb.charAt(symb.length()-1);
                        isSaved = true ;
                        m.setIndex(updatedindex);
                        m.setSymbol(nextsymb+"");
                        arraymap.add(m) ;
                        dictionary.add(symb) ;
                        break ;
                    }
                }
                if(!isSaved) {
                    m.setIndex(dictionary.indexOf(symb));
                    m.setSymbol("null");
                    arraymap.add(m) ;
                }
            }
            numberOfTags++ ;
        }
        return true ;
    }
}
