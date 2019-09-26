#include <iostream>
#include <vector>
#include <deque>

using std::cin;
using std::cout;
using std::deque;
using std::max;
using std::vector;

void max_sliding_window_naive(vector<int> const &A, int w)
{
    for (size_t i = 0; i < A.size() - w + 1; ++i)
    {
        int window_max = A.at(i);
        for (size_t j = i + 1; j < i + w; ++j)
            window_max = max(window_max, A.at(j));

        cout << window_max << " ";
    }

    return;
}

// 1 2 3 5
// 3 2 1 5
// 1, 2, 3, 1, 4, 5, 2, 3, 6
// 3 3 4 5 5 5 6
void max_sliding_window(vector<int> const &A, int w)
{
    deque<int> w_max_indexes;
    size_t i = 0;
    for (; i < w; i++)
    {
        while (!w_max_indexes.empty() && A.at(w_max_indexes.back()) < A.at(i))
            w_max_indexes.pop_back();
        w_max_indexes.push_back(i);
    }
    for (; i < A.size(); ++i)
    {
        cout << A.at(w_max_indexes.front()) << " ";
        if (w_max_indexes.front() <= i - w)
            w_max_indexes.pop_front();
        while (!w_max_indexes.empty() && A.at(w_max_indexes.back()) < A.at(i))
            w_max_indexes.pop_back();
        w_max_indexes.push_back(i);
    }
    cout << A.at(w_max_indexes.front());

    return;
}

int main()
{
    int n = 0;
    cin >> n;

    vector<int> A(n);
    for (size_t i = 0; i < n; ++i)
        cin >> A.at(i);

    int w = 0;
    cin >> w;

    //max_sliding_window_naive(A, w);
    max_sliding_window(A, w);
    return 0;
}
