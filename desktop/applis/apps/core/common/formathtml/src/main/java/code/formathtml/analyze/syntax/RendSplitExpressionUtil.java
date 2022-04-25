package code.formathtml.analyze.syntax;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.instr.ElRetrieverAnonymous;
import code.expressionlanguage.analyze.syntax.IntermediaryResults;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.syntax.SplitExpressionUtil;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.blocks.*;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class RendSplitExpressionUtil {
    private static final char ESCAPED = '\\';
    private static final char RIGHT_EL = '}';
    private static final char LEFT_EL = '{';
    private RendSplitExpressionUtil(){
    }
    public static CustList<IntermediaryResults> getNextResults(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, CustList<AnaRendDocumentBlock> _docs) {
        IntermediaryResults int_ = new IntermediaryResults();
        for (AnaRendDocumentBlock o: _docs) {
            _page.setGlobalType(AnaFormattedRootBlock.defValue());
            _page.setCurrentPkg(_page.getDefaultPkg());
            _page.setCurrentFile(o.getFile());
            processFunction(_analyzingDoc,_page,int_,o, o.getDeclClass().getRootBlock());
        }
        return SplitExpressionUtil.anonymous(int_,_page);
    }
    private static void processFunction(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type) {
        AnaRendBlock current_ = _method.getFirstChild();
        MethodAccessKind st_ = _method.getStaticContext();
        _page.setAccessStaticContext(st_);
        while (current_ != null) {
            elt(_analyzingDoc,_page, _int, _method, _type, current_);
            AnaRendBlock ch_ = current_.getFirstChild();
            if (ch_ != null) {
                current_ = ch_;
                continue;
            }
            while (current_ != null) {
                AnaRendBlock next_ = current_.getNextSibling();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                AnaRendParentBlock parent_ = current_.getParent();
                if (parent_ == _method) {
                    current_ = null;
                    continue;
                }
                current_ = parent_;
            }
        }
    }

    private static void elt(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendBlock _current) {
        if (_current instanceof AnaRendLine) {
            _page.setGlobalOffset(((AnaRendLine) _current).getExpressionOffset());
            _page.zeroOffset();
            String value_ = ((AnaRendLine) _current).getExpression();
            ResultExpression resultExpression_ = ((AnaRendLine) _current).getRes();
            extractAnon(_page, _int, _method, _type, value_, resultExpression_);
        }
        if (_current instanceof AnaRendCaseCondition) {
            String value_ = ((AnaRendCaseCondition) _current).getValue();
            if (((AnaRendCaseCondition) _current).getVariableName().isEmpty()) {
                _page.setGlobalOffset(((AnaRendCaseCondition) _current).getValueOffset());
                _page.zeroOffset();
                ResultExpression resultExpression_ = ((AnaRendCaseCondition) _current).getRes();
                extractAnon(_page, _int, _method, _type, value_, resultExpression_);
            } else {
                if (((AnaRendCaseCondition) _current).isCaseWhen()) {
                    String substring_ = ((AnaRendCaseCondition) _current).getValue();
                    _page.setGlobalOffset(((AnaRendCaseCondition) _current).getValueOffset());
                    _page.zeroOffset();
                    ResultExpression resultExpression_ = ((AnaRendCaseCondition) _current).getRes();
                    extractAnon(_page, _int, _method, _type, substring_.trim(), resultExpression_);
                }
            }
        }
        if (_current instanceof AnaRendSwitchBlock) {
            String value_ = ((AnaRendSwitchBlock) _current).getValue();
            _page.setGlobalOffset(((AnaRendSwitchBlock) _current).getValueOffset());
            _page.zeroOffset();
            ResultExpression resultExpression_ = ((AnaRendSwitchBlock) _current).getRes();
            extractAnon(_page, _int, _method, _type, value_, resultExpression_);
        }
        if (_current instanceof AnaRendCondition) {
            String value_ = ((AnaRendCondition) _current).getCondition();
            _page.setGlobalOffset(((AnaRendCondition) _current).getConditionOffset());
            _page.zeroOffset();
            ResultExpression resultExpression_ = ((AnaRendCondition) _current).getRes();
            extractAnon(_page, _int, _method, _type, value_, resultExpression_);
        }
        if (_current instanceof AnaRendForEachLoop) {
            String value_ = ((AnaRendForEachLoop) _current).getExpression();
            _page.setGlobalOffset(((AnaRendForEachLoop) _current).getExpressionOffset());
            _page.zeroOffset();
            ResultExpression resultExpression_ = ((AnaRendForEachLoop) _current).getRes();
            extractAnon(_page, _int, _method, _type, value_, resultExpression_);
        }
        if (_current instanceof AnaRendForEachTable) {
            String value_ = ((AnaRendForEachTable) _current).getExpression();
            _page.setGlobalOffset(((AnaRendForEachTable) _current).getExpressionOffset());
            _page.zeroOffset();
            ResultExpression resultExpression_ = ((AnaRendForEachTable) _current).getRes();
            extractAnon(_page, _int, _method, _type, value_, resultExpression_);
        }
        if (_current instanceof AnaRendForIterativeLoop) {
            _page.setGlobalOffset(((AnaRendForIterativeLoop) _current).getInitOffset());
            _page.zeroOffset();
            ResultExpression resultInit_ = ((AnaRendForIterativeLoop) _current).getResInit();
            extractAnon(_page, _int, _method, _type, ((AnaRendForIterativeLoop) _current).getInit(), resultInit_);
            _page.setGlobalOffset(((AnaRendForIterativeLoop) _current).getExpressionOffset());
            _page.zeroOffset();
            ResultExpression resultExp_ = ((AnaRendForIterativeLoop) _current).getResExp();
            extractAnon(_page, _int, _method, _type, ((AnaRendForIterativeLoop) _current).getExpression(), resultExp_);
            _page.setGlobalOffset(((AnaRendForIterativeLoop) _current).getStepOffset());
            _page.zeroOffset();
            ResultExpression resultStep_ = ((AnaRendForIterativeLoop) _current).getResStep();
            extractAnon(_page, _int, _method, _type, ((AnaRendForIterativeLoop) _current).getStep(), resultStep_);
        }
        if (_current instanceof AnaRendForMutableIterativeLoop) {
            _page.setGlobalOffset(((AnaRendForMutableIterativeLoop) _current).getInitOffset());
            _page.zeroOffset();
            ResultExpression resultInit_ = ((AnaRendForMutableIterativeLoop) _current).getResInit();
            extractAnon(_page, _int, _method, _type, ((AnaRendForMutableIterativeLoop) _current).getInit(), resultInit_);
            _page.setGlobalOffset(((AnaRendForMutableIterativeLoop) _current).getExpressionOffset());
            _page.zeroOffset();
            ResultExpression resultExp_ = ((AnaRendForMutableIterativeLoop) _current).getResExp();
            extractAnon(_page, _int, _method, _type, ((AnaRendForMutableIterativeLoop) _current).getExpression(), resultExp_);
            _page.setGlobalOffset(((AnaRendForMutableIterativeLoop) _current).getStepOffset());
            _page.zeroOffset();
            ResultExpression resultStep_ = ((AnaRendForMutableIterativeLoop) _current).getResStep();
            extractAnon(_page, _int, _method, _type, ((AnaRendForMutableIterativeLoop) _current).getStep(), resultStep_);
        }
        if (_current instanceof AnaRendThrowing) {
            _page.setGlobalOffset(((AnaRendThrowing) _current).getExpressionOffset());
            _page.zeroOffset();
            String value_ = ((AnaRendThrowing) _current).getExpression();
            ResultExpression resultExpression_ = ((AnaRendThrowing) _current).getRes();
            extractAnon(_page, _int, _method, _type, value_, resultExpression_);
        }
        stdElt(_analyzingDoc, _page, _int, _method, _type, _current);
        etl(_analyzingDoc, _page, _int, _method, _type, _current);
    }

    private static void stdElt(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendBlock _current) {
        if (_current instanceof AnaRendField) {
            _page.setGlobalOffset(((AnaRendField) _current).getPrepareOffset());
            _page.zeroOffset();
            String value_ = ((AnaRendField) _current).getPrepare();
            ResultExpression resultExpression_ = ((AnaRendField) _current).getRes();
            extractAnon(_page, _int, _method, _type, value_, resultExpression_);
        }
        if (_current instanceof AnaRendMessage) {
            String val_ = ((AnaRendMessage) _current).getElt().getAttribute(_analyzingDoc.getRendKeyWords().getAttrValue());
            StringMap<String> preQuick_ = AnaRendBlock.getPreQuick(val_, _analyzingDoc);
            if (!preQuick_.isEmpty()) {
                CustList<ResultExpression> resultExpressionList_ = ((AnaRendMessage) _current).getResultExpressionList();
                for (AnaRendElement e: ((AnaRendMessage) _current).getChildren()) {
                    int attributeDelimiter_ = e.getAttributeDelimiter(_analyzingDoc.getRendKeyWords().getAttrValue());
                    String value_ = e.getRead().getAttribute(_analyzingDoc.getRendKeyWords().getAttrValue());
                    if (e.getRead().hasAttribute(_analyzingDoc.getRendKeyWords().getAttrQuoted())) {
                        continue;
                    }
                    _page.setGlobalOffset(attributeDelimiter_);
                    _page.zeroOffset();
                    ResultExpression resultExpression_ = new ResultExpression();
                    resultExpressionList_.add(resultExpression_);
                    extractAnon(_page, _int, _method, _type, value_, resultExpression_);
                }
            }

        }
        sel(_analyzingDoc, _page, _int, _method, _type, _current);
        tex(_analyzingDoc, _page, _int, _method, _type, _current);
        if (_current instanceof AnaRendImport) {
            AnaRendImport i_ = (AnaRendImport) _current;
            String varValue_ = i_.getElt().getAttribute(_analyzingDoc.getRendKeyWords().getAttrPage());
            int offVarValue_ = i_.getAttributeDelimiter(_analyzingDoc.getRendKeyWords().getAttrPage());
            _page.setGlobalOffset(offVarValue_);
            _page.zeroOffset();
            radGene(_page,_int,_method,_type,i_.getResultExpressionPage(),varValue_,offVarValue_);
        }
        if (_current instanceof AnaRendText) {
            AnaRendText i_ = (AnaRendText) _current;
            String varValue_ = i_.getExpression();
            int offVarValue_ = i_.getExpressionOffset();
            _page.setGlobalOffset(offVarValue_);
            _page.zeroOffset();
            buildIdAna(varValue_,_page,_int,_method,_type, i_.getRes());
        }
    }

    private static void tex(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendBlock _current) {
        if (_current instanceof AnaRendTextArea) {
            AnaRendTextArea s_ = (AnaRendTextArea) _current;
            String varValue_ = s_.getElt().getAttribute(StringUtil.concat(_analyzingDoc.getPrefix(), _analyzingDoc.getRendKeyWords().getAttrVarValue()));
            int offVarValue_ = s_.getAttributeDelimiter(StringUtil.concat(_analyzingDoc.getPrefix(), _analyzingDoc.getRendKeyWords().getAttrVarValue()));
            _page.setGlobalOffset(offVarValue_);
            _page.zeroOffset();
            ResultExpression resultValue_ = s_.getResultInput().getResultExpressionValue();
            extractAnon(_page, _int, _method, _type, varValue_, resultValue_);
            String name_ = s_.getElt().getAttribute(_analyzingDoc.getRendKeyWords().getAttrName());
            int offName_ = s_.getAttributeDelimiter(_analyzingDoc.getRendKeyWords().getAttrName());
            _page.setGlobalOffset(offName_);
            _page.zeroOffset();
            ResultExpression resultName_ = s_.getResultInput().getResultExpressionRead();
            extractAnon(_page, _int, _method, _type, name_, resultName_);
            String id_ = s_.getElt().getAttribute(_analyzingDoc.getRendKeyWords().getAttrId());
            if (!id_.isEmpty()) {
                ResultExpression rId_ = new ResultExpression();
                int off_ = s_.getAttributeDelimiter(_analyzingDoc.getRendKeyWords().getAttrId());
                s_.getAttributesText().put(_analyzingDoc.getRendKeyWords().getAttrId(),rId_);
                _page.setGlobalOffset(off_);
                _page.zeroOffset();
                radGene(_page,_int,_method,_type,rId_,id_,off_);
            }
            String prefixWrite_ = _analyzingDoc.getPrefix();
            String prefGr_ = StringUtil.concat(prefixWrite_, _analyzingDoc.getRendKeyWords().getAttrGroupId());
            String groupId_ = s_.getElt().getAttribute(prefGr_);
            int offGrId_ = s_.getAttributeDelimiter(prefGr_);
            if (!groupId_.isEmpty()) {
                ResultExpression rId_ = new ResultExpression();
                s_.getAttributesText().put(prefGr_,rId_);
                _page.setGlobalOffset(offGrId_);
                _page.zeroOffset();
                radGene(_page,_int,_method,_type,rId_,groupId_,offGrId_);
            }
            String rows_ = s_.getElt().getAttribute(_analyzingDoc.getRendKeyWords().getAttrRows());
            int rowsGrId_ = s_.getAttributeDelimiter(_analyzingDoc.getRendKeyWords().getAttrRows());
            if (!rows_.isEmpty()) {
                ResultExpression rId_ = new ResultExpression();
                s_.getAttributes().addEntry(_analyzingDoc.getRendKeyWords().getAttrRows(),rId_);
                _page.setGlobalOffset(rowsGrId_);
                _page.zeroOffset();
                radGene(_page,_int,_method,_type,rId_,groupId_,rowsGrId_);
            }
            String cols_ = s_.getElt().getAttribute(_analyzingDoc.getRendKeyWords().getAttrCols());
            int colsGrId_ = s_.getAttributeDelimiter(_analyzingDoc.getRendKeyWords().getAttrCols());
            if (!cols_.isEmpty()) {
                ResultExpression rId_ = new ResultExpression();
                s_.getAttributes().addEntry(_analyzingDoc.getRendKeyWords().getAttrCols(),rId_);
                _page.setGlobalOffset(colsGrId_);
                _page.zeroOffset();
                radGene(_page,_int,_method,_type,rId_,groupId_,colsGrId_);
            }
        }
    }

    private static void sel(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendBlock _current) {
        if (_current instanceof AnaRendSelect) {
            AnaRendSelect s_ = (AnaRendSelect) _current;
            String map_ = s_.getElt().getAttribute(_analyzingDoc.getRendKeyWords().getAttrMap());
            int offMap_ = s_.getAttributeDelimiter(_analyzingDoc.getRendKeyWords().getAttrMap());
            _page.setGlobalOffset(offMap_);
            _page.zeroOffset();
            ResultExpression resultExpression_ = s_.getResultExpressionMap();
            extractAnon(_page, _int, _method, _type, map_, resultExpression_);
            String varValue_ = s_.getElt().getAttribute(_analyzingDoc.getRendKeyWords().getAttrVarValue());
            int offVarValue_ = s_.getAttributeDelimiter(_analyzingDoc.getRendKeyWords().getAttrVarValue());
            _page.setGlobalOffset(offVarValue_);
            _page.zeroOffset();
            ResultExpression resultValue_ = s_.getResultInput().getResultExpressionValue();
            extractAnon(_page, _int, _method, _type, varValue_, resultValue_);
            String name_ = s_.getElt().getAttribute(_analyzingDoc.getRendKeyWords().getAttrName());
            int offName_ = s_.getAttributeDelimiter(_analyzingDoc.getRendKeyWords().getAttrName());
            _page.setGlobalOffset(offName_);
            _page.zeroOffset();
            ResultExpression resultName_ = s_.getResultInput().getResultExpressionRead();
            extractAnon(_page, _int, _method, _type, name_, resultName_);
            String id_ = s_.getElt().getAttribute(_analyzingDoc.getRendKeyWords().getAttrId());
            if (!id_.isEmpty()) {
                ResultExpression rId_ = new ResultExpression();
                int off_ = s_.getAttributeDelimiter(_analyzingDoc.getRendKeyWords().getAttrId());
                s_.getAttributesText().put(_analyzingDoc.getRendKeyWords().getAttrId(),rId_);
                _page.setGlobalOffset(off_);
                _page.zeroOffset();
                radGene(_page, _int, _method, _type, rId_, id_, off_);
            }
            String prefixWrite_ = _analyzingDoc.getPrefix();
            String prefGr_ = StringUtil.concat(prefixWrite_, _analyzingDoc.getRendKeyWords().getAttrGroupId());
            String groupId_ = s_.getElt().getAttribute(prefGr_);
            if (!groupId_.isEmpty()) {
                ResultExpression rId_ = new ResultExpression();
                int off_ = s_.getAttributeDelimiter(prefGr_);
                s_.getAttributesText().put(prefGr_,rId_);
                _page.setGlobalOffset(off_);
                _page.zeroOffset();
                radGene(_page, _int, _method, _type, rId_, groupId_, off_);
            }
            String rows_ = s_.getElt().getAttribute(_analyzingDoc.getRendKeyWords().getAttrRows());
            int rowsGrId_ = s_.getAttributeDelimiter(_analyzingDoc.getRendKeyWords().getAttrRows());
            if (!rows_.isEmpty()) {
                ResultExpression rId_ = new ResultExpression();
                s_.getAttributes().addEntry(_analyzingDoc.getRendKeyWords().getAttrRows(),rId_);
                _page.setGlobalOffset(rowsGrId_);
                _page.zeroOffset();
                radGene(_page, _int, _method, _type, rId_, rows_, rowsGrId_);
            }
        }
    }

    private static void etl(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendBlock _current) {
        if (!(_current instanceof AnaRendElement)) {
            return;
        }
        anc(_analyzingDoc, _page, _int, _method, _type, _current);
        if (_current instanceof AnaRendSubmit) {
            AnaRendSubmit s_ = (AnaRendSubmit) _current;
            for (String a: s_.titles(_analyzingDoc)) {
                ResultExpression r_ = new ResultExpression();
                s_.getOpExp().addEntry(a,r_);
                rad(_page,_int,_method,_type,s_,r_,a);
            }
        }
        if (_current instanceof AnaRendLink) {
            AnaRendLink s_ = (AnaRendLink) _current;
            for (String a: s_.titles(_analyzingDoc)) {
                ResultExpression r_ = new ResultExpression();
                s_.getOpExpTitle().addEntry(a,r_);
                rad(_page,_int,_method,_type,s_,r_,a);
            }
        }
        if (_current instanceof AnaRendInput) {
            AnaRendInput s_ = (AnaRendInput) _current;
            String varValue_ = s_.getRead().getAttribute(StringUtil.concat(_analyzingDoc.getPrefix(), _analyzingDoc.getRendKeyWords().getAttrVarValue()));
            int offVarValue_ = s_.getAttributeDelimiter(StringUtil.concat(_analyzingDoc.getPrefix(), _analyzingDoc.getRendKeyWords().getAttrVarValue()));
            _page.setGlobalOffset(offVarValue_);
            _page.zeroOffset();
            ResultExpression resultValue_ = s_.getResultInput().getResultExpressionValue();
            extractAnon(_page,_int,_method,_type,varValue_,resultValue_);
            String name_ = s_.getRead().getAttribute(_analyzingDoc.getRendKeyWords().getAttrName());
            int offName_ = s_.getAttributeDelimiter(_analyzingDoc.getRendKeyWords().getAttrName());
            _page.setGlobalOffset(offName_);
            _page.zeroOffset();
            ResultExpression resultName_ = s_.getResultInput().getResultExpressionRead();
            extractAnon(_page,_int,_method,_type,name_,resultName_);
        }
        if (_current instanceof AnaRendImg) {
            AnaRendImg i_ = (AnaRendImg) _current;
            rad(_page,_int,_method,_type,i_,i_.getResultExpressionSrc(),_analyzingDoc.getRendKeyWords().getAttrSrc());
        }
        if (_current instanceof AnaRendSpan) {
            AnaRendSpan i_ = (AnaRendSpan) _current;
            rad(_page, _int, _method, _type, i_, i_.getResultExpressionFor(),StringUtil.concat(_analyzingDoc.getPrefix(), _analyzingDoc.getRendKeyWords().getAttrFor()));
        }
        if (_current instanceof AnaRendRadio) {
            AnaRendRadio i_ = (AnaRendRadio) _current;
            rad(_page, _int, _method, _type, i_, i_.getExpRad(), _analyzingDoc.getRendKeyWords().getAttrNr());
        }
        procElt((AnaRendElement) _current, _int, _method, _analyzingDoc, _page, _type);
    }

    private static void rad(AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendElement _elt, ResultExpression _expRad, String _attrNr) {
        String varValue_ = _elt.getRead().getAttribute(_attrNr);
        int offVarValue_ = _elt.getAttributeDelimiter(_attrNr);
        radGene(_page, _int, _method, _type, _expRad, varValue_, offVarValue_);
    }

    private static void radGene(AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, ResultExpression _expRad, String _varValue, int _offVarValue) {
        if (!_varValue.trim().isEmpty()) {
            _page.setGlobalOffset(_offVarValue);
            _page.zeroOffset();
            extractAnon(_page, _int, _method, _type, _varValue, _expRad);
        }
    }

    private static void anc(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendBlock _current) {
        if (_current instanceof AnaRendTitledAnchor) {
            AnaRendTitledAnchor s_ = (AnaRendTitledAnchor) _current;
            for (String a: s_.titles(_analyzingDoc)) {
                ResultExpression r_ = new ResultExpression();
                s_.getOpExpTitle().addEntry(a,r_);
                rad(_page,_int,_method,_type,s_,r_,a);
            }
            lk(_analyzingDoc, _page, _int, _method, _type, s_, s_.getRes().getResults());
        }
        if (_current instanceof AnaRendAnchor) {
            AnaRendAnchor s_ = (AnaRendAnchor) _current;
            lk(_analyzingDoc, _page, _int, _method, _type, s_, s_.getRes().getResults());
        }
        if (_current instanceof AnaRendForm) {
            AnaRendForm s_ = (AnaRendForm) _current;
            lk(_analyzingDoc, _page, _int, _method, _type, s_, s_.getRes().getResults());
        }
    }

    private static void lk(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendElement _s, StringMap<ResultExpression> _results) {
        String attr_ = _s.getRead().getAttribute(StringUtil.concat(_analyzingDoc.getPrefix(), _analyzingDoc.getRendKeyWords().getAttrCommand()));
        if (attr_.startsWith("$")) {
            for (String a: _s.params(_analyzingDoc)) {
                ResultExpression r_ = new ResultExpression();
                _results.addEntry(a,r_);
                rad(_page,_int,_method,_type,_s,r_,a);
            }
        }
    }

    private static void procElt(AnaRendElement _inst, IntermediaryResults _int, AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page, RootBlock _type) {
        String prefixWrite_ = _anaDoc.getPrefix();
        String prefGr_ = StringUtil.concat(prefixWrite_, _anaDoc.getRendKeyWords().getAttrGroupId());
        String id_ = _inst.getRead().getAttribute(_anaDoc.getRendKeyWords().getAttrId());
        if (!id_.isEmpty()) {
            ResultExpression r_ = new ResultExpression();
            _inst.getAttributesText().put(_anaDoc.getRendKeyWords().getAttrId(),r_);
            rad(_page,_int,_doc,_type,_inst,r_,_anaDoc.getRendKeyWords().getAttrId());
        }
        String groupId_ = _inst.getRead().getAttribute(prefGr_);
        if (!groupId_.isEmpty()) {
            ResultExpression r_ = new ResultExpression();
            _inst.getAttributesText().put(prefGr_,r_);
            rad(_page,_int,_doc,_type,_inst,r_,prefGr_);
        }
        StringList list_ = _inst.processListAttributes(_doc, _anaDoc, _page);
        for (String a: list_) {
            String attr_ = _inst.getRead().getAttribute(a);
            if (attr_.trim().isEmpty()) {
                continue;
            }
            ResultExpression r_ = new ResultExpression();
            _inst.getAttributes().addEntry(a,r_);
            rad(_page,_int,_doc,_type,_inst,r_,a);
        }
    }

    private static void extractAnon(AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, String _value, ResultExpression _resultExpression) {
        ElRetrieverAnonymous.commonCheckQuick(_value, 0, _page, _resultExpression);
        SplitExpressionUtil.feed(_resultExpression,_int, _type, _method);
    }

    public static void buildIdAna(String _expression, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, ResultText _exp) {
        int length_ = _expression.length();
        boolean escaped_ = false;
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < length_) {
            char cur_ = _expression.charAt(i_);
            if (escaped_) {
                if (cur_ == ESCAPED) {
                    escaped_ = false;
                    i_++;
                    continue;
                }
                if (cur_ == LEFT_EL) {
                    escaped_ = false;
                    i_++;
                    continue;
                }
                if (cur_ == RIGHT_EL) {
                    escaped_ = false;
                    i_++;
                    continue;
                }
                return;
            }
            if (cur_ == ESCAPED) {
                escaped_ = true;
                i_++;
                continue;
            }
            if (cur_ == LEFT_EL) {
                i_++;
                if (i_ >= length_ || _expression.charAt(i_) == RIGHT_EL) {
                    return;
                }
                int next_ = ElRetrieverAnonymous.commonCheckQuick(_expression, i_, _page, _exp.getResultExpression());
                SplitExpressionUtil.feed(_exp.getResultExpression(),_int, _type, _method);
                i_ = next_+1;
                continue;
            }
            if (cur_ == RIGHT_EL){
                return;
            }
            i_++;
        }
    }


}
