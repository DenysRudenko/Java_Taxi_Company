package documents;

import dictionaries.Operator;
import dictionaries.OperatorRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderDoc {
    private int id;
    private String taxitype;
    private String createDateTime;
    private int isCurrentDateTime;
    private String plannedDateTime;
    private String confirmDateTime;
    private String appearanceDateTime;
    private String pickUpDateTime;
    private String endDateTime;
    private int inCash;
    private double amount;
    private String customerName;
    private String customerPhone;
    private String destinationPlace;
    private int driverShiftId;
    private int operatorId;
    private int orderOpened;
//    static private int activeOrders = 0;

    public OrderDoc() {
    }

    public OrderDoc(int id, TaxiType taxitype, int isCurrentDateTime, String plannedDateTime, String customerName, String customerPhone, String destinationPlace, Operator operator) {
        this.id = id;
        this.taxitype = taxitype.toString();
        this.isCurrentDateTime = isCurrentDateTime;
        this.plannedDateTime = plannedDateTime;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.destinationPlace = destinationPlace;
        this.setOperator(operator);
        this.orderOpened = 1;

        LocalDateTime createDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.createDateTime = createDateTime.format(dateFormatter);

//        activeOrders += 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaxitype() {
        return taxitype;
    }

    public void setTaxitype(String taxitype) {
        this.taxitype = taxitype;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public int getIsCurrentDateTime() {
        return isCurrentDateTime;
    }

    public void setIsCurrentDateTime(int isCurrentDateTime) {
        this.isCurrentDateTime = isCurrentDateTime;
    }

    public String getPlannedDateTime() {
        return plannedDateTime;
    }

    public void setPlannedDateTime(String plannedDateTime) {
        this.plannedDateTime = plannedDateTime;
    }

    public String getConfirmDateTime() {
        return confirmDateTime;
    }

    public void setConfirmDateTime(String confirmDateTime) {
        this.confirmDateTime = confirmDateTime;
    }

    public String getAppearanceDateTime() {
        return appearanceDateTime;
    }

    public void setAppearanceDateTime(String appearanceDateTime) {
        this.appearanceDateTime = appearanceDateTime;
    }

    public String getPickUpDateTime() {
        return pickUpDateTime;
    }

    public void setPickUpDateTime(String pickUpDateTime) {
        this.pickUpDateTime = pickUpDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public int getInCash() {
        return inCash;
    }

    public void setInCash(int inCash) {
        this.inCash = inCash;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getDestinationPlace() {
        return destinationPlace;
    }

    public void setDestinationPlace(String destinationPlace) {
        this.destinationPlace = destinationPlace;
    }

    public int getDriverShiftId() {
        return driverShiftId;
    }

    public void setDriverShiftId(int driverShiftId) {
        this.driverShiftId = driverShiftId;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public int getOrderOpened() {
        return orderOpened;
    }

    public void setOrderOpened(int orderOpened) {
        this.orderOpened = orderOpened;
    }

    public void AssignDriver(DriverShift driverShift) throws Exception {

        if(driverShift.getShiftOpened() == 0){
            throw new ClosedShiftException();
        }

        if(OrderDoc.ActiveOrdersByDriverShift(driverShift) > 0){
            throw new BusyShiftException();
        }

        this.setDriverShift(driverShift);

        LocalDateTime confirmDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.confirmDateTime = confirmDateTime.format(dateFormatter);
    }

    public void AppearDriver(){
        LocalDateTime appearanceDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.appearanceDateTime = appearanceDateTime.format(dateFormatter);
    }

    public void PickUp(){
        LocalDateTime pickUpDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.pickUpDateTime = pickUpDateTime.format(dateFormatter);
    }

    public void Payment(int inCash, double amount){
        this.inCash = inCash;
        this.amount = amount;
    }
    public void End(){
        if(this.orderOpened == 1){
            LocalDateTime endDateTime = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            this.endDateTime = endDateTime.format(dateFormatter);

            this.orderOpened = 0;
        }
    }

    public void setDriverShift(DriverShift driverShift) {
        this.driverShiftId = driverShift.getId();
    }

    public DriverShift getDriverShift() {
        DriverShiftRepository driverShiftRepository = new DriverShiftRepository();
        DriverShift driverShift = driverShiftRepository.find(DriverShift.class, this.driverShiftId);

        return driverShift;
    }

  public void setOperator(Operator operator){
        this.operatorId = operator.getId();
  }

  public Operator getOperator() {
      OperatorRepository operatorRepository = new OperatorRepository();
      Operator operator = operatorRepository.find(Operator.class, this.operatorId);

      return operator;
  }

    // Reads all records from table OrderDoc and counts(opened)
    public static int ActiveOrders() throws Exception {
        int activeOrders = 0;

        OrderDocRepository orderDocRepository = new OrderDocRepository();
        OrderDoc[] orderDocs = orderDocRepository.findAll(OrderDoc.class);

        for (OrderDoc orderDoc : orderDocs) {
            if (orderDoc.getOrderOpened() == 1){
                activeOrders++;
            }
        }
        return activeOrders;
    }

    public static int ActiveOrdersByDriverShift(DriverShift driverShift) throws Exception {
        int activeOrders = 0;

        OrderDocRepository orderDocRepository = new OrderDocRepository();
        OrderDoc[] orderDocs = orderDocRepository.findAll(OrderDoc.class);

        for (OrderDoc orderDoc : orderDocs) {
            if ((orderDoc.getOrderOpened() == 1) && (orderDoc.getDriverShiftId() == driverShift.getId())){
                activeOrders++;
            }
        }
        return activeOrders;
    }

    public static void PrintCompletedOrders() throws Exception {

        OrderDocRepository orderDocRepository = new OrderDocRepository();
        OrderDoc[] orderDocs = orderDocRepository.findAll(OrderDoc.class);

        System.out.println("\nCompleted Orders:\n");

        for (OrderDoc orderDoc : orderDocs) {
            if (orderDoc.getOrderOpened() == 0){
                System.out.println(orderDoc);
            }
        }
    }

    @Override
    public String toString() {
        return "OrderDoc: \n"
                + "\tid=" + id + "\n"
                + "\ttaxitype: '" + taxitype + "'\n"
                + "\tcreatedDateTime: '" + createDateTime + "'\n"
                + "\tisCurrentDateTime: " + isCurrentDateTime + "\n"
                + "\tplannedDateTime: '" + plannedDateTime + "'\n"
                + "\tconfirmDateTime: '" + confirmDateTime + "'\n"
                + "\tappearanceDateTime: '" + appearanceDateTime + "'\n"
                + "\tpickUpDateTime: '" + pickUpDateTime + "'\n"
                + "\tendDateTime: '" + endDateTime + "'\n"
                + "\tinCash: " + inCash
                + "\t, amount: " + amount + "$\n"
                + "\tcustomerName: '" + customerName + "'\n"
                + "\tcustomerPhone: '" + customerPhone + "'\n"
                + "\tdestinationPlace: '" + destinationPlace + "'\n"
                + "\tdriverShiftId: " + driverShiftId +
                ", operatorId: " + operatorId + "\n"
                + "\torderOpened: " + orderOpened;
    }
}
