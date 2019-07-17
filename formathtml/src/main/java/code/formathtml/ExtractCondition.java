package code.formathtml;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.util.BadElRender;
import code.sml.Element;
import code.sml.Node;
import code.util.StringList;

final class ExtractCondition {

    private static final String IF_BLOCK_TAG = "if";
    private static final String ELSE_IF_BLOCK_TAG = "elseif";
    private static final String ATTRIBUTE_CONDITION = "condition";
    private static final String ATTRIBUTE_REF_EQ = "refeq";

    private static final String NUMBER_EXPRESSION = "mathexpr";
    private ExtractCondition() {
    }

    static boolean evaluateGenericCondition(Element _en, Configuration _conf, ImportingPage _ip) {
        return evaluateCondition(_en, _conf, _ip);
    }

    static boolean evaluateCondition(Element _en, Configuration _conf, ImportingPage _ip) {
        String condition_ = _en.getAttribute(ATTRIBUTE_CONDITION);
        String mathExpr_ = _en.getAttribute(NUMBER_EXPRESSION);
        String refEq_ = _en.getAttribute(ATTRIBUTE_REF_EQ);
        boolean return_ = true;
        if (!mathExpr_.isEmpty()) {
            _ip.setProcessingAttribute(NUMBER_EXPRESSION);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            Boolean b_ = ((BooleanStruct) ExtractObject.evaluateMathExpression(_ip, _conf, true, mathExpr_)).getInstance();
            if (!b_) {
                return_ = false;
            }
        }
        if (return_ && !refEq_.isEmpty()) {
            _ip.setProcessingAttribute(ATTRIBUTE_REF_EQ);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            Struct argTwo_ = ElRenderUtil.processEl(refEq_, 0, _conf).getStruct();
            return ((BooleanStruct) argTwo_).getInstance();
        }
        if (return_ && !condition_.isEmpty()) {
            _ip.setProcessingAttribute(ATTRIBUTE_CONDITION);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            Argument a_ = ElRenderUtil.processEl(condition_, 0, _conf);
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
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getAdvStandards().getErrorEl()));
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
        return false;
    }
}
