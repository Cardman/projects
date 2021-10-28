package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.CustComponentStruct;
import code.expressionlanguage.guicompos.PanelStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.util.CustList;

public final class FctPanelAddCompo1 implements StdCaller {
    private final CustAliases custAliases;

    public FctPanelAddCompo1(CustAliases _custAliases) {
        this.custAliases = _custAliases;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
            custAliases.processFailInit(_cont, _stackCall);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct compo_ = argumentWrappers_.get(0).getValue().getStruct();
        if (!(compo_ instanceof CustComponentStruct)) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        PanelStruct strPan_ = (PanelStruct) _instance;
        strPan_.add((CustComponentStruct) compo_,((NumberStruct)argumentWrappers_.get(1).getValue().getStruct()).intStruct());
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
