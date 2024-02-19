package cards.gui;

import cards.belote.GameBelote;
import cards.belote.HandBelote;
import cards.facade.AbsCardGamesCrudImpl;
import cards.facade.DefCardGamesCrud;
import cards.president.GamePresident;
import cards.president.HandPresident;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;

public final class SampleCardGamesCrud extends AbsCardGamesCrudImpl {
    public SampleCardGamesCrud(AbstractProgramInfos _p) {
        super(_p);
        president(1,new HandPresident());
    }

    @Override
    public HandBelote belote() {
        return DefCardGamesCrud.sanitize(getHandBelote());
    }

    @Override
    public void belote(HandBelote _h) {
        setHandBelote(_h);
    }

    @Override
    public HandPresident president(int _nbStack) {
        return DefCardGamesCrud.sanitize(_nbStack,getHandPresident().getVal(Integer.toString(_nbStack)));
    }

    @Override
    public void president(int _nbStack, HandPresident _h) {
        getHandPresident().put(Integer.toString(_nbStack),_h);
    }

    @Override
    public HandTarot tarot() {
        return DefCardGamesCrud.sanitize(getHandTarot());
    }

    @Override
    public void tarot(HandTarot _h) {
        setHandTarot(_h);
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
