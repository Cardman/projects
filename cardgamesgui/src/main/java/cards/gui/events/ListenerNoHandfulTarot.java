package cards.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;

import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerTarot;
import cards.tarot.enumerations.Handfuls;

public class ListenerNoHandfulTarot extends MouseAdapter {

    private ContainerTarot container;
    private JRadioButton radio;
    private Handfuls handful;

    public ListenerNoHandfulTarot(ContainerTarot _container,JRadioButton _radio, Handfuls _handful) {
        container = _container;
        radio = _radio;
        handful = _handful;
    }

    @Override
    public void mouseEntered(MouseEvent _e) {
        container.getInfoCurrentHandful().setText(ContainerGame.EMPTY_STRING);
        container.setChoosenHandful(handful);
        radio.setSelected(true);
    }
}
