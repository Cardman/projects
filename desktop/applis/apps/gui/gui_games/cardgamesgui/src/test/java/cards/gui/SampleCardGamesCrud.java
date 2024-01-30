package cards.gui;

import cards.belote.GameBelote;
import cards.facade.AbsCardGamesCrudImpl;
import cards.president.GamePresident;
import cards.tarot.GameTarot;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;

public final class SampleCardGamesCrud extends AbsCardGamesCrudImpl {
    public SampleCardGamesCrud(AbstractProgramInfos _p) {
        super(_p);
    }

    @Override
    public void belote(String _k, GameBelote _n) {
        getBelote().put(_k, _n);
    }

    @Override
    public GameBelote belote(String _k) {
        return getBelote().getVal(_k);
    }

    @Override
    public GameBelote belote(String _k, Document _d) {
        return getBelote().getVal(_k);
    }

    @Override
    public void president(String _k, GamePresident _n) {
        getPresident().put(_k, _n);
    }

    @Override
    public GamePresident president(String _k) {
        return getPresident().getVal(_k);
    }

    @Override
    public GamePresident president(String _k, Document _d) {
        return getPresident().getVal(_k);
    }

    @Override
    public void tarot(String _k, GameTarot _n) {
        getTarot().put(_k, _n);
    }

    @Override
    public GameTarot tarot(String _k) {
        return getTarot().getVal(_k);
    }

    @Override
    public GameTarot tarot(String _k, Document _d) {
        return getTarot().getVal(_k);
    }

    @Override
    public String read(String _k) {
        return _k;
    }

    @Override
    public Document parse(String _k, String _c) {
        Document d_ = DocumentBuilder.newXmlDocument();
        d_.appendChild(d_.createElement(_c));
        return d_;
    }

    @Override
    public String tag(String _k, String _c, Document _d) {
        Element elt_ = _d.getDocumentElement();
        return elt_.getTagName();
    }
}
