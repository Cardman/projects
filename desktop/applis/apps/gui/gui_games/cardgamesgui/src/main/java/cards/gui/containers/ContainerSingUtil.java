package cards.gui.containers;

import cards.gui.WindowCardsInt;
import cards.gui.labels.AbsMetaLabelCard;
import cards.gui.labels.GraphicCard;
import cards.gui.labels.IntCardConverter;
import code.gui.images.AbstractImageFactory;
import code.sml.util.TranslationsLg;
import code.util.CustList;

public final class ContainerSingUtil<T> {
    private final IntCardConverter<T> converter;

    public ContainerSingUtil(IntCardConverter<T> _conv) {
        converter = _conv;
    }

    public CustList<GraphicCard<T>> getGraphicCardsGene(WindowCardsInt _fact, TranslationsLg _lg, CustList<T> _hand) {
        CustList<GraphicCard<T>> list_;
        list_ = new CustList<GraphicCard<T>>();
        boolean entered_ = false;
        for(T c: _hand) {
            GraphicCard<T> carte_ = prepare(_fact, _lg, c, !entered_);
//            int w_ = carte_.getWidth();
//            int h_ = carte_.getHeight();
//            AbstractImage img_ = imageFactory_.newImageArgb(w_, h_);
//            img_.setFont(carte_.getPaintableLabel());
//            carte_.paintComponent(img_);
//            carte_.setIcon(imageFactory_,img_);
            list_.add(carte_);
            entered_ = true;
        }
        return list_;
    }

    public GraphicCard<T> prepare(WindowCardsInt _fact, TranslationsLg _lg, T _c, boolean _full) {
        AbstractImageFactory imageFactory_ = _fact.getImageFactory();
        GraphicCard<T> carte_=new GraphicCard<T>(converter, _c, _full, _fact.getFrames(), _lg);
        carte_.setPreferredSize(!_full);
        AbsMetaLabelCard.paintCard(imageFactory_, carte_);
        return carte_;
    }
}
