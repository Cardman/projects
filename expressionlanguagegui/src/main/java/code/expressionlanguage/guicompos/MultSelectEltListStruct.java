package code.expressionlanguage.guicompos;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.gui.IndexableListener;
import code.util.CustList;
import code.util.StringList;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class MultSelectEltListStruct extends MouseAdapter implements IndexableListener {
    private ContextEl original;
    private GraphicListStruct grList;

    private int index;

    public MultSelectEltListStruct(ContextEl _contextEl, GraphicListStruct _graphicList, int _index) {
        original = _contextEl;
        grList = _graphicList;
        index = _index;
    }


    @Override
    public void mouseReleased(MouseEvent _e) {
        if (!grList.isEnabledList()) {
            return;
        }
        boolean sel_ = SwingUtilities.isLeftMouseButton(_e);
        if (!_e.isShiftDown()) {
            grList.setFirstIndex(index);
            grList.setLastIndex(index);
            if (!sel_) {
                grList.getSelectedIndexes().removeObj(index);
            } else {
                grList.getSelectedIndexes().add(index);
                grList.getSelectedIndexes().removeDuplicates();
            }
            GuiContextEl ctx_ = newCtx();
            LgNamesGui stds_ = (LgNamesGui) original.getStandards();
            StringList types_ = new StringList(stds_.getAliasGrList());
            CustList<Argument> args_ = new CustList<Argument>();
            args_.add(new Argument(grList));
            invoke(ctx_,stds_.getAliasPaint(),stds_.getAliasPaintRefresh(), types_, args_);
            SelectionStructUtil.selectEvent(index,index,grList,false);
            if (grList.getListComponents().isValidIndex(index)) {
                grList.getListComponents().get(index).requestFocus();
            }
            return;
        }
        grList.setLastIndex(index);
        int min_ = Math.min(grList.getFirstIndex(), grList.getLastIndex());
        int max_ = Math.max(grList.getFirstIndex(), grList.getLastIndex());
        if (!sel_) {
            for (int i = min_; i <= max_; i++) {
                grList.getSelectedIndexes().removeObj(i);
            }
        } else {
            for (int i = min_; i <= max_; i++) {
                grList.getSelectedIndexes().add(i);
            }
            grList.getSelectedIndexes().removeDuplicates();
        }
        GuiContextEl ctx_ = newCtx();
        LgNamesGui stds_ = (LgNamesGui) original.getStandards();
        StringList types_ = new StringList(stds_.getAliasGrList());
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(grList));
        invoke(ctx_,stds_.getAliasPaint(),stds_.getAliasPaintRefresh(), types_, args_);
        SelectionStructUtil.selectEvent(min_,max_,grList,false);
    }
    private void invoke(GuiContextEl _r, String _typeName, String _methName, StringList _argTypes, CustList<Argument> _args) {
        ClassMethodId mId_ = new ClassMethodId(_typeName,new MethodId(MethodAccessKind.STATIC,_methName,_argTypes));
        Argument arg_ = new Argument();
        RunnableStruct.invoke(arg_, mId_.getClassName(), mId_.getConstraints(), _args, _r,null);
    }
    private GuiContextEl newCtx() {
        GuiContextEl r_ = new GuiContextEl(original);
        r_.setNumber(RunnableStruct.setupThread(r_));
        return r_;
    }
    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int _index) {
        index = _index;
    }
}
