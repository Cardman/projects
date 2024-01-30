package cards.facade;

import cards.belote.GameBelote;
import cards.president.GamePresident;
import cards.tarot.GameTarot;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.Document;

public interface AbsCardGamesCrud {
    AbstractProgramInfos getProgramInfos();
    GameBelote belote(String _k);
    GameBelote belote(String _k, Document _d);
    void belote(String _k,GameBelote _n);
    GamePresident president(String _k);
    GamePresident president(String _k, Document _d);
    void president(String _k,GamePresident _n);
    GameTarot tarot(String _k);
    GameTarot tarot(String _k, Document _d);
    void tarot(String _k,GameTarot _n);
    String read(String _k);
    Document parse(String _k, String _c);
    String tag(String _k, String _c,Document _d);
}
