package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractInstancingPageEl;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ConstType;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OffsetAccessInfo;
import code.expressionlanguage.OffsetBooleanInfo;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.opers.AbstractUnaryOperation;
import code.expressionlanguage.opers.ArrOperation;
import code.expressionlanguage.opers.ArrayFieldOperation;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ChoiceFctOperation;
import code.expressionlanguage.opers.ConstantOperation;
import code.expressionlanguage.opers.DotOperation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.FctOperation;
import code.expressionlanguage.opers.IdOperation;
import code.expressionlanguage.opers.InstanceOperation;
import code.expressionlanguage.opers.NumericOperation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.PrimitiveBoolOperation;
import code.expressionlanguage.opers.SemiAffectationOperation;
import code.expressionlanguage.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.opers.StaticAccessOperation;
import code.expressionlanguage.opers.TernaryOperation;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.FieldableStruct;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class FieldBlock extends Leaf implements InfoBlock {

    private final String fieldName;

    private int fieldNameOffset;

    private final String className;

    private int classNameOffset;

    private final String value;

    private int valueOffset;

    private final boolean staticField;

    private int staticFieldOffset;

    private final boolean finalField;

    private int finalFieldOffset;

    private final AccessEnum access;

    private int accessOffset;

    private CustList<OperationNode> opValue;

    public FieldBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        fieldName = _el.getAttribute(ATTRIBUTE_NAME);
        className = _el.getAttribute(ATTRIBUTE_CLASS);
        value = _el.getAttribute(ATTRIBUTE_VALUE);
        staticField = _el.hasAttribute(ATTRIBUTE_STATIC);
        finalField = _el.hasAttribute(ATTRIBUTE_FINAL);
        access = AccessEnum.getAccessByName(_el.getAttribute(ATTRIBUTE_ACCESS));
    }

    public FieldBlock(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetAccessInfo _access,
            OffsetBooleanInfo _static, OffsetBooleanInfo _final,
            OffsetStringInfo _name, OffsetStringInfo _type, OffsetStringInfo _value, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        access = _access.getInfo();
        accessOffset = _access.getOffset();
        staticField = _static.isInfo();
        staticFieldOffset = _static.getOffset();
        finalField = _final.isInfo();
        finalFieldOffset = _final.getOffset();
        fieldName = _name.getInfo();
        fieldNameOffset = _name.getOffset();
        className = _type.getInfo();
        classNameOffset = _type.getOffset();
        value = _value.getInfo();
        valueOffset = _value.getOffset();
    }

    @Override
    public int getFieldNameOffset() {
        return fieldNameOffset;
    }

    public int getClassNameOffset() {
        return classNameOffset;
    }

    public int getValueOffset() {
        return valueOffset;
    }

    public int getStaticFieldOffset() {
        return staticFieldOffset;
    }

    public int getFinalFieldOffset() {
        return finalFieldOffset;
    }

    public int getAccessOffset() {
        return accessOffset;
    }

    public Struct getDefaultStruct(ContextEl _cont) {
        if (value.trim().isEmpty()) {
            return StdStruct.defaultClass(className, _cont);
        }
        ExpressionLanguage el_ = getValueEl();
        if (el_.isAlwaysCalculated()) {
            return el_.getConstValue();
        }
        return StdStruct.defaultClass(className, _cont);
    }

    @Override
    public AccessEnum getAccess() {
        return access;
    }

    public ExpressionLanguage getValueEl() {
        return new ExpressionLanguage(opValue);
    }

    public CustList<OperationNode> getOpValue() {
        return opValue;
    }

    @Override
    public boolean isStaticField() {
        return staticField;
    }

    @Override
    public boolean isFinalField() {
        return finalField;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
        if (!(getParent() instanceof RootBlock)) {
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _cont.getClasses().getErrorsDet().add(un_);
        }
    }

    @Override
    public void setAssignmentBefore(Analyzable _an, AnalyzingEl _anEl) {
        Block prev_ = getPreviousSibling();
        while (prev_ != null) {
            if (prev_ instanceof InitBlock) {
                if (((InitBlock)prev_).isStaticContext() == isStaticField()) {
                    break;
                }
            }
            if (prev_ instanceof InfoBlock) {
                if (((InfoBlock)prev_).isStaticField() == isStaticField()) {
                    break;
                }
            }
            prev_ = prev_.getPreviousSibling();
        }
        AssignedVariables ass_;
        if (prev_ == null) {
            ass_ = _an.getAssignedVariables().getFinalVariablesGlobal();
            IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
            id_.put(this, ass_);
        } else {
            IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
            AssignedVariables parAss_ = id_.getVal(prev_);
            if (parAss_ == null) {
                parAss_ = _an.getAssignedVariables().getFinalVariablesGlobal();
            }
            AssignedVariables assBl_ = buildNewAssignedVariable();
            for (EntryCust<ClassField, SimpleAssignment> e: parAss_.getFieldsRoot().entryList()) {
                assBl_.getFieldsRootBefore().put(e.getKey(), e.getValue().assignBefore());
            }
            assBl_.getFieldsRoot().putAllMap(parAss_.getFieldsRoot());
            id_.put(this, assBl_);
        }
    }
    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(getClassNameOffset());
        page_.setOffset(0);
        if (value.trim().isEmpty()) {
            return;
        }
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        _cont.setRootAffect(false);
        opValue = ElUtil.getAnalyzedOperations(value, _cont, Calculation.staticCalculation(staticField));
        if (opValue.isEmpty()) {
            return;
        }
        StringMap<StringList> vars_ = new StringMap<StringList>();
        if (!staticField) {
            String globalClass_ = page_.getGlobalClass();
            String curClassBase_ = StringList.getAllTypes(globalClass_).first();
            for (TypeVar t: _cont.getClasses().getClassBody(curClassBase_).getParamTypes()) {
                vars_.put(t.getName(), t.getConstraints());
            }
        }
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        String arg_ = opValue.last().getResultClass().getName();
        mapping_.setArg(arg_);
        mapping_.setParam(className);
        if (!Templates.isGenericCorrect(mapping_, _cont)) {
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setRc(getRowCol(0, valueOffset));
            _cont.getClasses().getErrorsDet().add(cast_);
        }
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        boolean ass_ = false;
        boolean unass_ = true;
        if (!value.trim().isEmpty()) {
            ass_ = true;
            unass_ = false;
        }
        AssignedVariablesBlock glAss_ = _an.getAssignedVariables();
        AssignedVariables varsAss_ = glAss_.getFinalVariables().getVal(this);
        ObjectMap<ClassField,SimpleAssignment> as_ = varsAss_.getFieldsRoot();
        for (EntryCust<ClassField, AssignmentBefore> e: varsAss_.getFieldsRootBefore().entryList()) {
            as_.put(e.getKey(), e.getValue().assignAfterClassic());
        }
        String className_ = getRooted().getFullName();
        as_.put(new ClassField(className_, fieldName), Assignment.assignClassic(ass_, unass_));
    }
    public boolean isSimpleStaticConstant() {
        if (!isStaticField()) {
            return false;
        }
        if (!isFinalField()) {
            return false;
        }
        for (OperationNode o: opValue) {
            OperationsSequence op_ = o.getOperations();
            if (op_.getConstType() == ConstType.NUMBER) {
                continue;
            }
            if (op_.getConstType() == ConstType.STRING) {
                continue;
            }
            if (op_.getConstType() == ConstType.CHARACTER) {
                continue;
            }
            if (op_.getConstType() == ConstType.TRUE_CST) {
                continue;
            }
            if (op_.getConstType() == ConstType.FALSE_CST) {
                continue;
            }
            if (op_.getConstType() == ConstType.NULL_CST) {
                continue;
            }
            if (o instanceof StaticAccessOperation) {
                continue;
            }
            if (o instanceof ConstantOperation) {
                continue;
            }
            if (o instanceof ArrayFieldOperation) {
                continue;
            }
            if (o instanceof SettableAbstractFieldOperation) {
                SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation) o;
                if (cst_.getFieldMetaInfo().isEnumField()) {
                    return false;
                }
                if (cst_.getFieldMetaInfo().isFinalField()) {
                    continue;
                }
            }
            if (o instanceof NumericOperation) {
                continue;
            }
            if (o instanceof DotOperation) {
                continue;
            }
            if (o instanceof IdOperation) {
                continue;
            }
            if (o instanceof PrimitiveBoolOperation) {
                continue;
            }
            if (o instanceof AbstractUnaryOperation) {
                if (!(o instanceof SemiAffectationOperation)) {
                    continue;
                }
                return false;
            }
            if (o instanceof TernaryOperation) {
                continue;
            }
            if (o instanceof ChoiceFctOperation) {
                continue;
            }
            if (o instanceof FctOperation) {
                continue;
            }
            if (o instanceof ArrOperation) {
                continue;
            }
            if (o instanceof InstanceOperation) {
                continue;
            }
            return false;
        }
        return true;
    }
    public EqList<ClassField> getStaticConstantDependencies(Analyzable _an) {
        EqList<ClassField> eq_ = new EqList<ClassField>();
        for (OperationNode o: opValue) {
            if (!(o instanceof SettableAbstractFieldOperation)) {
                continue;
            }
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation) o;
            ClassField key_ = cst_.getFieldId();
            if (key_ == null) {
                continue;
            }
            if (!_an.getClasses().isCustomType(key_.getClassName())) {
                continue;
            }
            eq_.add(key_);
        }
        return eq_;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public NatTreeMap<String, String> getClassNames(ContextEl _context) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        tr_.put(ATTRIBUTE_CLASS, className);
        return tr_;
    }

    @Override
    public NatTreeMap<Integer,String> getClassNamesOffsets(ContextEl _context) {
        NatTreeMap<Integer,String> tr_ = new NatTreeMap<Integer,String>();
        tr_.put(classNameOffset, className);
        return tr_;
    }

    @Override
    public String getTagName() {
        return TAG_FIELD;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        boolean instancing_ = ip_ instanceof AbstractInstancingPageEl;
        boolean static_ = isStaticField();
        if (static_ != instancing_ && !value.trim().isEmpty()) {
            ip_.setGlobalOffset(valueOffset);
            ip_.setOffset(0);
            String name_ = getFieldName();
            Struct struct_;
            ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, CustList.FIRST_INDEX, false, CustList.FIRST_INDEX);
            Argument arg_ = el_.calculateMember(_cont);
            if (_cont.callsOrException()) {
                return;
            }
            struct_ = arg_.getStruct();
            el_.setCurrentOper(null);
            ip_.clearCurrentEls();
            RootBlock r_ = getRooted();
            ClassField staticField_ = new ClassField(r_.getFullName(), name_);
            if (static_) {
                _cont.getClasses().initializeStaticField(staticField_, struct_);
            } else {
                Argument gl_ = ip_.getGlobalArgument();
                ((FieldableStruct) gl_.getStruct()).setStruct(staticField_, struct_);
            }
        }
        processBlock(_cont);
    }

    @Override
    public RootBlock belong() {
        return (RootBlock) getParent();
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return getValueEl();
    }
}
