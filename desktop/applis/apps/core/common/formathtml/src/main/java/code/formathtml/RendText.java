package code.formathtml;

import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.stacks.RendReadWrite;
import code.sml.Document;
import code.sml.MutableNode;
import code.sml.Node;
import code.sml.Text;
import code.util.CustList;
import code.util.StringList;

public final class RendText extends RendLeaf implements RendWithEl, RendReducableOperations,RendBuildableElMethod {

    private final String expression;

    private int expressionOffset;

    private CustList<CustList<RendDynOperationNode>> opExp;

    private StringList texts = new StringList();

    RendText(OffsetStringInfo _left, OffsetsBlock _offset) {
        super(_offset);
        expression = _left.getInfo();
        expressionOffset = _left.getOffset();
    }

    @Override
    public void buildExpressionLanguage(Configuration _conf,RendDocumentBlock _doc) {
        _conf.getAnalyzing().setOffset(expressionOffset);
        ResultText res_ = new ResultText();
        res_.build(expression,_conf,expressionOffset,_doc);
        opExp = res_.getOpExp();
        texts = res_.getTexts();
    }

    @Override
    public void reduce(Configuration _context) {
        ResultText.reduce(opExp);
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage lastPage_ = _cont.getLastPage();
        RendReadWrite rend_ = lastPage_.getRendReadWrite();
        Node write_ = rend_.getWrite();
        Document doc_ = write_.getOwnerDocument();
        Text t_ = doc_.createTextNode(EMPTY_STRING);
        ((MutableNode)write_).appendChild(t_);
        t_.appendData(ResultText.render(opExp,texts,_cont));
        if (_cont.getContext().hasException()) {
            return;
        }
        processBlock(_cont);
    }
}
