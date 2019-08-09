package code.expressionlanguage;

import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.gui.PreparedLabel;

import javax.swing.*;
import java.awt.event.MouseListener;

public final class PreparedLabelStruct extends CustComponentStruct {
    private PreparedLabel textLabel;
    protected PreparedLabelStruct(String _className) {
        super(_className);
        textLabel = new PreparedLabel();
    }
    protected PreparedLabelStruct(Struct _img,String _className) {
        super(_className);
        textLabel = new PreparedLabel();
        setImage(_img);
    }
    public void setImage(Struct text) {
        if (!(text instanceof ImageStruct)) {
            textLabel.setIcon(new ImageIcon());
        } else {
            textLabel.setIcon(new ImageIcon(((ImageStruct) text).getImage()));
        }
    }
    public void addMouse(Struct _mouseListener) {
        if (_mouseListener instanceof MouseListener) {
            textLabel.addMouseListener((MouseListener) _mouseListener);
        }
    }
    @Override
    protected CustComponent getComponent() {
        return textLabel;
    }
}
