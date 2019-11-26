package week1_basic_data_structures;

import ds.QueueLinkedList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Request {
    public Request(int arrival_time, int process_time) {
        this.arrival_time = arrival_time;
        this.process_time = process_time;
    }

    public int arrival_time;
    public int process_time;
}

class Response {
    public Response(boolean dropped, int start_time) {
        this.dropped = dropped;
        this.start_time = start_time;
    }

    public boolean dropped;
    public int start_time;
}

class Buffer {
    public Buffer(int size) {
        this.size_ = size;
        this.finish_time_ = new QueueLinkedList<Integer>();
        this.last_finsh_time = 0;
    }

    public Response Process(Request request) {
        // write your code here
        while (!finish_time_.empty() && finish_time_.top() <= request.arrival_time)
            finish_time_.dequeue();
        if (finish_time_.size() == size_)
            return new Response(true, -1);
        if (finish_time_.empty()) {
            last_finsh_time = request.arrival_time + request.process_time;
            finish_time_.enqueue(request.arrival_time + request.process_time);
            return new Response(false, request.arrival_time);
        }
        int lasttime = last_finsh_time;
        last_finsh_time = lasttime + request.process_time;
        finish_time_.enqueue(lasttime + request.process_time);
        return new Response(false, lasttime);
    }

    private int size_;
    private int last_finsh_time;
    private QueueLinkedList<Integer> finish_time_;
}

class process_packages {
    private static ArrayList<Request> ReadQueries(Scanner scanner) throws IOException {
        int requests_count = scanner.nextInt();
        ArrayList<Request> requests = new ArrayList<Request>();
        for (int i = 0; i < requests_count; ++i) {
            int arrival_time = scanner.nextInt();
            int process_time = scanner.nextInt();
            requests.add(new Request(arrival_time, process_time));
        }
        return requests;
    }

    private static ArrayList<Response> ProcessRequests(ArrayList<Request> requests, Buffer buffer) {
        ArrayList<Response> responses = new ArrayList<Response>();
        for (int i = 0; i < requests.size(); ++i) {
            responses.add(buffer.Process(requests.get(i)));
        }
        return responses;
    }

    private static void PrintResponses(ArrayList<Response> responses) {
        for (int i = 0; i < responses.size(); ++i) {
            Response response = responses.get(i);
            if (response.dropped) {
                System.out.println(-1);
            } else {
                System.out.println(response.start_time);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int buffer_max_size = scanner.nextInt();
        Buffer buffer = new Buffer(buffer_max_size);

        ArrayList<Request> requests = ReadQueries(scanner);
        ArrayList<Response> responses = ProcessRequests(requests, buffer);
        PrintResponses(responses);
    }
}
