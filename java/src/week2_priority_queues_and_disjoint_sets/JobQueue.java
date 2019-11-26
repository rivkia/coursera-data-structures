package week2_priority_queues_and_disjoint_sets;

import java.io.*;
import java.util.StringTokenizer;

class Thread implements Comparable<Thread> {
    int startTime;
    int endTime;
    int id;

    public Thread(int id, int startTime, int endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Thread o) {
        if (this.endTime < o.endTime || this.endTime == o.endTime && this.id < o.id)
            return 1;
        return 0;
    }
}

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        Thread[] threads = new Thread[numWorkers];
        ds.PriorityQueue2 pq = new ds.PriorityQueue2<Thread>(threads, 0, numWorkers);
        //first jobs
        for (int i = 0; i < numWorkers && i < jobs.length; i++) {
            pq.insert(new Thread(i, 0, jobs[i]));
            assignedWorker[i] = i;
            startTime[i] = 0;
        }
        for (int i = numWorkers; i < jobs.length; i++) {
            Thread firstJobFinish = (Thread) pq.extractMax();
            firstJobFinish.startTime = firstJobFinish.endTime;
            firstJobFinish.endTime = firstJobFinish.startTime + jobs[i];
            assignedWorker[i] = firstJobFinish.id;
            startTime[i] = firstJobFinish.startTime;
            pq.insert(firstJobFinish);
        }
    }

    private void assignJobs_naive() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        long[] nextFreeTime = new long[numWorkers];
        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            int bestWorker = 0;
            for (int j = 0; j < numWorkers; ++j) {
                if (nextFreeTime[j] < nextFreeTime[bestWorker])
                    bestWorker = j;
            }
            assignedWorker[i] = bestWorker;
            startTime[i] = nextFreeTime[bestWorker];
            nextFreeTime[bestWorker] += duration;
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
