package petterslektioner.arv;

public abstract class Shape {
    private String name;
    private int corners;
    private int base;
    private int height;
    private float area;
    String shapeName;

    public Shape(String name, String shapeName, int base, int height) {
        this.name = name;
        this.base = base;
        this.height = height;
        this.shapeName = shapeName;
    }

    public int getCorners() {
        return corners;
    }

    public void setCorners(int corners) {
        this.corners = corners;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getArea(){
        return area;
    }

    public abstract float calculateArea();

    @Override
    public String toString() {
        return "Shape{" +
                "name=" + name +
                ", shapeName=" + shapeName +
                ", base=" + base +
                ", height=" + height +
                ", area=" + calculateArea() +
                '}';
    }
}
