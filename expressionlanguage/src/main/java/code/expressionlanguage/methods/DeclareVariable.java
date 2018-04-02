package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.exceptions.AlreadyDefinedVarException;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Element;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class DeclareVariable extends Leaf implements InitVariable {

    private final String variableName;

    private int variableNameOffset;

    private final String className;

    private int classNameOffset;

    DeclareVariable(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        variableName = _el.getAttribute(ATTRIBUTE_VAR);
        className = _el.getAttribute(ATTRIBUTE_CLASS);
    }

    public DeclareVariable(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetStringInfo _className, OffsetStringInfo _variableName, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variableName.getInfo();
        variableNameOffset = _variableName.getOffset();
    }

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
    public String getVariableName() {
        return variableName;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(ATTRIBUTE_CLASS);
        page_.setOffset(0);
        if (_cont.getLastPage().getLocalVars().contains(variableName)) {
            page_.setProcessingAttribute(ATTRIBUTE_VAR);
            page_.setOffset(0);
            throw new AlreadyDefinedVarException(StringList.concat(variableName,RETURN_LINE,_cont.joinPages()));
        }
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(className);
        _cont.getLastPage().getLocalVars().put(variableName, lv_);
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
        StringMap<LocalVariable> map_ = ip_.getLocalVars();
        map_.put(name_, lv_);
        processBlock(_cont);
    }
}
