package code.expressionlanguage.guicompos;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.gui.IndexableListener;
import code.util.CustList;
import code.util.StringList;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class SimpleSelectEltListStruct extends MouseAdapter implements IndexableListener {
    private ContextEl original;
    private GraphicListStruct grList;

    private int index;

    public SimpleSelectEltListStruct(ContextEl _contextEl, GraphicListStruct _graphicList, int _index) {
        original = _contextEl;
        grList = _graphicList;
        index = _index;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        if (!grList.isEnabledList()) {
            return;
        }
        grList.setFirstIndex(index);
        grList.setLastIndex(index);
        boolean sel_ = SwingUtilities.isLeftMouseButton(_e);
        grList.getSelectedIndexes().clear();
        if (sel_) {
            grList.getSelectedIndexes().add(index);
        }
        GuiContextEl ctx_ = newCtx();
        LgNamesGui stds_ = (LgNamesGui) original.getStandards();
        StringList types_ = new StringList(stds_.getAliasGrList());
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(grList));
        invoke(ctx_,stds_.getAliasPaint(),stds_.getAliasPaintRefresh(), types_, args_);
        SelectionStructUtil.selectEvent(index,index,grList,false);
    }

    private void invoke(GuiContextEl _r, String _typeName, String _methName, StringList _argTypes, CustList<Argument> _args) {
        ClassMethodId mId_ = new ClassMethodId(_typeName,new MethodId(MethodAccessKind.STATIC,_methName,_argTypes));
        Argument arg_ = new Argument();
        ExecNamedFunctionBlock fct_ = ExecBlock.getMethodBodiesById(_r,mId_.getClassName(), mId_.getConstraints()).first();
        RunnableStruct.invoke(arg_, mId_.getClassName(),_r.getClasses().getClassBody(mId_.getClassName()), fct_, _args, _r);
    }
    private GuiContextEl newCtx() {
        GuiContextEl r_ = new GuiContextEl(original);
        RunnableStruct.setupThread(r_);
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
