package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {
    private final int minValue;

    public MinThread(int[] values) {
        this.minValue = Arrays.stream(values).reduce(Integer::min).orElse(0);
    }

    @Override
    public void run() {
    }

    public int getMinValue() {
        return minValue;
    }
}
// END
