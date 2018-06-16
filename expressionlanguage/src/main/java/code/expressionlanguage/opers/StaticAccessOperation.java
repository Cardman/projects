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

public final class StaticAccessOperation extends LeafOperation {

    public StaticAccessOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }
    @Override
    public final boolean isCalculated(IdMap<OperationNode, ArgumentsPair> _nodes) {
        return true;
    }

    @Override
    public final boolean isCalculated() {
        return true;
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
        String classStr_;
        classStr_ = _conf.resolveType(realCl_, false);
        String base_ = Templates.getIdFromAllTypes(classStr_);
        String glClass_ = _conf.getGlobalClass();
        Classes classes_ = _conf.getClasses();
        if (!checkExistBase(_conf, false, base_, false, 0)) {
            Argument a_ = new Argument();
            String argClName_ = _conf.getStandards().getAliasObject();
            setArguments(a_);
            setStaticResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
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
                _conf.getClasses().getErrorsDet().add(badAccess_);
            }
        }
        Argument a_ = new Argument();
        setArguments(a_);
        setStaticResultClass(new ClassArgumentMatching(classStr_));
        return;
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
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        Argument a_ = _nodes.getVal(this).getArgument();
        return a_;
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

}
