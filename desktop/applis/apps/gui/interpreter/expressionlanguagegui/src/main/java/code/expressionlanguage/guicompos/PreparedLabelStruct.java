package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.gui.PreparedLabel;

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
    public void setImage(Struct _text) {
        if (!(_text instanceof ImageStruct)) {
            textLabel.setEmptyIcon();
            width = 0;
            height = 0;
        } else {
            BufferedImage img_ = ((ImageStruct) _text).getImage();
            textLabel.setIcon(img_);
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
