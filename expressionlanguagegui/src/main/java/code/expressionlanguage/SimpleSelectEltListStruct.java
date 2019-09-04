package code.expressionlanguage;

import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.inherits.TypeUtil;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.gui.IndexableListener;
import code.util.CustList;
import code.util.StringList;

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
        grList.setFirstIndex(index);
        grList.setLastIndex(index);
        boolean sel_ = !_e.isPopupTrigger();
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
        grList.clearAllRange();
        if (sel_) {
            grList.addRange();
        }
    }

    private void invoke(GuiContextEl _r, String _typeName, String _methName, StringList _argTypes, CustList<Argument> _args) {
        ClassMethodId mId_ = new ClassMethodId(_typeName,new MethodId(true,_methName,_argTypes));
        Argument arg_ = new Argument();
        ProcessMethod.calculateArgument(arg_, mId_.getClassName(), mId_.getConstraints(), _args, _r,null);
        _r.getCustInit().prExc(_r);
    }
    private GuiContextEl newCtx() {
        Thread thread_ = Thread.currentThread();
        GuiContextEl r_ = new GuiContextEl(original);
        r_.getCustInit().initAlive(thread_);
        StringBuilder dtPart_ = new StringBuilder();
        dtPart_.append(LgNamesUtils.getDateTimeText("_", "_", "_"));
        dtPart_.append("__");
        dtPart_.append(r_.getCustInit().increment());
        dtPart_.append(".txt");
        r_.getCustInit().putNewCustTreadIdDate(thread_, dtPart_.toString());
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
