package documents;

import dictionaries.Operator;
import dictionaries.OperatorRepository;

public class Complain {
    private int id;
    private String complainType;
    private String message;
    private int orderDocId;
    private int operatorId;

    public Complain() {
    }

    public Complain(int id, ComplainType complainType, OrderDoc orderDoc, Operator operator, String message) {
        this.id = id;
        this.complainType = complainType.toString();
        this.setOrderDoc(orderDoc);
        this.setOperator(operator);
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComplainType() {
        return complainType;
    }

    public void setComplainType(String complainType) {
        this.complainType = complainType;
    }

    public OrderDoc getOrderDoc() {
        OrderDocRepository orderDocRepository = new OrderDocRepository();
        OrderDoc orderDoc = orderDocRepository.find(OrderDoc.class, this.orderDocId);

        return orderDoc;
    }

    public void setOrderDoc(OrderDoc orderDoc) {
        this.orderDocId = orderDoc.getId();
    }

    public Operator getOperator() {
        OperatorRepository operatorRepository = new OperatorRepository();
        Operator operator = operatorRepository.find(Operator.class, this.operatorId);

        return operator;
    }

    public void setOperator(Operator operator) {
        this.operatorId = operator.getId();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getOrderDocId() {
        return orderDocId;
    }

    public void setOrderDocId(int orderDocId) {
        this.orderDocId = orderDocId;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    @Override
    public String toString() {
        return "Complain:\n"
                + "\tid: " + id + "\n"
                + "\tcomplainType: '" + complainType + "'\n"
                + "\tmessage: '" + message + "'\n"
                + "\torderDocId: " + orderDocId + "\n"
                + "\toperatorId: " + operatorId;
    }
}
