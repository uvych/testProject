import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Task {
    private static final Logger logger = LoggerFactory.getLogger(Task.class);

    private static final List<Integer> randoms = new ArrayList<>();

    private static final AtomicBoolean lockWrite = new AtomicBoolean(false);

    private static final Thread writer = new Thread(() -> {
        for (int i = 0; i < Randomizer.positiveNumber(); i++) {
            try {
                writeToList();
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    });

    private static void writeToList() throws InterruptedException {
        while (lockWrite.get()) {
            Thread.sleep(200);
        }
        var randomNumber = Randomizer.number();
        randoms.add(randomNumber);
        logger.info("Write number " + randomNumber + " for list");
        lockWrite.set(true);
    }

    private static final Thread reader = new Thread(() -> {
        while (true) {
            if (lockWrite.get()) {
                logger.info("Start read list");
                randoms.forEach(System.out::println);
                logger.info("Finish read list");
                randoms.clear();
                logger.info("Clear list");
                lockWrite.set(false);
            }
        }
    });

   public void run() throws InterruptedException {
       writer.setDaemon(true);
       reader.setDaemon(true);
       writer.start();
       reader.start();
       Thread.sleep(5000);
   }
}
