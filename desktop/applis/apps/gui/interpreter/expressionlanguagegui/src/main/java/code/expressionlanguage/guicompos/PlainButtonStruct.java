package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsCustComponent;
import code.gui.events.AbsActionListener;
import code.gui.CustComponent;
import code.gui.PlainButton;

public final class PlainButtonStruct extends InputStruct {
    private PlainButton plainButton;
    public PlainButtonStruct(String _className) {
        super(_className);
        plainButton = new PlainButton();
    }
    public PlainButtonStruct(Struct _txt,String _className) {
        super(_className);
        plainButton = new PlainButton();
        setText(_txt);
    }

    public Struct getText() {
        String txt_ = plainButton.getText();
        if (txt_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return new StringStruct(txt_);
    }

    public void setText(Struct _text) {
        if (!(_text instanceof StringStruct)) {
            plainButton.setText(null);
        } else {
            plainButton.setText(((StringStruct)_text).getInstance());
        }
    }

    @Override
    public Struct isEnabled() {
        return BooleanStruct.of(plainButton.isEnabled());
    }
    @Override
    public void setEnabled(Struct _b) {
        plainButton.setEnabled(BooleanStruct.isTrue(_b));
    }
    public void addActionListener(Struct _list) {
        if (_list instanceof AbsActionListener) {
            plainButton.addActionListener((AbsActionListener)_list);
        }
    }
    @Override
    protected AbsCustComponent getComponent() {
        return plainButton;
    }
}
