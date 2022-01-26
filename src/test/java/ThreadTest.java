import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ThreadTest {

    @Test
    void testRunThread() {
        Task task = new Task();
        Assertions.assertDoesNotThrow(task::run);
    }
}
