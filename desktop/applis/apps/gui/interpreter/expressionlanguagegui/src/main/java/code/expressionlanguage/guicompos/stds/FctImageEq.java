package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.ImageStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctImageEq implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct imgOne_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct imgTwo_ = argumentWrappers_.get(1).getValue().getStruct();
        if (!(imgOne_ instanceof ImageStruct)) {
            return new ArgumentWrapper(BooleanStruct.of(imgTwo_ == NullStruct.NULL_VALUE));
        }
        if (!(imgTwo_ instanceof ImageStruct)) {
            return new ArgumentWrapper(BooleanStruct.of(false));
        }
        ImageStruct first_ = (ImageStruct) imgOne_;
        ImageStruct second_ = (ImageStruct) imgTwo_;
        return new ArgumentWrapper(BooleanStruct.of(ImageStruct.eq(first_.getImage(),second_.getImage())));
    }
}
