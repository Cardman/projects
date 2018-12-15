package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetAccessInfo;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.errors.custom.MissingReturnMethod;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;

public final class OperatorBlock extends NamedFunctionBlock implements GeneMethod, AccessingImportingBlock {

    private StringList imports = new StringList();

    private Numbers<Integer> importsOffset = new Numbers<Integer>();

    public OperatorBlock(ContextEl _importingPage,
            BracedBlock _m,
            OffsetStringInfo _retType, OffsetStringInfo _fctName,
            StringList _paramTypes, Numbers<Integer> _paramTypesOffset,
            StringList _paramNames, Numbers<Integer> _paramNamesOffset,
            OffsetsBlock _offset) {
        super(_importingPage, _m, new OffsetAccessInfo(0, AccessEnum.PUBLIC),
                _retType, _fctName, _paramTypes, _paramTypesOffset, _paramNames, _paramNamesOffset, _offset);
    }

    @Override
    public String getSignature() {
        return getId().getSignature();
    }

    @Override
    public MethodModifier getModifier() {
        return MethodModifier.STATIC;
    }

    @Override
    public String getDeclaringType() {
        return EMPTY_STRING;
    }

    @Override
    public MethodId getFormattedId(String _genericClass, ContextEl _context) {
        String name_ = getName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            String formatted_ = Templates.quickFormat(_genericClass, n_, _context);
            pTypes_.add(formatted_);
        }
        return new MethodId(isStaticMethod(), name_, pTypes_, isVarargs());
    }

    @Override
    public MethodId getQuickFormattedId(String _genericClass, ContextEl _context) {
        String name_ = getName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            String formatted_ = Templates.quickFormat(_genericClass, n_, _context);
            pTypes_.add(formatted_);
        }
        return new MethodId(isStaticMethod(), name_, pTypes_, isVarargs());
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

    @Override
    public boolean isConcreteMethod() {
        return isNormalMethod() || isFinalMethod();
    }

    @Override
    public boolean isStaticMethod() {
        return true;
    }

    @Override
    public boolean isFinalMethod() {
        return false;
    }

    @Override
    public boolean isAbstractMethod() {
        return false;
    }

    @Override
    public boolean isNormalMethod() {
        return false;
    }

    @Override
    public boolean isStaticContext() {
        return true;
    }

    @Override
    public RootBlock belong() {
        return (RootBlock) getParent();
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        LgNames stds_ = _an.getStandards();
        if (!StringList.quickEq(getImportedReturnType(), stds_.getAliasVoid())) {
            if (!isAbstractMethod() && _anEl.canCompleteNormally(this)) {
                //error
                MissingReturnMethod miss_ = new MissingReturnMethod();
                miss_.setIndexFile(getOffset().getOffsetTrim());
                miss_.setFileName(getFile().getFileName());
                miss_.setId(getSignature());
                miss_.setReturning(getImportedReturnType());
                _an.getClasses().addError(miss_);
            }
        }
    }
    @Override
    public StringList getImports() {
        return imports;
    }
    @Override
    public Numbers<Integer> getImportsOffset() {
        return importsOffset;
    }
    @Override
    public boolean canAccessClass(String _type, Analyzable _analyzable) {
        return _analyzable.getClassBody(_type).getAccess() == AccessEnum.PUBLIC;
    }

    @Override
    public CustList<TypeVar> getParamTypesMapValues() {
        return new CustList<TypeVar>();
    }
}
