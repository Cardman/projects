package minirts;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import minirts.rts.DataBase;
import minirts.rts.Direction;

public class Pad extends JPanel {

    private JLabel up;
    private JLabel left;
    private JLabel down;
    private JLabel right;

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

    public JLabel getUp() {
        return up;
    }

    public JLabel getLeft() {
        return left;
    }

    public JLabel getDown() {
        return down;
    }

    public JLabel getRight() {
        return right;
    }
}
