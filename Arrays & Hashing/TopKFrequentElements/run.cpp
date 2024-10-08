#include <iostream>
#include <unordered_map>
#include <string>
#include <queue>
using namespace std;

class Solution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        auto comp = [](pair<int, int>& a, pair<int, int>& b) {
            return a.second < b.second;
        };

        priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(comp)> maxHeap(comp);

        for (auto& element : nums) {
            maxHeap.push(element);
        }

        vector<int> topK;
        while (k-- > 0 && !maxHeap.empty()) {
            topK.push_back(maxHeap.top().first);
            maxHeap.pop();
        }

        return topK;
    }
};


int main() {
    vector<int> nums = {1, 1, 1, 2, 2, 3};
    int k = 2;

    Solution solution;
    vector<int> result = solution.topKFrequent(nums, k);

    for (int num : result) {
        cout << num << " ";
    }
    cout << endl;

    return 0;
}
