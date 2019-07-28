package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.BadElError;
import code.expressionlanguage.instr.Delimiters;
import code.expressionlanguage.instr.ElResolver;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.structs.ErrorStruct;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.BadElRender;
import code.util.CustList;

public final class DirRender {
    private DirRender(){}

    public static Argument processEl(String _el, Configuration _conf, int _minIndex, char _begin, char _end) {
        ContextEl context_ = _conf.getContext();
        _conf.setupAnalyzing();
        Delimiters d_ = ElResolver.checkSyntaxDelimiters(_el, _conf, _minIndex, _begin, _end);
        if (d_.getBadOffset() >= 0) {
            _conf.setOffset(d_.getBadOffset());
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getAdvStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        int beg_ = d_.getIndexBegin();
        int end_ = d_.getIndexEnd();
        int cap_ = end_+1 - beg_;
        StringBuilder str_ = new StringBuilder(cap_);
        for (int i = beg_; i <= end_; i++) {
            if (d_.getEscapings().contains(i)) {
                str_.append(' ');
            } else {
                str_.append(_el.charAt(i));
            }
        }
        _conf.setNextIndex(end_+2);
        String el_ = str_.toString();
        OperationsSequence opTwo_ = RenderExpUtil.getOperationsSequence(_minIndex, el_, _conf, d_);
        OperationNode op_ = RenderExpUtil.createOperationNode(_minIndex, CustList.FIRST_INDEX, null, opTwo_, _conf);
        Argument argGl_ = _conf.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        _conf.setStaticContext(static_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, static_, _conf);
        CustList<RendDynOperationNode> out_ = RenderExpUtil.getExecutableNodes(all_);
        if (!_conf.getClasses().isEmptyErrors()) {
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getAdvStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        context_.setAnalyzing(null);
        out_ = RenderExpUtil.getReducedNodes(out_.last());
        return RenderExpUtil.calculateReuse(out_, _conf);
    }
    public static Argument processEl(String _el, int _index, Configuration _conf) {
        ContextEl context_ = _conf.getContext();
        _conf.setupAnalyzing();
        Argument argGl_ = _conf.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        _conf.setStaticContext(static_);
        Delimiters d_ = ElResolver.checkSyntax(_el, _conf, _index);
        if (d_.getBadOffset() >= 0) {
            _conf.getLastPage().setOffset(d_.getBadOffset());
            BadElError badEl_ = new BadElError();
            badEl_.setOffsetInEl(d_.getBadOffset());
            badEl_.setEl(_el);
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(badEl_);
            _conf.setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getAdvStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        String el_ = _el.substring(_index);
        OperationsSequence opTwo_ = RenderExpUtil.getOperationsSequence(_index, el_, _conf, d_);
        OperationNode op_ = RenderExpUtil.createOperationNode(_index, CustList.FIRST_INDEX, null, opTwo_, _conf);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, static_, _conf);
        CustList<RendDynOperationNode> out_ = RenderExpUtil.getExecutableNodes(all_);
        if (!_conf.getClasses().isEmptyErrors()) {
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getAdvStandards().getErrorEl()));
            context_.setAnalyzing(null);
            return Argument.createVoid();
        }
        context_.setAnalyzing(null);
        out_ = RenderExpUtil.getReducedNodes(out_.last());
        return RenderExpUtil.calculateReuse(out_, _conf);
    }
}
