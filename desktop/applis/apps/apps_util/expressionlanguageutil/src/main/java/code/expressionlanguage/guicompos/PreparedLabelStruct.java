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
        textLabel = GuiBaseUtil.prep(_fact);
    }
    public PreparedLabelStruct(AbsCompoFactory _comp, Struct _img, String _className) {
        super(_className);
        textLabel = _comp.newPreparedLabel(builImage(_img));
    }
    public void setImage(AbstractImageFactory _fact, Struct _text) {
        AbstractImage i_ = builImage(_text);
        if (i_ == null) {
            return;
        }
        textLabel.setIcon(_fact, i_);
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


}
