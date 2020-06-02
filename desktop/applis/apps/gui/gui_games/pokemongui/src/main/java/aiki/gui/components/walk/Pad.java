package aiki.gui.components.walk;

import aiki.db.DataBase;
import aiki.gui.components.labels.KeyPad;
import aiki.map.enums.Direction;
import code.gui.PaintableLabel;
import code.gui.Panel;
import code.gui.TextLabel;

public class Pad {

    private PaintableLabel up;
    private PaintableLabel left;
    private PaintableLabel down;
    private PaintableLabel right;

    private Panel container;

    /**
    Create the panel.
    */
    public Pad() {
        container = Panel.newGrid(3, 3, 10, 10);

        TextLabel lblNewLabel_ = new TextLabel(DataBase.EMPTY_STRING);
        container.add(lblNewLabel_);

        up = new KeyPad(Direction.UP);
        container.add(up);

        TextLabel lblNewLabelSec_ = new TextLabel(DataBase.EMPTY_STRING);
        container.add(lblNewLabelSec_);

        left = new KeyPad(Direction.LEFT);
        container.add(left);

        TextLabel lblNewLabelThird_ = new TextLabel(DataBase.EMPTY_STRING);
        container.add(lblNewLabelThird_);

        right = new KeyPad(Direction.RIGHT);
        container.add(right);

        TextLabel lblNewLabelFour_ = new TextLabel(DataBase.EMPTY_STRING);
        container.add(lblNewLabelFour_);

        down = new KeyPad(Direction.DOWN);
        container.add(down);
    }

    public PaintableLabel getUp() {
        return up;
    }

    public PaintableLabel getLeft() {
        return left;
    }

    public PaintableLabel getDown() {
        return down;
    }

    public PaintableLabel getRight() {
        return right;
    }

    public Panel getContainer() {
        return container;
    }
}
