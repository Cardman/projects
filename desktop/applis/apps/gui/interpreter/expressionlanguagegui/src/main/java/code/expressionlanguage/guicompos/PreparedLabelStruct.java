package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.gui.PreparedLabel;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public final class PreparedLabelStruct extends CustComponentStruct {
    private final PreparedLabel textLabel;
    protected PreparedLabelStruct(String _className) {
        super(_className);
        textLabel = new PreparedLabel();
    }
    protected PreparedLabelStruct(Struct _img,String _className) {
        super(_className);
        textLabel = new PreparedLabel(builImage(_img));
    }
    public void setImage(Struct _text) {
        if (!(_text instanceof ImageStruct)) {
            textLabel.setEmptyIcon();
        } else {
            BufferedImage img_ = ((ImageStruct) _text).getImage();
            textLabel.setIcon(img_);
        }
    }
    public static ImageIcon builImage(Struct _text) {
        BufferedImage img_ = null;
        if (_text instanceof ImageStruct) {
            img_ = ((ImageStruct) _text).getImage();
        }
        return PreparedLabel.buildIcon(img_);
    }

    public void addMouseListener(MouseListener _mouseListener) {
        if (_mouseListener != null) {
            textLabel.addMouseListener(_mouseListener);
        }
    }

    public PreparedLabel getTextLabel() {
        return textLabel;
    }

    @Override
    protected CustComponent getComponent() {
        return textLabel;
    }

    @Override
    public int getWidth() {
        return textLabel.getWidth();
    }

    @Override
    public int getHeight() {
        return textLabel.getHeight();
    }

    public void addKeyListener(KeyListener _i) {
        textLabel.addKeyListener(_i);

    }
}
