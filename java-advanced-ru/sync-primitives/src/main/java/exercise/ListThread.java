package exercise;

import java.util.Random;

// BEGIN
public class ListThread extends Thread {
    private final SafetyList safetyList;

    public ListThread(SafetyList safetyList) {
        this.safetyList = safetyList;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000 ; i++) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            safetyList.add(new Random().nextInt(999999));
        }
    }
}
// END
