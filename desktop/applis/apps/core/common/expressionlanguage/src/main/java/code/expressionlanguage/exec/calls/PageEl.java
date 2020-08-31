package code.expressionlanguage.exec.calls;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.blocks.ExecUniqueRootedBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.util.StringMap;

public abstract class PageEl {

    private String globalClass = "";

    private ExecRootBlock blockRootType;

    private Argument globalArgument = Argument.createVoid();

    private StringMap<LoopVariable> vars;
    private StringMap<LocalVariable> valueVars = new StringMap<LocalVariable>();
    private Cache cache;
    protected PageEl() {
        setVars(new StringMap<LoopVariable>());
    }

    public String getGlobalClass() {
        return globalClass;
    }

    public void setGlobalClass(String _globalClass) {
        globalClass = _globalClass;
    }
    public String formatVarType(String _varType, ContextEl _cont) {
        if (globalArgument.isNull()) {
            return _varType;
        }
        return ExecTemplates.quickFormat(blockRootType,globalClass, _varType);
    }

    public Argument getGlobalArgument() {
        return globalArgument;
    }
    public void setGlobalArgumentStruct(Struct _obj) {
        Argument arg_ = new Argument();
        arg_.setStruct(_obj);
        globalArgument = arg_;
    }

    public void setGlobalArgument(Argument _globalArgument) {
        globalArgument = _globalArgument;
    }

    public StringMap<LocalVariable> getValueVars() {
        return valueVars;
    }

    public void putValueVar(String _key, LocalVariable _var) {
        valueVars.put(_key, _var);
    }
    public StringMap<LoopVariable> getVars() {
        return vars;
    }

    public void setVars(StringMap<LoopVariable> _vars) {
        vars = _vars;
    }

    public void putLocalVar(String _key, LocalVariable _var) {
        valueVars.put(_key, _var);
    }

    public void removeLocalVar(String _key) {
        getValueVars().removeKey(_key);
    }
    public ExecRootBlock getBlockRootType() {
        return blockRootType;
    }

    public void setBlockRootType(ExecRootBlock _blockRootType) {
        blockRootType = _blockRootType;
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }
}
