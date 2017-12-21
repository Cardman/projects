package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.LgNames;
import code.sml.Element;
import code.util.CustList;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.StringList;

public final class MethodBlock extends NamedFunctionBlock {

    private final boolean staticMethod;

    private final boolean finalMethod;
    private final boolean abstractMethod;

    private final boolean normalMethod;

    private final String declaringType;

    public MethodBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        String modifier_ = _el.getAttribute(ATTRIBUTE_MODIFIER);
        staticMethod = StringList.quickEq(modifier_, VALUE_STATIC);
        finalMethod = StringList.quickEq(modifier_, VALUE_FINAL);
        abstractMethod = StringList.quickEq(modifier_, VALUE_ABSTRACT);
        normalMethod = StringList.quickEq(modifier_, VALUE_NORMAL);
        declaringType = getRooted().getFullName();
    }

    @Override
    public String getSignature() {
        return getId().getSignature();
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
    public NatTreeMap<String,String> getClassNames(LgNames _stds) {
        NatTreeMap<String,String> tr_ = super.getClassNames(_stds);
        tr_.put(ATTRIBUTE_CLASS, getReturnType(_stds));
        return tr_;
    }

    public String getDeclaringType() {
        return declaringType;
    }

    public MethodId getFormattedId(String _genericClass, ContextEl _context) {
        String name_ = getName();
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        EqList<ClassName> pTypes_ = new EqList<ClassName>();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            String formatted_ = Templates.format(_genericClass, n_, _context);
            pTypes_.add(new ClassName(formatted_, i + 1 == len_ && isVarargs()));
        }
        return new MethodId(isStaticMethod(), name_, pTypes_);
    }

    public MethodId getFormattedId(ContextEl _context) {
        String className_ = declaringType;
        StringList vars_ = new StringList();
        Classes classes_ = _context.getClasses();
        for (TypeVar t: classes_.getClassBody(className_).getParamTypes()) {
            vars_.add(Templates.PREFIX_VAR_TYPE+t.getName());
        }
        String current_;
        if (vars_.isEmpty()) {
            current_ = className_;
        } else {
            current_ = className_+LT+vars_.join(SEP_TMP)+GT;
        }
        String name_ = getName();
        StringList types_ = getParametersTypes();
        int len_ = types_.size();
        EqList<ClassName> pTypes_ = new EqList<ClassName>();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            String formatted_ = Templates.format(current_, n_, _context);
            pTypes_.add(new ClassName(formatted_, i + 1 == len_ && isVarargs()));
        }
        return new MethodId(isStaticMethod(), name_, pTypes_);
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
        return new MethodId(isStaticMethod(), name_, pTypes_);
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

    public boolean isConcreteMethod() {
        return isNormalMethod() || isFinalMethod();
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
    public RootBlock belong() {
        return (RootBlock) getParent();
    }
}
