package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ConstType;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElResolver;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.ParsedArgument;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadFormatNumber;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.SortedClassField;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.IdMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class ConstantOperation extends LeafOperation {
    private static final String TAB = "\t";
    private static final String BOUND = "\b";
    private static final String LINE_FEED = "\r";
    private static final String LINE_RETURN = "\n";
    private static final String FORM = "\f";

    public ConstantOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }
    @Override
    public boolean isCalculated(IdMap<OperationNode, ArgumentsPair> _nodes) {
        return true;
    }

    @Override
    public boolean isCalculated() {
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
            str_ = str_.substring(CustList.SECOND_INDEX, str_.lastIndexOf(DELIMITER_STRING));
            StringBuilder strBuilder_ = new StringBuilder();
            StringBuilder unicodeString_ = new StringBuilder();
            int unicode_ = 0;
            boolean escaped_ = false;
            for (char c: str_.toCharArray()) {
                if (escaped_) {
                    if (unicode_ > 0) {
                        unicodeString_.append(c);
                        if (unicode_ < ElResolver.UNICODE_SIZE) {
                            unicode_++;
                        } else {
                            unicode_ = 0;
                            escaped_ = false;
                            char i_ = LgNames.parseCharSixteen(unicodeString_.toString());
                            strBuilder_.append(i_);
                        }
                        continue;
                    }
                    if (c == IND_BOUND) {
                        strBuilder_.append(BOUND);
                        escaped_ = false;
                        continue;
                    }
                    if (c == IND_LINE) {
                        strBuilder_.append(LINE_RETURN);
                        escaped_ = false;
                        continue;
                    }
                    if (c == IND_FORM) {
                        strBuilder_.append(FORM);
                        escaped_ = false;
                        continue;
                    }
                    if (c == IND_LINE_FEED) {
                        strBuilder_.append(LINE_FEED);
                        escaped_ = false;
                        continue;
                    }
                    if (c == IND_TAB) {
                        strBuilder_.append(TAB);
                        escaped_ = false;
                        continue;
                    }
                    if (c == DELIMITER_STRING) {
                        strBuilder_.append(DELIMITER_STRING);
                        escaped_ = false;
                        continue;
                    }
                    if (c == ESCAPE_META_CHAR) {
                        strBuilder_.append(ESCAPE_META_CHAR);
                        escaped_ = false;
                        continue;
                    }
                    unicode_ = 1;
                    unicodeString_ = new StringBuilder();
                    continue;
                }
                if (c == ESCAPE_META_CHAR) {
                    escaped_ = true;
                    continue;
                }
                strBuilder_.append(c);
            }
            a_.setObject(strBuilder_.toString());
            setSimpleArgument(a_);
            setResultClass(new ClassArgumentMatching(stringType_));
            return;
        }
        if (op_.getConstType() == ConstType.CHARACTER) {
            argClName_ = stds_.getAliasPrimChar();
            str_ = str_.substring(CustList.SECOND_INDEX, str_.lastIndexOf(DELIMITER_CHAR));
            StringBuilder strBuilder_ = new StringBuilder();
            StringBuilder unicodeString_ = new StringBuilder();
            int unicode_ = 0;
            boolean escaped_ = false;
            for (char c: str_.toCharArray()) {
                if (escaped_) {
                    if (unicode_ > 0) {
                        unicodeString_.append(c);
                        if (unicode_ < ElResolver.UNICODE_SIZE) {
                            unicode_++;
                        } else {
                            unicode_ = 0;
                            escaped_ = false;
                            char i_ = LgNames.parseCharSixteen(unicodeString_.toString());
                            strBuilder_.append(i_);
                        }
                        continue;
                    }
                    if (c == IND_BOUND) {
                        strBuilder_.append(BOUND);
                        escaped_ = false;
                        continue;
                    }
                    if (c == IND_LINE) {
                        strBuilder_.append(LINE_RETURN);
                        escaped_ = false;
                        continue;
                    }
                    if (c == IND_FORM) {
                        strBuilder_.append(FORM);
                        escaped_ = false;
                        continue;
                    }
                    if (c == IND_LINE_FEED) {
                        strBuilder_.append(LINE_FEED);
                        escaped_ = false;
                        continue;
                    }
                    if (c == IND_TAB) {
                        strBuilder_.append(TAB);
                        escaped_ = false;
                        continue;
                    }
                    if (c == DELIMITER_CHAR) {
                        strBuilder_.append(DELIMITER_CHAR);
                        escaped_ = false;
                        continue;
                    }
                    if (c == ESCAPE_META_CHAR) {
                        strBuilder_.append(ESCAPE_META_CHAR);
                        escaped_ = false;
                        continue;
                    }
                    unicode_ = 1;
                    unicodeString_ = new StringBuilder();
                    continue;
                }
                if (c == ESCAPE_META_CHAR) {
                    escaped_ = true;
                    continue;
                }
                strBuilder_.append(c);
            }
            a_.setObject(strBuilder_.toString().charAt(0));
            setSimpleArgument(a_);
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        ParsedArgument parsed_ = ParsedArgument.parse(op_.getNbInfos(), _conf);
        String argClassName_ = parsed_.getType();
        if (argClassName_.isEmpty()) {
            BadFormatNumber badFormat_ = new BadFormatNumber();
            badFormat_.setNumber(str_);
            badFormat_.setFileName(_conf.getCurrentFileName());
            badFormat_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(badFormat_);
            argClassName_ = stds_.getAliasPrimDouble();
        }
        Argument arg_ = Argument.createVoid();
        arg_.setStruct(parsed_.getStruct());
        setSimpleArgument(arg_);
        setResultClass(new ClassArgumentMatching(argClassName_));
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Argument arg_ = getArgument();
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<StringMap<AssignmentBefore>> assB_ = vars_.getVariablesBefore().getVal(this);
        ObjectMap<ClassField,AssignmentBefore> assF_ = vars_.getFieldsBefore().getVal(this);
        CustList<StringMap<Assignment>> ass_ = new CustList<StringMap<Assignment>>();
        ObjectMap<ClassField,Assignment> assA_ = new ObjectMap<ClassField,Assignment>();

        Object obj_ = arg_.getObject();
        if (obj_ instanceof Boolean) {
            //boolean constant assignment
            for (StringMap<AssignmentBefore> s: assB_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore bf_ = e.getValue();
                    BooleanAssignment b_ = new BooleanAssignment();
                    if ((Boolean)obj_) {
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
            for (EntryCust<ClassField, AssignmentBefore> e: assF_.entryList()) {
                AssignmentBefore bf_ = e.getValue();
                BooleanAssignment b_ = new BooleanAssignment();
                if ((Boolean)obj_) {
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
        }
        vars_.getVariables().put(this, ass_);
        vars_.getFields().put(this, assA_);
    }

    @Override
    public final void tryCalculateNode(ContextEl _conf, EqList<SortedClassField> _list, SortedClassField _current) {
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        return _nodes.getVal(this).getArgument();
    }


    @Override
    public void calculate(ExecutableCode _conf) {
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

}
