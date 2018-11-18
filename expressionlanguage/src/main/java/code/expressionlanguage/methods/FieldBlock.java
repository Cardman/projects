package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ConstType;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.FieldInitPageEl;
import code.expressionlanguage.OffsetAccessInfo;
import code.expressionlanguage.OffsetBooleanInfo;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.StaticInitPageEl;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.opers.AbstractInstancingOperation;
import code.expressionlanguage.opers.AbstractUnaryOperation;
import code.expressionlanguage.opers.AffectationOperation;
import code.expressionlanguage.opers.ArrOperation;
import code.expressionlanguage.opers.ArrayFieldOperation;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ChoiceFctOperation;
import code.expressionlanguage.opers.ConstantOperation;
import code.expressionlanguage.opers.DeclaringOperation;
import code.expressionlanguage.opers.DotOperation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.FctOperation;
import code.expressionlanguage.opers.IdOperation;
import code.expressionlanguage.opers.MethodOperation;
import code.expressionlanguage.opers.NumericOperation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.PrimitiveBoolOperation;
import code.expressionlanguage.opers.SemiAffectationOperation;
import code.expressionlanguage.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.opers.StaticAccessOperation;
import code.expressionlanguage.opers.TernaryOperation;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdMap;
import code.util.StringList;

public final class FieldBlock extends Leaf implements InfoBlock {

    private final StringList fieldName;

    private final String className;

    private int classNameOffset;

    private String importedClassName;

    private final String value;

    private int valueOffset;

    private final boolean staticField;

    private int staticFieldOffset;

    private final boolean finalField;

    private int finalFieldOffset;

    private final AccessEnum access;

    private int accessOffset;

    private CustList<OperationNode> opValue;

    public FieldBlock(ContextEl _importingPage,
            BracedBlock _m, OffsetAccessInfo _access,
            OffsetBooleanInfo _static, OffsetBooleanInfo _final,
            StringList _name, OffsetStringInfo _type, OffsetStringInfo _value, OffsetsBlock _offset) {
        super(_importingPage, _m, _offset);
        access = _access.getInfo();
        accessOffset = _access.getOffset();
        staticField = _static.isInfo();
        staticFieldOffset = _static.getOffset();
        finalField = _final.isInfo();
        finalFieldOffset = _final.getOffset();
        fieldName = _name;
        className = _type.getInfo();
        classNameOffset = _type.getOffset();
        value = _value.getInfo();
        valueOffset = _value.getOffset();
    }

    @Override
    public int getFieldNameOffset() {
        return valueOffset;
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

    @Override
    public AccessEnum getAccess() {
        return access;
    }

    public ExpressionLanguage getValueEl() {
        return new ExpressionLanguage(opValue);
    }

    public Struct getDefaultValue() {
        if (!finalField) {
            return null;
        }
        if (opValue == null) {
            return null;
        }
        Argument arg_ = opValue.last().getArgument();
        if (arg_ == null) {
            return null;
        }
        return arg_.getStruct();
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
    public String getImportedClassName() {
        return importedClassName;
    }

    @Override
    public StringList getFieldName() {
        return fieldName;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void setAssignmentBefore(Analyzable _an, AnalyzingEl _anEl) {
        if (!(getParent() instanceof RootBlock)) {
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _an.getClasses().addError(un_);
            return;
        }
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
            for (EntryCust<String, SimpleAssignment> e: parAss_.getFieldsRoot().entryList()) {
                assBl_.getFieldsRootBefore().put(e.getKey(), e.getValue().assignBefore());
            }
            assBl_.getFieldsRoot().putAllMap(parAss_.getFieldsRoot());
            id_.put(this, assBl_);
        }
    }
    @Override
    public void buildImportedType(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(getClassNameOffset());
        page_.setOffset(0);
        page_.setCurrentBlock(this);
        importedClassName = _cont.resolveCorrectType(className);
    }
    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        opValue = ElUtil.getAnalyzedOperations(value, _cont, Calculation.staticCalculation(staticField));
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
    }

    public boolean isSimpleStaticConstant(String _name) {
        if (!isStaticField()) {
            return false;
        }
        if (!isFinalField()) {
            return false;
        }
        OperationNode last_ = opValue.last();
        if (!(last_ instanceof DeclaringOperation)) {
            if (last_ instanceof AffectationOperation) {
                for (OperationNode o: opValue) {
                    if (o == last_) {
                        continue;
                    }
                    if (isCstOperation(o)) {
                        continue;
                    }
                    return false;
                }
                return true;
            }
            for (OperationNode o: opValue) {
                if (isCstOperation(o)) {
                    continue;
                }
                return false;
            }
            return true;
        }
        MethodOperation m_ = (MethodOperation)last_;
        int index_ = fieldName.indexOfObj(_name);
        CustList<OperationNode> ch_ = m_.getChildrenNodes();
        OperationNode lastOp_ = ch_.get(index_);
        int from_;
        int to_ = lastOp_.getOrder()-1;
        if (index_ == 0) {
            from_ = 0;
        } else {
            from_ = ch_.get(index_-1).getOrder() + 1;
        }
        for (OperationNode o: opValue.sub(from_, to_)) {
            if (isCstOperation(o)) {
                continue;
            }
            return false;
        }
        return true;
    }
    private boolean isCstOperation(OperationNode _op) {
        OperationsSequence op_ = _op.getOperations();
        if (op_ == null) {
            return true;
        }
        if (op_.getConstType() == ConstType.NUMBER) {
            return true;
        }
        if (op_.getConstType() == ConstType.STRING) {
            return true;
        }
        if (op_.getConstType() == ConstType.CHARACTER) {
            return true;
        }
        if (op_.getConstType() == ConstType.TRUE_CST) {
            return true;
        }
        if (op_.getConstType() == ConstType.FALSE_CST) {
            return true;
        }
        if (op_.getConstType() == ConstType.NULL_CST) {
            return true;
        }
        if (_op instanceof StaticAccessOperation) {
            return true;
        }
        if (_op instanceof ConstantOperation) {
            return true;
        }
        if (_op instanceof ArrayFieldOperation) {
            return true;
        }
        if (_op instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation) _op;
            if (cst_.getFieldMetaInfo().isEnumField()) {
                return false;
            }
            if (cst_.getFieldMetaInfo().isFinalField()) {
                return true;
            }
        }
        if (_op instanceof NumericOperation) {
            return true;
        }
        if (_op instanceof DotOperation) {
            return true;
        }
        if (_op instanceof IdOperation) {
            return true;
        }
        if (_op instanceof PrimitiveBoolOperation) {
            return true;
        }
        if (_op instanceof AbstractUnaryOperation) {
            if (!(_op instanceof SemiAffectationOperation)) {
                return true;
            }
            return false;
        }
        if (_op instanceof TernaryOperation) {
            return true;
        }
        if (_op instanceof ChoiceFctOperation) {
            return true;
        }
        if (_op instanceof FctOperation) {
            return true;
        }
        if (_op instanceof ArrOperation) {
            return true;
        }
        if (_op instanceof AbstractInstancingOperation) {
            return true;
        }
        return false;
    }
    public EqList<ClassField> getStaticConstantDependencies(Analyzable _an, String _name) {
        OperationNode last_ = opValue.last();
        if (!(last_ instanceof DeclaringOperation)) {
            EqList<ClassField> eq_ = new EqList<ClassField>();
            for (OperationNode o: opValue.mid(1)) {
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
        EqList<ClassField> eq_ = new EqList<ClassField>();
        MethodOperation m_ = (MethodOperation)last_;
        int index_ = fieldName.indexOfObj(_name);
        CustList<OperationNode> ch_ = m_.getChildrenNodes();
        int from_;
        int to_ = ch_.get(index_).getOrder();
        if (index_ == 0) {
            from_ = 1;
        } else {
            from_ = ch_.get(index_-1).getOrder() + 2;
        }
        for (OperationNode o: opValue.sub(from_, to_)) {
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
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        boolean static_ = isStaticField();
        boolean in_ = false;
        if (ip_ instanceof FieldInitPageEl && !static_) {
            in_ = true;
        } else if (ip_ instanceof StaticInitPageEl && static_) {
            in_ = true;
        }
        if (in_) {
            ip_.setGlobalOffset(valueOffset);
            ip_.setOffset(0);
            ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
            el_.calculateMember(_cont);
            if (_cont.callsOrException()) {
                return;
            }
            ip_.clearCurrentEls();
        }
        processBlock(_cont);
    }

    @Override
    public RootBlock belong() {
        return (RootBlock) getParent();
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        return getValueEl();
    }
}
