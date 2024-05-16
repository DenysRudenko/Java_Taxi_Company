package documents;

import dictionaries.Accounter;
import dictionaries.AccounterRepository;
import dictionaries.DriverRepository;

public class CashReport {
    private int id;
    private double cashAmmount;

    private  int driverShiftId;
    private int accounterId;

    public CashReport() {
    }

    public CashReport(int id, DriverShift driverShift, Accounter accounter, double cashAmmount) {
        this.id = id;
        this.setDriverShift(driverShift);
        this.setAccounter(accounter);
        this.cashAmmount = cashAmmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DriverShift getDriverShift() {
        DriverShiftRepository driverShiftRepository = new DriverShiftRepository();
        DriverShift driverShift = driverShiftRepository.find(DriverShift.class, this.driverShiftId);

        return driverShift;
    }

    public void setDriverShift(DriverShift driverShift) {
        this.driverShiftId = driverShift.getId();
    }

    public Accounter getAccounter() {
        AccounterRepository accounterRepository = new AccounterRepository();
        Accounter accounter = accounterRepository.find(Accounter.class, this.accounterId);

        return accounter;
    }

    public void setAccounter(Accounter accounter) {
        this.accounterId = accounter.getId();
    }

    public double getCashAmmount() {
        return cashAmmount;
    }

    public void setCashAmmount(double cashAmmount) {
        this.cashAmmount = cashAmmount;
    }

    public int getDriverShiftId() {
        return driverShiftId;
    }

    public void setDriverShiftId(int driverShiftId) {
        this.driverShiftId = driverShiftId;
    }

    public int getAccounterId() {
        return accounterId;
    }

    public void setAccounterId(int accounterId) {
        this.accounterId = accounterId;
    }

    @Override
    public String toString() {
        return "CashReport{" +
                "id=" + id +
                ", cashAmmount=" + cashAmmount +
                ", driverShiftId=" + driverShiftId +
                ", accounterId=" + accounterId +
                '}';
    }
}

