package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AnaRendForm extends AnaRendElement {
    private CustList<OperationNode> roots;
    private OperationNode root;
    private String sgn = "";
    private final ResultExpression resultExpression = new ResultExpression();


    private StringList texts = new StringList();
    private StringList varNames = new StringList();
    AnaRendForm(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrSgn()));
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrAction());
        roots = new CustList<OperationNode>();
        String href_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
        ResultText r_ = new ResultText();
        if (href_.startsWith(CALL_METHOD)) {
            String lk_ = href_.substring(1);
            int rowsGrId_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
            r_.buildIdAna(lk_, rowsGrId_, _anaDoc, _page);
            texts = r_.getTexts();
            roots = r_.getOpExpRoot();
            for (OperationNode e: r_.getOpExpRoot()) {
                Mapping m_ = new Mapping();
                m_.setArg(e.getResultClass());
                m_.setParam(_page.getAliasLong());
                if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFile(_page.getCurrentFile());
                    badEl_.setIndexFile(rowsGrId_);
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(e.getResultClass().getNames(),AND_ERR),
                            _page.getAliasLong());
                    AnalyzingDoc.addError(badEl_, _page);
                }
            }
            int l_ = roots.size();
            StringList formArg_ = new StringList();
            StringList varNames_ = new StringList();
            for (int i = 0; i< l_; i++) {
                String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
                varNames_.add(varLoc_);
            }
            varNames = varNames_;
            int i_ = 0;
            for (String v:varNames_) {
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(roots.get(i_).getResultClass().getSingleNameOrEmpty());
                _page.getInfosVars().addEntry(v,lv_);
                formArg_.add(StringUtil.concat(AnaRendBlock.LEFT_PAR, v,AnaRendBlock.RIGHT_PAR));
                i_++;
            }
            String pref_ = r_.quickRender(lk_, formArg_);
            _page.zeroOffset();
            root = RenderAnalysis.getRootAnalyzedOperations(pref_, 0, _anaDoc, _page,resultExpression);
            sgn = AnaRendBlock.checkVars(rowsGrId_,varNames_,root,_page,_anaDoc);
            _read.setAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrSgn()),sgn);
            for (String v:varNames_) {
                _page.getInfosVars().removeKey(v);
            }
        }
    }

    public StringList getTexts() {
        return texts;
    }

    public OperationNode getRoot() {
        return root;
    }

    public StringList getVarNames() {
        return varNames;
    }

    public CustList<OperationNode> getRoots() {
        return roots;
    }
}
