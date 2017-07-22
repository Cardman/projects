package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.util.CustList;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.StringList;

public final class MethodBlock extends BracedBlock implements Returnable {

    private final String name;

    private final StringList parametersTypes;

    private final String returnType;

    private final StringList parametersNames;

    private final boolean varargs;

    private final boolean staticMethod;

    private final boolean finalMethod;
    
    private final boolean abstractMethod;

    private final boolean normalMethod;

    private final AccessEnum access;

    public MethodBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        name = _el.getAttribute(ATTRIBUTE_NAME);
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
        returnType = _el.getAttribute(ATTRIBUTE_CLASS);
        parametersNames = new StringList();
        i_ = CustList.FIRST_INDEX;
        while (_el.hasAttribute(ATTRIBUTE_VAR+i_)) {
            parametersNames.add(_el.getAttribute(ATTRIBUTE_VAR+i_));
            i_++;
        }
        String modifier_ = _el.getAttribute(ATTRIBUTE_MODIFIER);
        staticMethod = StringList.quickEq(modifier_, VALUE_STATIC);
        finalMethod = StringList.quickEq(modifier_, VALUE_FINAL);
        abstractMethod = StringList.quickEq(modifier_, VALUE_ABSTRACT);
        normalMethod = StringList.quickEq(modifier_, VALUE_NORMAL);
        access = AccessEnum.valueOf(_el.getAttribute(ATTRIBUTE_ACCESS));
    }

    public MethodModifier getModifier() {
        if (staticMethod) {
            return MethodModifier.STATIC;
        }
        if (finalMethod) {
            return MethodModifier.FINAL;
        }
        if (staticMethod) {
            return MethodModifier.STATIC;
        }
        return MethodModifier.NORMAL;
    }

    @Override
    public AccessEnum getAccess() {
        return access;
    }

    public MethodId getId() {
        String name_ = getName();
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        EqList<ClassName> pTypes_ = new EqList<ClassName>();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(new ClassName(n_, i + 1 == len_ && isVarargs()));
        }
        return new MethodId(name_, pTypes_);
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
        tr_.put(ATTRIBUTE_CLASS, returnType);
        return tr_;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public StringList getParametersTypes() {
        return new StringList(parametersTypes);
    }

    @Override
    public String getReturnType() {
        return returnType;
    }

    @Override
    public StringList getParametersNames() {
        return new StringList(parametersNames);
    }

    @Override
    public boolean isVarargs() {
        return varargs;
    }

    public boolean isConcreteInstanceDerivableMethod() {
        if (staticMethod) {
            return false;
        }
        if (finalMethod) {
            return false;
        }
        if (abstractMethod) {
            return false;
        }
        return true;
    }

    public boolean isStaticMethod() {
        return staticMethod;
    }

    public boolean isFinalMethod() {
        return finalMethod;
    }

    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public boolean isNormalMethod() {
        return normalMethod;
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
        PageEl page_ = _cont.getLastPage();
//        page_.setProcessingNode(getAssociateElement());
        page_.setProcessingAttribute(EMPTY_STRING);
//        page_.setLookForAttrValue(false);
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
//            if (en_ == this) {
//                break;
//            }
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
//        page_.setProcessingNode(getAssociateElement());
        page_.setProcessingAttribute(EMPTY_STRING);
//        page_.setLookForAttrValue(false);
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
//            if (en_ == this) {
//                break;
//            }
            n_ = en_.getNextSibling();
            if (n_ != null) {
                tryBuildExpressionLanguage(n_, _cont);
                en_ = n_;
                continue;
            }
            n_ = en_.getParent();
            if (n_ == this) {
                tryBuildExpressionLanguage(en_, _cont);
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
                    tryBuildExpressionLanguage(n_, _cont);
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
//        page_.setProcessingNode(getAssociateElement());
        page_.setProcessingAttribute(EMPTY_STRING);
//        page_.setLookForAttrValue(false);
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
//            if (en_ == this) {
//                break;
//            }
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
        return staticMethod;
    }

    @Override
    public String getTagName() {
        return TAG_METHOD;
    }

    @Override
    public RootedBlock belong() {
        return (RootedBlock) getParent();
    }
}
