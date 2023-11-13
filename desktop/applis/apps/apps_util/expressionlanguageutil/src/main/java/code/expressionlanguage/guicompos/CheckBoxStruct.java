package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.*;
import code.gui.AbsCustCheckBox;
import code.gui.AbsCustComponent;
import code.gui.events.AbsAdvActionListener;
import code.gui.initialize.AbsCompoFactory;
import code.util.IdList;

public final class CheckBoxStruct extends CustComponentStruct {
    private final AbsCustCheckBox checkBox;
    private final IdList<Struct> actionsCheck = new IdList<Struct>();

    public CheckBoxStruct(String _className, AbsCompoFactory _compo) {
        super(_className);
        checkBox = _compo.newCustCheckBox();
    }

    public CheckBoxStruct(String _className, Struct _txt, AbsCompoFactory _compo) {
        super(_className);
        checkBox = _compo.newCustCheckBox(getText(_txt));
    }

    public CheckBoxStruct(String _className, Struct _txt, Struct _sel, AbsCompoFactory _compo) {
        super(_className);
        checkBox = _compo.newCustCheckBox(getText(_txt),BooleanStruct.isTrue(_sel));
    }

    public Struct getText() {
        String t_ = checkBox.getText();
        if (t_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return new StringStruct(t_);
    }
    public void setText(Struct _txt) {
        checkBox.setText(getText(_txt));
    }
    private static String getText(Struct _txt) {
        if (_txt instanceof StringStruct) {
            return ((StringStruct)_txt).getInstance();
        }
        return null;
    }

    public void addActionListener(Struct _list, StackCall _stackCall) {
        if (_list instanceof AbsAdvActionListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                checkBox.addActionListenerMap((AbsAdvActionListener)_list);
            } else {
                checkBox.addActionListener((AbsAdvActionListener)_list);
            }
            actionsCheck.add(_list);
        }
    }
    public void removeActionListener(Struct _list, StackCall _stackCall) {
        if (_list instanceof AbsAdvActionListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                checkBox.removeActionListenerMap((AbsAdvActionListener) _list);
            } else {
                checkBox.removeActionListener((AbsAdvActionListener) _list);
            }
            actionsCheck.removeObj(_list);
        }
    }
    public ArrayStruct getActions(ContextEl _ctx) {
        String aliasMouseListener_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasActionListener();
        return nulls(aliasMouseListener_, actionsCheck);
    }

    public IdList<Struct> getActionsCheck() {
        return actionsCheck;
    }

    public Struct isSelected() {
        return BooleanStruct.of(checkBox.isSelected());
    }

    public void setSelected(Struct _b) {
        checkBox.setSelected(BooleanStruct.isTrue(_b));
    }

    @Override
    protected AbsCustComponent getComponent() {
        return checkBox;
    }
}
