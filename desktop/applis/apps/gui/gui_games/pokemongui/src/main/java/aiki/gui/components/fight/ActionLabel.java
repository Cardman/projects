package aiki.gui.components.fight;


import aiki.game.fight.enums.ActionType;
import code.gui.AbsMetaLabel;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbsCompoFactory;

public final class ActionLabel extends AbsMetaLabel {

    private String action;

    private ActionType actionEnum;

    private boolean selected;

    public ActionLabel(String _action, ActionType _actionEnum, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        action = _action;
        actionEnum = _actionEnum;
        //setPreferredSize(new Dimension(50,10));
    }

    public void setSelected(ActionType _actionEnum) {
        selected = actionEnum == _actionEnum;
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        _g.setColor(GuiConstants.BLACK);
        _g.drawString(action, 0, getHeight());
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
