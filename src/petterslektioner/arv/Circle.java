package petterslektioner.arv;

public class Circle extends Shape {
    public Circle(String name, int base, int height) {
        super(name, "Circle", base, height);
    }

    @Override
    public float calculateArea() {
        return (float) (Math.pow(getBase()/2, 2) * Math.PI);
    }
}
