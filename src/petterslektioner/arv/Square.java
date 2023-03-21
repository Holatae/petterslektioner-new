package petterslektioner.arv;

public class Square extends Shape{
    public Square(String name, int base, int height) {
        super(name, "Square", base, height);
    }

    @Override
    public float calculateArea() {
        return getBase() * getHeight();
    }

}
