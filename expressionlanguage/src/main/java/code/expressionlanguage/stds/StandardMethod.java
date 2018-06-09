package code.expressionlanguage.stds;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.util.CustList;
import code.util.StringList;

public final class StandardMethod extends StandardNamedFunction implements GeneMethod {

    private final MethodModifier modifier;

    public StandardMethod(String _name, StringList _parametersTypes,
            String _returnType, boolean _varargs, MethodModifier _modifier, StandardType _type) {
        super(_name, _parametersTypes, _returnType, _varargs, _type);
        modifier = _modifier;
    }

    public MethodId getId() {
        String name_ = getName();
        StringList types_ = getParametersTypes(null);
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new MethodId(isStaticMethod(), name_, pTypes_, isVarargs());
    }

    @Override
    public boolean isStaticMethod() {
        return modifier == MethodModifier.STATIC;
    }

    @Override
    public boolean isFinalMethod() {
        return modifier == MethodModifier.FINAL;
    }

    @Override
    public boolean isAbstractMethod() {
        return modifier == MethodModifier.ABSTRACT;
    }

    @Override
    public boolean isNormalMethod() {
        return modifier == MethodModifier.NORMAL;
    }

    @Override
    public String getDeclaringType() {
        return getOwner().getFullName();
    }
    public String getPrettyString() {
        return StringList.concat(getDeclaringType(),".",getId().getSignature(),":",getReturnType()," is ",modifier.name());
    }

    public String getSignature() {
        return getId().getSignature();
    }

    @Override
    public MethodModifier getModifier() {
        return modifier;
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
        String className_ = getDeclaringType();
        StringList vars_ = new StringList();
        for (TypeVar t: _context.getClassBody(className_).getParamTypesMapValues()) {
            vars_.add(StringList.concat(Templates.PREFIX_VAR_TYPE,t.getName()));
        }
        String current_ = className_;
        String name_ = getName();
        StringList types_ = getParametersTypes(null);
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            String formatted_ = Templates.format(current_, n_, _context);
            pTypes_.add(formatted_);
        }
        return new MethodId(isStaticMethod(), name_, pTypes_, isVarargs());
    }

    @Override
    public boolean isConcreteInstanceDerivableMethod() {
        if (isStaticMethod()) {
            return false;
        }
        if (isFinalMethod()) {
            return false;
        }
        if (isAbstractMethod()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isConcreteMethod() {
        return isNormalMethod() || isFinalMethod();
    }
}
