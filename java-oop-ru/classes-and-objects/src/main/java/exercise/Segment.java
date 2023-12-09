package exercise;

// BEGIN
class Segment {
    private final Point begin;
    private final Point end;
    private final Point middle;

    Segment (Point begin, Point end) {
        this.begin = begin;
        this.end = end;
        middle = new Point((end.getX() + begin.getX())/2, (end.getY() + begin.getY())/2);
    }

    public Point getBeginPoint() {
        return begin;
    }

    public Point getEndPoint() {
        return end;
    }

    public Point getMidPoint() {
        return middle;
    }
}
// END
