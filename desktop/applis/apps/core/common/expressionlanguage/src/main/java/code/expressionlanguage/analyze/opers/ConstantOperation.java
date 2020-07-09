package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.Delimiters;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.ParsedArgument;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.CharStruct;
import code.expressionlanguage.structs.StringStruct;
import code.util.CustList;

public final class ConstantOperation extends LeafOperation {

    public ConstantOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(ContextEl _conf) {
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
            a_.setStruct(BooleanStruct.of(true));
            setSimpleArgument(a_);
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        if (op_.getConstType() == ConstType.FALSE_CST) {
            argClName_ = stds_.getAliasPrimBoolean();
            a_.setStruct(BooleanStruct.of(false));
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
            a_.setStruct(new StringStruct(originalStr_));
            setSimpleArgument(a_);
            setResultClass(new ClassArgumentMatching(stringType_));
            return;
        }
        if (op_.getConstType() == ConstType.CHARACTER) {
            argClName_ = stds_.getAliasPrimChar();
            if (!originalStr_.isEmpty()) {
                a_.setStruct(new CharStruct(originalStr_.charAt(0)));
            } else {
                FoundErrorInterpret badFormat_ = new FoundErrorInterpret();
                badFormat_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                badFormat_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //constant len
                badFormat_.buildError(_conf.getAnalysisMessages().getBadCharFormat(),originalStr_);
                _conf.getAnalyzing().getLocalizer().addError(badFormat_);
                getErrs().add(badFormat_.getBuiltError());
            }
            setSimpleArgument(a_);
            setResultClass(new ClassArgumentMatching(argClName_));
            return;
        }
        ParsedArgument parsed_ = parse(_conf, op_);
        String argClassName_ = parsed_.getType();
        if (argClassName_.isEmpty()) {
            FoundErrorInterpret badFormat_ = new FoundErrorInterpret();
            badFormat_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            badFormat_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //constant len
            badFormat_.buildError(_conf.getAnalysisMessages().getBadNbFormat(),
                    str_);
            _conf.getAnalyzing().getLocalizer().addError(badFormat_);
            getErrs().add(badFormat_.getBuiltError());
            argClassName_ = stds_.getAliasPrimDouble();
        }
        Argument arg_ = Argument.createVoid();
        arg_.setStruct(parsed_.getStruct());
        setSimpleArgument(arg_);
        setResultClass(new ClassArgumentMatching(argClassName_));
    }

    private static ParsedArgument parse(ContextEl _conf, OperationsSequence _op) {
        return ParsedArgument.parse(_op.getNbInfos(), _conf.getStandards());
    }

    public int getLength() {
        Delimiters d_ = getOperations().getDelimiter();
        int firstPrintChar_ = getOperations().getOffset();
        int offset_ = getIndexInEl();
        int begin_ = d_.getDelStringsChars().indexOfObj(firstPrintChar_+offset_);
        return d_.getDelStringsChars().get(begin_+1)-offset_+1-firstPrintChar_;
    }

    public int getNbLength() {
        Delimiters d_ = getOperations().getDelimiter();
        int firstPrintChar_ = getOperations().getOffset();
        int offset_ = getIndexInEl();
        int begin_ = d_.getDelNumbers().indexOfObj(firstPrintChar_+offset_);
        return d_.getDelNumbers().get(begin_+1)-offset_-firstPrintChar_;
    }
}
