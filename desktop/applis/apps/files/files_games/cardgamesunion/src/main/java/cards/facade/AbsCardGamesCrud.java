package cards.facade;

import cards.belote.GameBelote;
import cards.belote.HandBelote;
import cards.president.GamePresident;
import cards.president.HandPresident;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.Document;

public interface AbsCardGamesCrud {
    AbstractProgramInfos getProgramInfos();
    HandBelote belote();
    void belote(HandBelote _h);
    HandBelote belote24();
    void belote24(HandBelote _h);
    HandPresident president(int _nbStack);
    void president(int _nbStack,HandPresident _h);
    HandTarot tarot();
    void tarot(HandTarot _h);
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
