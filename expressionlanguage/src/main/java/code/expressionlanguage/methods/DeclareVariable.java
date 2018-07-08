package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetBooleanInfo;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Element;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class DeclareVariable extends Leaf implements InitVariable {

    private final StringList variableNames = new StringList();

    private final String className;

    private String importedClassName;

    private int classNameOffset;

    private boolean finalVariable;

    private int finalVariableOffset;

    DeclareVariable(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        className = _el.getAttribute(ATTRIBUTE_CLASS);
    }

    public DeclareVariable(boolean _merged, ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetBooleanInfo _finalVar, OffsetStringInfo _className, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        finalVariable = _finalVar.isInfo();
        finalVariableOffset = _finalVar.getOffset();
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
    }

    @Override
    public StringList getVariableNames() {
        return variableNames;
    }
    public int getClassNameOffset() {
        return classNameOffset;
    }

    @Override
    public String getClassName() {
        return className;
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        importedClassName = _cont.resolveCorrectType(className);
        _cont.setMerged(true);
        _cont.setFinalVariable(finalVariable);
        _cont.setCurrentVarSetting(importedClassName);
        _cont.getVariablesNames().clear();
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        AssignedVariablesBlock glAss_ = _an.getAssignedVariables();
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
    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public String getTagName() {
        return TAG_DECLARE;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(importedClassName);
        lv_.setStruct(PrimitiveTypeUtil.defaultValue(importedClassName, _cont));
        for (String v: getVariableNames()) {
            ip_.putLocalVar(v, lv_);
        }
        processBlock(_cont);
    }

    public boolean isFinalVariable() {
        return finalVariable;
    }

    public int getFinalVariableOffset() {
        return finalVariableOffset;
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return null;
    }
}
