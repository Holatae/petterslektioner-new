package petterslektioner.arv;

public class Main {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle("nancy", 15, 4);
        Square square = new Square("bob", 4, 4);
        Circle circle = new Circle("Edwin", 4, 4);

        System.out.println(rectangle.toString());
        System.out.println(square.toString());
        System.out.println(circle.toString());

    }
}
