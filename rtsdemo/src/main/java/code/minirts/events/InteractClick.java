package code.minirts.events;

import code.minirts.MainWindow;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InteractClick extends MouseAdapter {

    private MainWindow fenetre;
    public InteractClick(MainWindow _fenetre) {
        fenetre = _fenetre;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!SwingUtilities.isLeftMouseButton(e)) {
            fenetre.setNewLocation(e.getX(), e.getY());
            return;
        }
        if (fenetre.isDragged()) {
            fenetre.setDragged(false);
            return;
        }
        if (fenetre.isAddingSoldier()) {
            fenetre.addNewSoldier(e.getX(), e.getY());
            return;
        }
        fenetre.selectOrDeselect(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent _e) {
        fenetre.setFirst(_e.getX(), _e.getY());
    }


    @Override
    public void mouseDragged(MouseEvent _e) {
        if (!SwingUtilities.isLeftMouseButton(_e)) {
            return;
        }
        fenetre.setDragged(true);
        fenetre.setLast(_e.getX(), _e.getY());
        fenetre.selectOrDeselectMulti();
    }
}
