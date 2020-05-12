package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.ConstType;
import code.expressionlanguage.instr.Delimiters;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.ParsedArgument;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class ConstantOperation extends LeafOperation {

    public ConstantOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _conf);
        String argClName_;
        Argument a_ = new Argument();
        LgNames stds_ = _conf.getStandards();
        String stringType_;
        stringType_ = stds_.getAliasString();
        if (op_.getConstType() == ConstType.TRUE_CST) {
            argClName_ = stds_.getAliasPrimBoolean();
            a_.setObject(true);
            setSimpleArgument(a_);
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        if (op_.getConstType() == ConstType.FALSE_CST) {
            argClName_ = stds_.getAliasPrimBoolean();
            a_.setObject(false);
            setSimpleArgument(a_);
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        if (op_.getConstType() == ConstType.NULL_CST) {
            argClName_ = EMPTY_STRING;
            setSimpleArgument(a_);
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        if (op_.getConstType() == ConstType.STRING) {
            a_.setObject(originalStr_);
            setSimpleArgument(a_);
            setResultClass(new ClassArgumentMatching(stringType_));
            return;
        }
        if (op_.getConstType() == ConstType.CHARACTER) {
            argClName_ = stds_.getAliasPrimChar();
            if (!originalStr_.isEmpty()) {
                a_.setObject(originalStr_.charAt(0));
            } else {
                FoundErrorInterpret badFormat_ = new FoundErrorInterpret();
                badFormat_.setFileName(_conf.getCurrentFileName());
                badFormat_.setIndexFile(_conf.getCurrentLocationIndex());
                //constant len
                badFormat_.buildError(_conf.getContextEl().getAnalysisMessages().getBadCharFormat());
                _conf.addError(badFormat_);
            }
            setSimpleArgument(a_);
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        ParsedArgument parsed_ = ParsedArgument.parse(op_.getNbInfos(), _conf);
        String argClassName_ = parsed_.getType();
        if (argClassName_.isEmpty()) {
            FoundErrorInterpret badFormat_ = new FoundErrorInterpret();
            badFormat_.setFileName(_conf.getCurrentFileName());
            badFormat_.setIndexFile(_conf.getCurrentLocationIndex());
            //constant len
            badFormat_.buildError(_conf.getContextEl().getAnalysisMessages().getBadNbFormat(),
                    str_);
            _conf.addError(badFormat_);
            argClassName_ = stds_.getAliasPrimDouble();
        }
        Argument arg_ = Argument.createVoid();
        arg_.setStruct(parsed_.getStruct());
        setSimpleArgument(arg_);
        setResultClass(new ClassArgumentMatching(argClassName_));
    }

    public int getLength() {
        Delimiters d_ = getOperations().getDelimiter();
        int firstPrintChar_ = getOperations().getOffset();
        int offset_ = getIndexInEl();
        int begin_ = d_.getDelStringsChars().indexOfObj(firstPrintChar_+offset_);
        return d_.getDelStringsChars().get(begin_+1)-offset_+1-firstPrintChar_;
    }
    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        setAssignments(this,_conf);
    }

    public static void setAssignments(OperationNode _current, Analyzable _conf) {
        Argument arg_ = _current.getArgument();
        Block block_ = _conf.getAnalyzing().getCurrentBlock();
        AssignedVariables vars_ = _conf.getContextEl().getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<StringMap<AssignmentBefore>> assB_ = vars_.getVariablesBefore().getVal(_current);
        CustList<StringMap<AssignmentBefore>> assM_ = vars_.getMutableLoopBefore().getVal(_current);
        StringMap<AssignmentBefore> assF_ = vars_.getFieldsBefore().getVal(_current);
        CustList<StringMap<Assignment>> ass_ = new CustList<StringMap<Assignment>>();
        CustList<StringMap<Assignment>> assAfM_ = new CustList<StringMap<Assignment>>();
        StringMap<Assignment> assA_ = new StringMap<Assignment>();

        if (arg_.getStruct() instanceof BooleanStruct) {
            //boolean constant assignment
            for (StringMap<AssignmentBefore> s: assB_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore bf_ = e.getValue();
                    BooleanAssignment b_ = new BooleanAssignment();
                    if (BooleanStruct.of(true).sameReference(arg_.getStruct())) {
                        b_.setAssignedAfterWhenFalse(true);
                        b_.setUnassignedAfterWhenFalse(true);
                        b_.setAssignedAfterWhenTrue(bf_.isAssignedBefore());
                        b_.setUnassignedAfterWhenTrue(bf_.isUnassignedBefore());
                    } else {
                        b_.setAssignedAfterWhenTrue(true);
                        b_.setUnassignedAfterWhenTrue(true);
                        b_.setAssignedAfterWhenFalse(bf_.isAssignedBefore());
                        b_.setUnassignedAfterWhenFalse(bf_.isUnassignedBefore());
                    }
                    sm_.put(e.getKey(), b_);
                }
                ass_.add(sm_);
            }
            for (StringMap<AssignmentBefore> s: assM_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore bf_ = e.getValue();
                    BooleanAssignment b_ = new BooleanAssignment();
                    if (BooleanStruct.of(true).sameReference(arg_.getStruct())) {
                        b_.setAssignedAfterWhenFalse(true);
                        b_.setUnassignedAfterWhenFalse(true);
                        b_.setAssignedAfterWhenTrue(bf_.isAssignedBefore());
                        b_.setUnassignedAfterWhenTrue(bf_.isUnassignedBefore());
                    } else {
                        b_.setAssignedAfterWhenTrue(true);
                        b_.setUnassignedAfterWhenTrue(true);
                        b_.setAssignedAfterWhenFalse(bf_.isAssignedBefore());
                        b_.setUnassignedAfterWhenFalse(bf_.isUnassignedBefore());
                    }
                    sm_.put(e.getKey(), b_);
                }
                assAfM_.add(sm_);
            }
            for (EntryCust<String, AssignmentBefore> e: assF_.entryList()) {
                AssignmentBefore bf_ = e.getValue();
                BooleanAssignment b_ = new BooleanAssignment();
                if (BooleanStruct.of(true).sameReference(arg_.getStruct())) {
                    b_.setAssignedAfterWhenFalse(true);
                    b_.setUnassignedAfterWhenFalse(true);
                    b_.setAssignedAfterWhenTrue(bf_.isAssignedBefore());
                    b_.setUnassignedAfterWhenTrue(bf_.isUnassignedBefore());
                } else {
                    b_.setAssignedAfterWhenTrue(true);
                    b_.setUnassignedAfterWhenTrue(true);
                    b_.setAssignedAfterWhenFalse(bf_.isAssignedBefore());
                    b_.setUnassignedAfterWhenFalse(bf_.isUnassignedBefore());
                }
                assA_.put(e.getKey(), b_);
            }
        } else {
            //simple assignment
            ass_.addAllElts(AssignmentsUtil.assignAfter(false,assB_));
            assAfM_.addAllElts(AssignmentsUtil.assignAfter(false,assM_));
            assA_.putAllMap(AssignmentsUtil.assignAfter(false,assF_));
        }
        vars_.getVariables().put(_current, ass_);
        vars_.getMutableLoop().put(_current, assAfM_);
        vars_.getFields().put(_current, assA_);
    }
}
