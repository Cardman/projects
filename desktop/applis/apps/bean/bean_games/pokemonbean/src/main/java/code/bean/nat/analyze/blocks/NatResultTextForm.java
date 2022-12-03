package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.*;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.sml.NatAnalyzingDoc;
import code.util.*;
import code.util.core.*;

public final class NatResultTextForm extends NatResultText {
    private StringList varNames = new StringList();
    private NatOperationNode opExpAnchorRoot;

    @Override
    public void buildIdAna(String _expression, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        setOpExpRoot(new CustList<NatOperationNode>());
        StringBuilder str_ = new StringBuilder();
        int length_ = _expression.length();
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < length_) {
            char cur_ = _expression.charAt(i_);
            if (cur_ == LEFT_EL) {
                getTexts().add(str_.toString());
                str_.delete(0,str_.length());
                getExpOffsets().add(i_);
                i_++;
//                _conf.getLastPage().setOffset(i_);
                NatOperationNode opsLoc_ = NatRenderAnalysis.getRootAnalyzedOperationsDel(_expression, i_, _anaDoc, _page);
                getOpExpRoot().add(opsLoc_);
                i_ = _anaDoc.getNextIndex();
                getExpEnds().add(i_);
                continue;
            }
            str_.append(cur_);
            i_++;
        }
        getTexts().add(str_.toString());
    }
    public String quickRender(String _expression,StringList _args) {
        StringBuilder str_ = new StringBuilder();
        int length_ = _expression.length();
        int i_ = IndexConstants.FIRST_INDEX;
        int iExp_ = IndexConstants.FIRST_INDEX;
        while (i_ < length_) {
            if (getExpOffsets().isValidIndex(iExp_) && getExpOffsets().get(iExp_) == i_) {
                str_.append(_args.get(iExp_));
                i_ = getExpEnds().get(iExp_);
                iExp_++;
                continue;
            }
            str_.append(_expression.charAt(i_));
            i_++;
        }
        return str_.toString();
    }

    public StringList getVarNames() {
        return varNames;
    }

    public void setVarNames(StringList _v) {
        this.varNames = _v;
    }

    public NatOperationNode getOpExpAnchorRoot() {
        return opExpAnchorRoot;
    }

    public void setOpExpAnchorRoot(NatOperationNode _v) {
        this.opExpAnchorRoot = _v;
    }

}
