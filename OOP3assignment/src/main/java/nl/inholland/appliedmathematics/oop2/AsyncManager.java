package nl.inholland.appliedmathematics.oop2;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;

// The class which makes multithreading

public class AsyncManager {

    // This method creates a thread pool that uses virtual threads
    // to execute tasks concurrently.
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}
