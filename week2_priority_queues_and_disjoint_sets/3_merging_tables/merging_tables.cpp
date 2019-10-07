#include <cstdio>
#include <cstdlib>
#include <vector>
#include <algorithm>
#include <iostream>

using std::cin;
using std::cout;
using std::endl;
using std::max;
using std::vector;

struct DisjointSetsElement
{
	int size, parent, rank;

	DisjointSetsElement(int size = 0, int parent = -1, int rank = 0) : size(size), parent(parent), rank(rank) {}
};

struct DisjointSets
{
	int size;
	int max_table_size;
	vector<DisjointSetsElement> sets;

	DisjointSets(int size) : size(size), max_table_size(0), sets(size)
	{
		for (int i = 0; i < size; i++)
			sets[i].parent = i;
	}

	int getParent(int table)
	{
		// find parent and compress path
		if (table != sets.at(table).parent)
		{
			long parent_table = sets.at(table).parent;
			sets.at(table).parent = getParent(parent_table);
		}
		return table;
	}

	void merge(int destination, int source)
	{
		int realDestination = getParent(destination);
		int realSource = getParent(source);
		if (realDestination != realSource)
		{
			// merge two components
			int size;
			// use union by rank heuristic
			int rank_destination = sets.at(realDestination).rank;
			int rank_source = sets.at(realSource).rank;
			if (rank_destination > rank_source)
			{
				sets.at(realDestination).size += sets.at(realSource).size;
				size = sets.at(realDestination).size;
				sets.at(realSource).parent = realDestination;
			}
			else
			{
				sets.at(realSource).size += sets.at(realDestination).size;
				size = sets.at(realSource).size;
				sets.at(realDestination).parent = realSource;
				if (rank_source == rank_destination)
				{
					sets.at(realSource).rank++;
				}
			}
			// update max_table_size
			max_table_size = max(max_table_size, size);
		}
	}
};

int main()
{
	int n, m;
	cin >> n >> m;

	DisjointSets tables(n);
	for (auto &table : tables.sets)
	{
		cin >> table.size;
		tables.max_table_size = max(tables.max_table_size, table.size);
	}

	for (int i = 0; i < m; i++)
	{
		int destination, source;
		cin >> destination >> source;
		--destination;
		--source;

		tables.merge(destination, source);
		cout << tables.max_table_size << endl;
	}

	return 0;
}
