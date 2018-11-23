package code.expressionlanguage.methods;

import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.FieldInitPageEl;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OffsetAccessInfo;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

public final class AnnotationMethodBlock extends NamedFunctionBlock implements
        GeneMethod, WithEl {

    private String defaultValue;
    private int defaultValueOffset;

    private CustList<OperationNode> opValue;

    public AnnotationMethodBlock(ContextEl _importingPage,
            BracedBlock _m,
            OffsetStringInfo _retType, OffsetStringInfo _fctName,
            OffsetStringInfo _defaultValue,
            OffsetsBlock _offset) {
        super(_importingPage, _m, new OffsetAccessInfo(0, AccessEnum.PUBLIC),
                _retType, _fctName,
                new StringList(), new Numbers<Integer>(), new StringList(), new Numbers<Integer>(),
                _offset);
        defaultValue = _defaultValue.getInfo();
        defaultValueOffset = _defaultValue.getOffset();
    }

    @Override
    public String getSignature() {
        return getId().getSignature();
    }

    @Override
    public GeneType belong() {
        return (RootBlock) getParent();
    }

    @Override
    public boolean isStaticContext() {
        return false;
    }

    @Override
    public MethodModifier getModifier() {
        return MethodModifier.ABSTRACT;
    }

    @Override
    public MethodId getId() {
        return new MethodId(false, getName(), new StringList(), false);
    }

    @Override
    public String getDeclaringType() {
        return getRooted().getFullName();
    }

    @Override
    public MethodId getFormattedId(String _genericClass, ContextEl _context) {
        return getId();
    }

    @Override
    public MethodId getQuickFormattedId(String _genericClass, ContextEl _context) {
        return getId();
    }

    @Override
    public void buildImportedReturnTypes(Analyzable _stds) {
        super.buildImportedReturnTypes(_stds);
        LgNames stds_ = _stds.getStandards();
        String string_ = stds_.getAliasString();
        String class_ = stds_.getAliasClass();
        String itype_ = getImportedReturnType();
        String type_ = itype_;
        String ctype_ = PrimitiveTypeUtil.getQuickComponentType(type_);
        if (ctype_ != null) {
            type_ = ctype_;
        }
        if (PrimitiveTypeUtil.isPrimitiveOrWrapper(type_, _stds)) {
            return;
        }
        GeneType r_ = _stds.getClassBody(type_);
        if (r_ instanceof AnnotationBlock) {
            return;
        }
        if (r_ instanceof EnumBlock) {
            return;
        }
        if (StringList.quickEq(type_, string_)) {
            return;
        }
        if (StringList.quickEq(type_, class_)) {
            return;
        }
        Mapping mapping_ = new Mapping();
        mapping_.setArg(itype_);
        mapping_.setParam(stds_.getAliasObject());
        BadImplicitCast cast_ = new BadImplicitCast();
        cast_.setMapping(mapping_);
        cast_.setFileName(_stds.getCurrentFileName());
        cast_.setRc(_stds.getCurrentLocation());
        _stds.getClasses().addError(cast_);
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public Struct getDefaultArgument() {
        if (opValue.isEmpty()) {
            return null;
        }
        Argument arg_ = opValue.last().getArgument();
        if (arg_ == null) {
            return null;
        }
        return arg_.getStruct();
    }
    public int getDefaultValueOffset() {
        return defaultValueOffset;
    }

    @Override
    public boolean isConcreteInstanceDerivableMethod() {
        return false;
    }

    @Override
    public boolean isConcreteMethod() {
        return false;
    }

    @Override
    public boolean isStaticMethod() {
        return false;
    }

    @Override
    public boolean isFinalMethod() {
        return false;
    }

    @Override
    public boolean isAbstractMethod() {
        return true;
    }

    @Override
    public boolean isNormalMethod() {
        return false;
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
    public void buildExpressionLanguage(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        if (defaultValue.trim().isEmpty()) {
            opValue = new CustList<OperationNode>();
            return;
        }
        page_.setGlobalOffset(defaultValueOffset);
        page_.setOffset(0);
        opValue = ElUtil.getAnalyzedOperations(defaultValue, _cont, Calculation.staticCalculation(true));
        if (opValue.isEmpty()) {
            return;
        }
        String import_ = getImportedReturnType();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        ClassArgumentMatching arg_ = opValue.last().getResultClass();
        mapping_.setArg(arg_);
        mapping_.setParam(import_);
        if (!Templates.isGenericCorrect(mapping_, _cont)) {
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setRc(getRowCol(0, defaultValueOffset));
            _cont.getClasses().addError(cast_);
        }
        if (PrimitiveTypeUtil.isPrimitive(import_, _cont)) {
            opValue.last().getResultClass().setUnwrapObject(import_);
        }
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        boolean in_ = false;
        if (ip_ instanceof FieldInitPageEl) {
            in_ = true;
        }
        if (in_ && !defaultValue.trim().isEmpty()) {
            ip_.setGlobalOffset(defaultValueOffset);
            ip_.setOffset(0);
            String name_ = getName();
            Struct struct_;
            ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
            Argument arg_ = el_.calculateMember(_cont);
            if (_cont.callsOrException()) {
                return;
            }
            struct_ = arg_.getStruct();
            ip_.clearCurrentEls();
            RootBlock r_ = getRooted();
            ClassField staticField_ = new ClassField(r_.getFullName(), name_);
            Argument gl_ = ip_.getGlobalArgument();
            ((FieldableStruct) gl_.getStruct()).setStruct(staticField_, struct_);
        }
        processBlock(_cont);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        return new ExpressionLanguage(opValue);
    }
    public CustList<OperationNode> getOpValue() {
        return opValue;
    }
}
