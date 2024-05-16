package taxi;

import dictionaries.*;
import documents.*;

public class Main {
    public static void main(String[] args) {
        try {
            AccounterRepository accounterRepository = new AccounterRepository();
            accounterRepository.resetTable();

            CarRepository carRepository = new CarRepository();
            carRepository.resetTable();

            DriverRepository driverRepository = new DriverRepository();
            driverRepository.resetTable();

            OperatorRepository operatorRepository = new OperatorRepository();
            operatorRepository.resetTable();

            DriverShiftRepository driverShiftRepository = new DriverShiftRepository();
            driverShiftRepository.resetTable();

            OrderDocRepository orderDocRepository = new OrderDocRepository();
            orderDocRepository.resetTable();

            CashReportRepository cashReportRepository = new CashReportRepository();
            cashReportRepository.resetTable();

            ComplainRepository complainRepository = new ComplainRepository();
            complainRepository.resetTable();


            System.out.println(showStatus());


            Car car1 = new Car(1,CarType.CAR,"Honda", ColorType.BLACK, "AI 4433 HI");
            carRepository.insert(car1);

            Car car2 = new Car(2, CarType.SPORTCAR, "Lambargini", ColorType.RED, "IA 1233 IH");
            carRepository.insert(car2);


            Driver driver1 = new Driver(1,"Petro", "Rudenko", "AA5577");
            driverRepository.insert(driver1);
            System.out.println(driver1);

            Driver driver2 = new Driver(2,"Semen", "Shevchenko", "AA5588");
            driverRepository.insert(driver2);
            System.out.println(driver2);


            Operator operator1 = new Operator(1,"Oksana", "Shvudka");
            operatorRepository.insert(operator1);
            System.out.println(operator1);


            DriverShift driverShift1 = new DriverShift(1, driver1, car1, operator1);
            driverShiftRepository.insert(driverShift1);
            System.out.println(driverShift1);

            System.out.println(showStatus());

            DriverShift driverShift2 = new DriverShift(2,driver2, car2, operator1);
            driverShiftRepository.insert(driverShift2);
            System.out.println(driverShift2);
            System.out.println(showStatus());


            OrderDoc orderDoc1 = new OrderDoc(1,TaxiType.STANDART, 1, null,
                    "Galina", "380506385323", "Kreshatuk 1", operator1);

            System.out.println(orderDoc1);

            orderDocRepository.insert(orderDoc1);
            assignDriverShiftToOrder(driverShift1, orderDoc1);
            orderDocRepository.update(orderDoc1);

            orderDoc1.AppearDriver();
            orderDocRepository.update(orderDoc1);

            orderDoc1.PickUp();
            orderDocRepository.update(orderDoc1);

            System.out.println(showStatus());

            OrderDoc orderDoc2 = new OrderDoc(2,TaxiType.EXPRESS, 0, "2023-12-04 15:10:00",
                    "Mykol", "380683651311", "Petrivka 1", operator1);
            orderDocRepository.insert(orderDoc2);

            System.out.println(showStatus());

            assignDriverShiftToOrder(driverShift2, orderDoc2);
            orderDocRepository.update(orderDoc2);

            orderDoc2.AppearDriver();
            orderDocRepository.update(orderDoc2);

            orderDoc2.PickUp();
            orderDocRepository.update(orderDoc2);

            orderDoc1.End();
            orderDoc1.Payment(1, 120);
            orderDocRepository.update(orderDoc1);


            System.out.println(showStatus());

            driverShift1.close();
            driverShiftRepository.update(driverShift1);

            Accounter accounter1 = new Accounter(1,"Marina", "Tochna");
            accounterRepository.insert(accounter1);

            System.out.println(accounter1);

            CashReport cashReport1 = new CashReport(1,driverShift1, accounter1, 120);
            cashReportRepository.insert(cashReport1);

            System.out.println(cashReport1);

            System.out.println(showStatus());



            OrderDoc orderDoc3 = new OrderDoc(3,TaxiType.ECONOMY, 1, null,
                    "Andrew", "353873789763", "Alderwood Road 14", operator1);
            orderDocRepository.insert(orderDoc3);

            System.out.println(showStatus());


            if(!assignDriverShiftToOrder(driverShift1, orderDoc3)){
                assignDriverShiftToOrder(driverShift2, orderDoc3);
            }

            orderDoc2.End();
            orderDoc2.Payment(0, 500);
            orderDocRepository.update(orderDoc2);

            Complain complain1 = new Complain(1,ComplainType.SERVICE, orderDoc2, operator1, "Rude Driver");
            complainRepository.insert(complain1);

            System.out.println(complain1);
            System.out.println(showStatus());


            driverShift2.close();
            driverShiftRepository.update(driverShift2);


            System.out.println(showStatus());
            OrderDoc.PrintCompletedOrders();

        } catch (Exception e) {
            System.out.println("main() :: Exception occurred!!!!!!!! " + e.getMessage());
        }

        System.exit(0);
    }

    private static boolean assignDriverShiftToOrder(DriverShift driverShift, OrderDoc orderDoc) throws Exception {
        try {
            orderDoc.AssignDriver(driverShift);
            return true;
        } catch (ClosedShiftException e){
            System.out.println("Driver Shift is closed (" + driverShift + ")" + "\n\tDriver is not Assigned!");
            return false;
        } catch (BusyShiftException e) {
            System.out.println("Driver Shift is busy (" + driverShift + ")" + "\n\tDriver currently busy!");
            return false;
        }
    }

    public static String showStatus() throws Exception {
        String activeDriversStatus = "Active drivers (" + DriverShift.ActiveDrivers() + ")";
        String openedOrdersStatus = "Opened orders (" + OrderDoc.ActiveOrders() + ")";
        String freeDriversStatus = "Free drivers (" + calculateFreeDrivers() + ")";
        String line = "-------------------";

        return line + "\n" + activeDriversStatus + ". " + openedOrdersStatus + ". " + freeDriversStatus + ".";
    }

    public static int calculateFreeDrivers() throws Exception {
        int totalDrivers = DriverShift.ActiveDrivers();
        int driversWithOrders = OrderDoc.ActiveOrders();

        return totalDrivers - driversWithOrders;
    }
}