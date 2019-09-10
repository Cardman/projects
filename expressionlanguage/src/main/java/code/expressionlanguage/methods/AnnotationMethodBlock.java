package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.FieldInitPageEl;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;

public final class AnnotationMethodBlock extends NamedFunctionBlock implements
        GeneMethod, WithNotEmptyEl {

    private String defaultValue;
    private int defaultValueOffset;

    private CustList<ExecOperationNode> opValue;

    public AnnotationMethodBlock(OffsetStringInfo _retType, OffsetStringInfo _fctName,
                                 OffsetStringInfo _defaultValue,
                                 OffsetsBlock _offset) {
        super(new OffsetAccessInfo(0, AccessEnum.PUBLIC),
                _retType, _fctName,
                new StringList(), new Ints(), new StringList(), new Ints(),
                _offset);
        defaultValue = _defaultValue.getInfo();
        defaultValueOffset = _defaultValue.getOffset();
    }

    @Override
    public GeneType belong() {
        return (RootBlock) getParent();
    }

    @Override
    public boolean isStaticContext() {
        return false;
    }

    public MethodModifier getModifier() {
        return MethodModifier.ABSTRACT;
    }

    @Override
    public MethodId getId() {
        return new MethodId(false, getName(), new StringList(), false);
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
        cast_.setIndexFile(_stds.getCurrentLocationIndex());
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

    public boolean isNormalMethod() {
        return false;
    }

    public void buildExpressionLanguage(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        if (defaultValue.trim().isEmpty()) {
            opValue = new CustList<ExecOperationNode>();
            return;
        }
        page_.setGlobalOffset(defaultValueOffset);
        page_.setOffset(0);
        _cont.getCoverage().putBlockOperationsField(_cont,this);
        opValue = ElUtil.getAnalyzedOperationsReadOnly(defaultValue, _cont, Calculation.staticCalculation(true));
        String import_ = getImportedReturnType();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        ClassArgumentMatching arg_ = opValue.last().getResultClass();
        mapping_.setArg(arg_);
        mapping_.setParam(import_);
        if (!Templates.isCorrectOrNumbers(mapping_, _cont)) {
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(defaultValueOffset);
            _cont.getClasses().addError(cast_);
        }
        if (PrimitiveTypeUtil.isPrimitive(import_, _cont)) {
            opValue.last().getResultClass().setUnwrapObject(import_);
        }
    }

    @Override
    public void reduce(ContextEl _context) {
        super.reduce(_context);
        if (opValue.isEmpty()) {
            return;
        }
        ExecOperationNode r_ = opValue.last();
        opValue = ElUtil.getReducedNodes(r_);
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
            RootBlock r_ = (RootBlock) getParent();
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
    public CustList<ExecOperationNode> getOpValue() {
        return opValue;
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        buildAnnotationsReport(_cont,_parts);
        _parts.addAllElts(getPartOffsetsReturn());
        int begName_ = getNameOffset();
        _parts.add(new PartOffset("<a name=\"m"+begName_+"\">",begName_));
        int endName_ = begName_ + getName().length();
        _parts.add(new PartOffset("</a>",endName_));
        if (!opValue.isEmpty()) {
            int blOffset_ = defaultValueOffset;
            int endBl_ = blOffset_ + defaultValue.length();
            ElUtil.buildCoverageReport(_cont,blOffset_,this,opValue,endBl_,_parts);
        }
    }
}
