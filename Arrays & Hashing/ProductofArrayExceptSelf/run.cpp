#include <iostream>
#include <unordered_set>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    //直白解
    vector<int> productExceptSelf(vector<int>& nums) {
        //找出zero有幾個和他們的位置
        vector<int> zero_positions;
        vector<int> ans;
       
        for (size_t i = 0; i < nums.size(); ++i) {
            if (nums[i] == 0) {
                zero_positions.push_back(i);
            }
        }

        if(zero_positions.size() > 1){
            for (size_t i = 0; i < nums.size(); ++i) {
                ans.push_back(0);
            }
            return ans;
        }

        //正式邏輯, 先全部乘起來
        int all_except_zero = 1;
        int all = 1;

        for (int num:nums) {
            if(num != 0){
                all_except_zero *= num;
            }
            all *= num;
        }

        //ans vector中加入除以自己的數字
        for (int num:nums) {
            if(num != 0){
                ans.push_back(all/num);
            }else{
                ans.push_back(all_except_zero);
            }
        }
        return ans;
    }
};


int main() {

    vector<int> nums = {100, 4, 200, 1, 3, 2};

    Solution solution;
    int ans = solution.longestConsecutive(nums);

    return 0;
}