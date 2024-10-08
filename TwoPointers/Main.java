package TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution{
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();


        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1, r = nums.length - 1;

            while(l < r){
                int sum = nums[i] + nums [l] + nums[r];

                if(sum > 0){
                    r--;
                } else if (sum < 0){
                    l++;
                }else{
                    res.add(Arrays.asList(nums[i],nums[l],nums[r]));
                    l++;
                    r--;
                    while (l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }
                    if(r < nums.length   && r >= 1){
                        while ( l < r && nums[r] == nums[r + 1]) {
                            r--;
                        }
                    }

                }
            }
            
        }
        return res;
    
    }

    public boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {      
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            }
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            }


            
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            }
            l++;
            r--;    
        }  
        return true;
    }

    public int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];

            if (sum > target) {
                r--;
            } else if (sum < target) {
                l++;
            } else {
                return new int[]{l + 1, r + 1};
            }
        }
        return null;
    }

    public int maxArea(int[] heights) {

        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int l = i;
            int r = heights.length - 1;
            while(l < r){
                int min = Math.min(heights[l], heights[r]);
                max = Math.max(max, min * (r-l));
                r--;
            }
        }
        return max;
    }

    public int trap(int[] height) {
        //////////////抄答案 
        if (height == null || height.length == 0) {
            return 0;
        }

        int l = 0, r = height.length - 1;
        int leftMax = height[l], rightMax = height[r];
        int res = 0;
        while (l < r) {
            if (leftMax < rightMax) {
                l++;
                leftMax = Math.max(leftMax, height[l]);
                res += leftMax - height[l];
            } else {
                r--;
                rightMax = Math.max(rightMax, height[r]);
                res += rightMax - height[r];
            }
        }
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String testString = "racecar";

        System.out.println("Is \"" + testString + "\" a palindrome? " + solution.isPalindrome(testString));


    //------------------------------maxArea----------------------------------------
    int[] heights = {100,2,100,4,8,3,7};
    System.out.println(solution.maxArea(heights));
    

    //------------------------------Trapping Rain Water----------------------------------------
    int[] height = {0,2,0,3,1,0,1,3};
    System.out.println(solution.trap(height));
    }
}