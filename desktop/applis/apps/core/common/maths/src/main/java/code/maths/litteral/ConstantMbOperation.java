package code.maths.litteral;
import code.maths.MathList;
import code.maths.Rate;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ConstantMbOperation extends MbOperationNode {

    public ConstantMbOperation(int _index, int _indexChild, MethodMbOperation _m, MbOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void analyze(StringMap<String> _conf, ErrorStatus _error, MbDelimiters _del) {
        analyzeCalculate(_error, _del);
        if (getArgument() != null) {
            processConst(_conf);
            return;
        }
        String str_ = getOperations().getValues().getValue(IndexConstants.FIRST_INDEX).trim();
        if (_conf.contains(str_)) {
            String value_ = _conf.getVal(str_);
            if (Rate.isValid(value_)) {
                setResultClass(MathType.RATE);
                return;
            }
            if (value_.startsWith(Character.toString(DELIMITER_STRING_BEGIN))) {
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

    private void processConst(StringMap<String> _conf) {
        String str_ = getOperations().getValues().getValue(IndexConstants.FIRST_INDEX).trim();

        for (EntryCust<String,String> v: _conf.entryList()) {
            if (StringUtil.quickEq(str_, StringUtil.concat(Character.toString(DELIMITER_STRING_BEGIN),v.getKey(),Character.toString(DELIMITER_STRING_END)))) {
                MathList m_ = new MathList();
                for (String e: StringUtil.splitChars(v.getValue(), DELIMITER_STRING_SEP)) {
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
    }

    @Override
    void calculate(StringMap<String> _conf, ErrorStatus _error) {
        String str_ = getOperations().getValues().getValue(IndexConstants.FIRST_INDEX).trim();
        String val_ = StringUtil.nullToEmpty(_conf.getVal(str_));
        MbArgument a_;
        a_ = new MbArgument();
        a_.setArgClass(getResultClass());
        if (getResultClass() == MathType.RATE) {
            a_.setObject(new Rate(val_));
        } else if (getResultClass() == MathType.BOOLEAN) {
            a_.setObject(StringUtil.quickEq(val_, TRUE_STRING));
        } else {
            MathList m_ = new MathList();
            String value_ = val_;
            value_ = StringUtil.removeChars(value_,DELIMITER_STRING_BEGIN,DELIMITER_STRING_END);
            for (String e: StringUtil.splitChars(value_, DELIMITER_STRING_SEP)) {
                if (e.isEmpty()) {
                    continue;
                }
                m_.add(e);
            }
            a_.setObject(m_);
        }
        setArgument(a_);
    }

    private void analyzeCalculate(ErrorStatus _error, MbDelimiters _delimiter) {
        if (getOperations().getConstType() == MbConstType.STRING) {
            int begin_ = getOperations().getIndexCst();
            StringList info_ = _delimiter.getStringInfo().get(begin_);
            if (info_.size() == 1 && info_.first().trim().isEmpty()) {
                MathList m_ = new MathList();
                MbArgument a_ = new MbArgument();
                a_.setArgClass(MathType.SET);
                a_.setObject(m_);
                setArgument(a_);
                return;
            }
            MathList m_ = new MathList(info_);
            MbArgument a_ = new MbArgument();
            a_.setArgClass(MathType.SET);
            a_.setObject(m_);
            setArgument(a_);
            return;
        }
        if (getOperations().getConstType() == MbConstType.NUMBER) {
            int begin_ = getOperations().getIndexCst();
            String nb_ = _delimiter.getNbInfos().get(begin_).toString();
            if (Rate.isValid(nb_)) {
                setArgument(MbArgument.numberToArgument(nb_));
                return;
            }
            _error.setString(nb_);
            _error.setIndex(getIndexInEl());
            _error.setError(true);
            return;
        }
        String str_ = getOperations().getValues().getValue(IndexConstants.FIRST_INDEX).trim();
        if (str_.isEmpty()) {
            _error.setString(str_);
            _error.setIndex(getIndexInEl());
            _error.setError(true);
            return;
        }
        MbArgument a_ = new MbArgument();
        if (StringUtil.quickEq(str_, TRUE_STRING)) {
            a_.setArgClass(MathType.BOOLEAN);
            a_.setObject(true);
            setArgument(a_);
            return;
        }
        if (StringUtil.quickEq(str_, FALSE_STRING)) {
            a_.setArgClass(MathType.BOOLEAN);
            a_.setObject(false);
            setArgument(a_);
        }
    }

}
