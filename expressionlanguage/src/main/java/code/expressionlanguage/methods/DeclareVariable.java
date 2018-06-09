package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetBooleanInfo;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.util.BadVariableName;
import code.expressionlanguage.methods.util.DuplicateVariable;
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

    private final String variableName;

    private int variableNameOffset;

    private final String className;

    private int classNameOffset;

    private boolean merged;

    private boolean finalVariable;

    private int finalVariableOffset;

    DeclareVariable(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        variableName = _el.getAttribute(ATTRIBUTE_VAR);
        className = _el.getAttribute(ATTRIBUTE_CLASS);
    }

    public DeclareVariable(boolean _merged, ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetBooleanInfo _finalVar, OffsetStringInfo _className, OffsetStringInfo _variableName, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        merged = _merged;
        finalVariable = _finalVar.isInfo();
        finalVariableOffset = _finalVar.getOffset();
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variableName.getInfo();
        variableNameOffset = _variableName.getOffset();
    }

    @Override
    public int getVariableNameOffset() {
        return variableNameOffset;
    }

    public int getClassNameOffset() {
        return classNameOffset;
    }

    @Override
    public String getVariableName() {
        return variableName;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        String cl_ = _cont.resolveType(className);
        _cont.setMerged(merged);
        _cont.setFinalVariable(finalVariable);
        if (_cont.containsLocalVar(variableName)) {
            page_.setGlobalOffset(variableNameOffset);
            page_.setOffset(0);
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableName);
            d_.setFileName(getFile().getFileName());
            d_.setRc(getRowCol(0, variableNameOffset));
            _cont.getClasses().getErrorsDet().add(d_);
            return;
        }
        if (!StringList.isWord(variableName)) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(getFile().getFileName());
            b_.setRc(getRowCol(0, variableNameOffset));
            b_.setVarName(variableName);
            _cont.getClasses().getErrorsDet().add(b_);
        }
        if (!merged) {
            LocalVariable lv_ = new LocalVariable();
            lv_.setClassName(cl_);
            lv_.setFinalVariable(finalVariable);
            _cont.putLocalVar(variableName, lv_);
        } else {
            _cont.setCurrentVarSetting(cl_);
        }
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        AssignedVariablesBlock glAss_ = _an.getAssignedVariables();
        AssignedVariables ass_ = glAss_.getFinalVariables().getVal(this);
        for (EntryCust<ClassField,AssignmentBefore> e: ass_.getFieldsRootBefore().entryList()) {
            ClassField key_ = e.getKey();
            ass_.getFieldsRoot().put(key_, e.getValue().assignAfterClassic());
        }
        AssignmentBefore asBe_ = new AssignmentBefore();
        asBe_.setUnassignedBefore(true);
        ass_.getVariablesRootBefore().last().put(variableName, asBe_);
        for (StringMap<AssignmentBefore> s: ass_.getVariablesRootBefore()) {
            StringMap<SimpleAssignment> vars_ = new StringMap<SimpleAssignment>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                vars_.put(e.getKey(), e.getValue().assignAfterClassic());
            }
            ass_.getVariablesRoot().add(vars_);
        }
        SimpleAssignment asf_ = asBe_.assignAfterClassic();
        StringMap<SimpleAssignment> as_ = ass_.getVariablesRoot().last();
        as_.put(variableName, asf_);
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
        String className_ = getClassName();
        lv_.setClassName(className_);
        lv_.setStruct(PrimitiveTypeUtil.defaultValue(className_, _cont));
        String name_ = getVariableName();
        ip_.putLocalVar(name_, lv_);
        processBlock(_cont);
    }
    public boolean isMerged() {
        return merged;
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
