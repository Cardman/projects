package code.formathtml.analyze.syntax;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.instr.ElRetrieverAnonymous;
import code.expressionlanguage.analyze.syntax.IntermediaryResults;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.syntax.SplitExpressionUtil;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.*;
import code.formathtml.analyze.blocks.AnaRendParentBlock;
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

    private static void elt(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendBlock current_) {
        if (current_ instanceof AnaRendLine) {
            _page.setGlobalOffset(((AnaRendLine) current_).getExpressionOffset());
            _page.zeroOffset();
            String value_ = ((AnaRendLine) current_).getExpression();
            ResultExpression resultExpression_ = ((AnaRendLine) current_).getRes();
            extractAnon(_page, _int, _method, _type, value_, resultExpression_);
        }
        if (current_ instanceof AnaRendCaseCondition) {
            String value_ = ((AnaRendCaseCondition) current_).getValue();
            if (((AnaRendCaseCondition) current_).getVariableName().isEmpty()) {
                _page.setGlobalOffset(((AnaRendCaseCondition) current_).getValueOffset());
                _page.zeroOffset();
                ResultExpression resultExpression_ = ((AnaRendCaseCondition) current_).getRes();
                extractAnon(_page, _int, _method, _type, value_, resultExpression_);
            } else {
                if (((AnaRendCaseCondition) current_).isCaseWhen()) {
                    String substring_ = ((AnaRendCaseCondition) current_).getValue();
                    _page.setGlobalOffset(((AnaRendCaseCondition) current_).getValueOffset());
                    _page.zeroOffset();
                    ResultExpression resultExpression_ = ((AnaRendCaseCondition) current_).getRes();
                    extractAnon(_page, _int, _method, _type, substring_.trim(), resultExpression_);
                }
            }
        }
        if (current_ instanceof AnaRendSwitchBlock) {
            String value_ = ((AnaRendSwitchBlock) current_).getValue();
            _page.setGlobalOffset(((AnaRendSwitchBlock) current_).getValueOffset());
            _page.zeroOffset();
            ResultExpression resultExpression_ = ((AnaRendSwitchBlock) current_).getRes();
            extractAnon(_page, _int, _method, _type, value_, resultExpression_);
        }
        if (current_ instanceof AnaRendCondition) {
            String value_ = ((AnaRendCondition) current_).getCondition();
            _page.setGlobalOffset(((AnaRendCondition) current_).getConditionOffset());
            _page.zeroOffset();
            ResultExpression resultExpression_ = ((AnaRendCondition) current_).getRes();
            extractAnon(_page, _int, _method, _type, value_, resultExpression_);
        }
        if (current_ instanceof AnaRendForEachLoop) {
            String value_ = ((AnaRendForEachLoop) current_).getExpression();
            _page.setGlobalOffset(((AnaRendForEachLoop) current_).getExpressionOffset());
            _page.zeroOffset();
            ResultExpression resultExpression_ = ((AnaRendForEachLoop) current_).getRes();
            extractAnon(_page, _int, _method, _type, value_, resultExpression_);
        }
        if (current_ instanceof AnaRendForEachTable) {
            String value_ = ((AnaRendForEachTable) current_).getExpression();
            _page.setGlobalOffset(((AnaRendForEachTable) current_).getExpressionOffset());
            _page.zeroOffset();
            ResultExpression resultExpression_ = ((AnaRendForEachTable) current_).getRes();
            extractAnon(_page, _int, _method, _type, value_, resultExpression_);
        }
        if (current_ instanceof AnaRendForIterativeLoop) {
            _page.setGlobalOffset(((AnaRendForIterativeLoop) current_).getInitOffset());
            _page.zeroOffset();
            ResultExpression resultInit_ = ((AnaRendForIterativeLoop) current_).getResInit();
            extractAnon(_page, _int, _method, _type, ((AnaRendForIterativeLoop) current_).getInit(), resultInit_);
            _page.setGlobalOffset(((AnaRendForIterativeLoop) current_).getExpressionOffset());
            _page.zeroOffset();
            ResultExpression resultExp_ = ((AnaRendForIterativeLoop) current_).getResExp();
            extractAnon(_page, _int, _method, _type, ((AnaRendForIterativeLoop) current_).getExpression(), resultExp_);
            _page.setGlobalOffset(((AnaRendForIterativeLoop) current_).getStepOffset());
            _page.zeroOffset();
            ResultExpression resultStep_ = ((AnaRendForIterativeLoop) current_).getResStep();
            extractAnon(_page, _int, _method, _type, ((AnaRendForIterativeLoop) current_).getStep(), resultStep_);
        }
        if (current_ instanceof AnaRendForMutableIterativeLoop) {
            _page.setGlobalOffset(((AnaRendForMutableIterativeLoop) current_).getInitOffset());
            _page.zeroOffset();
            ResultExpression resultInit_ = ((AnaRendForMutableIterativeLoop) current_).getResInit();
            extractAnon(_page, _int, _method, _type, ((AnaRendForMutableIterativeLoop) current_).getInit(), resultInit_);
            _page.setGlobalOffset(((AnaRendForMutableIterativeLoop) current_).getExpressionOffset());
            _page.zeroOffset();
            ResultExpression resultExp_ = ((AnaRendForMutableIterativeLoop) current_).getResExp();
            extractAnon(_page, _int, _method, _type, ((AnaRendForMutableIterativeLoop) current_).getExpression(), resultExp_);
            _page.setGlobalOffset(((AnaRendForMutableIterativeLoop) current_).getStepOffset());
            _page.zeroOffset();
            ResultExpression resultStep_ = ((AnaRendForMutableIterativeLoop) current_).getResStep();
            extractAnon(_page, _int, _method, _type, ((AnaRendForMutableIterativeLoop) current_).getStep(), resultStep_);
        }
        if (current_ instanceof AnaRendThrowing) {
            _page.setGlobalOffset(((AnaRendThrowing) current_).getExpressionOffset());
            _page.zeroOffset();
            String value_ = ((AnaRendThrowing) current_).getExpression();
            ResultExpression resultExpression_ = ((AnaRendThrowing) current_).getRes();
            extractAnon(_page, _int, _method, _type, value_, resultExpression_);
        }
        stdElt(_analyzingDoc, _page, _int, _method, _type, current_);
        etl(_analyzingDoc, _page, _int, _method, _type, current_);
    }

    private static void stdElt(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendBlock current_) {
        if (current_ instanceof AnaRendField) {
            _page.setGlobalOffset(((AnaRendField) current_).getPrepareOffset());
            _page.zeroOffset();
            String value_ = ((AnaRendField) current_).getPrepare();
            ResultExpression resultExpression_ = ((AnaRendField) current_).getRes();
            extractAnon(_page, _int, _method, _type, value_, resultExpression_);
        }
        if (current_ instanceof AnaRendMessage) {
            String val_ = ((AnaRendMessage) current_).getElt().getAttribute(_analyzingDoc.getRendKeyWords().getAttrValue());
            StringMap<String> preQuick_ = AnaRendBlock.getPreQuick(val_, _analyzingDoc);
            if (!preQuick_.isEmpty()) {
                CustList<ResultExpression> resultExpressionList_ = ((AnaRendMessage) current_).getResultExpressionList();
                for (AnaRendElement e: ((AnaRendMessage) current_).getChildren()) {
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
        sel(_analyzingDoc, _page, _int, _method, _type, current_);
        tex(_analyzingDoc, _page, _int, _method, _type, current_);
        if (current_ instanceof AnaRendImport) {
            AnaRendImport i_ = (AnaRendImport) current_;
            String varValue_ = i_.getElt().getAttribute(_analyzingDoc.getRendKeyWords().getAttrPage());
            int offVarValue_ = i_.getAttributeDelimiter(_analyzingDoc.getRendKeyWords().getAttrPage());
            _page.setGlobalOffset(offVarValue_);
            _page.zeroOffset();
            buildIdAna(varValue_,_page,_int,_method,_type, i_.getRes());
        }
        if (current_ instanceof AnaRendText) {
            AnaRendText i_ = (AnaRendText) current_;
            String varValue_ = i_.getExpression();
            int offVarValue_ = i_.getExpressionOffset();
            _page.setGlobalOffset(offVarValue_);
            _page.zeroOffset();
            buildIdAna(varValue_,_page,_int,_method,_type, i_.getRes());
        }
    }

    private static void tex(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendBlock current_) {
        if (current_ instanceof AnaRendTextArea) {
            AnaRendTextArea s_ = (AnaRendTextArea) current_;
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
                ResultText rId_ = new ResultText();
                int off_ = s_.getAttributeDelimiter(_analyzingDoc.getRendKeyWords().getAttrId());
                s_.getAttributesText().put(_analyzingDoc.getRendKeyWords().getAttrId(),rId_);
                _page.setGlobalOffset(off_);
                _page.zeroOffset();
                buildIdAna(id_, _page,_int,_method,_type,rId_);
            }
            String prefixWrite_ = _analyzingDoc.getPrefix();
            String prefGr_ = StringUtil.concat(prefixWrite_, _analyzingDoc.getRendKeyWords().getAttrGroupId());
            String groupId_ = s_.getElt().getAttribute(prefGr_);
            int offGrId_ = s_.getAttributeDelimiter(prefGr_);
            if (!groupId_.isEmpty()) {
                ResultText rId_ = new ResultText();
                s_.getAttributesText().put(prefGr_,rId_);
                _page.setGlobalOffset(offGrId_);
                _page.zeroOffset();
                buildIdAna(groupId_, _page,_int,_method,_type,rId_);
            }
            String rows_ = s_.getElt().getAttribute(_analyzingDoc.getRendKeyWords().getAttrRows());
            int rowsGrId_ = s_.getAttributeDelimiter(_analyzingDoc.getRendKeyWords().getAttrRows());
            if (!rows_.isEmpty()) {
                ResultText rId_ = new ResultText();
                s_.getAttributes().addEntry(_analyzingDoc.getRendKeyWords().getAttrRows(),rId_);
                _page.setGlobalOffset(rowsGrId_);
                _page.zeroOffset();
                buildIdAna(rows_, _page,_int,_method,_type,rId_);
            }
            String cols_ = s_.getElt().getAttribute(_analyzingDoc.getRendKeyWords().getAttrCols());
            int colsGrId_ = s_.getAttributeDelimiter(_analyzingDoc.getRendKeyWords().getAttrCols());
            if (!cols_.isEmpty()) {
                ResultText rId_ = new ResultText();
                s_.getAttributes().addEntry(_analyzingDoc.getRendKeyWords().getAttrCols(),rId_);
                _page.setGlobalOffset(colsGrId_);
                _page.zeroOffset();
                buildIdAna(cols_, _page,_int,_method,_type,rId_);
            }
        }
    }

    private static void sel(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendBlock current_) {
        if (current_ instanceof AnaRendSelect) {
            AnaRendSelect s_ = (AnaRendSelect) current_;
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
                ResultText rId_ = new ResultText();
                int off_ = s_.getAttributeDelimiter(_analyzingDoc.getRendKeyWords().getAttrId());
                s_.getAttributesText().put(_analyzingDoc.getRendKeyWords().getAttrId(),rId_);
                _page.setGlobalOffset(off_);
                _page.zeroOffset();
                buildIdAna(id_,_page,_int,_method,_type,rId_);
            }
            String prefixWrite_ = _analyzingDoc.getPrefix();
            String prefGr_ = StringUtil.concat(prefixWrite_, _analyzingDoc.getRendKeyWords().getAttrGroupId());
            String groupId_ = s_.getElt().getAttribute(prefGr_);
            if (!groupId_.isEmpty()) {
                ResultText rId_ = new ResultText();
                int off_ = s_.getAttributeDelimiter(prefGr_);
                s_.getAttributesText().put(prefGr_,rId_);
                _page.setGlobalOffset(off_);
                _page.zeroOffset();
                buildIdAna(groupId_,_page,_int,_method,_type,rId_);
            }
            String rows_ = s_.getElt().getAttribute(_analyzingDoc.getRendKeyWords().getAttrRows());
            int rowsGrId_ = s_.getAttributeDelimiter(_analyzingDoc.getRendKeyWords().getAttrRows());
            if (!rows_.isEmpty()) {
                ResultText rId_ = new ResultText();
                s_.getAttributes().addEntry(_analyzingDoc.getRendKeyWords().getAttrRows(),rId_);
                _page.setGlobalOffset(rowsGrId_);
                _page.zeroOffset();
                buildIdAna(rows_,_page,_int,_method,_type,rId_);
            }
        }
    }

    private static void etl(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendBlock current_) {
        if (!(current_ instanceof AnaRendElement)) {
            return;
        }
        anc(_analyzingDoc, _page, _int, _method, _type, current_);
        if (current_ instanceof AnaRendSubmit) {
            AnaRendSubmit s_ = (AnaRendSubmit) current_;
            for (String a: s_.titles(_analyzingDoc)) {
                ResultText r_ = new ResultText();
                int rowsGrId_ = s_.getAttributeDelimiter(a);
                String attr_ = s_.getRead().getAttribute(a);
                _page.setGlobalOffset(rowsGrId_);
                _page.zeroOffset();
                s_.getOpExp().addEntry(a,r_);
                buildIdAna(attr_, _page,_int,_method,_type,r_);
            }
        }
        if (current_ instanceof AnaRendLink) {
            AnaRendLink s_ = (AnaRendLink) current_;
            for (String a: s_.titles(_analyzingDoc)) {
                ResultText r_ = new ResultText();
                int rowsGrId_ = s_.getAttributeDelimiter(a);
                String attr_ = s_.getRead().getAttribute(a);
                _page.setGlobalOffset(rowsGrId_);
                _page.zeroOffset();
                s_.getOpExpTitle().addEntry(a,r_);
                buildIdAna(attr_, _page,_int,_method,_type,r_);
            }
        }
        if (current_ instanceof AnaRendInput) {
            AnaRendInput s_ = (AnaRendInput) current_;
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
        if (current_ instanceof AnaRendImg) {
            AnaRendImg i_ = (AnaRendImg) current_;
            String varValue_ = i_.getRead().getAttribute(_analyzingDoc.getRendKeyWords().getAttrSrc());
            int offVarValue_ = i_.getAttributeDelimiter(_analyzingDoc.getRendKeyWords().getAttrSrc());
            _page.setGlobalOffset(offVarValue_);
            _page.zeroOffset();
            buildIdAna(varValue_, _page,_int,_method,_type,i_.getRes());
        }
        if (current_ instanceof AnaRendSpan) {
            AnaRendSpan i_ = (AnaRendSpan) current_;
            String varValue_ = i_.getRead().getAttribute(StringUtil.concat(_analyzingDoc.getPrefix(), _analyzingDoc.getRendKeyWords().getAttrFor()));
            int offVarValue_ = i_.getAttributeDelimiter(StringUtil.concat(_analyzingDoc.getPrefix(), _analyzingDoc.getRendKeyWords().getAttrFor()));
            _page.setGlobalOffset(offVarValue_);
            _page.zeroOffset();
            buildIdAna(varValue_, _page,_int,_method,_type,i_.getRes());
        }
        procElt((AnaRendElement) current_, _int, _method, _analyzingDoc, _page, _type);
    }

    private static void anc(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, AnaRendBlock current_) {
        if (current_ instanceof AnaRendTitledAnchor) {
            AnaRendTitledAnchor s_ = (AnaRendTitledAnchor) current_;
            for (String a: s_.titles(_analyzingDoc)) {
                ResultText r_ = new ResultText();
                int rowsGrId_ = s_.getAttributeDelimiter(a);
                String attr_ = s_.getRead().getAttribute(a);
                _page.setGlobalOffset(rowsGrId_);
                _page.zeroOffset();
                s_.getOpExpTitle().addEntry(a,r_);
                buildIdAna(attr_, _page,_int,_method,_type,r_);
            }
            String atName_ = StringUtil.concat(_analyzingDoc.getPrefix(), _analyzingDoc.getRendKeyWords().getAttrCommand());
            int rowsGrId_ = s_.getAttributeDelimiter(atName_);
            String attr_ = s_.getRead().getAttribute(atName_);
            lk(_page, _int, _method, _type, rowsGrId_, attr_, s_.getRes());
        }
        if (current_ instanceof AnaRendAnchor) {
            AnaRendAnchor s_ = (AnaRendAnchor) current_;
            String atName_ = StringUtil.concat(_analyzingDoc.getPrefix(), _analyzingDoc.getRendKeyWords().getAttrCommand());
            int rowsGrId_ = s_.getAttributeDelimiter(atName_);
            String attr_ = s_.getRead().getAttribute(atName_);
            lk(_page, _int, _method, _type, rowsGrId_, attr_, s_.getRes());
        }
        if (current_ instanceof AnaRendForm) {
            AnaRendForm s_ = (AnaRendForm) current_;
            String atName_ = StringUtil.concat(_analyzingDoc.getPrefix(), _analyzingDoc.getRendKeyWords().getAttrCommand());
            int rowsGrId_ = s_.getAttributeDelimiter(atName_);
            String attr_ = s_.getRead().getAttribute(atName_);
            lk(_page, _int, _method, _type, rowsGrId_, attr_, s_.getRes());
        }
    }

    private static void lk(AnalyzedPageEl _page, IntermediaryResults _int, AnaRendDocumentBlock _method, RootBlock _type, int rowsGrId_, String attr_, ResultText _res) {
        if (attr_.startsWith("$")) {
            String lk_ = attr_.substring(1);
            _page.setGlobalOffset(rowsGrId_);
            _page.zeroOffset();
            buildIdAna(lk_, _page, _int, _method, _type, _res);
        }
    }

    private static void procElt(AnaRendElement _inst, IntermediaryResults _int, AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page, RootBlock _type) {
        String prefixWrite_ = _anaDoc.getPrefix();
        String prefGr_ = StringUtil.concat(prefixWrite_, _anaDoc.getRendKeyWords().getAttrGroupId());
        String id_ = _inst.getRead().getAttribute(_anaDoc.getRendKeyWords().getAttrId());
        if (!id_.isEmpty()) {
            ResultText r_ = new ResultText();
            int off_ = _inst.getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrId());
            _page.setGlobalOffset(off_);
            _page.zeroOffset();
            _inst.getAttributesText().put(_anaDoc.getRendKeyWords().getAttrId(),r_);
            buildIdAna(id_, _page,_int,_doc,_type, r_);
        }
        String groupId_ = _inst.getRead().getAttribute(prefGr_);
        if (!groupId_.isEmpty()) {
            ResultText r_ = new ResultText();
            int off_ = _inst.getAttributeDelimiter(prefGr_);
            _page.setGlobalOffset(off_);
            _page.zeroOffset();
            _inst.getAttributesText().put(prefGr_,r_);
            buildIdAna(groupId_, _page,_int,_doc,_type, r_);
        }
        StringList list_ = _inst.processListAttributes(_doc, _anaDoc, _page);
        for (String a: list_) {
            String attr_ = _inst.getRead().getAttribute(a);
            if (attr_.trim().isEmpty()) {
                continue;
            }
            ResultText r_ = new ResultText();
            int rowsGrId_ = _inst.getAttributeDelimiter(a);
            _page.setGlobalOffset(rowsGrId_);
            _page.zeroOffset();
            _inst.getAttributes().addEntry(a,r_);
            buildIdAna(attr_, _page,_int,_doc,_type,r_);
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
