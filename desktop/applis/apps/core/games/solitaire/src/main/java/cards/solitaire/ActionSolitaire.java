package cards.solitaire;

public final class ActionSolitaire {
    private int fromIndex;
    private int cardIndex;
    private int toIndex;

    public int getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(int _p) {
        this.fromIndex = _p;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public void setCardIndex(int _p) {
        this.cardIndex = _p;
    }

    public int getToIndex() {
        return toIndex;
    }

    public void setToIndex(int _p) {
        this.toIndex = _p;
    }
}
