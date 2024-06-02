package exercise;

import java.util.ArrayList;
import java.util.List;

class SafetyList {
    // BEGIN
    private List<Integer> list = new ArrayList<>();

    public synchronized void add(Integer value) {
        list.add(value);
    }

    public Integer get(Integer variable) {
        return list.get(variable);
    }

    public Integer getSize() {
        return list.size();
    }
    // END
}
