package cards.consts.sml;
import cards.consts.*;
import code.sml.*;
import code.sml.core.DocumentReaderCoreUtil;
import code.util.CollCapacity;
import code.util.IdList;

public final class DocumentReaderCardsCommonUtil {

    private DocumentReaderCardsCommonUtil() {
    }

    public static Document strToDocDoc(String _str) {
        Document doc_ = DocumentBuilder.parseSax(_str);
        if (doc_ == null) {
            FullDocument d_ = DocumentBuilder.newDocumentBuilder().newDocument();
            d_.appendChild(d_.createElement("_"));
            return d_;
        }
        return doc_;
    }
    public static GameType getGameType(Element _elt) {
        return EnumCardsRetrieverUtil.toGameType(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    public static MixCardsChoice getMixCardsChoice(Element _elt) {
        return EnumCardsRetrieverUtil.toMixCardsChoice(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    public static Order getOrder(Element _elt) {
        return EnumCardsRetrieverUtil.toOrder(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
    }

    public static Suit getSuit(Element _elt) {
        return EnumCardsRetrieverUtil.toSuit(_elt.getAttribute(DocumentReaderCoreUtil.VALUE));
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