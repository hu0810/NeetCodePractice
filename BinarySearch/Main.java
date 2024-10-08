package BinarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

class Solution {
    public int bisearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while(l <= r){
            int m = (l + r) / 2;

            if(nums[m] > target){
                r = m - 1;
            }
            else if(nums[m] < target){
                l = m + 1;
            }else {
                return m;
            }
        }

        return -1;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        //search在第幾個row
        int l = 0, r = matrix.length - 1, row = -1;

        while(l <= r){
            int m = (l + r) / 2;

            if(matrix[m][0] > target){
                r = m - 1;
            }
            else if(matrix[m][0] < target){
                l = m + 1;
            }else {
                row = m;
                break;
            }
        }

        if (row == -1) {
            row = r;  
        }

        if (row >= 0 && row < matrix.length) {
            l = 0;
            r = matrix[row].length - 1;
            while(l <= r){
                int m = (l + r) / 2;

                if(matrix[row][m] > target){
                    r = m - 1;
                }
                else if(matrix[row][m] < target){
                    l = m + 1;
                }else {
                    return true;
                }
            }
        }
        return false;
    }


    public int minEatingSpeed(int[] piles, int h) {
        //找最大的吃香蕉速度, 就是piles裡面最大的
        int max = -1;
        for(int pile : piles){
            max = Math.max(max, pile);
        }
        int l = 1, r = max;
        int res = r;
        while(l <= r){
            int m = (l + r) / 2;
            int total = 0;
            for(int pile:piles){
                total += (pile + m - 1) / m ;
            }
            if(total > h){
                l = m + 1;
            }else {
                res = m;    
                r = m - 1;
            }
        }
        return res;
    }

    public int findMin(int[] nums) {
        return binarySearch(nums, 0, nums.length - 1);
    }

    private int binarySearch(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left]; 
        }
        
        if (nums[left] < nums[right]) {
            return nums[left]; 
        }

        int mid = (left + right) / 2;

        if (nums[mid] >= nums[left]) {
            return binarySearch(nums, mid + 1, right);
        } else {
            return binarySearch(nums, left, mid);
        }
    }

    public int search(int[] nums, int target) {
        return binarySearchTwo(nums, 0, nums.length - 1, target);
    }

    public int binarySearchTwo(int[] nums, int left, int right, int target){
        if (left > right) {
            return -1;  
        }

        int mid = (left + right) / 2;

        if (nums[mid] == target) {
            return mid;
        }


        if (nums[left] <= nums[mid]) {
            if (target >= nums[left] && target < nums[mid]) {
                return binarySearchTwo(nums, left, mid - 1, target);
            } else {
                return binarySearchTwo(nums, mid + 1, right, target);
            }
        } else {
            if (target > nums[mid] && target <= nums[right]) {
                return binarySearchTwo(nums, mid + 1, right, target);
            } else {
                return binarySearchTwo(nums, left, mid - 1, target);
            }
        }
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //refer https://claude.ai/chat/87202806-0230-4bb4-8e5e-70af0fc7840c

        // 確保 nums1 是較短的數組，這樣可以優化時間複雜度
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        
        int m = nums1.length;
        int n = nums2.length;
        
        // 在較短的數組上進行二分搜索
        int left = 0;
        int right = m;
        
        while (left <= right) {
            // nums1 的分割點
            int partitionX = (left + right) / 2;
            // nums2 的分割點
            int partitionY = (m + n + 1) / 2 - partitionX;
            
            // 獲取分割點左右的值
            // 如果分割點在最左邊，則左邊最大值為負無窮
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            // 如果分割點在最右邊，則右邊最小值為正無窮
            int minRightX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];
            
            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];
            
            // 檢查是否找到了正確的分割點
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // 如果總長度為奇數
                if ((m + n) % 2 == 1) {
                    return Math.max(maxLeftX, maxLeftY);
                }
                // 如果總長度為偶數
                else {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                }
            }
            // 如果分割點不正確，調整搜索範圍
            else if (maxLeftX > minRightY) {
                // nums1 的左半部分太大，需要向左移動
                right = partitionX - 1;
            }
            else {
                // nums1 的左半部分太小，需要向右移動
                left = partitionX + 1;
            }
        }

        return -1;
    }
    
}
 
class TimeMap {

    private Map<String, List<Integer>> timeMap;
    private Map<String, List<String>> valueMap;

    public TimeMap() {
        timeMap = new HashMap<>();
        valueMap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        timeMap.putIfAbsent(key, new ArrayList<>());
        valueMap.putIfAbsent(key, new ArrayList<>());

        timeMap.get(key).add(timestamp);
        valueMap.get(key).add(value);
    }

    public String get(String key, int timestamp) {
        if (!timeMap.containsKey(key)) {
            return "";
        }

        List<Integer> times = timeMap.get(key);
        List<String> values = valueMap.get(key);
        int left = 0, right = times.size() - 1;
        String result = "";

        while (left <= right) {
            int mid = (left + right) / 2;
            if (times.get(mid) <= timestamp) {
                result = values.get(mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }
}

public class Main {
        public static void main(String[] args) {
            int[] test = {4,5,0,1,2,3};
            Solution sol = new Solution();
            int[] nums1 = {1, 3};
            int[] nums2 = {2};
            System.out.println("Test case 1: " + sol.findMedianSortedArrays(nums1, nums2));  // 預期輸出: 2.0
            
            // 測試用例 2
            int[] nums3 = {1, 2};
            int[] nums4 = {3, 4};
            System.out.println("Test case 2: " + sol.findMedianSortedArrays(nums3, nums4));  // 預期輸出: 2.5


    }
}
