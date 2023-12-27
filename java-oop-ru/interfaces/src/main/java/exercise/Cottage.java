package exercise;

// BEGIN

class Cottage implements Home {
    private final double area;
    private final int floorCount;

    Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    public String toString() {
        return String.format("%d этажный коттедж площадью %.1f метров", floorCount, area);
    }

    public int compareTo(Home another) {
        if (this.area == another.getArea()) {
            return 0;
        }
        return this.area - another.getArea() > 0 ? 1 : -1;
    }
}
// END
