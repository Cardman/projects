package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.ActionEventStruct;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.gui.KeyActionEvent;
import code.util.CustList;

public final class FctActionEvent extends FctCompoCtor {
    private final String aliasActionEvent;

    public FctActionEvent(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasActionEvent) {
        super(_custAliases, _guiEx);
        this.aliasActionEvent = _aliasActionEvent;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        boolean alt_ = BooleanStruct.isTrue(argumentWrappers_.get(0).getValue().getStruct());
        boolean ctrl_ = BooleanStruct.isTrue(argumentWrappers_.get(1).getValue().getStruct());
        boolean shift_ = BooleanStruct.isTrue(argumentWrappers_.get(2).getValue().getStruct());
        Struct struct_ = argumentWrappers_.get(3).getValue().getStruct();
        String command_ = NumParsers.getStringValue(struct_);
        return new ArgumentWrapper(new ActionEventStruct(aliasActionEvent, new KeyActionEvent(ctrl_,alt_,shift_),command_));
    }
}
