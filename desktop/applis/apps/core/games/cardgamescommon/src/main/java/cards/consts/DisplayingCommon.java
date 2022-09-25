package cards.consts;

import code.util.IdList;

public final class DisplayingCommon {

    private boolean clockwise;
    private IdList<Suit> suits=new IdList<Suit>();
    private boolean decreasing=true;
    public DisplayingCommon() {
    }
    public DisplayingCommon(DisplayingCommon _other) {
        clockwise = _other.isClockwise();
        decreasing = _other.isDecreasing();
        suits = new IdList<Suit>(_other.getSuits());
    }
    public void validate(IdList<Suit> _input) {
        IdList<Suit> s_ = new IdList<Suit>(_input);
        if (!Suit.equalsSuits(suits, s_)) {
            suits.clear();
            for(Suit c:_input){
                suits.add(c);
            }
        }
    }

    public IdList<Suit> getSuits() {
        return suits;
    }

    public void setSuits(IdList<Suit> _p) {
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
