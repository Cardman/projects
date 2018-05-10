package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ArgumentCall;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitClassState;
import code.expressionlanguage.InitializatingClass;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.EnumBlock;
import code.expressionlanguage.methods.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadAccessClass;
import code.expressionlanguage.methods.util.UnexpectedTypeError;
import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.CausingErrorStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class ValuesOperation extends LeafOperation {

    private String className;
    private int argOffset;

    public ValuesOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        className = vs_.firstValue();
        argOffset = vs_.firstKey();
        vs_.clear();
    }

    @Override
    public void analyze(Analyzable _conf, String _fieldName) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+argOffset, _conf);
        String glClass_ = _conf.getGlobalClass();
        Classes classes_ = _conf.getClasses();
        String clName_ = StringList.removeAllSpaces(className);
        if (!checkCorrect(_conf, clName_, false, 0)) {
            String argClName_ = _conf.getStandards().getAliasObject();
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        if (!(classes_.getClassBody(clName_) instanceof EnumBlock)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(_conf.getCurrentFileName());
            un_.setRc(_conf.getCurrentLocation());
            un_.setType(clName_);
            _conf.getClasses().getErrorsDet().add(un_);
            String argClName_ = _conf.getStandards().getAliasObject();
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        String curClassBase_ = null;
        if (glClass_ != null) {
            curClassBase_ = StringList.getAllTypes(glClass_).first();
        }
        if (!Classes.canAccessClass(curClassBase_, clName_, _conf)) {
            BadAccessClass badAccess_ = new BadAccessClass();
            badAccess_.setId(clName_);
            badAccess_.setRc(_conf.getCurrentLocation());
            badAccess_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().getErrorsDet().add(badAccess_);
            String argClName_ = _conf.getStandards().getAliasObject();
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        className = clName_;
        String ret_ = PrimitiveTypeUtil.getPrettyArrayType(clName_);
        setResultClass(new ClassArgumentMatching(ret_));
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<StringMap<AssignmentBefore>> assB_ = vars_.getVariablesBefore().getVal(this);
        CustList<StringMap<Assignment>> ass_ = new CustList<StringMap<Assignment>>();
        ObjectMap<ClassField,AssignmentBefore> assF_ = vars_.getFieldsBefore().getVal(this);
        ObjectMap<ClassField,Assignment> assA_ = new ObjectMap<ClassField,Assignment>();
        //simple assignment
        for (StringMap<AssignmentBefore> s: assB_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                AssignmentBefore bf_ = e.getValue();
                sm_.put(e.getKey(), bf_.assignAfter(false));
            }
            ass_.add(sm_);
        }
        for (EntryCust<ClassField, AssignmentBefore> e: assF_.entryList()) {
            AssignmentBefore bf_ = e.getValue();
            assA_.put(e.getKey(), bf_.assignAfter(false));
        }
        vars_.getVariables().put(this, ass_);
        vars_.getFields().put(this, assA_);
    }

    @Override
    public void tryCalculateNode(ContextEl _conf,
            EqList<SortedClassField> _list, SortedClassField _current) {
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
    }

    @Override
    public void calculate(ContextEl _conf) {
        ArgumentCall argres_ = getCommonArgument(_conf);
        if (argres_.isInitClass()) {
            ProcessMethod.initializeClass(argres_.getInitClass().getClassName(), _conf);
            if (_conf.getException() != null) {
                return;
            }
            argres_ = getCommonArgument(_conf);
        }
        if (_conf.getException() != null) {
            return;
        }
        Argument argRes_ = argres_.getArgument();
        setSimpleArgument(argRes_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        ArgumentCall argres_ = getCommonArgument(_conf);
        Argument arg_ = argres_.getArgument();
        if (argres_.isInitClass()) {
            _conf.setInitClass(new NotInitializedClass(argres_.getInitClass().getClassName()));
        } else {
            PossibleIntermediateDotted n_ = getSiblingSet();
            if (n_ != null) {
                _nodes.getVal((OperationNode)n_).setPreviousArgument(arg_);
            }
        }
        return arg_;
    }
    ArgumentCall getCommonArgument(ContextEl _conf) {
        InitClassState res_ = _conf.getClasses().getLocks().getState(_conf, className);
        if (res_ == InitClassState.NOT_YET) {
            InitializatingClass inv_ = new InitializatingClass(className);
            return ArgumentCall.newCall(inv_);
        }
        if (res_ == InitClassState.ERROR) {
            CausingErrorStruct causing_ = new CausingErrorStruct(className);
            _conf.setException(causing_);
            return ArgumentCall.newArgument(Argument.createVoid());
        }
        Classes classes_ = _conf.getClasses();
        ClassMetaInfo custClass_ = classes_.getClassMetaInfo(className, _conf);
        CustList<Struct> enums_ = new CustList<Struct>();
        for (EntryCust<String, FieldMetaInfo> e: custClass_.getFields().entryList()) {
            if (e.getValue().isEnumElement()) {
                enums_.add(classes_.getStaticField(new ClassField(className, e.getKey())));
            }
        }
        Struct[] o_ = new Struct[enums_.size()];
        int i_ = CustList.FIRST_INDEX;
        for (Struct o: enums_) {
            o_[i_] = o;
            i_++;
        }
        String clArr_ = PrimitiveTypeUtil.getPrettyArrayType(className);
        Argument argres_ = new Argument();
        argres_.setStruct(new ArrayStruct(o_,clArr_));
        return ArgumentCall.newArgument(argres_);
    }
    @Override
    public final boolean isCalculated(IdMap<OperationNode, ArgumentsPair> _nodes) {
        OperationNode op_ = this;
        while (op_ != null) {
            if (_nodes.getVal(op_).getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    @Override
    public final boolean isCalculated() {
        OperationNode op_ = this;
        while (op_ != null) {
            if (op_.getArgument() != null) {
                return true;
            }
            op_ = op_.getParent();
        }
        return false;
    }

    @Override
    public boolean isSuperConstructorCall() {
        return false;
    }

    @Override
    public boolean isOtherConstructorClass() {
        return false;
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

}
