package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsCustComponent;
import code.gui.AbsPlainButton;
import code.gui.events.AbsAdvActionListener;
import code.gui.initialize.AbsCompoFactory;

public final class PlainButtonStruct extends InputStruct {
    private AbsPlainButton plainButton;
    public PlainButtonStruct(String _className, AbsCompoFactory _compo) {
        super(_className);
        plainButton = _compo.newPlainButton();
    }
    public PlainButtonStruct(Struct _txt,String _className, AbsCompoFactory _compo) {
        super(_className);
        plainButton = _compo.newPlainButton();
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
        if (_list instanceof AbsAdvActionListener) {
            plainButton.addActionListener((AbsAdvActionListener)_list);
        }
    }
    @Override
    protected AbsCustComponent getComponent() {
        return plainButton;
    }
}
