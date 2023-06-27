package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.exec.AbsStackStopper;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.DefStackStopper;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public final class DebugMapping {
    private final IdMap<FileBlock, ExecFileBlock> files = new IdMap<FileBlock, ExecFileBlock>();
    private StringMap<StringMap<Struct>> shared = new StringMap<StringMap<Struct>>();
    private final CustList<String> typesInit = new CustList<String>();
    private String initClass="";
    private final BreakPointBlockList breakPointsBlock;
    private final StringMap<ConditionReturn> exceptions = new StringMap<ConditionReturn>();
    private final AbsStackStopper stopper;

    public DebugMapping(AbsStackStopper _s, AbstractInterceptorStdCaller _i) {
        this.stopper = _s;
        breakPointsBlock = new BreakPointBlockList(_i);
    }

    public void addFile(FileBlock _file, ExecFileBlock _e) {
        if (stopper instanceof DefStackStopper) {
            return;
        }
        files.addEntry(_file, _e);
    }
    public BreakPointBlockList getBreakPointsBlock() {
        return breakPointsBlock;
    }

    public StringMap<ConditionReturn> getExceptions() {
        return exceptions;
    }

    public IdMap<FileBlock, ExecFileBlock> getFiles() {
        return files;
    }

    public CustList<String> getTypesInit() {
        return typesInit;
    }

    public StringMap<StringMap<Struct>> getShared() {
        return shared;
    }

    public void setShared(StringMap<StringMap<Struct>> _s) {
        this.shared = _s;
    }

    public String getInitClass() {
        return initClass;
    }

    public void setInitClass(String _i) {
        this.initClass = _i;
    }

    public AbsStackStopper getStopper() {
        return stopper;
    }
}
