package com.okasha;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LZW {
    public static List<Integer> Compress(String input){
        int dictionary_size = 256;
        Map<String, Integer> dictionary = new HashMap<String, Integer>();
        for(int i=0;i<dictionary_size;++i){
            dictionary.put("" + (char)i, i);
        }

        String p = "", c = "";
        int code = 256;
        p += input.charAt(0);
        List<Integer> output = new ArrayList<Integer>();
        System.out.println("The Dictionary of the input: " + "\n");
        System.out.println("String\tOutput_Code\tAddition\n");
        for(int i=0;i<input.length();++i){
            if( i != (input.length() - 1) ){
                c += input.charAt(i+1);
            }
            if(dictionary.containsKey(p + c)){
                p = p + c;
            }
            else{
                System.out.println(p + "\t\t" + dictionary.get(p) + "\t\t" + p + c + "\t\t" + code + "\n");
                output.add(dictionary.get(p));
                dictionary.put(p + c,code);
                code++;
                p = c;
            }
            c = "";
        }
        System.out.println(p + "\t" + dictionary.get(p) + "\n");
        output.add(dictionary.get(p));
        return output;
    }

    public static String decompress(List<Integer> compressed) {
        // Build the dictionary.
            int dictSize = 256;
            Map<Integer,String> dictionary = new HashMap<Integer,String>();
            for (int i = 0; i < 256; i++)
                dictionary.put(i, "" + (char)i);

            String w = "" + (char)(int)compressed.remove(0);
            StringBuffer result = new StringBuffer(w);
            for (int i : compressed) {
                String entry;
                if (dictionary.containsKey(i))
                    entry = dictionary.get(i);
                else if (i == dictSize)
                    entry = w + w.charAt(0);
                else
                    throw new IllegalArgumentException("Not good Compression i: " + i);

                result.append(entry);

                // Add w+entry[0] to the dictionary.
                dictionary.put(dictSize++, w + entry.charAt(0));

                w = entry;
            }
            return result.toString();
        }





    public static void main(String[] args){
        String s = "ABAABABBAABAABAAAABABBBBBBBB";
        List<Integer>output = Compress(s);
        System.out.println("The compressed output");
        for (int i = 0; i <output.size() ; i++) {
            int x = output.get(i);
            System.out.print(x + " ");
        }
        System.out.println("\n");
        String d = decompress(output);
        System.out.println(d);
    }
}
