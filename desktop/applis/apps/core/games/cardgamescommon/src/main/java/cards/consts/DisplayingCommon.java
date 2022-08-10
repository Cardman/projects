package cards.consts;

import code.util.EnumList;

public final class DisplayingCommon {

    private boolean clockwise;
    private EnumList<Suit> suits=new EnumList<Suit>();
    private boolean decreasing=true;
    public DisplayingCommon() {
    }
    public DisplayingCommon(DisplayingCommon _other) {
        clockwise = _other.isClockwise();
        decreasing = _other.isDecreasing();
        suits = new EnumList<Suit>(_other.getSuits());
    }
    public void validate(EnumList<Suit> _input) {
        EnumList<Suit> s_ = new EnumList<Suit>(_input);
        if (!Suit.equalsSuits(suits, s_)) {
            suits.clear();
            for(Suit c:_input){
                suits.add(c);
            }
        }
    }

    public EnumList<Suit> getSuits() {
        return suits;
    }

    public void setSuits(EnumList<Suit> _p) {
        this.suits = _p;
    }

    public boolean isClockwise() {
        return clockwise;
    }

    public void setClockwise(boolean _p) {
        this.clockwise = _p;
    }

    public boolean isDecreasing() {
        return decreasing;
    }

    public void setDecreasing(boolean _p) {
        this.decreasing = _p;
    }
}
