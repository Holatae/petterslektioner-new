package petterslektioner.arv;

public class Rectangle extends Shape {

    public Rectangle(String name, int base, int height) {
        super(name, "Rectangle", base, height);
    }

    @Override
    public float calculateArea() {
        return getBase() * getHeight();
    }
}
