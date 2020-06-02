package cards.consts.sml;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Order;
import cards.consts.Suit;
import code.sml.Element;
import code.sml.ElementList;
import code.util.CollCapacity;
import code.util.EnumList;
import code.util.StringList;
public final class DocumentReaderCardsCommonUtil {

    private static final String ATTR_VALUE = "value";

    public static GameType getGameType(Element _elt) {
        for (GameType e: GameType.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return GameType.RANDOM;
    }

    public static MixCardsChoice getMixCardsChoice(Element _elt) {
        for (MixCardsChoice e: MixCardsChoice.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return MixCardsChoice.NEVER;
    }

    public static Order getOrder(Element _elt) {
        for (Order e: Order.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return Order.NOTHING;
    }

    public static Suit getSuit(Element _elt) {
        for (Suit e: Suit.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return Suit.UNDEFINED;
    }

    public static EnumList<Suit> getListSuit(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EnumList<Suit> list_ = new EnumList<Suit>(cap_);
        for (Element c: childElements_) {
            list_.add(getSuit(c));
        }
        return list_;
    }

}