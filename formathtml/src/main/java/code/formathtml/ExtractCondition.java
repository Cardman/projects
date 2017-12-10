package code.formathtml;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.methods.exceptions.BadConditionExpressionException;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.exceptions.BadReferenceEqualsException;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

final class ExtractCondition {

    private static final String IF_BLOCK_TAG = "if";
    private static final String TAG_IF_DEF_PARAM = "ifdefparam";
    private static final String TAG_IF_DEF_RET_VAL = "ifdefretval";
    private static final String ELSE_IF_BLOCK_TAG = "elseif";
    private static final String TAG_ELSE_IF_DEF_PARAM = "elseifdefparam";
    private static final String TAG_ELSE_IF_DEF_RET_VAL = "elseifdefretval";
    private static final String EMPTY_STRING = "";
    private static final String RETURN_LINE = "\n";
    private static final char COMMA_CHAR = ',';
    private static final char NEG = '!';
    private static final String ATTRIBUTE_CONDITION = "condition";
    private static final String ATTRIBUTE_REF_EQ = "refeq";
    private static final int NB_INTERPRET=2;
    private static final String DEFINED_ATTRIBUTE = "defined";
    private static final String UNDEFINED_ATTRIBUTE = "undefined";

    private static final String IS_NULL_ATTRIBUTE = "isnull";
    private static final String IS_NOT_NULL_ATTRIBUTE = "isnotnull";
    private static final String NUMBER_EXPRESSION = "mathexpr";
    private static final char MATH_INTERPRET = '`';
    private static final char EQUALS = '=';
    private ExtractCondition() {
    }

    static boolean evaluateGenericCondition(Element _en, Configuration _conf, ImportingPage _ip) {
        String prefix_ = _conf.getLastPage().getPrefix();
        if (StringList.quickEq(_en.getTagName(), prefix_+IF_BLOCK_TAG)) {
            return evaluateCondition(_en, _conf, _ip);
        }
        if (StringList.quickEq(_en.getTagName(), prefix_+ELSE_IF_BLOCK_TAG)) {
            return evaluateCondition(_en, _conf, _ip);
        }
        _ip.setProcessingAttribute(EMPTY_STRING);
        _ip.setLookForAttrValue(false);
        _ip.setOffset(0);
        String defined_ = _en.getAttribute(DEFINED_ATTRIBUTE);
        if (defined_.isEmpty()) {
            throw new BadConditionExpressionException(_conf.joinPages());
        }
        if (StringList.quickEq(_en.getTagName(), prefix_+TAG_IF_DEF_PARAM) || StringList.quickEq(_en.getTagName(), prefix_+TAG_ELSE_IF_DEF_PARAM)) {
            StringMap<LocalVariable> locVars_ = _ip.getParameters();
            boolean return_ = true;
            for (String a: StringList.splitChars(defined_, COMMA_CHAR)) {
                if (!locVars_.contains(a)) {
                    return_ = false;
                    break;
                }
            }
            return return_;
        }
        StringMap<LocalVariable> locVars_ = _ip.getReturnedValues();
        boolean return_ = true;
        for (String a: StringList.splitChars(defined_, COMMA_CHAR)) {
            if (!locVars_.contains(a)) {
                return_ = false;
                break;
            }
        }
        return return_;
    }

    static boolean evaluateCondition(Element _en, Configuration _conf, ImportingPage _ip) {
        StringMap<LocalVariable> locVars_ = _ip.getLocalVars();
        String defined_ = _en.getAttribute(DEFINED_ATTRIBUTE);
        String undefined_ = _en.getAttribute(UNDEFINED_ATTRIBUTE);
        String isNotNull_ = _en.getAttribute(IS_NOT_NULL_ATTRIBUTE);
        String isNull_ = _en.getAttribute(IS_NULL_ATTRIBUTE);
        String condition_ = _en.getAttribute(ATTRIBUTE_CONDITION);
        String mathExpr_ = _en.getAttribute(NUMBER_EXPRESSION);
        String refEq_ = _en.getAttribute(ATTRIBUTE_REF_EQ);
        boolean return_ = true;
        if (!defined_.isEmpty()) {
            for (String a: StringList.splitChars(defined_, COMMA_CHAR)) {
                if (!locVars_.contains(a)) {
                    return_ = false;
                    break;
                }
            }
        }
        if (return_) {
            if (!undefined_.isEmpty()) {
                for (String a: StringList.splitChars(undefined_, COMMA_CHAR)) {
                    if (locVars_.contains(a)) {
                        return_ = false;
                        break;
                    }
                }
            }
        }
        if (return_ && !isNotNull_.isEmpty()) {
            String isNotNullWithoutNeg_ = replaceNegPrefix(isNotNull_);
            if (isNotNullWithoutNeg_.isEmpty()) {
                throw new BadConditionExpressionException(isNotNull_+RETURN_LINE+_conf.joinPages());
            }
            int nbNeg_ = isNotNull_.length() - isNotNullWithoutNeg_.length();
            _ip.setProcessingAttribute(IS_NOT_NULL_ATTRIBUTE);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(nbNeg_);
            Object obj_ = ElUtil.processEl(isNotNullWithoutNeg_, 0, _conf.toContextEl()).getObject();
            boolean b_ = obj_ != null;
            if (nbNeg_%2 == 1) {
                b_ = !b_;
            }
            if (!b_) {
                return_ = false;
            }
        }
        if (return_ && !isNull_.isEmpty()) {
            String isNullWithoutNeg_ = replaceNegPrefix(isNull_);
            if (isNullWithoutNeg_.isEmpty()) {
                throw new BadConditionExpressionException(isNull_+RETURN_LINE+_conf.joinPages());
            }
            int nbNeg_ = isNull_.length() - isNullWithoutNeg_.length();
            _ip.setProcessingAttribute(IS_NULL_ATTRIBUTE);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(nbNeg_);
            Object obj_ = ElUtil.processEl(isNullWithoutNeg_, 0, _conf.toContextEl()).getObject();
            boolean b_ = obj_ == null;
            if (nbNeg_%2 == 1) {
                b_ = !b_;
            }
            if (!b_) {
                return_ = false;
            }
        }
        if (return_ && !mathExpr_.isEmpty()) {
            _ip.setProcessingAttribute(NUMBER_EXPRESSION);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            Boolean b_ = (Boolean) ExtractObject.evaluateMathExpression(_ip, _conf, true, mathExpr_);
            ExtractObject.checkNullPointer(_conf, b_);
            if (!b_) {
                return_ = false;
            }
        }
        if (return_ && !refEq_.isEmpty()) {
            _ip.setProcessingAttribute(ATTRIBUTE_REF_EQ);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            if (StringList.indexesOfChar(refEq_, MATH_INTERPRET).size() != NB_INTERPRET) {
                throw new BadReferenceEqualsException(_conf.joinPages());
            }
            StringList parts_ = StringList.splitChars(refEq_, MATH_INTERPRET);
            String accessPartOne_ = parts_.first();
            String accessOp_ = parts_.get(CustList.SECOND_INDEX);
            String accessPartTwo_ = parts_.last();
            String eq_ = String.valueOf(EQUALS);
            String diff_ = String.valueOf(NEG+eq_);
            boolean eqCmp_;
            if (StringList.quickEq(accessOp_, eq_)) {
                eqCmp_ = true;
            } else if (StringList.quickEq(accessOp_, diff_)) {
                eqCmp_ = false;
            } else {
                throw new BadReferenceEqualsException(_conf.joinPages());
            }
            Object argOne_ = ElUtil.processEl(accessPartOne_, 0, _conf.toContextEl()).getObject();
            _ip.addToOffset(accessPartOne_.length()+1);
            _ip.addToOffset(accessOp_.length());
            Object argTwo_ = ElUtil.processEl(accessPartTwo_, 0, _conf.toContextEl()).getObject();
            if (eqCmp_) {
                if (argOne_ != argTwo_) {
                    return_ = false;
                }
            } else {
                if (argOne_ == argTwo_) {
                    return_ = false;
                }
            }
        }
        if (return_ && !condition_.isEmpty()) {
            String conditionWithoutNeg_ = replaceNegPrefix(condition_);
            if (conditionWithoutNeg_.isEmpty()) {
                throw new BadConditionExpressionException(condition_+RETURN_LINE+_conf.joinPages());
            }
            int nbNeg_ = condition_.length() - conditionWithoutNeg_.length();
            _ip.setProcessingAttribute(ATTRIBUTE_CONDITION);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(nbNeg_);
            Argument a_ = ElUtil.processEl(conditionWithoutNeg_, 0, _conf.toContextEl());
            Object o_ = a_.getObject();
            if (!(o_ instanceof Boolean)) {
                throw new DynamicCastClassException(a_.getObjectClassName()+RETURN_LINE+PrimitiveTypeUtil.PRIM_BOOLEAN+RETURN_LINE+_conf.joinPages());
            }
            Boolean b_ = (Boolean) o_;
            if (nbNeg_%2 == 1) {
                b_ = !b_;
            }
            if (!b_) {
                return_ = false;
            }
        }
        return return_;
    }

    static boolean isBeginOfConditionNode(Configuration _conf, Node _node) {
        if (!(_node instanceof Element)) {
            return false;
        }
        String nodeName_ = ((Element) _node).getTagName();
        String prefix_ = _conf.getLastPage().getPrefix();
        if (StringList.quickEq(nodeName_, prefix_+IF_BLOCK_TAG)) {
            return true;
        }
        if (StringList.quickEq(nodeName_, prefix_+TAG_IF_DEF_PARAM)) {
            return true;
        }
        if (StringList.quickEq(nodeName_, prefix_+TAG_IF_DEF_RET_VAL)) {
            return true;
        }
        return false;
    }
    static boolean isContentOfConditionNode(Configuration _conf, Node _node) {
        if (!(_node instanceof Element)) {
            return false;
        }
        String nodeName_ = ((Element) _node).getTagName();
        String prefix_ = _conf.getLastPage().getPrefix();
        if (StringList.quickEq(nodeName_, prefix_+ELSE_IF_BLOCK_TAG)) {
            return true;
        }
        if (StringList.quickEq(nodeName_, prefix_+TAG_ELSE_IF_DEF_PARAM)) {
            return true;
        }
        if (StringList.quickEq(nodeName_, prefix_+TAG_ELSE_IF_DEF_RET_VAL)) {
            return true;
        }
        return false;
    }

    private static String replaceNegPrefix(String _el) {
        int len_ = _el.length();
        int i_ = CustList.FIRST_INDEX;
        while (i_ < len_) {
            if (_el.charAt(i_) != NEG) {
                break;
            }
            i_++;
        }
        if (i_ < len_) {
            return _el.substring(i_);
        }
        return EMPTY_STRING;
    }
}
