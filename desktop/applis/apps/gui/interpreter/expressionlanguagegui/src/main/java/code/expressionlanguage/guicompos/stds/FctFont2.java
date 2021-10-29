package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.FontStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctFont2 implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct name_ = argumentWrappers_.get(0).getValue().getStruct();
        boolean bold_ = BooleanStruct.isTrue(argumentWrappers_.get(1).getValue().getStruct());
        boolean italic_ = BooleanStruct.isTrue(argumentWrappers_.get(2).getValue().getStruct());
        int size_ = ((NumberStruct) argumentWrappers_.get(3).getValue().getStruct()).intStruct();
        return new ArgumentWrapper(new FontStruct(name_,bold_,italic_,size_));
    }
}
