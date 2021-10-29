package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.KeyEventStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.util.CustList;

public final class FctKeyEvent extends FctCompoCtor {
    private final String aliasKeyEvent;

    public FctKeyEvent(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasKeyEvent) {
        super(_custAliases, _guiEx);
        this.aliasKeyEvent = _aliasKeyEvent;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        KeyEventStruct res_ = new KeyEventStruct(aliasKeyEvent);
        res_.setAlt(argumentWrappers_.get(0).getValue().getStruct());
        res_.setCtrl(argumentWrappers_.get(1).getValue().getStruct());
        res_.setShift(argumentWrappers_.get(2).getValue().getStruct());
        res_.setKeyChar(argumentWrappers_.get(3).getValue().getStruct());
        res_.setKeyCode(argumentWrappers_.get(4).getValue().getStruct());
        return new ArgumentWrapper(res_);
    }
}
