package code.formathtml.analyze.syntax;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.files.StringCommentIteration;
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
                } else {
                    current_ = parent_;
                }
            }
        }
    }

    private static void elt(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendBlock _current) {
        if (_current instanceof AnaRendLine) {
            _page.zeroOffset();
            String value_ = ((AnaRendLine) _current).getExpression();
            ResultExpression resultExpression_ = ((AnaRendLine) _current).getRes();
            resultExpression_.partsAbsol(_current.getStringPartsElt(_analyzingDoc.getRendKeyWords().getAttrValue()));
            resultExpression_.setAnalyzedString(value_);
            resultExpression_.setSumOffset(((AnaRendLine) _current).getExpressionOffset());
            extractAnon(_page, _int, _method, _type, resultExpression_);
        }
        if (_current instanceof AnaRendCaseCondition) {
            if (((AnaRendCaseCondition) _current).getVariableName().isEmpty()) {
                String value_ = ((AnaRendCaseCondition) _current).getValue();
                _page.zeroOffset();
                ResultExpression resultExpression_ = ((AnaRendCaseCondition) _current).getRes();
                resultExpression_.partsAbsol(_current.getStringPartsElt(_analyzingDoc.getRendKeyWords().getAttrValue()));
                resultExpression_.setAnalyzedString(value_);
                resultExpression_.setSumOffset(((AnaRendCaseCondition) _current).getValueOffset());
                extractAnon(_page, _int, _method, _type, resultExpression_);
            } else {
                if (((AnaRendCaseCondition) _current).isCaseWhen()) {
                    String substring_ = ((AnaRendCaseCondition) _current).getValue();
                    _page.zeroOffset();
                    ResultExpression resultExpression_ = ((AnaRendCaseCondition) _current).getRes();
                    resultExpression_.partsAbsol(_current.getStringPartsElt(_analyzingDoc.getRendKeyWords().getAttrValue()));
                    resultExpression_.setAnalyzedString(substring_.trim());
                    resultExpression_.setSumOffset(((AnaRendCaseCondition) _current).getValueOffset());
                    extractAnon(_page, _int, _method, _type, resultExpression_);
                }
            }
        }
        if (_current instanceof AnaRendSwitchBlock) {
            String value_ = ((AnaRendSwitchBlock) _current).getValue();
            _page.zeroOffset();
            ResultExpression resultExpression_ = ((AnaRendSwitchBlock) _current).getRes();
            resultExpression_.partsAbsol(_current.getStringPartsElt(_analyzingDoc.getRendKeyWords().getAttrValue()));
            resultExpression_.setAnalyzedString(value_);
            resultExpression_.setSumOffset(((AnaRendSwitchBlock) _current).getValueOffset());
            extractAnon(_page, _int, _method, _type, resultExpression_);
        }
        if (_current instanceof AnaRendCondition) {
            String value_ = ((AnaRendCondition) _current).getCondition();
            _page.zeroOffset();
            ResultExpression resultExpression_ = ((AnaRendCondition) _current).getRes();
            resultExpression_.partsAbsol(_current.getStringPartsElt(_analyzingDoc.getRendKeyWords().getAttrCondition()));
            resultExpression_.setAnalyzedString(value_);
            resultExpression_.setSumOffset(((AnaRendCondition) _current).getConditionOffset());
            extractAnon(_page, _int, _method, _type, resultExpression_);
        }
        if (_current instanceof AnaRendForEachLoop) {
            String value_ = ((AnaRendForEachLoop) _current).getExpression();
            _page.zeroOffset();
            ResultExpression resultExpression_ = ((AnaRendForEachLoop) _current).getRes();
            resultExpression_.partsAbsol(_current.getStringPartsElt(_analyzingDoc.getRendKeyWords().getAttrList()));
            resultExpression_.setAnalyzedString(value_);
            resultExpression_.setSumOffset(((AnaRendForEachLoop) _current).getExpressionOffset());
            extractAnon(_page, _int, _method, _type, resultExpression_);
        }
        if (_current instanceof AnaRendForEachTable) {
            String value_ = ((AnaRendForEachTable) _current).getExpression();
            _page.zeroOffset();
            ResultExpression resultExpression_ = ((AnaRendForEachTable) _current).getRes();
            resultExpression_.partsAbsol(_current.getStringPartsElt(_analyzingDoc.getRendKeyWords().getAttrMap()));
            resultExpression_.setAnalyzedString(value_);
            resultExpression_.setSumOffset(((AnaRendForEachTable) _current).getExpressionOffset());
            extractAnon(_page, _int, _method, _type, resultExpression_);
        }
        if (_current instanceof AnaRendForIterativeLoop) {
            _page.zeroOffset();
            ResultExpression resultInit_ = ((AnaRendForIterativeLoop) _current).getResInit();
            resultInit_.partsAbsol(_current.getStringPartsElt(_analyzingDoc.getRendKeyWords().getAttrFrom()));
            resultInit_.setAnalyzedString(((AnaRendForIterativeLoop) _current).getInit());
            resultInit_.setSumOffset(((AnaRendForIterativeLoop) _current).getInitOffset());
            extractAnon(_page, _int, _method, _type, resultInit_);
            _page.zeroOffset();
            ResultExpression resultExp_ = ((AnaRendForIterativeLoop) _current).getResExp();
            resultExp_.partsAbsol(_current.getStringPartsElt(_analyzingDoc.getRendKeyWords().getAttrTo()));
            resultExp_.setAnalyzedString(((AnaRendForIterativeLoop) _current).getExpression());
            resultExp_.setSumOffset(((AnaRendForIterativeLoop) _current).getExpressionOffset());
            extractAnon(_page, _int, _method, _type, resultExp_);
            _page.zeroOffset();
            ResultExpression resultStep_ = ((AnaRendForIterativeLoop) _current).getResStep();
            resultStep_.partsAbsol(_current.getStringPartsElt(_analyzingDoc.getRendKeyWords().getAttrStep()));
            resultStep_.setAnalyzedString(((AnaRendForIterativeLoop) _current).getStep());
            resultStep_.setSumOffset(((AnaRendForIterativeLoop) _current).getStepOffset());
            extractAnon(_page, _int, _method, _type, resultStep_);
        }
        if (_current instanceof AnaRendForMutableIterativeLoop) {
            _page.zeroOffset();
            ResultExpression resultInit_ = ((AnaRendForMutableIterativeLoop) _current).getResInit();
            resultInit_.partsAbsol(_current.getStringPartsElt(_analyzingDoc.getRendKeyWords().getAttrInit()));
            resultInit_.setAnalyzedString(((AnaRendForMutableIterativeLoop) _current).getInit());
            resultInit_.setSumOffset(((AnaRendForMutableIterativeLoop) _current).getInitOffset());
            extractAnon(_page, _int, _method, _type, resultInit_);
            _page.zeroOffset();
            ResultExpression resultExp_ = ((AnaRendForMutableIterativeLoop) _current).getResExp();
            resultExp_.partsAbsol(_current.getStringPartsElt(_analyzingDoc.getRendKeyWords().getAttrCondition()));
            resultExp_.setAnalyzedString(((AnaRendForMutableIterativeLoop) _current).getExpression());
            resultExp_.setSumOffset(((AnaRendForMutableIterativeLoop) _current).getExpressionOffset());
            extractAnon(_page, _int, _method, _type, resultExp_);
            _page.zeroOffset();
            ResultExpression resultStep_ = ((AnaRendForMutableIterativeLoop) _current).getResStep();
            resultStep_.partsAbsol(_current.getStringPartsElt(_analyzingDoc.getRendKeyWords().getAttrStep()));
            resultStep_.setAnalyzedString(((AnaRendForMutableIterativeLoop) _current).getStep());
            resultStep_.setSumOffset(((AnaRendForMutableIterativeLoop) _current).getStepOffset());
            extractAnon(_page, _int, _method, _type, resultStep_);
        }
        if (_current instanceof AnaRendThrowing) {
            _page.zeroOffset();
            String value_ = ((AnaRendThrowing) _current).getExpression();
            ResultExpression resultExpression_ = ((AnaRendThrowing) _current).getRes();
            resultExpression_.partsAbsol(_current.getStringPartsElt(_analyzingDoc.getRendKeyWords().getAttrValue()));
            resultExpression_.setAnalyzedString(value_);
            resultExpression_.setSumOffset(((AnaRendThrowing) _current).getExpressionOffset());
            extractAnon(_page, _int, _method, _type, resultExpression_);
        }
        stdElt(_analyzingDoc, _page, _int, _method, _type, _current);
        etl(_analyzingDoc, _page, _int, _method, _type, _current);
    }

    private static void stdElt(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendBlock _current) {
        if (_current instanceof AnaRendField) {
            _page.zeroOffset();
            String value_ = ((AnaRendField) _current).getPrepare();
            ResultExpression resultExpression_ = ((AnaRendField) _current).getRes();
            resultExpression_.partsAbsol(_current.getStringPartsElt(_analyzingDoc.getRendKeyWords().getAttrPrepare()));
            resultExpression_.setAnalyzedString(value_);
            resultExpression_.setSumOffset(((AnaRendField) _current).getPrepareOffset());
            extractAnon(_page, _int, _method, _type, resultExpression_);
        }
        if (_current instanceof AnaRendMessage) {
            String val_ = ((AnaRendMessage) _current).getElt().getAttribute(_analyzingDoc.getRendKeyWords().getAttrValue());
            StringMap<String> preQuick_ = AnaRendBlock.getPreQuick(val_, _analyzingDoc);
            if (!preQuick_.isEmpty()) {
                CustList<ResultExpression> resultExpressionList_ = ((AnaRendMessage) _current).getResultExpressionList();
                for (AnaRendElement e: ((AnaRendMessage) _current).getChildren()) {
                    String attrValue_ = _analyzingDoc.getRendKeyWords().getAttrValue();
                    int attributeDelimiter_ = e.getAttributeDelimiter(attrValue_);
                    String value_ = e.getRead().getAttribute(attrValue_);
                    if (e.getRead().hasAttribute(_analyzingDoc.getRendKeyWords().getAttrQuoted())) {
                        continue;
                    }
                    _page.zeroOffset();
                    ResultExpression resultExpression_ = new ResultExpression();
                    resultExpression_.partsAbsol(e.getStringPartsElt(attrValue_));
                    resultExpression_.setAnalyzedString(value_);
                    resultExpression_.setSumOffset(attributeDelimiter_);
                    resultExpressionList_.add(resultExpression_);
                    extractAnon(_page, _int, _method, _type, resultExpression_);
                }
            }

        }
        sel(_analyzingDoc, _page, _int, _method, _type, _current);
        tex(_analyzingDoc, _page, _int, _method, _type, _current);
        if (_current instanceof AnaRendImport) {
            AnaRendImport i_ = (AnaRendImport) _current;
            String vPage_ = _analyzingDoc.getRendKeyWords().getAttrPage();
            String varValue_ = i_.getElt().getAttribute(vPage_);
            int offVarValue_ = i_.getAttributeDelimiter(vPage_);
            _page.zeroOffset();
            i_.getResultExpressionPage().partsAbsol(i_.getStringPartsElt(vPage_));
            i_.getResultExpressionPage().setAnalyzedString(varValue_);
            i_.getResultExpressionPage().setSumOffset(offVarValue_);
            radGene(_page,_int,_method,_type,i_.getResultExpressionPage(),varValue_,offVarValue_);
        }
        if (_current instanceof AnaRendText) {
            AnaRendText i_ = (AnaRendText) _current;
            String varValue_ = i_.getExpression();
            int offVarValue_ = i_.getExpressionOffset();
            _page.zeroOffset();
            i_.getRes().getResultExpression().partsAbsol(i_.getStringPartsText());
            i_.getRes().getResultExpression().setAnalyzedString(varValue_);
            i_.getRes().getResultExpression().setSumOffset(offVarValue_);
            buildIdAna(varValue_,_page,_int,_method,_type, i_.getRes());
        }
    }

    private static void tex(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendBlock _current) {
        if (_current instanceof AnaRendTextArea) {
            AnaRendTextArea s_ = (AnaRendTextArea) _current;
            String vVarVal_ = StringUtil.concat(_analyzingDoc.getPrefix(), _analyzingDoc.getRendKeyWords().getAttrVarValue());
            value(_page, _int, _method, _type, s_, vVarVal_);
            read(_analyzingDoc, _page, _int, _method, _type, s_);
        }
    }

    private static void sel(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendBlock _current) {
        if (_current instanceof AnaRendSelect) {
            AnaRendSelect s_ = (AnaRendSelect) _current;
            String vMap_ = _analyzingDoc.getRendKeyWords().getAttrMap();
            String map_ = s_.getElt().getAttribute(vMap_);
            int offMap_ = s_.getAttributeDelimiter(vMap_);
            _page.zeroOffset();
            ResultExpression resultExpression_ = s_.getResultExpressionMap();
            resultExpression_.partsAbsol(s_.getStringPartsElt(vMap_));
            resultExpression_.setAnalyzedString(map_);
            resultExpression_.setSumOffset(offMap_);
            extractAnon(_page, _int, _method, _type, resultExpression_);
            String vVarVal_ = _analyzingDoc.getRendKeyWords().getAttrVarValue();
            value(_page, _int, _method, _type, s_, vVarVal_);
            read(_analyzingDoc, _page, _int, _method, _type, s_);
        }
    }

    private static void value(AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendInputInt _i, String _vVarVal) {
        String varValue_ = _i.getElt().getAttribute(_vVarVal);
        int offVarValue_ = _i.getAttributeDelimiter(_vVarVal);
        _page.zeroOffset();
        ResultExpression resultValue_ = _i.getResultInput().getResultExpressionValue();
        resultValue_.partsAbsol(_i.getStringPartsElt(_vVarVal));
        resultValue_.setAnalyzedString(varValue_);
        resultValue_.setSumOffset(offVarValue_);
        extractAnon(_page, _int, _method, _type, resultValue_);
    }

    private static void read(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendInputInt _i) {
        String vName_ = _analyzingDoc.getRendKeyWords().getAttrName();
        String name_ = _i.getElt().getAttribute(vName_);
        int offName_ = _i.getAttributeDelimiter(vName_);
        _page.zeroOffset();
        ResultExpression resultName_ = _i.getResultInput().getResultExpressionRead();
        resultName_.partsAbsol(_i.getStringPartsElt(vName_));
        resultName_.setAnalyzedString(name_);
        resultName_.setSumOffset(offName_);
        extractAnon(_page, _int, _method, _type, resultName_);
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
            String vVar_ = StringUtil.concat(_analyzingDoc.getPrefix(), _analyzingDoc.getRendKeyWords().getAttrVarValue());
            String varValue_ = s_.getRead().getAttribute(vVar_);
            int offVarValue_ = s_.getAttributeDelimiter(vVar_);
            _page.zeroOffset();
            ResultExpression resultValue_ = s_.getResultInput().getResultExpressionValue();
            resultValue_.partsAbsol(s_.getStringPartsElt(vVar_));
            resultValue_.setAnalyzedString(varValue_);
            resultValue_.setSumOffset(offVarValue_);
            extractAnon(_page,_int,_method,_type, resultValue_);
            read(_analyzingDoc,_page,_int,_method,_type,s_);
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
        _expRad.partsAbsol(_elt.getStringPartsElt(_attrNr));
        radGene(_page, _int, _method, _type, _expRad, varValue_, offVarValue_);
    }

    private static void radGene(AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, ResultExpression _expRad, String _varValue, int _offVarValue) {
        if (!_varValue.trim().isEmpty()) {
            _page.zeroOffset();
            _expRad.setAnalyzedString(_varValue);
            _expRad.setSumOffset(_offVarValue);
            extractAnon(_page, _int, _method, _type, _expRad);
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

    private static void extractAnon(AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, ResultExpression _resultExpression) {
        _page.setSumOffset(_resultExpression.getSumOffset());
        ElRetrieverAnonymous.commonCheckQuick(0, _page, _resultExpression);
        SplitExpressionUtil.feed(_resultExpression,_int, _type, _method);
    }
    public static StringCommentIteration itText(int _offset,String _expression, AnalyzedPageEl _page) {
        StringCommentIteration strIt_ = new StringCommentIteration(0,_offset);
        StringBuilder str_ = new StringBuilder();
        int length_ = _expression.length();
        boolean escaped_ = false;
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < length_) {
            char cur_ = _expression.charAt(i_);
            if (escaped_) {
                if (ResultText.isDel(cur_)) {
                    escaped_ = false;
                    i_++;
                    continue;
                }
                return strIt_;
            }
            if (cur_ == ResultText.ESCAPED) {
                escaped_ = true;
                i_++;
            } else if (cur_ == ResultText.LEFT_EL) {
                i_++;
                if (ResultText.isRightBoundOrFar(_expression,i_)) {
                    return strIt_;
                }
                strIt_.setIndex(i_);
                appendChars(_expression, _page, strIt_, str_);
                i_ = strIt_.getIndex()+1;
            } else if (cur_ == ResultText.RIGHT_EL){
                return strIt_;
            } else {
                i_++;
            }
        }
        return strIt_;
    }

    private static void appendChars(String _expression, AnalyzedPageEl _page, StringCommentIteration _t, StringBuilder _str) {
        int length_ = _expression.length();
        int count_ = 0;
        while(_t.getIndex() < length_) {
            char inner_ = _expression.charAt(_t.getIndex());
            if (_t.code(_expression, _page.getComments(), _str)) {
                if (inner_ == ResultText.LEFT_EL) {
                    count_++;
                }
                if (inner_ == ResultText.RIGHT_EL) {
                    if (count_ == 0) {
                        break;
                    }
                    count_--;
                }
                _t.appendCode(_expression, _str);
            }
        }
    }

    public static void buildIdAna(String _expression, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, ResultText _exp) {
        _page.setSumOffset(_exp.getResultExpression().getSumOffset());
        int length_ = _expression.length();
        boolean escapedId_ = false;
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < length_) {
            char cur_ = _expression.charAt(i_);
            if (escapedId_) {
                if (ResultText.isDel(cur_)) {
                    escapedId_ = false;
                    i_++;
                    continue;
                }
                return;
            }
            if (cur_ == ResultText.ESCAPED) {
                escapedId_ = true;
                i_++;
            } else if (cur_ == ResultText.LEFT_EL) {
                i_++;
                if (ResultText.isRightBoundOrFar(_expression,i_)) {
                    return;
                }
                int next_ = ElRetrieverAnonymous.commonCheckQuick(i_, _page, _exp.getResultExpression());
                SplitExpressionUtil.feed(_exp.getResultExpression(),_int, _type, _method);
                i_ = next_+1;
            } else if (cur_ == ResultText.RIGHT_EL){
                return;
            } else {
                i_++;
            }
        }
    }


}
