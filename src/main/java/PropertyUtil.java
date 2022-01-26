import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertyUtil {
    Logger logger = LoggerFactory.getLogger(PropertyUtil.class);

    private int max;
    private int min;
    private int positiveMax;

    public PropertyUtil() {
        URL url = getClass().getResource("config.properties");
        Properties property = new Properties();
        try (FileInputStream fis = new FileInputStream(url.getPath())) {
            property.load(fis);
            positiveMax = Integer.parseInt(property.getProperty("random.int.positive.max"));
            max = Integer.parseInt(property.getProperty("random.int.max"));
            min = Integer.parseInt(property.getProperty("random.int.min"));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public int getPositiveMax() {
        return positiveMax;
    }
}
