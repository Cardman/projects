package cards.gui.containers;

import cards.gui.labels.*;
import cards.solitaire.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.sml.util.*;
import code.util.*;

public final class ContainerSolitaireUtil {
    private ContainerSolitaireUtil() {
    }

    public static CustList<SolitaireGraphicCard> getGraphicCardsGene(AbstractProgramInfos _fact, TranslationsLg _lg, CustList<CardSolitaire> _hand, boolean _group, int _card) {
        CustList<SolitaireGraphicCard> list_;
        list_ = new CustList<SolitaireGraphicCard>();
        int len_ = _hand.size();
        for (int i = 0; i < len_; i++) {
            SolitaireGraphicCard carte_ = prepare(_fact, _lg, _hand.get(i), i + 1 == len_, _group && i == _card);
            list_.add(carte_);
        }
        return list_;
    }

    public static SolitaireGraphicCard prepare(AbstractProgramInfos _fact, TranslationsLg _lg, CardSolitaire _c, boolean _full, boolean _sel) {
        AbstractImageFactory imageFactory_ = _fact.getImageFactory();
        SolitaireGraphicCard carte_=new SolitaireGraphicCard( _c, _fact, _lg, _sel);
        carte_.setPreferredSize(!_full);
        AbsMetaLabelCard.paintCard(imageFactory_, carte_);
        return carte_;
    }
}
