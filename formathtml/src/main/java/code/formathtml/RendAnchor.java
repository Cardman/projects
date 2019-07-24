package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.BadElRender;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.StringList;

public final class RendAnchor extends RendElement {
    private CustList<CustList<RendDynOperationNode>> opExp;

    private StringList texts = new StringList();
    private StringList varNames = new StringList();
    RendAnchor(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _all, StringList _list) {
        opExp = new CustList<CustList<RendDynOperationNode>>();
        String href_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_COMMAND));
        ResultText r_ = new ResultText();
        if (href_.startsWith(CALL_METHOD)) {
            String lk_ = href_.substring(1);
            r_.build(lk_,_cont,_doc);
            texts = r_.getTexts();
            opExp = r_.getOpExp();
        } else {
            r_.build(href_,_cont,_doc);
            texts = r_.getTexts();
            opExp = r_.getOpExp();
        }
        for (CustList<RendDynOperationNode> e: opExp) {
            Mapping m_ = new Mapping();
            m_.setArg(e.last().getResultClass());
            m_.setParam(_cont.getStandards().getAliasNumber());
            if (!Templates.isCorrectOrNumbers(m_,_cont)) {
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_cont.getClasses().getErrorsDet());
                badEl_.setFileName(_cont.getCurrentFileName());
                badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                _cont.getClasses().addError(badEl_);
            }
        }
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        String href_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_COMMAND));
        _cont.getCallsExps().add(new CustList<RendDynOperationNode>());
        _cont.getConstAnchors().add(true);
        _cont.getAnchorsArgs().add(new StringList());
        _cont.getAnchorsVars().add(varNames);
        if (!href_.startsWith(CALL_METHOD)) {
            if (((Element)_nextWrite).hasAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_COMMAND))) {
                ((Element)_nextWrite).setAttribute(ATTRIBUTE_HREF, EMPTY_STRING);
            }
            _cont.getAnchorsNames().add(EMPTY_STRING);
            incrAncNb(_cont, (Element) _nextWrite);
            return;
        }
        String render_ = ResultText.render(opExp, texts, _cont);
        if (_cont.getContext().hasExceptionOrFailInit()) {
            _cont.getAnchorsNames().add(EMPTY_STRING);
            return;
        }
        _cont.getAnchorsNames().add(render_);
        String beanName_ = _cont.getLastPage().getBeanName();
        ((Element)_nextWrite).setAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_COMMAND), StringList.concat(CALL_METHOD,beanName_,DOT,render_));
        ((Element)_nextWrite).setAttribute(ATTRIBUTE_HREF, EMPTY_STRING);
        incrAncNb(_cont, (Element) _nextWrite);
    }

    @Override
    public void reduce(Configuration _context) {

    }
}
