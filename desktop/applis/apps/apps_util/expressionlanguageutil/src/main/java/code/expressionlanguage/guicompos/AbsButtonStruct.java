package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.*;
import code.gui.AbsButton;
import code.gui.AbsCustComponent;
import code.gui.events.AbsAdvActionListener;
import code.util.CustList;
import code.util.IdList;

public abstract class AbsButtonStruct extends CustComponentStruct {
    private final CustList<Struct> menus = new CustList<Struct>();
    private final IdList<Struct> actionsButton = new IdList<Struct>();
    private Struct parentMenu = NullStruct.NULL_VALUE;
    protected AbsButtonStruct(String _className) {
        super(_className);
    }

    public Struct getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Struct _parentMenu) {
        parentMenu = _parentMenu;
    }

    public static String getValue(Struct _str) {
        return NumParsers.getString(_str).getInstance();
    }

    public Struct getText() {
        String txt_ = but().getText();
        if (txt_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return new StringStruct(txt_);
    }

    public void setText(Struct _text) {
        if (!(_text instanceof StringStruct)) {
            but().setText(null);
        } else {
            but().setText(((StringStruct)_text).getInstance());
        }
    }

    public void addActionListener(Struct _list, StackCall _stackCall) {
        if (_list instanceof AbsAdvActionListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                but().addActionListenerMap((AbsAdvActionListener)_list);
            } else {
                but().addActionListener((AbsAdvActionListener)_list);
            }
            actionsButton.add(_list);
        }
    }
    public void removeActionListener(Struct _list, StackCall _stackCall) {
        if (_list instanceof AbsAdvActionListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                but().removeActionListenerMap((AbsAdvActionListener) _list);
            } else {
                but().removeActionListener((AbsAdvActionListener) _list);
            }
            actionsButton.removeObj(_list);
        }
    }
    public ArrayStruct getActions(ContextEl _ctx) {
        String aliasMouseListener_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasActionListener();
        return nulls(aliasMouseListener_, actionsButton);
    }

    public Struct getMenu(Struct _index) {
        if (!menus.isValidIndex(((NumberStruct)_index).intStruct())) {
            return NullStruct.NULL_VALUE;
        }
        return menus.get(((NumberStruct)_index).intStruct());
    }

    public IntStruct getMenuCount() {
        return new IntStruct(menus.size());
    }

    public IdList<Struct> getActionsButton() {
        return actionsButton;
    }

    @Override
    protected AbsCustComponent getComponent() {
        return but();
    }

    public CustList<Struct> getMenus() {
        return menus;
    }

    public abstract AbsButton but();
}
