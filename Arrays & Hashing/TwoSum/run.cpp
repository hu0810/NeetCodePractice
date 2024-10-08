#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> num_map; 

        for (int i = 0; i < nums.size(); ++i) {
            int complement = target - nums[i];

            if (num_map.find(complement) != num_map.end()) {
                return vector<int>{num_map[complement], i}; 
            }

           
            num_map[nums[i]] = i;
        }

        return {}; 
    }
};


int main() {
    vector<int> nums = {3,4,5,6};
    int target = 7;
    Solution solution;
    vector<int> ans = solution.twoSum(nums,target);

    for (auto it = ans.begin(); it != ans.end(); ++it) {
        cout << *it << " ";  
    }

    cout << endl;
}
