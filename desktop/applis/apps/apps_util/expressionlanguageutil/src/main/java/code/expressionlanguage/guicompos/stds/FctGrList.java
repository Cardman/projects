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
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.util.CustList;

public final class FctGrList extends FctCompoCtor {
    private final String aliasGrList;
    public FctGrList(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasGrList) {
        super(_custAliases, _guiEx);
        this.aliasGrList = _aliasGrList;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        EventStruct rend_ = new EventStruct(_cont, _guiEx.getDefCellRender().getGenericString(), "", -1, new CustList<ClassFieldStruct>(), NullStruct.NULL_VALUE, "");
        if (BooleanStruct.isTrue(_firstArgs.getArgumentWrappers().get(0).getValue().getStruct())) {
            return new ArgumentWrapper(new GraphicListStruct((GuiContextEl)_cont,aliasGrList,true, rend_));
        }
        return new ArgumentWrapper(new GraphicListStruct((GuiContextEl)_cont,aliasGrList,false, rend_));
    }
}
