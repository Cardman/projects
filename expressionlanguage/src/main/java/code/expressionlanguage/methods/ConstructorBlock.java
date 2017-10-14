package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;

public final class ConstructorBlock extends NamedFunctionBlock {

    private InstancingStep instancing;
    private ConstructorId constIdSameClass;

    private ConstructorId constId;

    private boolean implicitCallSuper;

    public ConstructorBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    public ConstructorId getId() {
        RootBlock clBlock_ = (RootBlock) getParent();
        String name_ = clBlock_.getFullName();
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        EqList<ClassName> pTypes_ = new EqList<ClassName>();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(new ClassName(n_, i + 1 == len_ && isVarargs()));
        }
        return new ConstructorId(name_, pTypes_);
    }
    public ConstructorId getGenericId() {
        RootBlock clBlock_ = (RootBlock) getParent();
        String name_ = clBlock_.getGenericString();
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        EqList<ClassName> pTypes_ = new EqList<ClassName>();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(new ClassName(n_, i + 1 == len_ && isVarargs()));
        }
        return new ConstructorId(name_, pTypes_);
    }
    public ConstructorId getFormattedId(String _genericClass, Classes _classes) {
        String name_ = Templates.format(_genericClass, getName(), _classes);
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        EqList<ClassName> pTypes_ = new EqList<ClassName>();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            String formatted_ = Templates.format(_genericClass, n_, _classes);
            pTypes_.add(new ClassName(formatted_, i + 1 == len_ && isVarargs()));
        }
        return new ConstructorId(name_, pTypes_);
    }
    public void setupInstancingStep(ContextEl _cont) {
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(EMPTY_STRING);
        page_.setOffset(0);
        Block first_ = getFirstChild();
        if (!(first_ instanceof Line)) {
            implicitCallSuper = true;
            instancing = InstancingStep.USING_SUPER;
            return;
        }
        Line l_ = (Line) first_;
        if (l_.isCallSuper()) {
            constId = l_.getConstId();
            instancing = InstancingStep.USING_SUPER;
            return;
        }
        if (l_.isCallThis()) {
            constIdSameClass = l_.getConstId();
            constId = constIdSameClass;
            instancing = InstancingStep.USING_THIS;
            return;
        }
        implicitCallSuper = true;
        instancing = InstancingStep.USING_SUPER;
    }
    public boolean superConstr() {
        return instancing == InstancingStep.USING_SUPER || implicitConstr();
    }

    public boolean implicitConstr() {
        return implicitCallSuper;
    }

    public boolean hasFirstBlockAfterSuperConstr() {
        if (!superConstr()) {
            return false;
        }
        return getFirstBlockAfterOtherConstr() != null;
    }

    public Block getFirstBlockAfterOtherConstr() {
        Block ch_ = getFirstChild();
        if (implicitConstr()) {
            return ch_;
        }
        //the constructor has a line as first block which call an other constructor
        return ch_.getNextSibling();
    }

    public ConstructorId getConstIdSameClass() {
        return constIdSameClass;
    }

    public ConstructorId getConstId() {
        return constId;
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return false;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public boolean isStaticContext() {
        return false;
    }

    @Override
    public String getTagName() {
        return TAG_CONSTRUCTOR;
    }

    @Override
    public String getReturnType() {
        return OperationNode.VOID_RETURN;
    }

    @Override
    public String getName() {
        return EMPTY_STRING;
    }

    @Override
    public RootBlock belong() {
        return (RootBlock) getParent();
    }
}
