package io.github.robbietree.infra;

public class DualWrapper {
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "DualWrapper{" +
                "count=" + count +
                '}';
    }
}
