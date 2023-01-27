package petterslektioner.arvinlämmning;

public abstract class Vehicle {
    private int numWheels;
    private int weight;
    private String brand;
    private int gears;

    public Vehicle(int numWheels, int weight, String brand, int gears) {
        this.numWheels = numWheels;
        this.weight = weight;
        this.brand = brand;
        this.gears = gears;
    }

    public abstract double topSpeed();

    public int getNumWheels() {
        return numWheels;
    }

    public int getWeight() {
        return weight;
    }

    public String getBrand() {
        return brand;
    }

    public int getGears() {
        return gears;
    }
}
