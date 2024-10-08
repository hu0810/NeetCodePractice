#include <iostream>
#include <vector>
#include <string>
using namespace std;

class Solution {
public:
    string encode(const vector<string>& strs) {
        string encoded_string;
        const string delimiter = "-/*";
        for (const string& str : strs) {
            encoded_string += str + delimiter;
        }
        return encoded_string;
    }

    vector<string> decode(const string& s) {
        vector<string> ans;
        const string delimiter = "-/*";
        size_t start = 0;
        size_t end = s.find(delimiter);

        while (end != string::npos) {
            ans.push_back(s.substr(start, end - start));
            start = end + delimiter.length();
            end = s.find(delimiter, start);
        }
        
        return ans;
    }
};

int main() {
    vector<string> strs = {"neet","code","love","you"};

    Solution solution;
    string encoded_strs = solution.encode(strs);
    vector<string> decoded_strs = solution.decode(encoded_strs);

    cout << "Encoded string: " << encoded_strs << endl;


    cout << "strs: ";
    for (const string& str : strs) {
        cout << str << " ";
    }

    cout << "Decoded strings: ";
    for (const string& str : decoded_strs) {
        cout << str << " ";
    }
    cout << endl;

    return 0;
}
