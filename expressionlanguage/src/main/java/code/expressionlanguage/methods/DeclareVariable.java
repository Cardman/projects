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
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.variables.LocalVariable;
import code.util.StringList;

public final class DeclareVariable extends Leaf implements InitVariable {

    private final StringList variableNames = new StringList();

    private final String className;

    private String importedClassName;

    private int classNameOffset;

    private boolean finalVariable;

    private int finalVariableOffset;

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

    public void setImportedClassName(String _importedClassName) {
        importedClassName = _importedClassName;
    }
    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringList.quickEq(className.trim(), keyWordVar_)) {
            importedClassName = keyWordVar_;
        } else {
            importedClassName = _cont.resolveCorrectType(className);
        }
        _cont.setMerged(true);
        _cont.setFinalVariable(finalVariable);
        _cont.setCurrentVarSetting(importedClassName);
        _cont.getVariablesNames().clear();
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        buildEmptyEl(_an);
    }
    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        Struct struct_ = PrimitiveTypeUtil.defaultValue(importedClassName, _cont);
        for (String v: getVariableNames()) {
            LocalVariable lv_ = new LocalVariable();
            lv_.setClassName(importedClassName);
            lv_.setStruct(struct_);
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
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        return null;
    }
}
