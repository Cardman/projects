package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.util.BadVariableName;
import code.expressionlanguage.methods.util.DuplicateVariable;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stacks.TryBlockStack;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Element;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class CatchEval extends AbstractCatchEval {

    private final String className;

    private String importedClassName;

    private int classNameOffset;

    private final String variableName;

    private int variableNameOffset;

    public CatchEval(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        className = _el.getAttribute(ATTRIBUTE_CLASS);
        variableName = _el.getAttribute(ATTRIBUTE_VAR);
    }

    public CatchEval(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetStringInfo _className, OffsetStringInfo _variable, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variable.getInfo();
        variableNameOffset = _variable.getOffset();
    }

    public int getClassNameOffset() {
        return classNameOffset;
    }
    public int getVariableNameOffset() {
        return variableNameOffset;
    }
    public String getClassName() {
        return className;
    }

    public String getImportedClassName() {
        return importedClassName;
    }
    public String getVariableName() {
        return variableName;
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(variableNameOffset);
        page_.setOffset(0);
        if (getFirstChild() == null) {
            AssignedVariablesBlock glAss_ = _cont.getAssignedVariables();
            AssignedVariables ass_ = glAss_.getFinalVariables().getVal(this);
            for (EntryCust<ClassField,AssignmentBefore> e: ass_.getFieldsRootBefore().entryList()) {
                ClassField key_ = e.getKey();
                ass_.getFieldsRoot().put(key_, e.getValue().assignAfterClassic());
            }
            for (StringMap<AssignmentBefore> s: ass_.getVariablesRootBefore()) {
                StringMap<SimpleAssignment> vars_ = new StringMap<SimpleAssignment>();
                for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                    vars_.put(e.getKey(), e.getValue().assignAfterClassic());
                }
                ass_.getVariablesRoot().add(vars_);
            }
        }
        if (_cont.getAnalyzing().containsCatchVar(variableName)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableName);
            d_.setFileName(getFile().getFileName());
            d_.setRc(getRowCol(0, variableNameOffset));
            _cont.getClasses().addError(d_);
            return;
        }
        if (!StringList.isWord(variableName)) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(getFile().getFileName());
            b_.setRc(getRowCol(0, variableNameOffset));
            b_.setVarName(variableName);
            _cont.getClasses().addError(b_);
        }
        if (getFirstChild() == null) {
            return;
        }
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(importedClassName);
        _cont.getAnalyzing().putCatchVar(variableName, lv_);
    }

    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        String var_ = getVariableName();
        StringMap<LocalVariable> vars_ = ip_.getCatchVars();
        vars_.removeKey(var_);
        rw_.setBlock(this);
    }

    @Override
    public void reach(Analyzable _an, AnalyzingEl _anEl) {
        AnalyzedPageEl page_ = _an.getAnalyzing();
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        importedClassName = _an.resolveCorrectType(className);
        StringList classes_ = new StringList();
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof TryEval)) {
            if (p_ instanceof CatchEval) {
                classes_.add(((CatchEval)p_).importedClassName);
            }
            p_ = p_.getPreviousSibling();
        }
        _anEl.setArgMapping(importedClassName);
        boolean reachCatch_ = true;
        for (String c: classes_) {
            _anEl.setParamMapping(c);
            if (_anEl.isCorrectMapping(_an)) {
                reachCatch_ = false;
                break;
            }
        }
        if (reachCatch_) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }

    @Override
    public void processToFinally(AbstractPageEl _ip, TryBlockStack _stack) {
        String var_ = getVariableName();
        _ip.getCatchVars().removeKey(var_);
        super.processToFinally(_ip, _stack);
    }
}
