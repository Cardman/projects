package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.Delimiters;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.ParsedArgument;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.CharStruct;
import code.expressionlanguage.structs.StringStruct;
import code.util.core.IndexConstants;

public final class ConstantOperation extends LeafOperation {

    public ConstantOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(IndexConstants.FIRST_INDEX);
        String str_ = originalStr_.trim();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        String argClName_;
        String stringType_;
        stringType_ = _page.getAliasString();
        if (op_.getConstType() == ConstType.TRUE_CST) {
            argClName_ = _page.getAliasPrimBoolean();
            Argument a_ = new Argument(BooleanStruct.of(true));
            setSimpleArgument(a_);
            setResultClass(new AnaClassArgumentMatching(argClName_));
            return;
        }
        if (op_.getConstType() == ConstType.FALSE_CST) {
            argClName_ = _page.getAliasPrimBoolean();
            Argument a_ = new Argument(BooleanStruct.of(false));
            setSimpleArgument(a_);
            setResultClass(new AnaClassArgumentMatching(argClName_));
            return;
        }
        if (op_.getConstType() == ConstType.NULL_CST) {
            argClName_ = EMPTY_STRING;
            Argument a_ = new Argument();
            setSimpleArgument(a_);
            setResultClass(new AnaClassArgumentMatching(argClName_));
            return;
        }
        if (op_.getConstType() == ConstType.STRING) {
            Argument a_ = new Argument(new StringStruct(originalStr_));
            setSimpleArgument(a_);
            setResultClass(new AnaClassArgumentMatching(stringType_));
            if (op_.getStrInfo().isKo()) {
                FoundErrorInterpret badFormat_ = new FoundErrorInterpret();
                badFormat_.setFileName(_page.getLocalizer().getCurrentFileName());
                badFormat_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //constant len
                badFormat_.buildError(_page.getAnalysisMessages().getBadCharFormat(),op_.getStrInfo().getFound());
                _page.getLocalizer().addError(badFormat_);
                addErr(badFormat_.getBuiltError());
            }
            return;
        }
        if (op_.getConstType() == ConstType.CHARACTER) {
            argClName_ = _page.getAliasPrimChar();
            Argument a_;
            if (op_.getStrInfo().isKo()) {
                a_ = new Argument();
                FoundErrorInterpret badFormat_ = new FoundErrorInterpret();
                badFormat_.setFileName(_page.getLocalizer().getCurrentFileName());
                badFormat_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //constant len
                badFormat_.buildError(_page.getAnalysisMessages().getBadCharFormat(),op_.getStrInfo().getFound());
                _page.getLocalizer().addError(badFormat_);
                addErr(badFormat_.getBuiltError());
            } else if (!originalStr_.isEmpty()) {
                a_ = new Argument(new CharStruct(originalStr_.charAt(0)));
            } else {
                a_ = new Argument();
                FoundErrorInterpret badFormat_ = new FoundErrorInterpret();
                badFormat_.setFileName(_page.getLocalizer().getCurrentFileName());
                badFormat_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //constant len
                badFormat_.buildError(_page.getAnalysisMessages().getBadCharFormat(),op_.getStrInfo().getFound());
                _page.getLocalizer().addError(badFormat_);
                addErr(badFormat_.getBuiltError());
            }
            setSimpleArgument(a_);
            setResultClass(new AnaClassArgumentMatching(argClName_));
            return;
        }
        ParsedArgument parsed_ = parse(op_, _page);
        String argClassName_ = parsed_.getType();
        if (argClassName_.isEmpty()) {
            FoundErrorInterpret badFormat_ = new FoundErrorInterpret();
            badFormat_.setFileName(_page.getLocalizer().getCurrentFileName());
            badFormat_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //constant len
            badFormat_.buildError(_page.getAnalysisMessages().getBadNbFormat(),
                    str_);
            _page.getLocalizer().addError(badFormat_);
            addErr(badFormat_.getBuiltError());
            argClassName_ = _page.getAliasPrimDouble();
        }
        Argument arg_ = new Argument(parsed_.getStruct());
        setSimpleArgument(arg_);
        setResultClass(new AnaClassArgumentMatching(argClassName_));
    }

    private static ParsedArgument parse(OperationsSequence _op, AnalyzedPageEl _page) {
        return ParsedArgument.parse(_op.getNbInfos(), _page);
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
