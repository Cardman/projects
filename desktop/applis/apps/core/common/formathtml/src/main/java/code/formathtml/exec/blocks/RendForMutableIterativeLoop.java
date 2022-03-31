package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.StringList;

public final class RendForMutableIterativeLoop extends RendParentBlock implements RendWithEl {


    private final String label;

    private final String importedClassName;

    private final String importedClassIndexName;

    private final StringList variableNames;

    private final RendOperationNodeListOff init;
    private final RendOperationNodeListOff exp;
    private final RendOperationNodeListOff step;

    public RendForMutableIterativeLoop(String _className,
                                       StringList _varNames, String _classIndex, String _label,
                                       RendOperationNodeListOff _init, RendOperationNodeListOff _exp, RendOperationNodeListOff _step) {
        importedClassName = _className;
        variableNames = _varNames;
        importedClassIndexName = _classIndex;
        label = _label;
        init = _init;
        exp = _exp;
        step = _step;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        processForMutable(_cont, _stds, _ctx, _rendStack, label, this);
    }

    public RendOperationNodeListOff getInit() {
        return init;
    }

    public RendOperationNodeListOff getExp() {
        return exp;
    }

    public RendOperationNodeListOff getStep() {
        return step;
    }

    public StringList getVariableNames() {
        return variableNames;
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    public String getImportedClassIndexName() {
        return importedClassIndexName;
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        for (String v: variableNames) {
            _ip.getVars().removeKey(v);
        }
        for (String v: variableNames) {
            _ip.removeRefVar(v);
        }
    }

}
