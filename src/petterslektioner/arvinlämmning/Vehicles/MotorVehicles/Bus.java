package petterslektioner.arvinlämmning.Vehicles.MotorVehicles;

import petterslektioner.arvinlämmning.MotorVehicle;

public class Bus extends MotorVehicle {
    public Bus(int numWheels, int weight, String brand, int gears) {
        super(numWheels, weight, brand, gears);
    }

    @Override
    public double topSpeed() {

        return 100;
    }
}
