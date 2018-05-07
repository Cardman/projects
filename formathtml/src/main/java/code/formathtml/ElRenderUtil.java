package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.Delimiters;
import code.expressionlanguage.ElResolver;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.BadElError;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.ExpLanguages;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UnexpectedOperationAffect;
import code.expressionlanguage.opers.ConstantOperation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.SettableElResult;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.stds.LgNames;
import code.formathtml.util.BadElRender;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class ElRenderUtil {

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
        _conf.getContext().setAnalyzing(null);
        CustList<OperationNode> left_ = members_.getLeft();
        CustList<OperationNode> right_ = members_.getRight();
        ContextEl cont_ = _conf.toContextEl();
        if (!cont_.getClasses().getErrorsDet().isEmpty()) {
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(cont_.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            cont_.setException(new StdStruct(new CustomError(badEl_.display()), _conf.getStandards().getErrorEl()));
            return;
        }
        tryToCalculateAffect(left_, cont_, right_, _oper);
    }

    public static void tryToCalculateAffect(CustList<OperationNode> _left, ContextEl _conf, CustList<OperationNode> _right, String _op) {
        CustList<OperationNode> allLeft_ = _left;
        calculate(allLeft_ , _conf);
        CustList<OperationNode> allRight_ = _right;
        calculate(allRight_, _conf);
        _conf.getLastPage().setRightArgument(_right.last().getArgument());
        SettableElResult settable_ =  ExpressionLanguage.getSettable(_left);
        _conf.setCheckAffectation(true);
        settable_.calculateSetting(_conf, _op, false);
        _conf.setCheckAffectation(false);
        _conf.getLastPage().setRightArgument(null);
    }

    public static Argument processEl(String _el, Configuration _conf, int _minIndex, char _begin, char _end) {
        ContextEl context_ = _conf.toContextEl();
        context_.setAnalyzing(new AnalyzedPageEl());
        context_.setRootAffect(false);
        context_.getAnalyzing().setGlobalClass(_conf.getGlobalClass());
        context_.getAnalyzing().setLocalVars(_conf.getLocalVars());
        context_.getAnalyzing().getVars().putAllMap(_conf.getVars());
        context_.getAnalyzing().getCatchVars().putAllMap(_conf.getCatchVars());
        context_.getAnalyzing().getParameters().putAllMap(_conf.getParameters());
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(_el, context_, _minIndex, _begin, _end);
        if (d_.getBadOffset() >= 0) {
            _conf.setOffset(d_.getBadOffset());
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            context_.setException(new StdStruct(new CustomError(badEl_.display()), _conf.getStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        String el_ = _el.substring(d_.getIndexBegin(), d_.getIndexEnd()+1);
        _conf.setNextIndex(d_.getIndexEnd()+2);
        context_.setAnalyzingRoot(false);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(_minIndex, el_, context_, d_);
        OperationNode op_ = OperationNode.createOperationNode(_minIndex, CustList.FIRST_INDEX, null, opTwo_);
        if (op_ == null) {
            _conf.setOffset(d_.getBadOffset());
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            context_.setException(new StdStruct(new CustomError(badEl_.display()), _conf.getStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        Argument argGl_ = _conf.getLastPage().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        context_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, EMPTY_STRING, static_, _conf);
        if (!_conf.getClasses().getErrorsDet().isEmpty()) {
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            context_.setException(new StdStruct(new CustomError(badEl_.display()), _conf.getStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        context_.setAnalyzing(null);
        calculate(all_, context_);
        Argument arg_ = op_.getArgument();
        return arg_;
    }

    public static Argument processEl(String _el, int _index, Configuration _conf) {
        ContextEl context_ = _conf.toContextEl();
        context_.setAnalyzing(new AnalyzedPageEl());
        context_.setRootAffect(false);
        context_.getAnalyzing().setGlobalClass(_conf.getGlobalClass());
        context_.getAnalyzing().setLocalVars(_conf.getLocalVars());
        context_.getAnalyzing().getVars().putAllMap(_conf.getVars());
        context_.getAnalyzing().getCatchVars().putAllMap(_conf.getCatchVars());
        context_.getAnalyzing().getParameters().putAllMap(_conf.getParameters());
        Delimiters d_ = ElResolver.checkSyntax(_el, context_, _index);
        if (d_.getBadOffset() >= 0) {
            _conf.getLastPage().setOffset(d_.getBadOffset());
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            context_.setException(new StdStruct(new CustomError(badEl_.display()), _conf.getStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        String el_ = _el.substring(_index);
        context_.setAnalyzingRoot(false);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(_index, el_, context_, d_);
        OperationNode op_ = OperationNode.createOperationNode(_index, CustList.FIRST_INDEX, null, opTwo_);
        if (op_ == null) {
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            context_.setException(new StdStruct(new CustomError(badEl_.display()), _conf.getStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        Argument argGl_ = _conf.getLastPage().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        _conf.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, EMPTY_STRING, static_, _conf);
        if (!_conf.getClasses().getErrorsDet().isEmpty()) {
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            context_.setException(new StdStruct(new CustomError(badEl_.display()), _conf.getStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        context_.setAnalyzing(null);
        calculate(all_, context_);
        Argument arg_  = op_.getArgument();
        return arg_;
    }
    public static ExpLanguages analyzeAffect(String _attrOp, String _attrLeft, String _attrRight,
            String _left, String _right, String _oper, Configuration _conf, boolean _staticContext, boolean _hiddenVarTypes) {
        ContextEl context_ = _conf.toContextEl();
        context_.setAnalyzing(new AnalyzedPageEl());
        context_.getAnalyzing().setGlobalClass(_conf.getGlobalClass());
        context_.getAnalyzing().setLocalVars(_conf.getLocalVars());
        context_.getAnalyzing().getVars().putAllMap(_conf.getVars());
        context_.getAnalyzing().getCatchVars().putAllMap(_conf.getCatchVars());
        context_.getAnalyzing().getParameters().putAllMap(_conf.getParameters());
        ImportingPage page_ = _conf.getLastPage();
        page_.setOffset(0);
        page_.setProcessingAttribute(_attrLeft);
        _conf.setRootAffect(false);
        Delimiters dLeft_ = ElResolver.checkSyntax(_left, _conf, CustList.FIRST_INDEX);
        if (dLeft_.getBadOffset() >= 0) {
            _conf.getLastPage().setOffset(dLeft_.getBadOffset());
            BadElError badEl_ = new BadElError();
            badEl_.setOffsetInEl(dLeft_.getBadOffset());
            badEl_.setEl(_left);
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(badEl_);
            return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
        }
        _conf.setAnalyzingRoot(false);
        OperationsSequence opTwoLeft_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _left, _conf, dLeft_);
        OperationNode opLeft_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwoLeft_);
        if (opLeft_ == null) {
            BadElError badEl_ = new BadElError();
            badEl_.setOffsetInEl(dLeft_.getBadOffset());
            badEl_.setEl(_left);
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(badEl_);
            return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
        }
        _conf.setStaticContext(_staticContext);
        CustList<OperationNode> allLeft_ = ElUtil.getSortedDescNodes(opLeft_, EMPTY_STRING, _hiddenVarTypes, _conf);
        page_.setOffset(0);
        page_.setProcessingAttribute(_attrRight);
        Delimiters dRight_ = ElResolver.checkSyntax(_right, _conf, CustList.FIRST_INDEX);
        if (dRight_.getBadOffset() >= 0) {
            _conf.getLastPage().setOffset(dRight_.getBadOffset());
            BadElError badEl_ = new BadElError();
            badEl_.setOffsetInEl(dRight_.getBadOffset());
            badEl_.setEl(_right);
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(badEl_);
            return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
        }
        OperationsSequence opTwoRight_ = ElResolver.getOperationsSequence(CustList.FIRST_INDEX, _right, _conf, dRight_);
        OperationNode opRight_ = OperationNode.createOperationNode(CustList.FIRST_INDEX, CustList.FIRST_INDEX, null, opTwoRight_);
        if (opRight_ == null) {
            BadElError badEl_ = new BadElError();
            badEl_.setOffsetInEl(dRight_.getBadOffset());
            badEl_.setEl(_right);
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(badEl_);
            return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
        }
        CustList<OperationNode> allRight_ = ElUtil.getSortedDescNodes(opRight_, EMPTY_STRING, _hiddenVarTypes, _conf);
        page_.setOffset(0);
        page_.setProcessingAttribute(_attrLeft);
        page_.setOffset(0);
        SettableElResult set_ = ExpressionLanguage.getSettable(allLeft_);
        set_.setVariable(true);
        LgNames stds_ = _conf.getStandards();
        String stringType_ = stds_.getAliasString();
        String res_ = set_.getResultClass().getName();
        if (set_.resultCanBeSet() && StringList.quickEq(res_, stringType_)) {
            set_.setCatenizeStrings();
        }
        if (set_ instanceof ConstantOperation) {
            if (((ConstantOperation)set_).isImmutablePart()) {
                UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                un_.setFileName(_conf.getCurrentFileName());
                un_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(un_);
                return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
            }
        }
        page_.setProcessingAttribute(_attrRight);
        page_.setOffset(0);
        page_.setProcessingAttribute(_attrLeft);
        ClassArgumentMatching clMatchRight_ = opRight_.getResultClass();
        ClassArgumentMatching clMatchLeft_ = opLeft_.getResultClass();
        page_.setOffset(0);
        if (!_attrOp.isEmpty()) {
            page_.setProcessingAttribute(_attrOp);
        }
        if (_oper.length() == 2) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(clMatchRight_.getName());
            mapping_.setParam(clMatchLeft_.getName());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setRc(_conf.getCurrentLocation());
            if (StringList.quickEq(_oper, Block.EQ_PLUS) || StringList.quickEq(_oper, Block.PLUS_EQ)) {
                if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_, _conf)) {
                    if (!clMatchLeft_.matchClass(_conf.getStandards().getAliasString())) {
                        _conf.getClasses().getErrorsDet().add(cast_);
                        return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
                    }
                } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchRight_, _conf)) {
                    _conf.getClasses().getErrorsDet().add(cast_);
                    return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
                }
            } else if (StringList.quickEq(_oper, Block.AND_EQ) || StringList.quickEq(_oper, Block.OR_EQ)) {
                if (!StringList.quickEq(clMatchLeft_.getName(), stds_.getAliasBoolean())) {
                    if (!StringList.quickEq(clMatchLeft_.getName(), stds_.getAliasPrimBoolean())) {
                        _conf.getClasses().getErrorsDet().add(cast_);
                        return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
                    }
                }
                if (!StringList.quickEq(clMatchRight_.getName(), stds_.getAliasBoolean())) {
                    if (!StringList.quickEq(clMatchRight_.getName(), stds_.getAliasPrimBoolean())) {
                        _conf.getClasses().getErrorsDet().add(cast_);
                        return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
                    }
                }
            } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_, _conf)) {
                _conf.getClasses().getErrorsDet().add(cast_);
                return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
            } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchRight_, _conf)) {
                _conf.getClasses().getErrorsDet().add(cast_);
                return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
            }
        } else {
            if (clMatchRight_.isVariable()) {
                if (!clMatchLeft_.isPrimitive(_conf)) {
                    return new ExpLanguages(allLeft_, allRight_);
                }
                Mapping mapping_ = new Mapping();
                mapping_.setArg(clMatchRight_.getName());
                mapping_.setParam(clMatchLeft_.getName());
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(cast_);
                return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
            }
            StringMap<StringList> vars_ = new StringMap<StringList>();
            boolean buildMap_ = true;
            if (_staticContext) {
                buildMap_ = false;
            } else if (page_.getGlobalClass() == null) {
                buildMap_ = false;
            }
            if (buildMap_) {
                for (TypeVar t: Templates.getConstraints(page_.getGlobalClass(), _conf)) {
                    vars_.put(t.getName(), t.getConstraints());
                }
            }
            Mapping mapping_ = new Mapping();
            mapping_.setMapping(vars_);
            mapping_.setArg(clMatchRight_.getName());
            mapping_.setParam(clMatchLeft_.getName());
            if (!Templates.isCorrect(mapping_, _conf)) {
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().getErrorsDet().add(cast_);
                return new ExpLanguages(new CustList<OperationNode>(),new CustList<OperationNode>());
            }
        }
        return new ExpLanguages(allLeft_, allRight_);
    }
    static void calculate(CustList<OperationNode> _nodes, ContextEl _context) {
        for (OperationNode e: _nodes) {
            if (!e.isCalculated()) {
                e.calculate(_context);
                if (_context.getException() != null) {
                    return;
                }
            }
        }
    }

}
