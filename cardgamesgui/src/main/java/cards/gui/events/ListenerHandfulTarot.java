package cards.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;

import cards.facade.Games;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerTarot;
import cards.tarot.enumerations.Handfuls;
import code.gui.RadioButton;
import code.util.CustList;
import code.util.StringList;

public class ListenerHandfulTarot extends MouseAdapter {

    private int requiredTrumps;

    private RadioButton radio;

    private ContainerTarot container;

    private Handfuls handful;
    private CustList<RadioButton> list;
    public ListenerHandfulTarot(int _requiredTrumps, RadioButton _radio,
            ContainerTarot _container, Handfuls _handful, CustList<RadioButton> _list) {
        requiredTrumps = _requiredTrumps;
        radio = _radio;
        container = _container;
        handful = _handful;
        list = _list;
    }

    @Override
    public void mouseEntered(MouseEvent _e) {
        if (!radio.isEnabled()) {
            return;
        }
        for (RadioButton r: list) {
            r.setSelected(false);
        }
        String lg_ = container.getOwner().getLanguageKey();
        String mes_ = container.getMessages().getVal(MainWindow.REMOVE_TRUMPS_HANDFUL);
        int exces_ = container.getCurrentIncludedTrumps().total()-requiredTrumps;
        container.getInfoCurrentHandful().setText(StringList.simpleStringsFormat(mes_, Long.toString(exces_), Games.toString(handful,lg_)));
        container.setChoosenHandful(handful);
        radio.setSelected(true);
    }
}
