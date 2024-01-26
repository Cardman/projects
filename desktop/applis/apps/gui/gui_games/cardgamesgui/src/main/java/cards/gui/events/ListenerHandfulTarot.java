package cards.gui.events;

import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.containers.ContainerTarot;
import cards.tarot.enumerations.Handfuls;
import code.gui.*;
import code.gui.events.AbsMouseListenerEnt;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.core.StringUtil;

public class ListenerHandfulTarot implements AbsMouseListenerEnt {

    private int requiredTrumps;

    private AbsRadioButton radio;

    private ContainerTarot container;

    private Handfuls handful;
    private CustList<AbsRadioButton> list;
    public ListenerHandfulTarot(int _requiredTrumps, AbsRadioButton _radio,
            ContainerTarot _container, Handfuls _handful, CustList<AbsRadioButton> _list) {
        requiredTrumps = _requiredTrumps;
        radio = _radio;
        container = _container;
        handful = _handful;
        list = _list;
    }

    @Override
    public void mouseEntered(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        if (!radio.isEnabled()) {
            return;
        }
        for (AbsRadioButton r: list) {
            r.setSelected(false);
        }
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        String mes_ = container.getMessages().getVal(WindowCards.REMOVE_TRUMPS_HANDFUL);
        int exces_ = container.getCurrentIncludedTrumps().total()-requiredTrumps;
        container.getInfoCurrentHandful().setText(StringUtil.simpleStringsFormat(mes_, Long.toString(exces_), Games.toString(handful,lg_)));
        container.setChoosenHandful(handful);
        radio.setSelected(true);
    }
}
