package cards.consts.sml;
import cards.consts.*;
import code.sml.Element;
import code.sml.ElementList;
import code.util.CollCapacity;
import code.util.IdList;

public final class DocumentReaderCardsCommonUtil {

    private static final String ATTR_VALUE = "value";

    private DocumentReaderCardsCommonUtil() {
    }

    public static GameType getGameType(Element _elt) {
        return EnumCardsRetrieverUtil.toGameType(_elt.getAttribute(ATTR_VALUE));
    }

    public static MixCardsChoice getMixCardsChoice(Element _elt) {
        return EnumCardsRetrieverUtil.toMixCardsChoice(_elt.getAttribute(ATTR_VALUE));
    }

    public static Order getOrder(Element _elt) {
        return EnumCardsRetrieverUtil.toOrder(_elt.getAttribute(ATTR_VALUE));
    }

    public static Suit getSuit(Element _elt) {
        return EnumCardsRetrieverUtil.toSuit(_elt.getAttribute(ATTR_VALUE));
    }

    public static IdList<Suit> getListSuit(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        IdList<Suit> list_ = new IdList<Suit>(cap_);
        for (Element c: childElements_) {
            list_.add(getSuit(c));
        }
        return list_;
    }

}