package code.expressionlanguage.guicompos.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.guicompos.GuiExecutingBlocks;
import code.expressionlanguage.guicompos.PanelStruct;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.util.CustList;

public final class FctPanelGrid implements StdCaller {
    private final CustAliases custAliases;
    private final GuiExecutingBlocks guiExecutingBlocks;
    private final String aliasPanel;

    public FctPanelGrid(CustAliases _custAliases, GuiExecutingBlocks _guiEx, String _aliasPanel) {
        this.custAliases = _custAliases;
        guiExecutingBlocks = _guiEx;
        this.aliasPanel = _aliasPanel;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        if (_stackCall.getInitializingTypeInfos().isWideInitEnums()) {
            custAliases.processFailInit(_cont, _stackCall);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        return new ArgumentWrapper(PanelStruct.newGrid(aliasPanel,((NumberStruct)argumentWrappers_.get(0).getValue().getStruct()).intStruct(),((NumberStruct)argumentWrappers_.get(1).getValue().getStruct()).intStruct(),guiExecutingBlocks.getCompoFactory()));
    }
}
