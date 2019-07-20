package code.formathtml;

import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.util.RendReadWrite;
import code.sml.Document;
import code.sml.MutableNode;
import code.sml.Node;
import code.sml.Text;

public final class RendEmptyText extends RendLeaf implements RendWithEl, RendReducableOperations,RendBuildableElMethod,RendPossibleEmpty {
    private static final char SEP_TR = ',';
    private static final char RIGHT_EL = '}';
    private static final char LEFT_EL = '{';
    private static final char QUOTE = 39;
    private static final char ESCAPED = '\\';
    private final String expression;

    private int expressionOffset;

    RendEmptyText(OffsetStringInfo _left, OffsetsBlock _offset) {
        super(_offset);
        expression = _left.getInfo();
        expressionOffset = _left.getOffset();
    }

    @Override
    public void buildExpressionLanguage(Configuration _conf,RendDocumentBlock _doc) {
    }

    @Override
    public void reduce(Configuration _context) {
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage lastPage_ = _cont.getLastPage();
        RendReadWrite rend_ = lastPage_.getRendReadWrite();
        Node write_ = rend_.getWrite();
        Document doc_ = write_.getOwnerDocument();
        Text t_ = doc_.createTextNode(EMPTY_STRING);
        ((MutableNode)write_).appendChild(t_);
        t_.appendData(expression);
        processBlock(_cont);
    }
}
