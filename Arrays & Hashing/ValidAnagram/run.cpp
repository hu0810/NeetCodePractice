#include <iostream>
#include <unordered_map>
#include <string>
using namespace std;

class Solution {
public:
    bool isAnagram(string s, string t) {
        if (s.length() != t.length()) {
            return false;
        }

        unordered_map<char, int> umap;        

        for (char word : t) {
            umap[word] += 1;
        }

        for (char word : s) {
            if (umap.find(word) != umap.end()) {
                umap[word] -= 1;
                if (umap[word] == 0) {
                    umap.erase(word); 
                }
            } else {
                return false; 
            }
        }

        return umap.empty(); 
    }
};

int main() {
    string s = "racecar";
    string t = "carrace";
    Solution solution;
    bool ans = solution.isAnagram(s,t);

    cout << ans;
}
