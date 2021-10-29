package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.Struct;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;

public final class PreparedLabelStruct extends CustComponentStruct {
    private final AbsPreparedLabel textLabel;

    public PreparedLabelStruct(AbstractImageFactory _fact, String _className) {
        super(_className);
        textLabel = FrameUtil.prep(_fact);
    }
    public PreparedLabelStruct(AbstractImageFactory _fact, AbsCompoFactory _comp, Struct _img, String _className) {
        super(_className);
        textLabel = _comp.newPreparedLabel(builImage(_img));
    }
    public void setImage(AbstractImageFactory _fact, Struct _text) {
        textLabel.setIcon(_fact,builImage(_text));
    }

    public static AbstractImage builImage(Struct _text) {
        AbstractImage img_ = null;
        if (_text instanceof ImageStruct) {
            img_ = ((ImageStruct) _text).getImage();
        }
        return img_;
    }

    public AbsPreparedLabel getTextLabel() {
        return textLabel;
    }

    @Override
    protected AbsCustComponent getComponent() {
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

}
