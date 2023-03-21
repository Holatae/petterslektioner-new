package petterslektioner.arvinlämmning.Vehicles.MotorVehicles;

import petterslektioner.arvinlämmning.MotorVehicle;

public class Car extends MotorVehicle {
    public Car(int numWheels, int weight, String brand, int gears) {
        super(numWheels, weight, brand, gears);
    }

    @Override
    public double topSpeed() {
        switch (getGears()){
            case 1 -> {
                return 10;
            }
            case 2 -> {
                return 30;
            }
            default -> {
                return getGears() * (getEngineHP()/4);
            }
        }
    }
}
