package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetAccessInfo;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.methods.util.MissingReturnMethod;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.LgNames;
import code.sml.Element;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;

public final class MethodBlock extends NamedFunctionBlock implements GeneMethod {

    private int modifierOffset;

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
    public MethodBlock(ContextEl _importingPage,
            int _indexChild, BracedBlock _m,
            OffsetAccessInfo _access,
            OffsetStringInfo _retType, OffsetStringInfo _fctName,
            StringList _paramTypes, Numbers<Integer> _paramTypesOffset,
            StringList _paramNames, Numbers<Integer> _paramNamesOffset,
            OffsetStringInfo _modifier, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _access, _retType, _fctName, _paramTypes, _paramTypesOffset, _paramNames, _paramNamesOffset, _offset);
        modifierOffset = _modifier.getOffset();
        String modifier_ = _modifier.getInfo();
        staticMethod = StringList.quickEq(modifier_, VALUE_STATIC);
        finalMethod = StringList.quickEq(modifier_, VALUE_FINAL);
        abstractMethod = StringList.quickEq(modifier_, VALUE_ABSTRACT);
        normalMethod = StringList.quickEq(modifier_, VALUE_NORMAL);
        declaringType = getRooted().getFullName();
    }

    public int getModifierOffset() {
        return modifierOffset;
    }

    @Override
    public String getSignature(Analyzable _an) {
        return getId(_an).getSignature();
    }

    @Override
    public MethodModifier getModifier() {
        if (abstractMethod) {
            return MethodModifier.ABSTRACT;
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
    public String getDeclaringType() {
        return declaringType;
    }

    @Override
    public MethodId getFormattedId(String _genericClass, ContextEl _context) {
        String name_ = getName();
        StringList types_ = getParametersTypes(_context);
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            String formatted_ = Templates.format(_genericClass, n_, _context);
            pTypes_.add(formatted_);
        }
        return new MethodId(isStaticMethod(), name_, pTypes_, isVarargs());
    }

    @Override
    public MethodId getFormattedId(ContextEl _context) {
        String className_ = declaringType;
        StringList vars_ = new StringList();
        for (TypeVar t: _context.getClassBody(className_).getParamTypesMapValues()) {
            vars_.add(StringList.concat(Templates.PREFIX_VAR_TYPE,t.getName()));
        }
        String current_;
        if (vars_.isEmpty()) {
            current_ = className_;
        } else {
            current_ = StringList.concat(className_,LT,vars_.join(SEP_TMP),GT);
        }
        String name_ = getName();
        StringList types_ = getParametersTypes(_context);
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            String formatted_ = Templates.format(current_, n_, _context);
            pTypes_.add(formatted_);
        }
        return new MethodId(isStaticMethod(), name_, pTypes_, isVarargs());
    }

    public MethodId getId(Analyzable _an) {
        String name_ = getName();
        StringList types_ = getParametersTypes(_an);
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new MethodId(isStaticMethod(), name_, pTypes_, isVarargs());
    }


    @Override
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

    @Override
    public boolean isConcreteMethod() {
        return isNormalMethod() || isFinalMethod();
    }

    @Override
    public boolean isStaticMethod() {
        return staticMethod;
    }

    @Override
    public boolean isFinalMethod() {
        return finalMethod;
    }

    @Override
    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    @Override
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
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        LgNames stds_ = _an.getStandards();
        if (!StringList.quickEq(getReturnType(_an), stds_.getAliasVoid())) {
            if (!isAbstractMethod() && _anEl.canCompleteNormally(this)) {
                //error
                MissingReturnMethod miss_ = new MissingReturnMethod();
                miss_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
                miss_.setFileName(getFile().getFileName());
                miss_.setId(getSignature(_an));
                miss_.setReturning(getReturnType(_an));
                _an.getClasses().getErrorsDet().add(miss_);
            }
        }
    }
}
