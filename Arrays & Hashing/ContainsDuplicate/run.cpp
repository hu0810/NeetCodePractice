#include <vector>
#include <iostream>
#include <unordered_set>

using namespace std;

class Solution {
public:
    bool hasDuplicate(vector<int>& nums) {
        unordered_set<int> ans;

        for (int num: nums){
            if(ans.find(num) != ans.end()){
                return true;
            }

            ans.insert(num);
        }
        return false;
    }
};

int main() {
    vector<int> nums = {1, 2, 3, 3};
    Solution solution;
    bool ans = solution.hasDuplicate(nums);

    cout << ans;
}