package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsCustComponent;
import code.gui.AbsPlainButton;
import code.gui.events.AbsAdvActionListener;
import code.gui.initialize.AbsCompoFactory;
import code.util.IdList;

public final class PlainButtonStruct extends InputStruct {
    private final AbsPlainButton plainButton;
    private final IdList<Struct> actionsButton = new IdList<Struct>();
    public PlainButtonStruct(String _className, AbsCompoFactory _compo) {
        super(_className);
        plainButton = _compo.newPlainButton();
    }
    public PlainButtonStruct(Struct _txt,String _className, AbsCompoFactory _compo) {
        super(_className);
        plainButton = _compo.newPlainButton();
        setText(_txt);
    }

//    public Struct getText() {
//        String txt_ = plainButton.getText();
//        if (txt_ == null) {
//            return NullStruct.NULL_VALUE;
//        }
//        return new StringStruct(txt_);
//    }

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
    public void addActionListener(Struct _list, StackCall _stackCall) {
        if (_list instanceof AbsAdvActionListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                plainButton.addActionListenerMap((AbsAdvActionListener)_list);
            } else {
                plainButton.addActionListener((AbsAdvActionListener)_list);
            }
            actionsButton.add(_list);
        }
    }
    public void removeActionListener(Struct _list, StackCall _stackCall) {
        if (_list instanceof AbsAdvActionListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                plainButton.removeActionListenerMap((AbsAdvActionListener) _list);
            } else {
                plainButton.removeActionListener((AbsAdvActionListener) _list);
            }
            actionsButton.removeObj(_list);
        }
    }
    public ArrayStruct getActions(ContextEl _ctx) {
        String aliasMouseListener_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasActionListener();
        return nulls(aliasMouseListener_, actionsButton);
    }

    public IdList<Struct> getActionsButton() {
        return actionsButton;
    }

    @Override
    protected AbsCustComponent getComponent() {
        return plainButton;
    }
}
