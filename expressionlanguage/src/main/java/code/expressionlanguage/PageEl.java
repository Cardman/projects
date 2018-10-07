package code.expressionlanguage;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.util.CustList;
import code.util.StringMap;

public abstract class PageEl {

    private Argument rightArgument;

    private String globalClass;

    private Argument globalArgument;

    private StringMap<LoopVariable> vars = new StringMap<LoopVariable>();

    private StringMap<LocalVariable> catchVars = new StringMap<LocalVariable>();

    private StringMap<LocalVariable> localVars = new StringMap<LocalVariable>();

    private StringMap<LocalVariable> parameters = new StringMap<LocalVariable>();

    public Argument getRightArgument() {
        return rightArgument;
    }

    public void setRightArgument(Argument _rightArgument) {
        rightArgument = _rightArgument;
    }

    public String getGlobalClass() {
        return globalClass;
    }

    public void setGlobalClass(String _globalClass) {
        globalClass = _globalClass;
    }

    public String formatVarType(String _varType, ExecutableCode _cont) {
        if (globalArgument == null) {
            return _varType;
        }
        if (globalArgument.isNull()) {
            return _varType;
        }
        String objClass_ = globalArgument.getObjectClassName(_cont.getContextEl());
        String gl_ = globalClass;
        gl_ = Templates.getIdFromAllTypes(gl_);
        gl_ = Templates.getFullTypeByBases(objClass_, gl_, _cont);
        return Templates.quickFormat(gl_, _varType, _cont);
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

    public void clearAllLocalVars() {
        localVars.clear();
    }

    public void removeLocalVar(String _key) {
        localVars.removeKey(_key);
    }

    public boolean containsLocalVar(String _key) {
        return localVars.contains(_key);
    }

    public LocalVariable getLocalVar(String _key) {
        return localVars.getVal(_key);
    }

    public StringMap<LocalVariable> getLocalVars() {
        return localVars;
    }

    public void setLocalVars(StringMap<LocalVariable> _localVars) {
        localVars = _localVars;
    }

    public void setLocalVars(CustList<StringMap<LocalVariable>> _localVars) {
        localVars = _localVars.last();
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

    public void setParameters(StringMap<LocalVariable> _parameters) {
        parameters = _parameters;
    }

}
