package cards.belote;
import cards.consts.Order;
import cards.consts.Suit;
import code.util.EnumList;


public final class DisplayingBelote {

    private boolean clockwise;
    private EnumList<Suit> suits=new EnumList<Suit>();
    private boolean decreasing=true;
    private Order orderBeforeBids=Order.TRUMP;
    public DisplayingBelote() {
        for(Suit c:Suit.couleursOrdinaires()){
            suits.add(c);
        }
    }
    public DisplayingBelote(DisplayingBelote _displayingBelote) {
        clockwise = _displayingBelote.clockwise;
        suits = new EnumList<Suit>(_displayingBelote.suits);
        decreasing = _displayingBelote.decreasing;
        orderBeforeBids = _displayingBelote.orderBeforeBids;
    }
    public void validate() {
        if (orderBeforeBids == Order.NOTHING) {
            orderBeforeBids = Order.TRUMP;
        }
        EnumList<Suit> s_ = new EnumList<Suit>(Suit.couleursOrdinaires());
        if (!Suit.equalsSuits(suits, s_)) {
            suits.clear();
            for(Suit c:Suit.couleursOrdinaires()){
                suits.add(c);
            }
        }
    }
    public boolean isClockwise() {
        return clockwise;
    }
    public void setClockwise(boolean _clockwise) {
        clockwise = _clockwise;
    }
    public EnumList<Suit> getSuits() {
        return suits;
    }
    public void setSuits(EnumList<Suit> _suits) {
        suits = _suits;
    }
    public boolean isDecreasing() {
        return decreasing;
    }
    public void setDecreasing(boolean _decreasing) {
        decreasing = _decreasing;
    }
    public Order getOrderBeforeBids() {
        return orderBeforeBids;
    }
    public void setOrderBeforeBids(Order _orderBeforeBids) {
        orderBeforeBids = _orderBeforeBids;
    }
}
