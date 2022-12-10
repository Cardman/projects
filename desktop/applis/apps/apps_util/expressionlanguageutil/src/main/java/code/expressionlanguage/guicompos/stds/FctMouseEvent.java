package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.MouseEventStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.util.CustList;

public final class FctMouseEvent extends FctCompoCtor {
    private final String aliasMouseEvent;

    public FctMouseEvent(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasMouseEvent) {
        super(_custAliases, _guiEx);
        this.aliasMouseEvent = _aliasMouseEvent;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        MouseEventStruct res_ = new MouseEventStruct(aliasMouseEvent);
        res_.setFirst(argumentWrappers_.get(0).getValue().getStruct());
        res_.setSecond(argumentWrappers_.get(1).getValue().getStruct());
        res_.setAlt(argumentWrappers_.get(2).getValue().getStruct());
        res_.setCtrl(argumentWrappers_.get(3).getValue().getStruct());
        res_.setShift(argumentWrappers_.get(4).getValue().getStruct());
        res_.setLeft(argumentWrappers_.get(5).getValue().getStruct());
        res_.setMiddle(argumentWrappers_.get(6).getValue().getStruct());
        res_.setRight(argumentWrappers_.get(7).getValue().getStruct());
        res_.setClicks(argumentWrappers_.get(8).getValue().getStruct());
        return new ArgumentWrapper(res_);
    }
}
