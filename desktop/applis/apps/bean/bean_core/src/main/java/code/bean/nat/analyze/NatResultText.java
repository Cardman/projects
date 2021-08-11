package code.bean.nat.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.bean.nat.analyze.blocks.AnaRendBlockHelp;
import code.sml.Element;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class NatResultText {

    private static final String CALL_METHOD = "$";
    private static final char ESCAPED = '\\';
    private static final char RIGHT_EL = '}';
    private static final char LEFT_EL = '{';
    private static final char QUOTE = 39;
    private CustList<NatOperationNode> opExpRoot;
    private NatOperationNode opExpAnchorRoot;

    private StringList texts = new StringList();
    private StringList varNames = new StringList();
    private final Ints expOffsets = new Ints();
    private final Ints expEnds = new Ints();

    public void buildAna(String _expression, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        opExpRoot = new CustList<NatOperationNode>();
        StringBuilder str_ = new StringBuilder();
        int length_ = _expression.length();
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < length_) {
            char cur_ = _expression.charAt(i_);
            if (cur_ == QUOTE) {
                str_.append(QUOTE);
                i_++;
                i_++;
                continue;
            }
            if (cur_ == LEFT_EL) {
                texts.add(str_.toString());
                str_.delete(0,str_.length());
                expOffsets.add(i_);
                i_++;
                NatOperationNode root_ = NatRenderAnalysis.getRootAnalyzedOperationsDel(_expression, i_, _anaDoc, _page);
                opExpRoot.add(root_);
                i_ = _anaDoc.getNextIndex();
                expEnds.add(i_);
                continue;
            }
            str_.append(cur_);
            i_++;
        }
        texts.add(str_.toString());
    }

    public void buildIdAna(String _expression, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        opExpRoot = new CustList<NatOperationNode>();
        StringBuilder str_ = new StringBuilder();
        int length_ = _expression.length();
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < length_) {
            char cur_ = _expression.charAt(i_);
            if (cur_ == LEFT_EL) {
                texts.add(str_.toString());
                str_.delete(0,str_.length());
                expOffsets.add(i_);
                i_++;
//                _conf.getLastPage().setOffset(i_);
                NatOperationNode opsLoc_ = NatRenderAnalysis.getRootAnalyzedOperationsDel(_expression, i_, _anaDoc, _page);
                opExpRoot.add(opsLoc_);
                i_ = _anaDoc.getNextIndex();
                expEnds.add(i_);
                continue;
            }
            str_.append(cur_);
            i_++;
        }
        texts.add(str_.toString());
    }

    public static NatResultText buildAnchor(AnaRendBlock _r, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrHref());
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
        String href_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
        NatResultText r_ = new NatResultText();
        r_.opExpRoot = new CustList<NatOperationNode>();
        r_.texts = new StringList();
        if (href_.startsWith(CALL_METHOD)) {
            String lk_ = href_.substring(1);
            r_.buildAna(lk_, _anaDoc, _page);
            CustList<NatOperationNode> opExpRoot_ = r_.getOpExpRoot();
            int l_ = opExpRoot_.size();
            StringList formArg_ = new StringList();
            StringList varNames_ = new StringList();
            for (int i = 0; i< l_; i++) {
                String varLoc_ = AnaRendBlockHelp.lookForVar(varNames_);
                varNames_.add(varLoc_);
            }
            r_.varNames = varNames_;
            int i_ = 0;
            for (String v:varNames_) {
                AnaLocalVariable lv_ = new AnaLocalVariable();
                lv_.setClassName(opExpRoot_.get(i_).getResultClass().getSingleNameOrEmpty());
                _page.getInfosVars().addEntry(v,lv_);
                formArg_.add(StringUtil.concat(AnaRendBlock.LEFT_PAR, v,AnaRendBlock.RIGHT_PAR));
                i_++;
            }
            String pref_ = r_.quickRender(lk_, formArg_);
            if (pref_.indexOf('(') < 0) {
                pref_ = StringUtil.concat(pref_,AnaRendBlock.LEFT_PAR,AnaRendBlock.RIGHT_PAR);
            }
            r_.opExpAnchorRoot = NatRenderAnalysis.getRootAnalyzedOperations(pref_, 0, _anaDoc, _page);
            for (String v:varNames_) {
                _page.getInfosVars().removeKey(v);
            }
        }
        return r_;
    }
    public String quickRender(String _expression,StringList _args) {
        StringBuilder str_ = new StringBuilder();
        int length_ = _expression.length();
        int i_ = IndexConstants.FIRST_INDEX;
        int iExp_ = IndexConstants.FIRST_INDEX;
        while (i_ < length_) {
            if (expOffsets.isValidIndex(iExp_) && expOffsets.get(iExp_) == i_) {
                str_.append(_args.get(iExp_));
                i_ = expEnds.get(iExp_);
                iExp_++;
                continue;
            }
            str_.append(_expression.charAt(i_));
            i_++;
        }
        return str_.toString();
    }

    public StringList getTexts() {
        return texts;
    }

    public CustList<NatOperationNode> getOpExpRoot() {
        return opExpRoot;
    }

    public NatOperationNode getOpExpAnchorRoot() {
        return opExpAnchorRoot;
    }

    public StringList getVarNames() {
        return varNames;
    }

}
