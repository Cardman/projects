package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadAccessClass;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.SortedClassField;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class StaticInfoOperation extends LeafOperation {

    private String className;

    public StaticInfoOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public boolean isCalculated(IdMap<OperationNode, ArgumentsPair> _nodes) {
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
    public boolean isCalculated() {
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
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        String realCl_ = str_.substring(str_.indexOf(PAR_LEFT)+1, str_.lastIndexOf(PAR_RIGHT));
        if (StringList.quickEq(realCl_.trim(), _conf.getStandards().getAliasVoid())) {
            className = realCl_.trim();
            setResultClass(new ClassArgumentMatching(_conf.getStandards().getAliasClass()));
            return;
        }
        String classStr_;
        classStr_ = _conf.resolveCorrectType(realCl_, false);
        String glClass_ = _conf.getGlobalClass();
        Classes classes_ = _conf.getClasses();
        if (classes_.isCustomType(classStr_)) {
            String curClassBase_ = null;
            if (glClass_ != null) {
                curClassBase_ = Templates.getIdFromAllTypes(glClass_);
            }
            if (!Classes.canAccessClass(curClassBase_, classStr_, _conf)) {
                BadAccessClass badAccess_ = new BadAccessClass();
                badAccess_.setId(classStr_);
                badAccess_.setRc(_conf.getCurrentLocation());
                badAccess_.setFileName(_conf.getCurrentFileName());
                _conf.getClasses().addError(badAccess_);
            }
        }
        className = classStr_;
        setResultClass(new ClassArgumentMatching(_conf.getStandards().getAliasClass()));
    }
    @Override
    public final void tryCalculateNode(ContextEl _conf, EqList<SortedClassField> _list, SortedClassField _current) {
    }
    @Override
    public void tryCalculateNode(Analyzable _conf) {
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
    public void calculate(ExecutableCode _conf) {
        Argument a_ = new Argument();
        String classStr_ = _conf.getOperationPageEl().formatVarType(className, _conf);
        a_.setStruct(_conf.getExtendedClassMetaInfo(classStr_));
        setSimpleArgument(a_, _conf);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        Argument a_ = new Argument();
        String classStr_ = _conf.getOperationPageEl().formatVarType(className, _conf);
        a_.setStruct(_conf.getExtendedClassMetaInfo(classStr_));
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    @Override
    public ConstructorId getConstId() {
        return null;
    }

}
