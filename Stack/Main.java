package Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.empty() || stack.pop() != '(') {
                    return false;
                }
            } else if (c == '}') {
                if (stack.empty() || stack.pop() != '{') {
                    return false;
                }
            } else if (c == ']') {
                if (stack.empty() || stack.pop() != '[') {
                    return false;
                }
            }
        }

        return stack.empty();
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> num_stack = new Stack<>();

        for (String token : tokens) {
            if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
                int b = num_stack.pop();
                int a = num_stack.pop();
                switch (token) {
                    case "+":
                        num_stack.push(a + b);
                    case "-":
                        num_stack.push(a - b);
                    case "*":
                        num_stack.push(a * b);
                    case "/":
                        num_stack.push(a / b);
                }
            } else {
                num_stack.push(Integer.parseInt(token));
            }
        }

        return num_stack.pop();
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();

        gen(ans, "", n);

        Iterator<String> iter = ans.iterator();
        while (iter.hasNext()) {
            String pa = iter.next();
            if (!isValid(pa)) {
                iter.remove();  
            }
        }

        return ans;
    }

    private static void gen(List<String> result, String current, int max) {
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        gen(result, current + "(", max);
        gen(result, current + ")", max);
    }


    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];

        Stack<Integer> keyStack = new Stack<>();

        int current = 0;
        for (int i = 0; i < temperatures.length;i++){
            
            while(!keyStack.empty() && temperatures[i] > temperatures[keyStack.peek()]){
                current = keyStack.pop();
                ans[current] = i - current;
            }
            keyStack.push(i);
        }
        return ans;

    }

    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        double[][] cars = new double[n][2];
        //先 sort position 和speed
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i]; 
            cars[i][1] = speed[i];    
        }

        Arrays.sort(cars, (a, b) -> Double.compare(b[0], a[0]));

        // 計算每輛車到達target的時間
        double[] time = new double[n];
        for (int i = 0; i < n; i++) {
            time[i] = (target - cars[i][0]) / cars[i][1];
        }
        //遍歷上步驟所算出的時間, 檢查有幾個遞減數列就是答案 
        int ans = 0;
        double lastTime = 0;
        for (int i = 0; i < n; i++) {
            if (time[i] > lastTime) {
                ans++; 
                lastTime = time[i]; 
            }
        }

        return ans; 
    }
    public int largestRectangleArea(int[] heights) {

        int left = 0, right = 0, maxArea = 0;
        //遍歷每直列, 搜尋最左到一樣數字到最右一樣數字的長度*當前列的高度, 更新area.
        for (int i = 0; i < heights.length; i++){
            left = 0;
            right = 0;
            //當前向左最大寬度
            for (int j = i; j >= 0; j--){
                if(heights[j] < heights[i]){
                    break;
                }
                left ++;
            }
            for (int j = i; j < heights.length; j++){
                if(heights[j] < heights[i]){
                    break;
                }
                right ++;
            }
            maxArea = Math.max(maxArea, heights[i] * (left+right-1));
        }
        return maxArea;
    }
}




public class Main {
    public void main(String[] args) {

        Solution solution = new Solution();
        int[] test = {30,38,30,36,35,40,28};
        int[] ans = solution.dailyTemperatures(test);
        for (int i : ans){
            System.out.println(i);
        }

    }
}