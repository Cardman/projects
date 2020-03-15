package code.formathtml;

import code.expressionlanguage.errors.custom.BadElError;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.NodeContainer;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.LongTreeMap;
import code.util.StringList;

public final class RendForm extends RendElement {
    private CustList<CustList<RendDynOperationNode>> opExp;

    private StringList texts = new StringList();
    private StringList varNames = new StringList();

    RendForm(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _list) {
        _list.removeAllString(StringList.concat(_cont.getPrefix(),ATTRIBUTE_COMMAND));
        _list.removeAllString(ATTRIBUTE_ACTION);
        opExp = new CustList<CustList<RendDynOperationNode>>();
        String href_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_COMMAND));
        ResultText r_ = new ResultText();
        if (href_.startsWith(CALL_METHOD)) {
            String lk_ = href_.substring(1);
            r_.build(lk_,_cont,_doc);
            texts = r_.getTexts();
            opExp = r_.getOpExp();
            for (CustList<RendDynOperationNode> e: opExp) {
                Mapping m_ = new Mapping();
                m_.setArg(e.last().getResultClass());
                m_.setParam(_cont.getStandards().getAliasNumber());
                if (!Templates.isCorrectOrNumbers(m_,_cont)) {
                    BadElError badEl_ = new BadElError();
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(_cont.getCurrentLocationIndex());
                    _cont.addError(badEl_);
                }
            }
            StringList argList_ = new StringList();
            for (CustList<RendDynOperationNode> e: opExp) {
                String cl_ = e.last().getResultClass().getSingleNameOrEmpty();
                if (cl_.isEmpty()) {
                    argList_.add(_cont.getKeyWords().getKeyWordNull());
                } else {
                    String cast_ = _cont.getKeyWords().getKeyWordCast();
                    cast_ = StringList.concat(cast_,"(",cl_,")");
                    argList_.add(StringList.concat(cast_,"0"));
                }
            }
            String pref_ = r_.quickRender(lk_, argList_);
            if (pref_.indexOf('(') < 0) {
                pref_ = StringList.concat(pref_,"()");
            }
            MethodAccessKind st_ = _doc.getStaticContext();
            RenderExpUtil.getAnalyzedOperations(pref_,0,_cont,Calculation.staticCalculation(st_));
        }
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        long currentForm_ = _cont.getCurrentForm();
        _cont.getContainersMapStack().add(new LongTreeMap< NodeContainer>());
        _cont.getFormatIdMapStack().add(new StringList());
        _cont.getFormsNb().add(currentForm_);
        _cont.getInputs().add(0L);
        currentForm_++;
        _cont.setCurrentForm(currentForm_);
        String href_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_COMMAND));
        _cont.getCallsFormExps().add(new CustList<RendDynOperationNode>());
        _cont.getFormsArgs().add(new StringList());
        _cont.getFormsVars().add(varNames);
        Element elt_ = (Element) _nextWrite;
        if (!href_.startsWith(CALL_METHOD)) {
            if (elt_.hasAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_COMMAND))) {
                elt_.setAttribute(ATTRIBUTE_ACTION, EMPTY_STRING);
            }
            _cont.getFormsNames().add(EMPTY_STRING);
            currentForm_ = _cont.getCurrentForm();
            elt_.setAttribute(NUMBER_FORM, String.valueOf(currentForm_ - 1));
            return;
        }
        String render_ = ResultText.render(opExp, texts, _cont);
        if (_cont.getContext().hasException()) {
            _cont.getFormsNames().add(EMPTY_STRING);
            currentForm_ = _cont.getCurrentForm();
            elt_.setAttribute(NUMBER_FORM, String.valueOf(currentForm_ - 1));
            return;
        }
        _cont.getFormsNames().add(render_);
        String beanName_ = _cont.getLastPage().getBeanName();
        elt_.setAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_COMMAND), StringList.concat(CALL_METHOD,beanName_,DOT,render_));
        elt_.setAttribute(ATTRIBUTE_ACTION, EMPTY_STRING);
        currentForm_ = _cont.getCurrentForm();
        elt_.setAttribute(NUMBER_FORM, String.valueOf(currentForm_ - 1));
    }

    @Override
    public void reduce(Configuration _context) {
        super.reduce(_context);
        ResultText.reduce(opExp);
    }
}
