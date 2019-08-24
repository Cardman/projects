package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.MissingReturnMethod;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class OverridableBlock extends NamedFunctionBlock implements GeneMethod,ReturnableWithSignature {

    private int modifierOffset;

    private final boolean staticMethod;

    private final boolean finalMethod;
    private final boolean abstractMethod;

    private final boolean normalMethod;
    private MethodKind kind;

    public OverridableBlock(ContextEl _importingPage,
                            OffsetAccessInfo _access,
                            OffsetStringInfo _retType, OffsetStringInfo _fctName,
                            StringList _paramTypes, Ints _paramTypesOffset,
                            StringList _paramNames, Ints _paramNamesOffset,
                            OffsetStringInfo _modifier, OffsetsBlock _offset) {
        super(_access, _retType, _fctName, _paramTypes, _paramTypesOffset, _paramNames, _paramNamesOffset, _offset);
        modifierOffset = _modifier.getOffset();
        String modifier_ = _modifier.getInfo();
        KeyWords keyWords_ = _importingPage.getKeyWords();
        String keyWordStatic_ = keyWords_.getKeyWordStatic();
        String keyWordFinal_ = keyWords_.getKeyWordFinal();
        String keyWordAbstract_ = keyWords_.getKeyWordAbstract();
        String keyWordNormal_ = keyWords_.getKeyWordNormal();
        staticMethod = StringList.quickEq(modifier_, keyWordStatic_);
        finalMethod = StringList.quickEq(modifier_, keyWordFinal_);
        abstractMethod = StringList.quickEq(modifier_, keyWordAbstract_);
        normalMethod = StringList.quickEq(modifier_, keyWordNormal_);
    }

    @Override
    public String getSignature(Analyzable _ana) {
        return getId().getSignature(_ana);
    }

    public int getModifierOffset() {
        return modifierOffset;
    }

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
    public MethodId getId() {
        String name_ = getName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new MethodId(isStaticMethod(), name_, pTypes_, isVarargs());
    }

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

    public boolean isNormalMethod() {
        return normalMethod;
    }

    @Override
    public boolean isStaticContext() {
        return staticMethod;
    }

    @Override
    public GeneType belong() {
        return (RootBlock) getParent();
    }
    @Override
    public void setAssignmentAfterCall(Analyzable _an, AnalyzingEl _anEl) {
        setAssignmentAfter(_an,_anEl);
        LgNames stds_ = _an.getStandards();
        if (!StringList.quickEq(getImportedReturnType(), stds_.getAliasVoid())) {
            if (!isAbstractMethod() && _anEl.canCompleteNormally(this)) {
                //error
                MissingReturnMethod miss_ = new MissingReturnMethod();
                miss_.setIndexFile(getOffset().getOffsetTrim());
                miss_.setFileName(getFile().getFileName());
                miss_.setId(getId().getSignature(_an));
                miss_.setReturning(getImportedReturnType());
                _an.getClasses().addError(miss_);
            }
        }
    }

    public MethodId getWildCardFormattedId(String _genericClass, ContextEl _context) {
        String name_ = getName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        boolean isStatic_ = isStaticMethod();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            String formatted_ = Templates.wildCardFormatParam(isStatic_, _genericClass, n_, _context);
            if (formatted_ == null) {
                return null;
            }
            pTypes_.add(formatted_);
        }
        return new MethodId(isStaticMethod(), name_, pTypes_, isVarargs());
    }

    public MethodKind getKind() {
        return kind;
    }

    public void setKind(MethodKind _kind) {
        kind = _kind;
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        int begName_ = getNameOffset();
        if (kind == MethodKind.GET_INDEX) {
            _parts.add(new PartOffset("<a name=\"m"+begName_+"\">",begName_));
            int endName_ = begName_ + _cont.getKeyWords().getKeyWordThis().length();
            _parts.add(new PartOffset("</a>",endName_));
            return;
        }
        if (kind == MethodKind.SET_INDEX) {
            _parts.add(new PartOffset("<a name=\"m"+begName_+"\">",begName_));
            int endName_ = begName_ + _cont.getKeyWords().getKeyWordThis().length();
            _parts.add(new PartOffset("</a>",endName_));
            return;
        }
        int endName_ = begName_ + getName().length();
        _parts.add(new PartOffset("<a name=\"m"+begName_+"\">",begName_));
        _parts.add(new PartOffset("</a>",endName_));
    }
}
