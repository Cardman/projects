package code.bean.nat.analyze;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.sml.NatAnalyzingDoc;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.IndexConstants;

public class NatResultText implements NatResultTextInt {

    protected static final char LEFT_EL = '{';
    protected static final char QUOTE = 39;
    private CustList<NatOperationNode> opExpRoot;

    private final StringList texts = new StringList();
    private final Ints expOffsets = new Ints();
    private final Ints expEnds = new Ints();

    public void buildAna(String _expression, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        setOpExpRoot(new CustList<NatOperationNode>());
        StringBuilder str_ = new StringBuilder();
        int length_ = _expression.length();
        int i_ = IndexConstants.FIRST_INDEX;
        while (i_ < length_) {
            char cur_ = _expression.charAt(i_);
            if (cur_ == QUOTE) {
                str_.append(QUOTE);
                i_++;
                i_++;
            } else if (cur_ == LEFT_EL) {
                getTexts().add(str_.toString());
                str_.delete(0,str_.length());
                getExpOffsets().add(i_);
                i_++;
                NatOperationNode root_ = NatRenderAnalysis.getRootAnalyzedOperationsDel(_expression, i_, _anaDoc, _page);
                getOpExpRoot().add(root_);
                i_ = _anaDoc.getNextIndex();
                getExpEnds().add(i_);
            } else {
                str_.append(cur_);
                i_++;
            }
        }
        getTexts().add(str_.toString());
    }
    @Override
    public void buildIdAna(String _expression, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        setOpExpRoot(new CustList<NatOperationNode>());
        getTexts().add(_expression);
    }

    public StringList getTexts() {
        return texts;
    }

    public CustList<NatOperationNode> getOpExpRoot() {
        return opExpRoot;
    }

    public Ints getExpEnds() {
        return expEnds;
    }

    public Ints getExpOffsets() {
        return expOffsets;
    }

    public void setOpExpRoot(CustList<NatOperationNode> _v) {
        this.opExpRoot = _v;
    }
}
