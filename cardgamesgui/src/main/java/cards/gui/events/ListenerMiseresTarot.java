package cards.gui.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import cards.gui.containers.ContainerTarot;
import cards.tarot.enumerations.Miseres;

public class ListenerMiseresTarot implements ActionListener {

    private ContainerTarot container;
    private JCheckBox check;
    private Miseres miseres;
    public ListenerMiseresTarot(ContainerTarot _container,JCheckBox _check,Miseres _m) {
        container = _container;
        check = _check;
        miseres = _m;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        container.getSelectedMiseres().put(miseres, check.isSelected());
    }
}
