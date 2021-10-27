package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.ImageStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctImageSet implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isContainedSensibleFields(_instance)) {
            _stackCall.getInitializingTypeInfos().failInitEnums();
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        ImageStruct image_ = (ImageStruct) _instance;
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        image_.setPixel(((NumberStruct)argumentWrappers_.get(0).getValue().getStruct()).intStruct(),((NumberStruct)argumentWrappers_.get(1).getValue().getStruct()).intStruct(),((NumberStruct)argumentWrappers_.get(2).getValue().getStruct()).intStruct());
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
