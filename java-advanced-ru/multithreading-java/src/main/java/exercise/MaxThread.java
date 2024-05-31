package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread {
    private final int maxValue;

    public MaxThread(int[] values) {
        this.maxValue = Arrays.stream(values).reduce(Integer::max).orElse(0);
    }

    @Override
    public void run() {
    }

    public int getMaxValue() {
        return maxValue;
    }
}
// END
