public class Randomizer {

    private static final PropertyUtil propertyUtil = new PropertyUtil();
    private static final int max = propertyUtil.getMax();
    private static final int min = propertyUtil.getMin();
    private static final int positiveMax = propertyUtil.getPositiveMax();


    public static int positiveNumber() {
        return (int) (1 + Math.random() * positiveMax);
    }

    public static int number() {
        int range = max - min;
        return (int) (Math.random() * range + min);
    }
}
