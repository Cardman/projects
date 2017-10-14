package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.methods.exceptions.BadConstructorCall;
import code.expressionlanguage.methods.exceptions.BadFieldException;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class FieldBlock extends Leaf implements InfoBlock {

    private final String fieldName;

    private final String className;

    private final String value;

    private final boolean staticField;

    private final boolean finalField;

    private final AccessEnum access;

    private CustList<OperationNode> opValue;

    public FieldBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        fieldName = _el.getAttribute(ATTRIBUTE_NAME);
        className = _el.getAttribute(ATTRIBUTE_CLASS);
        value = _el.getAttribute(ATTRIBUTE_VALUE);
        staticField = _el.hasAttribute(ATTRIBUTE_STATIC);
        finalField = _el.hasAttribute(ATTRIBUTE_FINAL);
        access = AccessEnum.valueOf(_el.getAttribute(ATTRIBUTE_ACCESS));
    }
    public Struct getDefaultStruct() {
        Object value_ = PrimitiveTypeUtil.defaultValue(className);
        if (value.isEmpty()) {
            if (value_ == null) {
                return new Struct();
            }
            return new Struct(value_);
        }
        ExpressionLanguage el_ = getValueEl();
        if (el_.isAlwaysCalculated()) {
            return el_.getConstValue();
        }
        if (value_ == null) {
            return new Struct();
        }
        return new Struct(value_);
    }

    @Override
    public AccessEnum getAccess() {
        return access;
    }

    public ExpressionLanguage getValueEl() {
        return new ExpressionLanguage(opValue);
    }

    @Override
    public boolean isStaticField() {
        return staticField;
    }

    @Override
    public boolean isFinalField() {
        return finalField;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
        if (!(getParent() instanceof RootBlock)) {
            PageEl page_ = _cont.getLastPage();
            page_.setProcessingAttribute(EMPTY_STRING);
            page_.setOffset(0);
            throw new BadFieldException(_cont.joinPages());
        }
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(ATTRIBUTE_CLASS);
        page_.setOffset(0);
        if (value.isEmpty()) {
            return;
        }
        page_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        page_.setOffset(0);
        opValue = ElUtil.getAnalyzedOperations(value, _cont, Calculation.staticCalculation(staticField));
        StringMap<StringList> vars_ = new StringMap<StringList>();
        if (!staticField) {
            String globalClass_ = page_.getGlobalClass();
            String curClassBase_ = StringList.getAllTypes(globalClass_).first();
            for (TypeVar t: _cont.getClasses().getClassBody(curClassBase_).getParamTypes()) {
                vars_.put(t.getName(), t.getConstraints());
            }
        }
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        mapping_.setArg(opValue.last().getResultClass().getName());
        mapping_.setParam(className);
        if (!Templates.isCorrect(mapping_, _cont.getClasses())) {
            throw new DynamicCastClassException(_cont.joinPages());
        }
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public NatTreeMap<String, String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        tr_.put(ATTRIBUTE_CLASS, className);
        return tr_;
    }
    @Override
    public void checkCallConstructor(ContextEl _cont) {
        if (value.isEmpty()) {
            return;
        }
        PageEl p_ = _cont.getLastPage();
        p_.setProcessingAttribute(ATTRIBUTE_VALUE);
        for (OperationNode o: opValue) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                throw new BadConstructorCall(_cont.joinPages());
            }
        }
    }

    @Override
    public String getTagName() {
        return TAG_FIELD;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        boolean instancing_ = ip_.isInstancing();
        boolean static_ = isStaticField();
        if (static_ != instancing_) {
            ip_.setProcessingAttribute(ATTRIBUTE_VALUE);
            ip_.setOffset(0);
            String name_ = getFieldName();
            Struct struct_;
            if (value.isEmpty()) {
                Object value_ = PrimitiveTypeUtil.defaultValue(className);
                if (value_ == null) {
                    struct_ = new Struct();
                } else {
                    struct_ = new Struct(value_);
                }
            } else {
                ExpressionLanguage el_ = ip_.getCurrentEl(this, CustList.FIRST_INDEX, getValueEl());
                Argument arg_ = el_.calculateMember(_cont);
                struct_ = arg_.getStruct();
                el_.setCurrentOper(null);
                ip_.clearCurrentEls();
            }
            RootBlock r_ = getRooted();
            ClassField staticField_ = new ClassField(r_.getFullName(), name_);
            if (static_) {
                _cont.getClasses().initializeStaticField(staticField_, struct_);
            } else {
                Argument gl_ = ip_.getGlobalArgument();
                gl_.getStruct().setStruct(staticField_, struct_);
            }
        }
        processBlock(_cont);
    }

    @Override
    public RootBlock belong() {
        return (RootBlock) getParent();
    }
}
