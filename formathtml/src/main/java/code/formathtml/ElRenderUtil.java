package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Delimiters;
import code.expressionlanguage.ElResolver;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.opers.DirectCalculableOperation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.structs.ErrorStruct;
import code.formathtml.util.BadElRender;
import code.util.CustList;

public final class ElRenderUtil {
    public static Argument processEl(String _el, Configuration _conf, int _minIndex, char _begin, char _end) {
        ContextEl context_ = _conf.toContextEl();
        context_.setAnalyzing(new AnalyzedPageEl());
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
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            context_.setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        String el_ = _el.substring(d_.getIndexBegin(), d_.getIndexEnd()+1);
        _conf.setNextIndex(d_.getIndexEnd()+2);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(_minIndex, el_, context_, d_);
        OperationNode op_ = OperationNode.createOperationNode(_minIndex, CustList.FIRST_INDEX, null, opTwo_, _conf);
        if (opTwo_.isError()) {
            _conf.setOffset(d_.getBadOffset());
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            context_.setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
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
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            context_.setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        context_.setAnalyzing(null);
        calculate(all_, _conf);
        Argument arg_ = op_.getArgument();
        return arg_;
    }

    public static Argument processEl(String _el, int _index, Configuration _conf) {
        ContextEl context_ = _conf.toContextEl();
        context_.setAnalyzing(new AnalyzedPageEl());
        context_.getAnalyzing().setGlobalClass(_conf.getGlobalClass());
        context_.getAnalyzing().setLocalVars(_conf.getLocalVars());
        context_.getAnalyzing().setVars(_conf.getVars());
        context_.getAnalyzing().setCatchVars(_conf.getCatchVars());
        context_.getAnalyzing().getParameters().putAllMap(_conf.getParameters());
        Argument argGl_ = _conf.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        _conf.setStaticContext(static_);
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, _index);
        if (d_.getBadOffset() >= 0) {
            _conf.getLastPage().setOffset(d_.getBadOffset());
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            context_.setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        String el_ = _el.substring(_index);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(_index, el_, _conf, d_);
        OperationNode op_ = OperationNode.createOperationNode(_index, CustList.FIRST_INDEX, null, opTwo_, _conf);
        if (opTwo_.isError()) {
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            context_.setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, _conf);
        if (!_conf.getClasses().isEmptyErrors()) {
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            context_.setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        context_.setAnalyzing(null);
        calculate(all_, _conf);
        Argument arg_  = op_.getArgument();
        return arg_;
    }
    static void calculate(CustList<OperationNode> _nodes, Configuration _context) {     
        int ind_ = 0;
        int len_ = _nodes.size();
        while (ind_ < len_) {
            OperationNode curr_ = _nodes.get(ind_);
            if (!(curr_ instanceof DirectCalculableOperation)) {
                ind_++;
                continue;
            }
            Argument a_ = curr_.getArgument();
            if (a_ != null) {
                ind_++;
                continue;
            }
            DirectCalculableOperation dir_ = (DirectCalculableOperation) curr_;
            dir_.calculate(_context);
            a_ = curr_.getArgument();
            if (_context.getException() != null) {
                return;
            }
            ind_ = ElUtil.getNextIndex(curr_, a_.getStruct());
        }
        
    }

}
