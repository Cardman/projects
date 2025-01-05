package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.structs.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.util.*;

public final class TextFieldStruct extends TxtComponentStruct {
    private final AbsTextField textField;
    private final IdList<Struct> actionsField = new IdList<Struct>();
    public TextFieldStruct(String _className, AbsCompoFactory _compo) {
        super(_className);
        textField = _compo.newTextField();
    }
    public TextFieldStruct(String _className,Struct _txt, AbsCompoFactory _compo) {
        super(_className);
        textField = _compo.newTextField(getText(_txt));
    }
    public TextFieldStruct(String _className,NumberStruct _cols, AbsCompoFactory _compo) {
        super(_className);
        textField = _compo.newTextField(_cols.intStruct());
    }
    public TextFieldStruct(String _className,Struct _txt,Struct _cols, AbsCompoFactory _compo) {
        super(_className);
        textField = _compo.newTextField(getText(_txt),((NumberStruct)_cols).intStruct());
    }

    @Override
    protected AbsTxtComponent component() {
        return textField;
    }

    public void addActionListener(Struct _arg, StackCall _stackCall) {
        if (_arg instanceof AbsAdvActionListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                textField.addActionListenerMap((AbsAdvActionListener)_arg);
            } else {
                textField.addActionListener((AbsAdvActionListener)_arg);
            }
            actionsField.add(_arg);
        }
    }

    public void removeActionListener(Struct _arg, StackCall _stackCall) {
        if (_arg instanceof AbsAdvActionListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                textField.removeActionListenerMap((AbsAdvActionListener)_arg);
            } else {
                textField.removeActionListener((AbsAdvActionListener)_arg);
            }
            actionsField.removeObj(_arg);
        }
    }

    public ArrayStruct getActions(ContextEl _ctx) {
        String aliasMouseListener_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasActionListener();
        return nulls(aliasMouseListener_, actionsField);
    }
    public IdList<Struct> getActionsField() {
        return actionsField;
    }
}
