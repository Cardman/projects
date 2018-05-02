package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetBooleanInfo;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.util.DuplicateVariable;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Element;
import code.util.NatTreeMap;

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
    public NatTreeMap<String,String> getClassNames(ContextEl _context) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        tr_.put(ATTRIBUTE_CLASS, className);
        return tr_;
    }

    @Override
    public NatTreeMap<Integer,String> getClassNamesOffsets(ContextEl _context) {
        NatTreeMap<Integer,String> tr_ = new NatTreeMap<Integer,String>();
        tr_.put(classNameOffset, className);
        return tr_;
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
        if (!merged) {
            LocalVariable lv_ = new LocalVariable();
            lv_.setClassName(className);
            _cont.putLocalVar(variableName, lv_);
        } else {
            _cont.setCurrentVarSetting(className);
        }
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public void checkCallConstructor(ContextEl _cont) {
    }

    @Override
    public String getTagName() {
        return TAG_DECLARE;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
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
