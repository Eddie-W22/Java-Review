package Leetcode;

import java.util.ArrayList;

public class RomanToInt {
    public static void main(String[] args){
        RomanToInt.change("III");
        RomanToInt.change("LVIII");
        RomanToInt.change("MCMXCIV");
    }
    public static int change(String s) {
        int value = 0;
        ArrayList<Character> input = new ArrayList<>();
        //* adds the full input to an arraylist
        for(int i = 0; i < s.length();i++){
            input.add(s.charAt(i));
        }
        //* does the math
        for(int j = 0; j < input.size(); j++){
            if(input.get(j) == 'I'){
                try {
                    if(input.get(j+1) == 'V'){
                        value += 4;
                        j++;
                        continue;
                    }
                    if(input.get(j+1) == 'X'){
                        value += 9;
                        j++;
                        continue;
                    }
                } catch (IndexOutOfBoundsException e) {

                }
                value++;
            }else if(input.get(j) == 'V'){
                value += 5;
            }else if(input.get(j) == 'X'){
                try {
                    if(input.get(j+1) == 'L'){
                        value += 40;
                        j++;
                        continue;
                    }
                    if(input.get(j+1) == 'C'){
                        value += 90;
                        j++;
                        continue;
                    }
                } catch (IndexOutOfBoundsException e) {

                }
                value += 10;
            }else if(input.get(j) == 'L'){
                value += 50;
            }else if(input.get(j) == 'C'){
                try {
                    if(input.get(j+1) == 'D'){
                        value += 400;
                        j++;
                        continue;
                    }
                    if(input.get(j+1) == 'M'){
                        value += 900;
                        j++;
                        continue;
                    }
                } catch (IndexOutOfBoundsException e) {

                }
                value += 100;
            }else if(input.get(j) == 'D'){
                value += 500;
            }else if(input.get(j) == 'M'){
                value += 1000;
            }
        }
        System.out.println(value);
        return value;
    }
}