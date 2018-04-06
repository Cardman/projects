package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Delimiters;
import code.expressionlanguage.ElResolver;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.exceptions.BadExpressionLanguageException;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.exceptions.PrimitiveTypeException;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ExpLanguages;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class ElRenderUtil {

    private static final String RETURN_LINE = "\n";
    private static final String EMPTY_STRING = "";
    public static void processAffect(String _attrOp, String _attrLeft, String _attrRight,
            String _left, String _right, String _oper, Configuration _conf) {
        Argument arg_ = _conf.getLastPage().getGlobalArgument();
        boolean staticContext_ = arg_ == null || arg_.isNull();
        processAffect(_attrOp, _attrLeft, _attrRight, _left, _right, _oper, _conf, staticContext_, staticContext_);
    }

    public static void processAffect(String _attrOp, String _attrLeft, String _attrRight,
            String _left, String _right, String _oper, Configuration _conf, boolean _staticContext, boolean _hiddentVarTypes) {
        ExpLanguages members_ = analyzeAffect(_attrOp, _attrLeft, _attrRight, _left, _right, _oper, _conf, _staticContext, _hiddentVarTypes);
        CustList<OperationNode> left_ = members_.getLeft();
        CustList<OperationNode> right_ = members_.getRight();
        ContextEl cont_ = _conf.toContextEl();
        ElUtil.tryToCalculateAffect(left_, cont_, right_, _oper);
    }

    public static ExpLanguages analyzeAffect(String _attrOp, String _attrLeft, String _attrRight,
            String _left, String _right, String _oper, Configuration _conf, boolean _staticContext, boolean _hiddenVarTypes) {
        ContextEl cont_ = _conf.toContextEl();
        ImportingPage page_ = _conf.getLastPage();
        page_.setOffset(0);
        page_.setProcessingAttribute(_attrLeft);
        Delimiters dLeft_ = ElResolver.checkSyntax(_left, cont_, CustList.FIRST_INDEX);
        OperationsSequence opTwoLeft_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _left, cont_, dLeft_);
        OperationNode opLeft_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwoLeft_);
        if (opLeft_ == null) {
            throw new BadExpressionLanguageException(StringList.concat(_left,RETURN_LINE,_conf.joinPages()));
        }
        CustList<OperationNode> allLeft_ = ElUtil.getOperationNodes(opLeft_, cont_);
        page_.setOffset(0);
        page_.setProcessingAttribute(_attrRight);
        Delimiters dRight_ = ElResolver.checkSyntax(_right, cont_, CustList.FIRST_INDEX);
        OperationsSequence opTwoRight_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _right, cont_, dRight_);
        OperationNode opRight_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwoRight_);
        if (opRight_ == null) {
            throw new BadExpressionLanguageException(StringList.concat(_right,RETURN_LINE,_conf.joinPages()));
        }
        CustList<OperationNode> allRight_ = ElUtil.getOperationNodes(opRight_, cont_);
        page_.setOffset(0);
        page_.setProcessingAttribute(_attrLeft);
        ElUtil.analyzeSetting(true, allLeft_, cont_);
        ElUtil.analyze(allLeft_, cont_, _staticContext, _hiddenVarTypes, EMPTY_STRING, _oper);
        ElUtil.analyzeSetting(false, allLeft_, cont_);
        page_.setOffset(0);
        page_.setProcessingAttribute(_attrRight);
        ElUtil.analyze(allRight_, cont_, _staticContext, _hiddenVarTypes, EMPTY_STRING, _oper);
        page_.setOffset(0);
        page_.setProcessingAttribute(_attrLeft);
        ClassArgumentMatching clMatchRight_ = opRight_.getResultClass();
        ClassArgumentMatching clMatchLeft_ = opLeft_.getResultClass();
        page_.setOffset(0);
        if (!_attrOp.isEmpty()) {
            page_.setProcessingAttribute(_attrOp);
        }
        if (_oper.length() == 2) {
            if (StringList.quickEq(_oper, Block.EQ_PLUS) || StringList.quickEq(_oper, Block.PLUS_EQ)) {
                if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_, cont_)) {
                    if (!clMatchLeft_.matchClass(_conf.getStandards().getAliasString())) {
                        throw new DynamicCastClassException(_conf.joinPages());
                    }
                } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchRight_, cont_)) {
                    throw new DynamicCastClassException(_conf.joinPages());
                }
            } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_, cont_)) {
                throw new DynamicCastClassException(_conf.joinPages());
            } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchRight_, cont_)) {
                throw new DynamicCastClassException(_conf.joinPages());
            }
        } else {
            if (clMatchRight_.isVariable()) {
                if (!clMatchLeft_.isPrimitive(cont_)) {
                    return new ExpLanguages(allLeft_, allRight_);
                }
                throw new PrimitiveTypeException(_conf.joinPages());
            }
            StringMap<StringList> vars_ = new StringMap<StringList>();
            boolean buildMap_ = true;
            if (_staticContext) {
                buildMap_ = false;
            } else if (page_.getGlobalClass() == null) {
                buildMap_ = false;
            }
            if (buildMap_) {
                for (TypeVar t: Templates.getConstraints(page_.getGlobalClass(), cont_)) {
                    vars_.put(t.getName(), t.getConstraints());
                }
            }
            Mapping mapping_ = new Mapping();
            mapping_.setMapping(vars_);
            mapping_.setArg(clMatchRight_.getName());
            mapping_.setParam(clMatchLeft_.getName());
            if (!Templates.isCorrect(mapping_, cont_)) {
                throw new DynamicCastClassException(_conf.joinPages());
            }
        }
        return new ExpLanguages(allLeft_, allRight_);
    }
}
