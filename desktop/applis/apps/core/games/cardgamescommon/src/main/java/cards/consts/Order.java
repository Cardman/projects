package cards.consts;


import code.util.IdList;

public enum Order {
    TRUMP("1"),SUIT("2"), NOTHING("0");
    private final String orderSt;

    Order(String _o) {
        this.orderSt = _o;
    }

    public String getOrderSt() {
        return orderSt;
    }
    public static IdList<Order> all() {
        IdList<Order> all_ = new IdList<Order>();
        all_.add(TRUMP);
        all_.add(SUIT);
        all_.add(NOTHING);
        return all_;
    }
}
