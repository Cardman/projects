package code.maths.litteral;
import code.maths.MathList;
import code.maths.Rate;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class ConstantOperation extends OperationNode {

    public ConstantOperation(String _el, int _index, StringMap<String> _importingPage, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    @Override
    void analyze(StringMap<String> _conf, ErrorStatus _error) {
        analyzeCalculate(_error);
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
        _error.setError(true);
        _error.setIndex(getIndexInEl());
        _error.setString(str_);
    }

    @Override
    void calculate(StringMap<String> _conf, ErrorStatus _error) {
        if (getArgument() != null) {
            return;
        }
        String str_ = getOperations().getValues().getValue(CustList.FIRST_INDEX).trim();
        Argument a_;
        a_ = new Argument();
        a_.setArgClass(getResultClass());
        if (getResultClass() == MathType.RATE) {
            if (!Rate.isValid(_conf.getVal(str_))) {
                _error.setString(_conf.getVal(str_));
                _error.setIndex(getIndexInEl());
                _error.setError(true);
                return;
            }
            a_.setObject(new Rate(_conf.getVal(str_)));
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
    }

    private void analyzeCalculate(ErrorStatus _error) {
        if (getOperations().getConstType() == ConstType.STRING) {
            int begin_ = getOperations().getIndexCst();
            StringList info_ = getOperations().getDelimiter().getStringInfo().get(begin_);
            if (info_.size() == 1 && info_.first().trim().isEmpty()) {
                MathList m_ = new MathList();
                Argument a_ = new Argument();
                a_.setArgClass(MathType.SET);
                a_.setObject(m_);
                setArgument(a_);
                return;
            }
            MathList m_ = new MathList(info_);
            Argument a_ = new Argument();
            a_.setArgClass(MathType.SET);
            a_.setObject(m_);
            setArgument(a_);
            return;
        }
        if (getOperations().getConstType() == ConstType.NUMBER) {
            int begin_ = getOperations().getIndexCst();
            String nb_ = getOperations().getDelimiter().getNbInfos().get(begin_).toString();
            setArgument(Argument.numberToArgument(nb_));
            return;
        }
        String str_ = getOperations().getValues().getValue(CustList.FIRST_INDEX).trim();
        if (str_.isEmpty()) {
            _error.setString(str_);
            _error.setIndex(getIndexInEl());
            _error.setError(true);
            return;
        }
        Argument a_ = new Argument();
        if (StringList.quickEq(str_, TRUE_STRING)) {
            a_.setArgClass(MathType.BOOLEAN);
            a_.setObject(true);
            setArgument(a_);
            return;
        }
        if (StringList.quickEq(str_, FALSE_STRING)) {
            a_.setArgClass(MathType.BOOLEAN);
            a_.setObject(false);
            setArgument(a_);
            return;
        }
    }

    @Override
    public OperationNode getFirstChild() {
        return null;
    }

}
