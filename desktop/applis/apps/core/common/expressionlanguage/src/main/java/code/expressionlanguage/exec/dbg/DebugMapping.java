package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public final class DebugMapping {
    private final IdMap<FileBlock, ExecFileBlock> files = new IdMap<FileBlock, ExecFileBlock>();
    private StringMap<StringMap<Struct>> shared = new StringMap<StringMap<Struct>>();
    private CustList<String> typesInit = new CustList<String>();
    private String initClass="";
    private final BreakPointBlockList breakPointsBlock = new BreakPointBlockList();
    private final boolean debugging;

    public DebugMapping(boolean _d) {
        this.debugging = _d;
    }

    public void addFile(FileBlock _file, ExecFileBlock _e) {
        if (!debugging) {
            return;
        }
        files.addEntry(_file, _e);
    }
    public BreakPointBlockList getBreakPointsBlock() {
        return breakPointsBlock;
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
}
