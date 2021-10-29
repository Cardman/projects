package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.TreeNodeStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;

public final class FctTreeNode1 extends FctCompoCtor {
    public FctTreeNode1(CustAliases _custAliases, GuiExecutingBlocks _guiEx) {
        super(_custAliases, _guiEx);
    }

    @Override
    public ArgumentWrapper inst(GuiExecutingBlocks _guiEx, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(new TreeNodeStruct(_guiEx.getWindow().getCompoFactory().newMutableTreeNode(NumParsers.getString(_firstArgs.getArgumentWrappers().get(0).getValue().getStruct()).getInstance())));
    }
}
