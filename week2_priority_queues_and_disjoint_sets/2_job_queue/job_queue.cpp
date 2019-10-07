#include <iostream>
#include <vector>
#include <algorithm>

using std::vector;
using std::cin;
using std::cout;
using std::min;
using std::swap;

class WorkerThread
{
public:
  long id;
  long last_start_time = 0;
  long last_end_time = 0;
  WorkerThread() : last_end_time(0),
                   last_start_time(0)
  {
  }

  WorkerThread(long id) : id(id),
                          last_end_time(0),
                          last_start_time(0)
  {
  }

  WorkerThread(WorkerThread *workerThread) : id(workerThread->id),
                                             last_end_time(workerThread->last_end_time),
                                             last_start_time(workerThread->last_start_time)
  {
  }
  void UpdateJob(long process_time)
  {
    this->last_start_time = this->last_end_time;
    this->last_end_time += process_time;
  }

  bool Compare(WorkerThread &workerThread) //is smaller and index is first
  {
    return this->last_end_time < workerThread.last_end_time ||
           this->last_end_time == workerThread.last_end_time && this->id < workerThread.id;
  }
};

class PriorityQueue
{
private:
  vector<WorkerThread> threads;

  WorkerThread &GetMin()
  {
    return threads.front();
  }

public:
  PriorityQueue(int size)
  {
    threads.resize(size);
    for (size_t i = 0; i < size; i++)
    {
      threads[i] = WorkerThread(i);
    }
  }
  WorkerThread Assign(long job_time)
  {
    WorkerThread &minThread = this->GetMin();
    minThread.UpdateJob(job_time);
    WorkerThread localMinThread = WorkerThread(minThread);
    SiftDown(0);
    return localMinThread;
  }
  void SiftDown(int thread_index)
  {
    int max_index = thread_index;
    int left_index = LeftChild(thread_index);
    if (left_index < threads.size() && threads[left_index].Compare(threads[max_index]))
      max_index = left_index;

    int right_index = RightChild(thread_index);
    if (right_index < threads.size() && threads[right_index].Compare(threads[max_index]))
      max_index = right_index;

    if (thread_index != max_index)
    {
      swap(threads[max_index], threads[thread_index]);
      SiftDown(max_index);
    }
  }

  long LeftChild(int i)
  {
    return 2 * i + 1;
  }
  long RightChild(int i)
  {
    return 2 * i + 2;
  }
};

class JobQueue
{
private:
  int num_workers_;
  vector<int> jobs_;

  vector<int> assigned_workers_;
  vector<long long> start_times_;

  void WriteResponse() const
  {
    for (int i = 0; i < jobs_.size(); ++i)
    {
      cout << assigned_workers_[i] << " " << start_times_[i] << "\n";
    }
  }

  void ReadData()
  {
    int m;
    cin >> num_workers_ >> m;
    jobs_.resize(m);
    for (int i = 0; i < m; ++i)
      cin >> jobs_[i];
  }

  void AssignJobs_Faster()
  {
    assigned_workers_.resize(jobs_.size());
    start_times_.resize(jobs_.size());
    PriorityQueue Queue = PriorityQueue(num_workers_);

    for (int i = 0; i < jobs_.size(); ++i)
    {
      WorkerThread workerThread = Queue.Assign(jobs_[i]);
      assigned_workers_[i] = workerThread.id;
      start_times_[i] = workerThread.last_start_time;
    }
  }

  void AssignJobs()
  {
    // TODO: replace this code with a faster algorithm.
    assigned_workers_.resize(jobs_.size());
    start_times_.resize(jobs_.size());
    vector<long long> next_free_time(num_workers_, 0);
    for (int i = 0; i < jobs_.size(); ++i)
    {
      int duration = jobs_[i];
      int next_worker = 0;
      for (int j = 0; j < num_workers_; ++j)
      {
        if (next_free_time[j] < next_free_time[next_worker])
          next_worker = j;
      }
      assigned_workers_[i] = next_worker;
      start_times_[i] = next_free_time[next_worker];
      next_free_time[next_worker] += duration;
    }
  }

public:
  void Solve()
  {
    ReadData();
    AssignJobs_Faster();
    WriteResponse();
  }
};

int main()
{
  std::ios_base::sync_with_stdio(false);
  JobQueue job_queue;
  job_queue.Solve();
  return 0;
}
