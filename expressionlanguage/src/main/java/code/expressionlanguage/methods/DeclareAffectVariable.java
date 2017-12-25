package code.expressionlanguage.methods;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.Templates;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.methods.exceptions.AlreadyDefinedVarException;
import code.expressionlanguage.methods.exceptions.BadConstructorCall;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Element;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class DeclareAffectVariable extends Leaf implements InitVariable {

    private final String variableName;

    private final String className;

    private final String rightMember;

    private CustList<OperationNode> opRight;

    DeclareAffectVariable(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        variableName = _el.getAttribute(ATTRIBUTE_VAR);
        className = _el.getAttribute(ATTRIBUTE_CLASS);
        rightMember = _el.getAttribute(ATTRIBUTE_EXPRESSION);
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

    public ExpressionLanguage getRightEl() {
        return new ExpressionLanguage(opRight);
    }

    public String getRightMember() {
        return rightMember;
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(ATTRIBUTE_CLASS);
        page_.setOffset(0);
        if (_cont.getLastPage().getLocalVars().contains(variableName)) {
            page_.setProcessingAttribute(ATTRIBUTE_VAR);
            page_.setOffset(0);
            throw new AlreadyDefinedVarException(variableName+RETURN_LINE+_cont.joinPages());
        }
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(className);
        _cont.getLastPage().getLocalVars().put(variableName, lv_);
        page_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        page_.setOffset(0);
        opRight = ElUtil.getAnalyzedOperations(rightMember, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        StringMap<StringList> vars_ = new StringMap<StringList>();
        if (!f_.isStaticContext()) {
            String globalClass_ = page_.getGlobalClass();
            String curClassBase_ = StringList.getAllTypes(globalClass_).first();
            for (TypeVar t: _cont.getClasses().getClassBody(curClassBase_).getParamTypes()) {
                vars_.put(t.getName(), t.getConstraints());
            }
        }
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        mapping_.setArg(opRight.last().getResultClass().getName());
        mapping_.setParam(className);
        if (!Templates.isCorrect(mapping_, _cont)) {
            throw new DynamicCastClassException(_cont.joinPages());
        }
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public void checkCallConstructor(ContextEl _cont) {
        PageEl p_ = _cont.getLastPage();
        p_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        for (OperationNode o: opRight) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                throw new BadConstructorCall(_cont.joinPages());
            }
        }
    }

    @Override
    public String getTagName() {
        return TAG_DECLARE_SET;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        ip_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        ip_.setOffset(0);
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(getClassName());
        String name_ = getVariableName();
        StringMap<LocalVariable> map_ = ip_.getLocalVars();
        ExpressionLanguage el_ = ip_.getCurrentEl(this, CustList.FIRST_INDEX, getRightEl());
        Argument arg_ = el_.calculateMember(_cont);
        el_.setCurrentOper(null);
        ip_.clearCurrentEls();
        lv_.setStruct(arg_.getStruct());
        map_.put(name_, lv_);
        processBlock(_cont);
    }
}
