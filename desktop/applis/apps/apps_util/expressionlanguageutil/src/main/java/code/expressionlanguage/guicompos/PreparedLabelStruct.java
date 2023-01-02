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
    public PreparedLabelStruct(AbstractImageFactory _fact, AbsCompoFactory _comp, Struct _img, String _className) {
        super(_className);
        textLabel = _comp.newPreparedLabel(builImage(_fact,_img));
    }
    public void setImage(AbstractImageFactory _fact, Struct _text) {
        AbstractImage i_ = builImage(_fact,_text);
        textLabel.setIcon(_fact, i_);
    }

    public static AbstractImage builImage(AbstractImageFactory _fact, Struct _text) {
        if (_text instanceof ImageStruct) {
            return ((ImageStruct) _text).getImage();
        }
        return _fact.newImageArgb(1,1);
    }

    public AbsPreparedLabel getTextLabel() {
        return textLabel;
    }

    @Override
    protected AbsCustComponent getComponent() {
        return textLabel;
    }


}
