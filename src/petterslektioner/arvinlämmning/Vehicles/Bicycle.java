package petterslektioner.arvinlämmning.Vehicles;

import petterslektioner.arvinlämmning.Vehicle;

public class Bicycle extends Vehicle {

    private int frameSize;
    public Bicycle(int numWheels, int weight, String brand, int gears) {
        super(numWheels, weight, brand, gears);
    }

    @Override
    public double topSpeed() {
        return 30;
    }
}
