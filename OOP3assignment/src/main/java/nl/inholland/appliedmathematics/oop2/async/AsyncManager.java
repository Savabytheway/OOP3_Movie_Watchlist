package nl.inholland.appliedmathematics.oop2.async;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;

// The class which makes multithreading
// AsyncManager is responsible for providing an Executor bean
// that enables asynchronous task execution using virtual threads.
public class AsyncManager {

    // This method creates a thread pool that uses virtual threads
    // to execute tasks concurrently.
    // The @Bean annotation registers this Executor as a Spring bean
    // with the name "taskExecutor", making it available for dependency injection.
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        // Returns an Executor that creates a new virtual thread for each task.
        // Virtual threads are lightweight threads introduced in Java 19 (Project Loom)
        // that allow for high concurrency with low resource usage.
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}
