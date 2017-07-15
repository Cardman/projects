package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.exceptions.IllegalClassConstructorException;
import code.expressionlanguage.methods.exceptions.BadConstructorCall;
import code.expressionlanguage.methods.exceptions.BadFieldException;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.InstanceOperation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.NatTreeMap;

public final class ElementBlock extends Leaf implements InfoBlock{

    private static final String NEW = "^new.";

    private final String fieldName;

    private final String value;

    private CustList<OperationNode> opValue;

    public ElementBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        fieldName = _el.getAttribute(ATTRIBUTE_NAME);
        value = _el.getAttribute(ATTRIBUTE_VALUE);
    }

    public ExpressionLanguage getValueEl() {
//        return new ExpressionLanguage(rightMember, _cont, true, new Calculation(StepCalculation.RIGHT));
        return new ExpressionLanguage(opValue);
    }

    @Override
    public boolean isStaticField() {
        return true;
    }

    @Override
    public String getClassName() {
        Block b_ = getParent();
        RootedBlock r_ = (RootedBlock) b_;
        return r_.getFullName();
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
        if (!(getParent() instanceof EnumBlock)) {
            PageEl page_ = _cont.getLastPage();
//            page_.setProcessingNode(getAssociateElement());
            page_.setProcessingAttribute(EMPTY_STRING);
//            page_.setLookForAttrValue(false);
            page_.setOffset(0);
            throw new BadFieldException(_cont.joinPages());
        }
        Block previous_ = getPreviousSibling();
        if (previous_ != null && !(previous_ instanceof ElementBlock)) {
            PageEl page_ = _cont.getLastPage();
//          page_.setProcessingNode(getAssociateElement());
            page_.setProcessingAttribute(EMPTY_STRING);
//          page_.setLookForAttrValue(false);
            page_.setOffset(0);
            throw new BadFieldException(_cont.joinPages());
        }
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        page_.setOffset(0);
        String className_ = getClassName();
        String fullInstance_ = NEW+className_ + PAR_LEFT + value + PAR_RIGHT;
        opValue = ElUtil.getAnalyzedOperations(fullInstance_, _cont, new Calculation(true));
        for (OperationNode o: opValue) {
            if (o == opValue.last()) {
                continue;
            }
            if (!(o instanceof InstanceOperation)) {
                continue;
            }
            InstanceOperation i_ = (InstanceOperation) o;
            String enumName_ = i_.getConstId().getName();
            if (_cont.getClasses().getClassBody(enumName_) instanceof EnumBlock) {
                throw new IllegalClassConstructorException(enumName_+RETURN_LINE+_cont.joinPages());
            }
        }
//        if (!PrimitiveTypeUtil.canBeUseAsArgument(getClassName(), opValue.last().getResultClass().getName(), _cont.getClasses())) {
//            throw new DynamicCastClassException(_cont.joinPages());
//        }
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public NatTreeMap<String, String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        tr_.put(ATTRIBUTE_CLASS, getClassName());
        return tr_;
    }
    @Override
    public void checkCallConstructor(ContextEl _cont) {
        PageEl p_ = _cont.getLastPage();
//        p_.setProcessingNode(getAssociateElement());
        p_.setProcessingAttribute(ATTRIBUTE_VALUE);
//        p_.setLookForAttrValue(true);
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
        return TAG_ELEMENT;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        boolean instancing_ = ip_.isInstancing();
        if (!instancing_) {
            ip_.setProcessingAttribute(ATTRIBUTE_VALUE);
            ip_.setOffset(0);
            String name_ = getFieldName();
            ip_.setEnumName(name_);
            Struct struct_;
            ExpressionLanguage el_;
            if (!ip_.getCurrentEls().isEmpty()) {
                el_ = ip_.getCurrentEls().last();
            } else {
                el_ = getValueEl();
                ip_.setCurrentBlock(this);
                ip_.setCurrentEls(new CustList<ExpressionLanguage>(el_));
            }
            Argument arg_ = el_.calculateMember(_cont);
            struct_ = arg_.getStruct();
            el_.setCurrentOper(null);
            ip_.getCurrentEls().clear();
            RootedBlock r_ = getRooted();
            ClassField staticField_ = new ClassField(r_.getFullName(), name_);
            _cont.getClasses().initializeStaticField(staticField_, struct_);
            ip_.setEnumName(EMPTY_STRING);
        }
        processBlock(_cont);
    }
}
