#include <iostream>
#include <vector>
#include <unordered_map>
#include <string>
#include <algorithm>

using namespace std;

class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        vector<vector<string>> result;
        unordered_map<string, vector<string>> anagramGroups;
        for (string str : strs) {
            string sortedStr = str;
            sort(sortedStr.begin(), sortedStr.end()); 

            anagramGroups[sortedStr].push_back(str);
        }


        for (auto& group : anagramGroups) {
            result.push_back(group.second);
        }

        return result;
    }
};


int main() {
    vector<string> strs = {"act","pots","tops","cat","stop","hat"};
    Solution solution;
    vector<vector<string>> ans = solution.groupAnagrams(strs);

}
