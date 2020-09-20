package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.AnalyzingDoc;
import code.sml.Document;
import code.sml.MutableNode;
import code.sml.Node;
import code.sml.Text;

public final class RendEmptyText extends RendLeaf implements RendWithEl,RendPossibleEmpty {

    private final String expression;

    private boolean add = true;

    RendEmptyText(OffsetStringInfo _left, OffsetsBlock _offset) {
        super(_offset);
        expression = _left.getInfo();
    }

    @Override
    public void buildExpressionLanguage(Configuration _conf, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (getNextSibling() instanceof RendElseIfCondition) {
            add = false;
        } else if (getNextSibling() instanceof RendElseCondition) {
            add = false;
        } else if (getNextSibling() instanceof RendDoWhileCondition){
            add = false;
        } else if (getNextSibling() instanceof RendAbstractCatchEval) {
            add = false;
        } else if (getNextSibling() instanceof RendFinallyEval) {
            add = false;
        } else if (getNextSibling() instanceof RendCaseCondition) {
            add = false;
        } else if (getNextSibling() instanceof RendDefaultCondition) {
            add = false;
        }
    }

    @Override
    public void processEl(Configuration _cont) {
        if (!add) {
            processBlock(_cont);
            return;
        }
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
