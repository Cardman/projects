package cards.gui.events;

import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerTarot;
import cards.tarot.enumerations.Handfuls;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.RadioButton;
import code.util.CustList;

public class ListenerNoHandfulTarot extends AbsListenerHandfulTarot {

    private ContainerTarot container;
    private RadioButton radio;
    private Handfuls handful;
    private CustList<RadioButton> list;

    public ListenerNoHandfulTarot(ContainerTarot _container,RadioButton _radio, Handfuls _handful, CustList<RadioButton> _list) {
        container = _container;
        radio = _radio;
        handful = _handful;
        list = _list;
    }

    @Override
    public void mouseEntered(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        for (RadioButton r: list) {
            r.setSelected(false);
        }
        container.getInfoCurrentHandful().setText(ContainerGame.EMPTY_STRING);
        container.setChoosenHandful(handful);
        radio.setSelected(true);
    }
}
