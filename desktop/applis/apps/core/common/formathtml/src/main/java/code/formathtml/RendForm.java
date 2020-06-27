package code.formathtml;

import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.NodeContainer;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.LongTreeMap;
import code.util.StringList;

public final class RendForm extends RendElement {
    private CustList<CustList<RendDynOperationNode>> opExp;
    private CustList<RendDynOperationNode> opForm;

    private StringList texts = new StringList();
    private StringList varNames = new StringList();

    RendForm(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _list) {
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
        _list.removeAllString(_cont.getRendKeyWords().getAttrAction());
        opExp = new CustList<CustList<RendDynOperationNode>>();
        String href_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
        ResultText r_ = new ResultText();
        if (href_.startsWith(CALL_METHOD)) {
            String lk_ = href_.substring(1);
            int rowsGrId_ = getAttributeDelimiter(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
            r_.build(lk_,_cont,rowsGrId_,_doc);
            texts = r_.getTexts();
            opExp = r_.getOpExp();
            for (CustList<RendDynOperationNode> e: opExp) {
                Mapping m_ = new Mapping();
                m_.setArg(e.last().getResultClass());
                m_.setParam(_cont.getStandards().getAliasNumber());
                if (!AnaTemplates.isCorrectOrNumbers(m_,_cont.getContext())) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(rowsGrId_);
                    badEl_.buildError(_cont.getContext().getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(e.last().getResultClass().getNames(),AND_ERR),
                            _cont.getStandards().getAliasNumber());
                    _cont.addError(badEl_);
                }
            }
            int l_ = opExp.size();
            StringList formArg_ = new StringList();
            StringList varNames_ = new StringList();
            for (int i = 0; i< l_; i++) {
                String varLoc_ = RendBlock.lookForVar(_cont, varNames_);
                varNames_.add(varLoc_);
            }
            varNames = varNames_;
            int i_ = 0;
            for (String v:varNames_) {
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(opExp.get(i_).last().getResultClass().getSingleNameOrEmpty());
                _cont.getLocalVarsAna().last().addEntry(v,lv_);
                formArg_.add(StringList.concat(RendBlock.LEFT_PAR, v,RendBlock.RIGHT_PAR));
                i_++;
            }
            StringList argList_ = new StringList();
            for (CustList<RendDynOperationNode> e: opExp) {
                String cl_ = e.last().getResultClass().getSingleNameOrEmpty();
                if (cl_.isEmpty()) {
                    argList_.add(_cont.getKeyWords().getKeyWordNull());
                } else {
                    String cast_ = _cont.getKeyWords().getKeyWordCast();
                    cast_ = StringList.concat(cast_,RendBlock.LEFT_PAR,cl_,RendBlock.RIGHT_PAR);
                    argList_.add(StringList.concat(cast_,ZERO));
                }
            }
            String pref_ = r_.quickRender(lk_, formArg_);
            if (pref_.indexOf('(') < 0) {
                pref_ = StringList.concat(pref_,RendBlock.LEFT_PAR,RendBlock.RIGHT_PAR);
            }
            opForm = RenderExpUtil.getAnalyzedOperations(pref_,rowsGrId_,0,_cont);
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
        String href_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
        _cont.getCallsFormExps().add(opForm);
        _cont.getFormsVars().add(varNames);
        Element elt_ = (Element) _nextWrite;
        if (!href_.startsWith(CALL_METHOD)) {
            _cont.getFormsArgs().add(new StringList());
            if (elt_.hasAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()))) {
                elt_.setAttribute(_cont.getRendKeyWords().getAttrAction(), EMPTY_STRING);
            }
            _cont.getFormsNames().add(EMPTY_STRING);
            currentForm_ = _cont.getCurrentForm();
            elt_.setAttribute(_cont.getRendKeyWords().getAttrNf(), String.valueOf(currentForm_ - 1));
            return;
        }
        StringList alt_ = ResultText.renderAltList(opExp, texts, _cont);
        StringList arg_ = new StringList();
        int len_ = alt_.size();
        for (int i = 1; i < len_; i += 2) {
            arg_.add(alt_.get(i));
        }
        String render_ = StringList.join(alt_,"");
        if (_cont.getContext().hasException()) {
            _cont.getFormsArgs().add(new StringList());
            _cont.getFormsNames().add(EMPTY_STRING);
            currentForm_ = _cont.getCurrentForm();
            elt_.setAttribute(_cont.getRendKeyWords().getAttrNf(), String.valueOf(currentForm_ - 1));
            return;
        }
        _cont.getFormsArgs().add(arg_);
        _cont.getFormsNames().add(render_);
        String beanName_ = _cont.getLastPage().getBeanName();
        elt_.setAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()), StringList.concat(CALL_METHOD,beanName_,DOT,render_));
        elt_.setAttribute(_cont.getRendKeyWords().getAttrAction(), EMPTY_STRING);
        currentForm_ = _cont.getCurrentForm();
        elt_.setAttribute(_cont.getRendKeyWords().getAttrNf(), String.valueOf(currentForm_ - 1));
    }

    @Override
    public void reduce(Configuration _context) {
        super.reduce(_context);
        ResultText.reduce(opExp);
    }
}
