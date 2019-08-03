package cards.gui.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import cards.gui.containers.ContainerTarot;
import cards.tarot.enumerations.Miseres;
import code.gui.CustCheckBox;

public class ListenerMiseresTarot implements ActionListener {

    private ContainerTarot container;
    private CustCheckBox check;
    private Miseres miseres;
    public ListenerMiseresTarot(ContainerTarot _container, CustCheckBox _check, Miseres _m) {
        container = _container;
        check = _check;
        miseres = _m;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        container.getSelectedMiseres().put(miseres, check.isSelected());
    }
}
