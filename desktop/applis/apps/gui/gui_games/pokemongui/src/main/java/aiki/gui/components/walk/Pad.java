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
        container.add(up);

        AbsPlainLabel lblNewLabelSec_ = _compoFactory.newPlainLabel(DataBase.EMPTY_STRING);
        container.add(lblNewLabelSec_);

        left = new KeyPad(Direction.LEFT, _compoFactory);
        container.add(left);

        AbsPlainLabel lblNewLabelThird_ = _compoFactory.newPlainLabel(DataBase.EMPTY_STRING);
        container.add(lblNewLabelThird_);

        right = new KeyPad(Direction.RIGHT, _compoFactory);
        container.add(right);

        AbsPlainLabel lblNewLabelFour_ = _compoFactory.newPlainLabel(DataBase.EMPTY_STRING);
        container.add(lblNewLabelFour_);

        down = new KeyPad(Direction.DOWN, _compoFactory);
        container.add(down);
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

    public AbsPanel getContainer() {
        return container;
    }
}
