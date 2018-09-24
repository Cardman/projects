package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.Delimiters;
import code.expressionlanguage.ElResolver;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.StdStruct;
import code.formathtml.util.BadElRender;
import code.util.CustList;

public final class ElRenderUtil {
    public static Argument processEl(String _el, Configuration _conf, int _minIndex, char _begin, char _end) {
        ContextEl context_ = _conf.toContextEl();
        context_.setAnalyzing(new AnalyzedPageEl());
        context_.setRootAffect(false);
        context_.getAnalyzing().setGlobalClass(_conf.getGlobalClass());
        context_.getAnalyzing().setLocalVars(_conf.getLocalVars());
        context_.getAnalyzing().setVars(_conf.getVars());
        context_.getAnalyzing().setCatchVars(_conf.getCatchVars());
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
        OperationNode op_ = OperationNode.createOperationNode(_minIndex, CustList.FIRST_INDEX, null, opTwo_, _conf);
        if (opTwo_.isError()) {
            _conf.setOffset(d_.getBadOffset());
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            context_.setException(new StdStruct(new CustomError(badEl_.display()), _conf.getStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        Argument argGl_ = _conf.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        _conf.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, _conf);
        if (!_conf.getClasses().isEmptyErrors()) {
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            context_.setException(new StdStruct(new CustomError(badEl_.display()), _conf.getStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        context_.setAnalyzing(null);
        calculate(all_, _conf);
        Argument arg_ = op_.getArgument();
        return arg_;
    }

    public static Argument processEl(String _el, int _index, Configuration _conf) {
        return processEl(_el, _index, _conf, false);
    }
    public static Argument processEl(String _el, int _index, Configuration _conf, boolean _line) {
        ContextEl context_ = _conf.toContextEl();
        context_.setAnalyzing(new AnalyzedPageEl());
        if (!_conf.isInternGlobal() && !_line) {
            context_.setRootAffect(false);
        } else {
            context_.setRootAffect(true);
            context_.setAnalyzingRoot(true);
        }
        context_.getAnalyzing().setGlobalClass(_conf.getGlobalClass());
        context_.getAnalyzing().setLocalVars(_conf.getLocalVars());
        context_.getAnalyzing().setVars(_conf.getVars());
        context_.getAnalyzing().setCatchVars(_conf.getCatchVars());
        context_.getAnalyzing().getParameters().putAllMap(_conf.getParameters());
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, _index);
        if (d_.getBadOffset() >= 0) {
            context_.setRootAffect(false);
            context_.setAnalyzingRoot(false);
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
        if (!_conf.isInternGlobal() && !_line) {
            context_.setAnalyzingRoot(false);
        }
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(_index, el_, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(_index, CustList.FIRST_INDEX, null, opTwo_, _conf);
        if (opTwo_.isError()) {
            context_.setRootAffect(false);
            context_.setAnalyzingRoot(false);
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            context_.setException(new StdStruct(new CustomError(badEl_.display()), _conf.getStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        Argument argGl_ = _conf.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        _conf.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, _conf);
        if (!_conf.getClasses().isEmptyErrors()) {
            context_.setRootAffect(false);
            context_.setAnalyzingRoot(false);
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setRc(_conf.getCurrentLocation());
            context_.setException(new StdStruct(new CustomError(badEl_.display()), _conf.getStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        context_.setRootAffect(false);
        context_.setAnalyzingRoot(false);
        context_.setAnalyzing(null);
        calculate(all_, _conf);
        Argument arg_  = op_.getArgument();
        return arg_;
    }
    static void calculate(CustList<OperationNode> _nodes, Configuration _context) {
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
