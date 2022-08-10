package cards.belote;
import cards.consts.DisplayingCommon;
import cards.consts.Order;
import cards.consts.Suit;


public final class DisplayingBelote {

    private final DisplayingCommon displaying;
    private Order orderBeforeBids=Order.TRUMP;
    public DisplayingBelote() {
        displaying = new DisplayingCommon();
        displaying.setSuits(Suit.couleursOrdinaires());
    }
    public DisplayingBelote(DisplayingBelote _displayingBelote) {
        displaying = new DisplayingCommon(_displayingBelote.displaying);
        orderBeforeBids = _displayingBelote.orderBeforeBids;
    }
    public void validate() {
        if (orderBeforeBids == Order.NOTHING) {
            orderBeforeBids = Order.TRUMP;
        }
        displaying.validate(Suit.couleursOrdinaires());
    }

    public DisplayingCommon getDisplaying() {
        return displaying;
    }

    public Order getOrderBeforeBids() {
        return orderBeforeBids;
    }
    public void setOrderBeforeBids(Order _orderBeforeBids) {
        orderBeforeBids = _orderBeforeBids;
    }
}
