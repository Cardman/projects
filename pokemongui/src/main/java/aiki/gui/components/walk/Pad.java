package aiki.gui.components.walk;
import java.awt.GridLayout;

import javax.swing.JLabel;

import aiki.DataBase;
import aiki.gui.components.labels.KeyPad;
import aiki.map.enums.Direction;
import code.gui.PaintableLabel;
import code.gui.Panel;

public class Pad extends Panel {

    private PaintableLabel up;
    private PaintableLabel left;
    private PaintableLabel down;
    private PaintableLabel right;

    /**
    Create the panel.
    */
    public Pad() {
        setLayout(new GridLayout(3, 3, 10, 10));

        JLabel lblNewLabel_ = new JLabel(DataBase.EMPTY_STRING);
        add(lblNewLabel_);

        up = new KeyPad(Direction.UP);
        add(up);

        JLabel lblNewLabelSec_ = new JLabel(DataBase.EMPTY_STRING);
        add(lblNewLabelSec_);

        left = new KeyPad(Direction.LEFT);
        add(left);

        JLabel lblNewLabelThird_ = new JLabel(DataBase.EMPTY_STRING);
        add(lblNewLabelThird_);

        right = new KeyPad(Direction.RIGHT);
        add(right);

        JLabel lblNewLabelFour_ = new JLabel(DataBase.EMPTY_STRING);
        add(lblNewLabelFour_);

        down = new KeyPad(Direction.DOWN);
        add(down);
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
}
