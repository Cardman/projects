package cards.tarot;
import cards.consts.DisplayingCommon;
import cards.consts.Suit;


public final class DisplayingTarot {

    private final DisplayingCommon displaying;
    public DisplayingTarot() {
        displaying = new DisplayingCommon();
        displaying.setSuits(Suit.couleursDefinies());
    }
    public DisplayingTarot(DisplayingTarot _displayingTarot) {
        displaying = new DisplayingCommon(_displayingTarot.displaying);
    }
    public void validate() {
        displaying.validate(Suit.couleursDefinies());
    }

    public DisplayingCommon getDisplaying() {
        return displaying;
    }

}
