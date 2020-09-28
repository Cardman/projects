package code.formathtml;

import code.formathtml.stacks.RendReadWrite;
import code.sml.Document;
import code.sml.MutableNode;
import code.sml.Node;
import code.sml.Text;

public final class RendEmptyText extends RendLeaf implements RendWithEl,RendPossibleEmpty {

    private final String expression;

    private boolean add;

    public RendEmptyText(int _offsetTrim, String expression, boolean add) {
        super(_offsetTrim);
        this.expression = expression;
        this.add = add;
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
