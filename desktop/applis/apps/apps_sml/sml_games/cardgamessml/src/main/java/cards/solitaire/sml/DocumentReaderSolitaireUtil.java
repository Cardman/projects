package cards.solitaire.sml;

import cards.solitaire.*;
import code.sml.*;
import code.sml.core.*;
import code.util.*;
import code.util.core.*;

public final class DocumentReaderSolitaireUtil {
    private DocumentReaderSolitaireUtil() {
    }
    public static AbsDealSolitaire getGameSolitaire(Document _doc) {
        return getDealSolitaire(_doc.getDocumentElement());
    }
    private static AbsDealSolitaire getDealSolitaire(Element _element) {
        SolitaireType sol_ = SolitaireType.getSolitaireTypeByName(_element.getAttribute(DocumentReaderCoreUtil.FIELD));
        if (sol_ == null) {
            return null;
        }
        AbsDealSolitaire object_ = init(sol_);
        object_.setHandsBegin(new CustList<HandSolitaire>());
        object_.setActions(new CustList<ActionSolitaire>());
        getDealSolitaire(object_,_element);
        return object_;
    }

    public static AbsDealSolitaire init(SolitaireType _type) {
        if (_type == SolitaireType.CLASSIC) {
            return new DealClassic();
        }
        if (_type == SolitaireType.FREECELL) {
            return new DealFreeCell();
        }
        return new DealSpider();
    }
    private static void getDealSolitaire(AbsDealSolitaire _object, Element _element) {
        ElementList childElements_ = _element.getChildElements();
        for (Element c: childElements_) {
            getDealSolitaire(_object,c.getAttribute(DocumentReaderCoreUtil.FIELD),c);
        }
    }
    private static void getDealSolitaire(AbsDealSolitaire _object, String _fieldName, Element _element) {
        if (StringUtil.quickEq(_fieldName, DocumentWriterSolitaireUtil.FIELD_CARDS)) {
            _object.setHandsBegin(getListHandSolitaire(_element));
            return;
        }
        _object.setActions(getListActionSolitaire(_element));
    }

    private static CustList<HandSolitaire> getListHandSolitaire(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<HandSolitaire> list_ = new CustList<HandSolitaire>(cap_);
        for (Element c: childElements_) {
            list_.add(getListCardSolitaire(c));
        }
        return list_;
    }
    private static HandSolitaire getListCardSolitaire(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        IdList<CardSolitaire> ls_ = new IdList<CardSolitaire>(cap_);
        for (Element c: childElements_) {
            ls_.add(getCardSolitaire(c));
        }
        HandSolitaire list_ = new HandSolitaire();
        list_.setCards(ls_);
        return list_;
    }
    private static CardSolitaire getCardSolitaire(Element _elt) {
        return SolitaireCardsRetrieverUtil.toCardSolitaire(_elt.getAttribute(DocumentWriterCoreUtil.FIELD));
    }
    private static CustList<ActionSolitaire> getListActionSolitaire(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<ActionSolitaire> list_ = new CustList<ActionSolitaire>(cap_);
        for (Element c: childElements_) {
            list_.add(getActionSolitaire(c));
        }
        return list_;
    }
    private static ActionSolitaire getActionSolitaire(Element _elt) {
        StringList parts_ = StringUtil.splitStrings(_elt.getAttribute(DocumentWriterCoreUtil.FIELD), DocumentWriterSolitaireUtil.SEP);
        ActionSolitaire ac_ = new ActionSolitaire();
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                ac_.setFromIndex(NumberUtil.parseInt(parts_.get(i)));
            }
            if (i == 1) {
                ac_.setCardIndex(NumberUtil.parseInt(parts_.get(i)));
            }
            if (i == 2) {
                ac_.setToIndex(NumberUtil.parseInt(parts_.get(i)));
            }
        }
        return ac_;
    }
    
}
