package com.anurag.k8sassignment;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping(path = "/addUsers")
  public @ResponseBody String addUsers() {
    User user1 = new User();
    user1.setName("Anurag_v4");
    user1.setEmail("anurag@gmail.com");

    User user2 = new User();
    user2.setName("Pranit_v4");
    user2.setEmail("pranit@gmail.com");

    userRepository.save(user1);
    userRepository.save(user2);

    return "Users Added Successfully";
  }

  @GetMapping(path = "/getUsers")
  public @ResponseBody Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping(path = "/testHPA")
  public String test() {
    int numCore = 2;
    int numThreadsPerCore = 2;
    double load = 0.8;
    final long duration = 100000;
    for (int thread = 0; thread < numCore * numThreadsPerCore; thread++) {
      new BusyThread("Thread" + thread, load, duration).start();
    }

    return "Increasing the CPU load now.";
  }

  private static class BusyThread extends Thread {

    private double load;
    private long duration;

    /**
     * Constructor which creates the thread
     *
     * @param name     Name of this thread
     * @param load     Load % that this thread should generate
     * @param duration Duration that this thread should generate the load for
     */
    public BusyThread(String name, double load, long duration) {
      super(name);
      this.load = load;
      this.duration = duration;
    }

    /**
     * Generates the load when run
     */
    @Override
    public void run() {
      long startTime = System.currentTimeMillis();
      try {
        // Loop for the given duration
        while (System.currentTimeMillis() - startTime < duration) {
          // Every 100ms, sleep for the percentage of unladen time
          if (System.currentTimeMillis() % 100 == 0) {
            Thread.sleep((long) Math.floor((1 - load) * 100));
          }
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
