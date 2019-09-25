package socketexamples;

/**
 * Simple skeleton socket client thread that coordinates termination with a cyclic barrier to
 * demonstration barrier synchronization
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

// Sockets of this class are coordinated  by a CyclicBarrier which pauses all threads 
// until the last one completes. At this stage, all threads terminate

public class SocketClientThread extends Thread {
  private long clientID;
  String hostName;
  int port;
  CyclicBarrier synk;

  public SocketClientThread(String hostName, int port, CyclicBarrier barrier) {
    this.hostName = hostName;
    this.port = port;
    clientID = Thread.currentThread().getId();
    synk = barrier;

  }

  public void run() {
    String[] args = {hostName, "12031"};

    SocketClientSingleThreaded.main(args);
    try {
      this.synk.await();
      System.out.println("doudaole");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }

  }
}
