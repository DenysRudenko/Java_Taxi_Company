package documents;

import dictionaries.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DriverShift {
    private int id;
    private String startDateTime;
    private String endDateTime;
    private int shiftOpened;
//    static private int activeDrivers = 0;
    private int driverId;
    private int carId;
    private int operatorId;

    public DriverShift() {
    }

    public DriverShift(int id, Driver driver, Car car, Operator operator) {
        this.id = id;
        this.shiftOpened = 1;
        this.setCar(car);
        this.setDriver(driver);
        this.setOperator(operator);
//        activeDrivers += 1;

        LocalDateTime startDate = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.startDateTime = startDate.format(dateFormatter);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    // Method to close the driver shift
    public void close() {
        if(this.shiftOpened == 1){

            LocalDateTime endDate = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            this.endDateTime = endDate.format(dateFormatter);

            this.shiftOpened = 0;
//            activeDrivers -= 1;
        }
    }

//    static public int ActiveDrivers(){
//        return activeDrivers;
//    }

    public void setDriver(Driver driver) {
        this.driverId = driver.getId();
    }

    public Driver getDriver() {
        DriverRepository driverRepository = new DriverRepository();
        Driver driver = driverRepository.find(Driver.class, this.driverId);

        return driver;
    }

    public void setCar(Car car) {
        this.carId = car.getId();
    }

    public Car getCar() {
        CarRepository carRepository = new CarRepository();
        Car car = carRepository.find(Car.class, this.carId);

        return car;
    }

    public void setOperator(Operator operator) {
        this.operatorId = operator.getId();
    }

    public Operator getOperator() {
        OperatorRepository operatorRepository = new OperatorRepository();
        Operator operator = operatorRepository.find(Operator.class, this.operatorId);

        return operator;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void setShiftOpened(int open) {
        shiftOpened = open;
    }

//    public static void setActiveDrivers(int activeDrivers) {
//        DriverShift.activeDrivers = activeDrivers;
//    }
//
//    public static int getActiveDrivers() {
//        return activeDrivers;
//    }

    public int getShiftOpened() {
        return shiftOpened;
    }

    @Override
    public String toString() {
        return "Driver Shift:\n"
                + "\tid: " + id + "\n"
                + "\t" + getDriver() + "\n"
                + "\t" + getCar() + "\n"
                + "\t" + getOperator() + "\n"
                + "\tStart Time: " +  getStartDateTime() + "\n"
                + "\tEnd Time: " + (shiftOpened == 1 ? getEndDateTime() : "Not closed yet");
    }

    // Reads all records from table DriverShift and counts(opened)
    public static int ActiveDrivers() throws Exception {
        int activeDrivers = 0;

        DriverShiftRepository driverShiftRepository = new DriverShiftRepository();
        DriverShift[] driverShifts = driverShiftRepository.findAll(DriverShift.class);

        for (DriverShift driverShift : driverShifts) {
            if (driverShift.getShiftOpened() == 1){
                activeDrivers++;
            }
        }
        return activeDrivers;
    }
}
