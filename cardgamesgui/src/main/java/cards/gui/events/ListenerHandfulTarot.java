package cards.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;

import code.util.StringList;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerTarot;
import cards.tarot.enumerations.Handfuls;

public class ListenerHandfulTarot extends MouseAdapter {

    private int requiredTrumps;

    private JRadioButton radio;

    private ContainerTarot container;

    private Handfuls handful;

    public ListenerHandfulTarot(int _requiredTrumps, JRadioButton _radio,
            ContainerTarot _container, Handfuls _handful) {
        requiredTrumps = _requiredTrumps;
        radio = _radio;
        container = _container;
        handful = _handful;
    }

    @Override
    public void mouseEntered(MouseEvent _e) {
        if (!radio.isEnabled()) {
            return;
        }
        String mes_ = container.getMessages().getVal(MainWindow.REMOVE_TRUMPS_HANDFUL);
        int exces_ = container.getCurrentIncludedTrumps().total()-requiredTrumps;
        container.getInfoCurrentHandful().setText(StringList.simpleStringsFormat(mes_, Long.toString(exces_), handful.toString()));
        container.setChoosenHandful(handful);
        radio.setSelected(true);
    }
}
