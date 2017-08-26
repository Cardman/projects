package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.FctConstraints;
import code.util.CustList;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.StringList;

public final class ConstructorBlock extends BracedBlock implements Returnable {

    private final StringList parametersTypes;

    private final StringList parametersNames;

    private final boolean varargs;

    private final AccessEnum access;

    private InstancingStep instancing;
    private FctConstraints constIdSameClass;

    private FctConstraints constId;

    private FctConstraints constraints;

    public ConstructorBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        parametersTypes = new StringList();
        int i_ = CustList.FIRST_INDEX;
        boolean varargs_ = false;
        while (_el.hasAttribute(ATTRIBUTE_CLASS+i_)) {
            String className_ = _el.getAttribute(ATTRIBUTE_CLASS+i_);
            if (!_el.hasAttribute(ATTRIBUTE_CLASS+(i_+1))) {
                varargs_ = className_.endsWith(VARARG);
                if (varargs_) {
                    parametersTypes.add(className_.substring(CustList.FIRST_INDEX, className_.length()-VARARG.length()));
                } else {
                    parametersTypes.add(className_);
                }
            } else {
                parametersTypes.add(className_);
            }
            i_++;
        }
        varargs = varargs_;
        parametersNames = new StringList();
        i_ = CustList.FIRST_INDEX;
        while (_el.hasAttribute(ATTRIBUTE_VAR+i_)) {
            parametersNames.add(_el.getAttribute(ATTRIBUTE_VAR+i_));
            i_++;
        }
        access = AccessEnum.valueOf(_el.getAttribute(ATTRIBUTE_ACCESS));
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

    @Override
    public NatTreeMap<String,String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        StringList l_ = getParametersTypes();
        int i_ = 0;
        for (String t: l_) {
            tr_.put(ATTRIBUTE_CLASS+i_, t);
            i_++;
        }
        return tr_;
    }

    @Override
    public StringList getParametersTypes() {
        return new StringList(parametersTypes);
    }

    @Override
    public StringList getParametersNames() {
        return new StringList(parametersNames);
    }

    @Override
    public boolean isVarargs() {
        return varargs;
    }

    @Override
    public AccessEnum getAccess() {
        return access;
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(EMPTY_STRING);
        page_.setOffset(0);
        if (getFirstChild() == null) {
            return;
        }
        Block en_ = this;
        while (true) {
            Block n_ = en_.getFirstChild();
            if (n_ != null) {
                tryCheckBlocksTree(n_, _cont);
                en_ = n_;
                continue;
            }
            n_ = en_.getNextSibling();
            if (n_ != null) {
                tryCheckBlocksTree(n_, _cont);
                en_ = n_;
                continue;
            }
            n_ = en_.getParent();
            if (n_ == this) {
                tryCheckBlocksTree(en_, _cont);
                en_.removeLocalVariablesFromParent(_cont);
                break;
            }
            en_.removeLocalVariablesFromParent(_cont);
            Block next_ = n_.getNextSibling();
            boolean exitByBreak_ = false;
            while (next_ == null) {
                Block par_ = n_.getParent();
                if (par_ == this) {
                    exitByBreak_ = true;
                    tryCheckBlocksTree(n_, _cont);
                    n_.removeLocalVariablesFromParent(_cont);
                    break;
                }
                n_.removeLocalVariablesFromParent(_cont);
                next_ = par_.getNextSibling();
                n_ = par_;
            }
            if (exitByBreak_) {
                break;
            }
            tryCheckBlocksTree(next_, _cont);
            en_ = next_;
        }
    }

    @Override
    public void buildInstructions(ContextEl _cont) {
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(EMPTY_STRING);
        page_.setOffset(0);
        if (getFirstChild() == null) {
            return;
        }
        Block en_ = this;
        while (true) {
            Block n_ = en_.getFirstChild();
            if (n_ != null) {
                tryBuildExpressionLanguage(n_, _cont);
                en_ = n_;
                continue;
            }
            n_ = en_.getNextSibling();
            if (n_ != null) {
                tryBuildExpressionLanguage(n_, _cont);
                en_ = n_;
                continue;
            }
            n_ = en_.getParent();
            if (n_ == this) {
                en_.removeLocalVariablesFromParent(_cont);
                break;
            }
            en_.removeLocalVariablesFromParent(_cont);
            Block next_ = n_.getNextSibling();
            boolean exitByBreak_ = false;
            while (next_ == null) {
                Block par_ = n_.getParent();
                if (par_ == this) {
                    exitByBreak_ = true;
                    n_.removeLocalVariablesFromParent(_cont);
                    break;
                }
                n_.removeLocalVariablesFromParent(_cont);
                next_ = par_.getNextSibling();
                n_ = par_;
            }
            if (exitByBreak_) {
                break;
            }
            tryBuildExpressionLanguage(next_, _cont);
            en_ = next_;
        }
    }

    @Override
    public void checkConstrCalls(ContextEl _cont) {
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(EMPTY_STRING);
        page_.setOffset(0);
        if (getFirstChild() == null) {
            return;
        }
        Block en_ = this;
        while (true) {
            Block n_ = en_.getFirstChild();
            if (n_ != null) {
                tryCheckConstCall(n_, _cont);
                en_ = n_;
                continue;
            }
            n_ = en_.getNextSibling();
            if (n_ != null) {
                tryCheckConstCall(n_, _cont);
                en_ = n_;
                continue;
            }
            n_ = en_.getParent();
            if (n_ == this) {
                tryCheckConstCall(en_, _cont);
                en_.removeLocalVariablesFromParent(_cont);
                break;
            }
            en_.removeLocalVariablesFromParent(_cont);
            Block next_ = n_.getNextSibling();
            boolean exitByBreak_ = false;
            while (next_ == null) {
                Block par_ = n_.getParent();
                if (par_ == this) {
                    exitByBreak_ = true;
                    tryCheckConstCall(n_, _cont);
                    n_.removeLocalVariablesFromParent(_cont);
                    break;
                }
                n_.removeLocalVariablesFromParent(_cont);
                next_ = par_.getNextSibling();
                n_ = par_;
            }
            if (exitByBreak_) {
                break;
            }
            tryCheckConstCall(next_, _cont);
            en_ = next_;
        }
    }

    public void setupInstancingStep(ContextEl _cont) {
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(EMPTY_STRING);
        page_.setOffset(0);
        Block first_ = getFirstChild();
        if (!(first_ instanceof Line)) {
            instancing = InstancingStep.USING_SUPER_IMPLICIT;
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
        instancing = InstancingStep.USING_SUPER_IMPLICIT;
    }
    public boolean superConstr() {
        return instancing == InstancingStep.USING_SUPER || implicitConstr();
    }

    public boolean implicitConstr() {
        return instancing == InstancingStep.USING_SUPER_IMPLICIT;
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

    public FctConstraints getConstIdSameClass() {
        return constIdSameClass;
    }

    public FctConstraints getConstId() {
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

    @Override
    public FctConstraints getConstraints() {
        return constraints;
    }

    @Override
    public void setConstraints(FctConstraints _constraints) {
        constraints = _constraints;
    }
}
