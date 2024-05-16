package dictionaries;

public class Car {
    private int id;
    private String carType;
    private String brand;
    private String colorType;
    private String regNumber;

    public Car() {
    }

    public Car(int id, CarType carType, String brand, ColorType colorType, String regNumber) {
        this.id = id;
        this.carType = carType.toString();
        this.brand = brand;
        this.colorType = colorType.toString();
        this.regNumber = regNumber;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColorType() {
        return colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    @Override
    public String toString() {
        return "Car: type: " + carType
                + ", brand: " + brand
                +", color: " + colorType +
                ", registration number: " + regNumber;
    }
}
