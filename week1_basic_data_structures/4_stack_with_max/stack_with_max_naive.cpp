#include <iostream>
#include <vector>
#include <string>
#include <cassert>
#include <algorithm>

using std::cin;
using std::cout;
using std::max_element;
using std::string;
using std::vector;

class StackWithMax
{
    vector<int> stack;
    vector<int> max_stack;

public:
    void Push(int value)
    {
        stack.push_back(value);
        if (max_stack.empty())
            max_stack.push_back(value);
        else
        {
            int last_max_value = max_stack.back();
            int max = last_max_value > value ? last_max_value : value;
            max_stack.push_back(max);
        }
    }

    void Pop()
    {
        assert(stack.size());
        stack.pop_back();
        max_stack.pop_back();
    }

    int Max() const
    {
        assert(max_stack.size());
        return max_stack.back();
    }
};

int main()
{
    int num_queries = 0;
    cin >> num_queries;

    string query;
    string value;

    StackWithMax stack;

    for (int i = 0; i < num_queries; ++i)
    {
        cin >> query;
        if (query == "push")
        {
            cin >> value;
            stack.Push(std::stoi(value));
        }
        else if (query == "pop")
        {
            stack.Pop();
        }
        else if (query == "max")
        {
            cout << stack.Max() << "\n";
        }
        else
        {
            assert(0);
        }
    }
    return 0;
}