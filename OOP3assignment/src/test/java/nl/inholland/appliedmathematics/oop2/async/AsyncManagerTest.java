package nl.inholland.appliedmathematics.oop2.async;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AsyncManagerTest {
 
    @Test
    void testTaskExecutorBeanExistsAndIsExecutable() throws InterruptedException {
       
        try (var context = new AnnotationConfigApplicationContext(AsyncManager.class)) {

            
            assertTrue(context.containsBean("taskExecutor"));

            Object bean = context.getBean("taskExecutor");
            assertNotNull(bean);
            assertTrue(bean instanceof Executor);

         
            Executor executor = (Executor) bean;
            AtomicBoolean taskRan = new AtomicBoolean(false);

            executor.execute(() -> taskRan.set(true));

            Thread.sleep(100);

            assertTrue(taskRan.get(), "The task was run");
        }
    }
}

