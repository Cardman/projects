package aiki.gui.components.walk;

import aiki.db.DataBase;
import aiki.gui.components.labels.KeyPad;
import aiki.map.enums.Direction;
import code.gui.AbsPanel;
import code.gui.AbsPlainLabel;
import code.gui.initialize.AbsCompoFactory;

public class Pad {

    private KeyPad up;
    private KeyPad left;
    private KeyPad down;
    private KeyPad right;
    private KeyPad middle;

    private AbsPanel container;

    /**
    Create the panel.
     * @param _compoFactory
     */
    public Pad(AbsCompoFactory _compoFactory) {
        container = _compoFactory.newGrid(3, 3, 10, 10);

        AbsPlainLabel lblNewLabel_ = _compoFactory.newPlainLabel(DataBase.EMPTY_STRING);
        container.add(lblNewLabel_);

        up = new KeyPad(Direction.UP, _compoFactory);
        container.add(up.getPaintableLabel());

        AbsPlainLabel lblNewLabelSec_ = _compoFactory.newPlainLabel(DataBase.EMPTY_STRING);
        container.add(lblNewLabelSec_);

        left = new KeyPad(Direction.LEFT, _compoFactory);
        container.add(left.getPaintableLabel());

        middle = new KeyPad(null, _compoFactory);
        container.add(middle.getPaintableLabel());

        right = new KeyPad(Direction.RIGHT, _compoFactory);
        container.add(right.getPaintableLabel());

        AbsPlainLabel lblNewLabelFour_ = _compoFactory.newPlainLabel(DataBase.EMPTY_STRING);
        container.add(lblNewLabelFour_);

        down = new KeyPad(Direction.DOWN, _compoFactory);
        container.add(down.getPaintableLabel());
    }

    public KeyPad getUp() {
        return up;
    }

    public KeyPad getLeft() {
        return left;
    }

    public KeyPad getDown() {
        return down;
    }

    public KeyPad getRight() {
        return right;
    }

    public KeyPad getMiddle() {
        return middle;
    }

    public AbsPanel getContainer() {
        return container;
    }
}
