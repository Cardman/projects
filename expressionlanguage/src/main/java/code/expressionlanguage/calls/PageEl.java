package code.expressionlanguage.calls;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.StringMap;

public abstract class PageEl {

    private String globalClass = "";

    private Argument globalArgument = Argument.createVoid();

    private StringMap<LoopVariable> vars;

    private StringMap<LocalVariable> catchVars;

    private StringMap<LocalVariable> localVars;

    private final StringMap<LocalVariable> parameters = new StringMap<LocalVariable>();

    protected PageEl() {
        setVars(new StringMap<LoopVariable>());
        setCatchVars(new StringMap<LocalVariable>());
        setLocalVars(new StringMap<LocalVariable>());
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
        return Templates.quickFormat(globalClass, _varType, _cont);
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

    public StringMap<LoopVariable> getVars() {
        return vars;
    }

    public void setVars(StringMap<LoopVariable> _vars) {
        vars = _vars;
    }

    public void putLocalVar(String _key, LocalVariable _var) {
        localVars.put(_key, _var);
    }

    public void removeLocalVar(String _key) {
        localVars.removeKey(_key);
    }

    public LocalVariable getLocalVar(String _key) {
        return getLocalVars().getVal(_key);
    }

    public StringMap<LocalVariable> getLocalVars() {
        return localVars;
    }

    public void setLocalVars(StringMap<LocalVariable> _localVars) {
        localVars = _localVars;
    }

    public StringMap<LocalVariable> getCatchVars() {
        return catchVars;
    }

    public void setCatchVars(StringMap<LocalVariable> _catchVars) {
        catchVars = _catchVars;
    }

    public StringMap<LocalVariable> getParameters() {
        return parameters;
    }

}
