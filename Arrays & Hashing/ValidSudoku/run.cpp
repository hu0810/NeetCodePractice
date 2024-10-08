#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

class Solution {
public:
    bool checkRow(const vector<vector<char>>& board, int row) {
        unordered_set<char> seen;
        for (int col = 0; col < 9; ++col) {
            char cell = board[row][col];
            if (cell != '.' && !seen.insert(cell).second) {
                return false;
            }
        }
        return true;
    }

    bool checkColumn(const vector<vector<char>>& board, int col) {
        unordered_set<char> seen;
        for (int row = 0; row < 9; ++row) {
            char cell = board[row][col];
            if (cell != '.' && !seen.insert(cell).second) {
                return false;
            }
        }
        return true;
    }

    bool checkSquare(const vector<vector<char>>& board, int startRow, int startCol) {
        unordered_set<char> seen;
        for (int row = startRow; row < startRow + 3; ++row) {
            for (int col = startCol; col < startCol + 3; ++col) {
                char cell = board[row][col];
                if (cell != '.' && !seen.insert(cell).second) {
                    return false;
                }
            }
        }
        return true;
    }

    bool isValidSudoku(vector<vector<char>>& board) {
        for (int i = 0; i < 9; ++i) {
            if (!checkRow(board, i) || !checkColumn(board, i)) {
                return false;
            }
        }
        
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                if (!checkSquare(board, row, col)) {
                    return false;
                }
            }
        }
        
        return true;
    }
};


int main() {
    vector<int> nums = {1,2,3,4,0};

    Solution solution;
    vector<int> ans = solution.productExceptSelf(nums);


    cout << "strs: ";
    for (const int& num : ans) {
        cout << num << " ";
        cout << endl;
    }


    return 0;
}
