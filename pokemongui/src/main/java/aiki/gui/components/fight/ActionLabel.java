package aiki.gui.components.fight;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import aiki.game.fight.enums.ActionType;

public class ActionLabel extends JLabel {

    private String action;

    private ActionType actionEnum;

    private boolean selected;

    public ActionLabel(String _action, ActionType _actionEnum) {
        action = _action;
        actionEnum = _actionEnum;
        setText(_action);
        //setPreferredSize(new Dimension(50,10));
    }

    public void setSelected(ActionType _actionEnum) {
        selected = actionEnum == _actionEnum;
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }

    @Override
    protected void paintComponent(Graphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        _g.setColor(Color.BLACK);
        _g.drawString(action, 0, getHeight());
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
