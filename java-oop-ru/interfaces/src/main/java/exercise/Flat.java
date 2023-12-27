package exercise;

// BEGIN
public class Flat implements Home {
    private final double areaCommon;
    private final int floor;

    Flat(double area, double balconyArea, int floor) {
        this.areaCommon = area + balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return areaCommon;
    }

    @Override
    public int compareTo(Home another) {
        if (this.areaCommon == another.getArea()) {
            return 0;
        }
        return this.areaCommon - another.getArea() > 0 ? 1 : -1;
    }

    public String toString() {
        return String.format("Квартира площадью %.1f метров на %d этаже", areaCommon, floor);
    }
}

// END
