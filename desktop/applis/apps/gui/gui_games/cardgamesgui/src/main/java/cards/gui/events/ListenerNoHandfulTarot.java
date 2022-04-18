package cards.gui.events;

import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerTarot;
import cards.tarot.enumerations.Handfuls;
import code.gui.*;
import code.gui.events.AbsMouseListenerEnt;
import code.util.CustList;

public class ListenerNoHandfulTarot implements AbsMouseListenerEnt {

    private ContainerTarot container;
    private AbsRadioButton radio;
    private Handfuls handful;
    private CustList<AbsRadioButton> list;

    public ListenerNoHandfulTarot(ContainerTarot _container,AbsRadioButton _radio, Handfuls _handful, CustList<AbsRadioButton> _list) {
        container = _container;
        radio = _radio;
        handful = _handful;
        list = _list;
    }

    @Override
    public void mouseEntered(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        for (AbsRadioButton r: list) {
            r.setSelected(false);
        }
        container.getInfoCurrentHandful().setText(ContainerGame.EMPTY_STRING);
        container.setChoosenHandful(handful);
        radio.setSelected(true);
    }
}
