package dictionaries;

public final class Driver {
    private int id;
    private String firstName;
    private String lastName;
    private String license;

    public Driver() {
    }

    public Driver(int id, String firstName, String lastName, String license) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.license = license;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @Override
    public String toString() {
        return "Driver: " + this.firstName + " " + this.lastName + " (License: " + license + ")";
    }
}
