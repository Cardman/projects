package code.expressionlanguage;

import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.gui.PreparedLabel;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public final class PreparedLabelStruct extends CustComponentStruct {
    private PreparedLabel textLabel;
    private int width;
    private int height;
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
            width = 0;
            height = 0;
        } else {
            BufferedImage img_ = ((ImageStruct) text).getImage();
            textLabel.setIcon(new ImageIcon(img_));
            width = img_.getWidth();
            height = img_.getHeight();
        }
    }

    public void addMouseListener(MouseListener _mouseListener) {
        if (_mouseListener != null) {
            textLabel.addMouseListener(_mouseListener);
        }
    }

    @Override
    protected CustComponent getComponent() {
        return textLabel;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void addKeyListener(KeyListener _i) {
        textLabel.addKeyListener(_i);

    }
}
