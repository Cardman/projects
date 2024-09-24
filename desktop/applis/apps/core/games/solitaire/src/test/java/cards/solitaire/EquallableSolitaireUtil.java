package cards.solitaire;

import code.util.StringMap;
import org.junit.Assert;

public abstract class EquallableSolitaireUtil {

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }
    public static void assertTrue(String _mess,boolean _value) {
        Assert.assertTrue(_mess,_value);
    }
    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(CardSolitaire _expected, CardSolitaire _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(SolitaireType _expected, SolitaireType _result) {
        Assert.assertSame(_expected, _result);
    }

    public static ActionSolitaire action(int _from, int _card, int _to) {
        ActionSolitaire ac_ = new ActionSolitaire();
        ac_.setFromIndex(_from);
        ac_.setCardIndex(_card);
        ac_.setToIndex(_to);
        return ac_;
    }

    public static void tryPlay(AbsDealSolitaire _d, ActionSolitaire _ac) {
        assertTrue(_d.canBeSelected(_ac.getFromIndex(), _ac.getCardIndex()));
        assertTrue(_d.canBePlayed(_ac.getFromIndex(), _ac.getCardIndex(), _ac.getToIndex()));
        play(_d, _ac);
    }

    public static void play(AbsDealSolitaire _d, ActionSolitaire _ac) {
        _d.play(_ac.getFromIndex(), _ac.getCardIndex(), _ac.getToIndex());
        _d.getActions().add(_ac);
    }

    public static StringMap<String> mes() {
        StringMap<String> ms_ = new StringMap<String>();
        ms_.addEntry(AbsDealSolitaire.NOT_PLAYABLE,"_");
        ms_.addEntry(AbsDealSolitaire.BAD_HAND_COUNT,":");
        ms_.addEntry(AbsDealSolitaire.BAD_CARD_HAND_COUNT,":");
        ms_.addEntry(AbsDealSolitaire.BAD_CARD_UNIT_COUNT,":");
        ms_.addEntry(AbsDealSolitaire.CANNOT_BE_SELECTED,"_");
        ms_.addEntry(AbsDealSolitaire.CANNOT_BE_PLAYED,"_");
        return ms_;
    }
}
