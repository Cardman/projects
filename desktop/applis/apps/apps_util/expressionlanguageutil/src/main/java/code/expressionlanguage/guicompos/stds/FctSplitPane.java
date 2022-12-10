package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.CustComponentStruct;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.SplitPaneStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.util.CustList;

public final class FctSplitPane extends FctCompoCtor {
    private final String aliasSplitPane;

    public FctSplitPane(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasSplitPane) {
        super(_custAliases, _guiEx);
        this.aliasSplitPane = _aliasSplitPane;
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct left_ = argumentWrappers_.get(1).getValue().getStruct();
        Struct right_ = argumentWrappers_.get(2).getValue().getStruct();
        if (!(left_ instanceof CustComponentStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        if (!(right_ instanceof CustComponentStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        CustComponentStruct first_ = (CustComponentStruct) left_;
        CustComponentStruct second_ = (CustComponentStruct) right_;
        if (first_.getParentComponent() != NullStruct.NULL_VALUE) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasIllegalArg(), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        if (second_.getParentComponent() != NullStruct.NULL_VALUE) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasIllegalArg(), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return new ArgumentWrapper(new SplitPaneStruct(aliasSplitPane,argumentWrappers_.get(0).getValue().getStruct(),left_,right_,_guiEx.getCompoFactory()));
    }
}
