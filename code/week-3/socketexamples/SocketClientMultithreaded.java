package socketexamples;

/**
 * Skeleton socket client. Accepts host/port on command line or defaults to localhost/12031 Then
 * (should) starts MAX_Threads and waits for them all to terminate before terminating main()
 *
 * @author Ian Gorton
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketClientMultithreaded {

  static CyclicBarrier barrier;

  public static void main(String[] args) {
    String hostName;
    final int MAX_THREADS = 120;
    int port;

    if (args.length == 2) {
      hostName = args[0];
      port = Integer.parseInt(args[1]);
    } else {
      hostName = null;
      port = 12031;  // default port in SocketServer
    }
    barrier = new CyclicBarrier(MAX_THREADS + 1);

    ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
    for(int i = 0; i < MAX_THREADS; i++) {
      executorService.submit(new SocketClientThread(hostName, port, barrier));
    }

    // TO DO wait for all threads to complete
    try {
      barrier.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }

    System.out.println("Terminating ....");

    executorService.shutdown();
    long startTime = System.nanoTime();
    try {
      executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    } catch (InterruptedException e) {
      //
    }
    long endTime = System.nanoTime();

    long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
    System.out.println("Terminated. It takes " + duration + " nanoseconds to terminate");
  }


}
