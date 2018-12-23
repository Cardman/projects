package code.formathtml;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.util.BadElRender;
import code.sml.Element;
import code.sml.Node;
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
    private static final char COMMA_CHAR = ',';
    private static final String ATTRIBUTE_CONDITION = "condition";
    private static final String ATTRIBUTE_REF_EQ = "refeq";
    private static final String DEFINED_ATTRIBUTE = "defined";
    private static final String UNDEFINED_ATTRIBUTE = "undefined";

    private static final String IS_NULL_ATTRIBUTE = "isnull";
    private static final String IS_NOT_NULL_ATTRIBUTE = "isnotnull";
    private static final String NUMBER_EXPRESSION = "mathexpr";
    private ExtractCondition() {
    }

    static boolean evaluateGenericCondition(Element _en, Configuration _conf, ImportingPage _ip) {
        String prefix_ = _conf.getLastPage().getPrefix();
        if (StringList.quickEq(_en.getTagName(), StringList.concat(prefix_,IF_BLOCK_TAG))) {
            return evaluateCondition(_en, _conf, _ip);
        }
        if (StringList.quickEq(_en.getTagName(), StringList.concat(prefix_,ELSE_IF_BLOCK_TAG))) {
            return evaluateCondition(_en, _conf, _ip);
        }
        _ip.setProcessingAttribute(EMPTY_STRING);
        _ip.setLookForAttrValue(false);
        _ip.setOffset(0);
        String defined_ = _en.getAttribute(DEFINED_ATTRIBUTE);
        if (defined_.isEmpty()) {
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
            return false;
        }
        if (StringList.quickEq(_en.getTagName(), StringList.concat(prefix_,TAG_IF_DEF_PARAM)) || StringList.quickEq(_en.getTagName(), StringList.concat(prefix_,TAG_ELSE_IF_DEF_PARAM))) {
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
                if (!_ip.containsLocalVar(a)) {
                    return_ = false;
                    break;
                }
            }
        }
        if (return_) {
            if (!undefined_.isEmpty()) {
                for (String a: StringList.splitChars(undefined_, COMMA_CHAR)) {
                    if (_ip.containsLocalVar(a)) {
                        return_ = false;
                        break;
                    }
                }
            }
        }
        if (return_ && !isNotNull_.isEmpty()) {
            _ip.setProcessingAttribute(IS_NOT_NULL_ATTRIBUTE);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            Struct obj_ = ElRenderUtil.processEl(isNotNull_, 0, _conf).getStruct();
            if (_conf.getContext().getException() != null) {
                return false;
            }
            boolean b_ = obj_ != NullStruct.NULL_VALUE;
            if (!b_) {
                return_ = false;
            }
        }
        if (return_ && !isNull_.isEmpty()) {
            _ip.setProcessingAttribute(IS_NULL_ATTRIBUTE);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            Struct obj_ = ElRenderUtil.processEl(isNull_, 0, _conf).getStruct();
            if (_conf.getContext().getException() != null) {
                return false;
            }
            boolean b_ = obj_ == NullStruct.NULL_VALUE;
            if (!b_) {
                return_ = false;
            }
        }
        if (return_ && !mathExpr_.isEmpty()) {
            _ip.setProcessingAttribute(NUMBER_EXPRESSION);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            Boolean b_ = ((BooleanStruct) ExtractObject.evaluateMathExpression(_ip, _conf, true, mathExpr_)).getInstance();
            if (_conf.getContext().getException() != null) {
                return false;
            }
            if (!b_) {
                return_ = false;
            }
        }
        if (return_ && !refEq_.isEmpty()) {
            _ip.setProcessingAttribute(ATTRIBUTE_REF_EQ);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            Struct argTwo_ = ElRenderUtil.processEl(refEq_, 0, _conf).getStruct();
            if (_conf.getContext().getException() != null) {
                return false;
            }
            return ((BooleanStruct) argTwo_).getInstance();
        }
        if (return_ && !condition_.isEmpty()) {
            _ip.setProcessingAttribute(ATTRIBUTE_CONDITION);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            Argument a_ = ElRenderUtil.processEl(condition_, 0, _conf);
            if (_conf.getContext().getException() != null) {
                return false;
            }
            Struct o_ = a_.getStruct();
            if (!(o_ instanceof BooleanStruct)) {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(a_.getObjectClassName(_conf.getContext()));
                mapping_.setParam(_conf.getStandards().getAliasPrimBoolean());
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().getErrorsDet().add(cast_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                return false;
            }
            Boolean b_ = ((BooleanStruct) o_).getInstance();
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
        if (StringList.quickEq(nodeName_,StringList.concat(prefix_,IF_BLOCK_TAG))) {
            return true;
        }
        if (StringList.quickEq(nodeName_,StringList.concat(prefix_,TAG_IF_DEF_PARAM))) {
            return true;
        }
        if (StringList.quickEq(nodeName_,StringList.concat(prefix_,TAG_IF_DEF_RET_VAL))) {
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
        if (StringList.quickEq(nodeName_,StringList.concat(prefix_,ELSE_IF_BLOCK_TAG))) {
            return true;
        }
        if (StringList.quickEq(nodeName_,StringList.concat(prefix_,TAG_ELSE_IF_DEF_PARAM))) {
            return true;
        }
        if (StringList.quickEq(nodeName_,StringList.concat(prefix_,TAG_ELSE_IF_DEF_RET_VAL))) {
            return true;
        }
        return false;
    }
}
