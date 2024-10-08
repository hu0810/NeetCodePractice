package SlidingWindows; 

import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0 || prices == null){
            return 0;
        }
        
        int min_value = Integer.MAX_VALUE;
        int max_profit = Integer.MIN_VALUE;
        for (int price:prices){
            if(min_value > price){
                min_value = price;
            }
            if((price - min_value) > max_profit){
                max_profit = price - min_value;
            }
        }

        return max_profit;
    }

    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0 || s == null){
            return 0;
        }

        int maxLength = 0;
        int[] charIndex = new int[128]; 

        for(int i  = 0, j = 0; j < s.length();j++){
            char c = s.charAt(j);

            i = Math.max(i, charIndex[c]);
            maxLength = Math.max(maxLength, j - i + 1);

            charIndex[c] = j + 1;

        }
        return maxLength;   
    }

    public int characterReplacement(String s, int k) {
        int maxLength = 0;
        
        for (int start = 0; start < s.length(); start++) {
            HashMap<Character, Integer> charCount = new HashMap<>();
            int maxCharCount = 0;
            
            for (int end = start; end < s.length(); end++) {
                char currentChar = s.charAt(end);
                
                charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);
                maxCharCount = Math.max(maxCharCount, charCount.get(currentChar));
                
                int replacements = (end - start + 1) - maxCharCount;
                
                if (replacements <= k) {
                    maxLength = Math.max(maxLength, end - start + 1);
                } else {
                    break;
                }
            }
        }
        
        return maxLength;
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
    
        HashMap<Character, Integer> charCountS1 = new HashMap<>();
        HashMap<Character, Integer> charCountS2 = new HashMap<>();
    
        for (char c : s1.toCharArray()) {
            charCountS1.put(c, charCountS1.getOrDefault(c, 0) + 1);
        }
    
        for (int i = 0; i < s2.length(); i++) {
            char rightChar = s2.charAt(i);
            charCountS2.put(rightChar, charCountS2.getOrDefault(rightChar, 0) + 1);
    
            if (i >= s1.length()) {
                char leftChar = s2.charAt(i - s1.length());
                charCountS2.put(leftChar, charCountS2.get(leftChar) - 1);
                if (charCountS2.get(leftChar) == 0) {
                    charCountS2.remove(leftChar);
                }
            }
    
            if (charCountS1.equals(charCountS2)) {
                return true;
            }
        }
    
        return false;
    }

    public String minWindow(String s, String t) {
        int[] map = new int[128];

        for (char cha: t.toCharArray()){
            map[cha]++;
        }

        int counter = 0;
        int left = 0, n = s.length();
        int minLength = Integer.MAX_VALUE;

        String ans = "";

        for (int right = 0; right < n; right++){
            map[s.charAt(right)]--;

            if(map[s.charAt(right)] >=0){
                counter ++;
            }

            while(counter == t.length()){

                if(minLength > right - left + 1){
                    minLength = right - left + 1;
                    ans = s.substring(left, right + 1);
                }

                map[s.charAt(left)]++;

                if(map[s.charAt(left)] > 0){
                    counter --;
                }
                left ++;
            }
        }
        return ans;


    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length <= 1 || k == 1) {
            return nums;
        }
    
        ArrayList<Integer> ans = new ArrayList<>();
        
        int currMax = -1001;
        int counter = 0;
        
        for (int i = 0; i < k; i++) {
            if (currMax < nums[i]) {
                currMax = nums[i];
                counter = 1;
            } else if (currMax == nums[i]) {
                counter += 1;
            }
        }
        
        
        ans.add(currMax);
    
        int left = 0;
        for (int right = k; right < nums.length; right++) {
            left++; 
            
            if (nums[right] > currMax) {
                currMax = nums[right];
                counter = 1;
            } else if (nums[right] == currMax) {
                counter += 1;
            }
    
            if (nums[left - 1] == currMax) {
                counter--;
                if (counter == 0) {
                    currMax = -1001;
                    for (int i = left; i <= right; i++) {
                        if (currMax < nums[i]) {
                            currMax = nums[i];
                            counter = 1;
                        } else if (currMax == nums[i]) {
                            counter += 1;
                        }
                    }
                }
            }
            
            ans.add(currMax);
        }
    
        int[] array = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            array[i] = ans.get(i);
        }
        return array;
    }
}


public class Main {
    public static void main(String[] args) {
    }
}