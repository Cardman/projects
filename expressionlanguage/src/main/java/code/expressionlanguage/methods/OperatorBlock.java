package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class OperatorBlock extends NamedFunctionBlock implements GeneMethod, AccessingImportingBlock,ReturnableWithSignature {

    private StringList imports = new StringList();

    private Ints importsOffset = new Ints();

    public OperatorBlock(OffsetStringInfo _retType, OffsetStringInfo _fctName,
                         StringList _paramTypes, Ints _paramTypesOffset,
                         StringList _paramNames, Ints _paramNamesOffset,
                         OffsetsBlock _offset) {
        super(new OffsetAccessInfo(0, AccessEnum.PUBLIC),
                _retType, _fctName, _paramTypes, _paramTypesOffset, _paramNames, _paramNamesOffset, _offset);
    }

    public MethodModifier getModifier() {
        return MethodModifier.STATIC;
    }

    public String getDeclaringType() {
        return EMPTY_STRING;
    }

    @Override
    public String getSignature(Analyzable _ana) {
        return getId().getSignature(_ana);
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
    public boolean isStaticContext() {
        return true;
    }

    @Override
    public GeneType belong() {
        return null;
    }

    @Override
    public StringList getImports() {
        return imports;
    }
    public Ints getImportsOffset() {
        return importsOffset;
    }
    @Override
    public boolean isTypeHidden(String _type, Analyzable _analyzable) {
        return _analyzable.getClassBody(_type).getAccess() != AccessEnum.PUBLIC;
    }

    @Override
    public CustList<TypeVar> getParamTypesMapValues() {
        return new CustList<TypeVar>();
    }
}
