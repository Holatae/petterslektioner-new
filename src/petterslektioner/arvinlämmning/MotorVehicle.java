package petterslektioner.arvinlämmning;

public abstract class MotorVehicle extends Vehicle{

    private int engineHP;
    private int numSeats;
    private String FuelType;
    private double FuelConsumption;
    private int years;

    public MotorVehicle(int numWheels, int weight, String brand, int gears) {
        super(numWheels, weight, brand, gears);
    }

    public abstract double topSpeed();

    public int getEngineHP() {
        return engineHP;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public String getFuelType() {
        return FuelType;
    }

    public double getFuelConsumption() {
        return FuelConsumption;
    }

    public int getYears() {
        return years;
    }
}
