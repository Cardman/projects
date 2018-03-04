package code.maths.litteral;
import code.maths.MathList;
import code.maths.Rate;
import code.maths.exceptions.BadDivisionException;
import code.maths.exceptions.FormatException;
import code.maths.litteral.exceptions.EmptyPartException;
import code.maths.litteral.exceptions.UndefinedVariableException;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class ConstantOperation extends OperationNode {

    private static final String RETURN_LINE = "\n";

    public ConstantOperation(String _el, int _index, StringMap<String> _importingPage, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    @Override
    boolean isFirstChild() {
        return getIndexChild() == CustList.FIRST_INDEX;
    }

    @Override
    void analyze(CustList<OperationNode> _nodes, StringMap<String> _conf) {
        analyzeCalculate();
        if (getArgument() != null) {
            String str_ = getOperations().getValues().getValue(CustList.FIRST_INDEX).trim();

            for (EntryCust<String,String> v: _conf.entryList()) {
                if (StringList.quickEq(str_, StringList.concat(String.valueOf(DELIMITER_STRING_BEGIN),v.getKey(),String.valueOf(DELIMITER_STRING_END)))) {
                    MathList m_ = new MathList();
                    for (String e: StringList.splitChars(v.getValue(), DELIMITER_STRING_SEP)) {
                        if (e.isEmpty()) {
                            continue;
                        }
                        m_.add(e);
                    }
                    getArgument().setObject(m_);
                    break;
                }
            }
            setResultClass(getArgument().getArgClass());
            return;
        }
        String str_ = getOperations().getValues().getValue(CustList.FIRST_INDEX).trim();
        if (_conf.contains(str_)) {
            String value_ = _conf.getVal(str_);
            if (Rate.isValid(value_)) {
                setResultClass(MathType.RATE);
                return;
            }
            if (value_.startsWith(String.valueOf(DELIMITER_STRING_BEGIN))) {
                setResultClass(MathType.SET);
                return;
            }
            setResultClass(MathType.BOOLEAN);
            return;
        }
        throw new UndefinedVariableException(str_, String.valueOf(getIndexInEl()));
    }

    @Override
    void calculate(CustList<OperationNode> _nodes, StringMap<String> _conf) {
        if (getArgument() != null) {
            return;
        }
        String str_ = getOperations().getValues().getValue(CustList.FIRST_INDEX).trim();
        Argument a_ = new Argument();
        a_ = new Argument();
        a_.setArgClass(getResultClass());
        if (getResultClass() == MathType.RATE) {
            try {
                a_.setObject(new Rate(_conf.getVal(str_)));
            } catch (FormatException _0) {
                throw new BadDivisionException(StringList.concat(_0.getMessage(),RETURN_LINE,String.valueOf(getIndexInEl())));
            }
        } else if (getResultClass() == MathType.BOOLEAN) {
            a_.setObject(StringList.quickEq(_conf.getVal(str_), TRUE_STRING));
        } else {
            MathList m_ = new MathList();
            for (String e: StringList.splitChars(_conf.getVal(str_), DELIMITER_STRING_SEP)) {
                if (e.isEmpty()) {
                    continue;
                }
                m_.add(e);
            }
            a_.setObject(m_);
        }
        setArgument(a_);
        setNextSiblingsArg(a_);
    }

    private void analyzeCalculate() {
        String str_ = getOperations().getValues().getValue(CustList.FIRST_INDEX).trim();
        if (str_.isEmpty()) {
            throw new EmptyPartException(String.valueOf(getIndexInEl()));
        }
        Argument a_ = new Argument();
        if (StringList.quickEq(str_, TRUE_STRING)) {
            a_.setArgClass(MathType.BOOLEAN);
            a_.setObject(true);
            setArgument(a_);
            setNextSiblingsArg(a_);
            return;
        }
        if (StringList.quickEq(str_, FALSE_STRING)) {
            a_.setArgClass(MathType.BOOLEAN);
            a_.setObject(false);
            setArgument(a_);
            setNextSiblingsArg(a_);
            return;
        }
        if (StringList.quickEq(str_, EMPTY_SET)) {
            a_.setArgClass(MathType.SET);
            a_.setObject(new MathList());
            setArgument(a_);
            setNextSiblingsArg(a_);
            return;
        }
        if (str_.startsWith(String.valueOf(DELIMITER_STRING_BEGIN))) {
            str_ = str_.substring(CustList.SECOND_INDEX, str_.lastIndexOf(DELIMITER_STRING_END));
            if (str_.isEmpty()) {
                a_.setArgClass(MathType.SET);
                a_.setObject(new MathList());
                setArgument(a_);
                setNextSiblingsArg(a_);
                return;
            }
            MathList m_ = new MathList();
            StringBuilder element_ = new StringBuilder();
            boolean escaped_ = false;
            for (char c: str_.toCharArray()) {
                if (escaped_) {
                    element_.append(c);
                    escaped_ = false;
                    continue;
                }
                if (c == ESCAPE_META_CHAR) {
                    escaped_ = true;
                    continue;
                }
                if (c == DELIMITER_STRING_SEP) {
                    m_.add(element_.toString());
                    element_ = new StringBuilder();
                } else {
                    element_.append(c);
                }
            }
            m_.add(element_.toString());
            a_.setArgClass(MathType.SET);
            a_.setObject(m_);
            setArgument(a_);
            setNextSiblingsArg(a_);
            return;
        }
        try {
            setArgument(Argument.numberToArgument(str_));
        } catch (RuntimeException _0) {
        }
    }

    @Override
    public OperationNode getFirstChild() {
        return null;
    }

}
