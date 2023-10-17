package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.EventStruct;
import code.expressionlanguage.guicompos.GraphicListStruct;
import code.expressionlanguage.guicompos.GuiContextEl;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.util.CustList;

public final class DfGrList extends DfCompoCtor {
    private final String aliasGrList;

    public DfGrList(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasGrList) {
        super(_custAliases, _guiEx);
        this.aliasGrList = _aliasGrList;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        EventStruct rend_ = new EventStruct((RunnableContextEl) _cont, _guiEx.getDefCellRender().getGenericString(), "", -1, new CustList<ClassFieldStruct>(), NullStruct.NULL_VALUE, "");
        return new ArgumentWrapper(new GraphicListStruct((GuiContextEl)_cont,aliasGrList,true, rend_));
    }
}
