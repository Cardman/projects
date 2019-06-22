package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.util.*;

public final class InstanceOfOperation extends AbstractUnaryOperation {

    private String className;
    private int offset;
    private boolean correctTemplate = true;
    public InstanceOfOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        offset = getOperations().getOperators().firstKey();
        className = getOperations().getOperators().firstValue();
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyzeUnary(Analyzable _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _conf);
        LgNames stds_ = _conf.getStandards();
        KeyWords keyWords_ = _conf.getKeyWords();
        String keyWordInstanceof_ = keyWords_.getKeyWordInstanceof();
        String sub_ = className.substring(keyWordInstanceof_.length() + className.indexOf(keyWordInstanceof_));
        sub_ = _conf.resolveCorrectType(sub_, sub_.contains(Templates.TEMPLATE_BEGIN));
        if (!sub_.contains(Templates.TEMPLATE_BEGIN)) {
            if (!sub_.startsWith(Templates.PREFIX_VAR_TYPE)) {
                correctTemplate = Templates.correctNbParameters(sub_, _conf);
            }
        }
        className = sub_;
        setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
    }

    public String getClassName() {
        return className;
    }

    public int getOffset() {
        return offset;
    }

    public boolean isCorrectTemplate() {
        return correctTemplate;
    }
}
