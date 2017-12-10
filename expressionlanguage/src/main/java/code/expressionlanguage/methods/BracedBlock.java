package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.ElementOffsetsNext;
import code.sml.Node;
import code.util.CustList;

public abstract class BracedBlock extends Block implements BracedBlockInt {

    private boolean initializedFirstChild;

    private Block firstChild;

    BracedBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    @Override
    public final Block getFirstChild() {
        if (initializedFirstChild) {
            return firstChild;
        }
        initializedFirstChild = true;
        Element elt_ = getAssociateElement();
        Node first_ = elt_.getFirstChild();
        while (first_ != null) {
            if (first_ instanceof Element) {
                break;
            }
            first_ = first_.getNextSibling();
        }
        if (first_ == null) {
            return null;
        }
        Element eltFirst_ = (Element) first_;
        firstChild = createOperationNode(eltFirst_, getConf(), CustList.FIRST_INDEX, this);
        String html_ = getConf().getHtml();
        int tabWidth_ = getConf().getTabWidth();
        ElementOffsetsNext e_ = getConf().getElements();
        ElementOffsetsNext ne_ = DocumentBuilder.getIndexesOfElementOrAttribute(html_, e_, eltFirst_, tabWidth_);
        firstChild.setAttributes(ne_.getAttributes());
        firstChild.setEndHeader(ne_.getEndHeader());
        firstChild.setTabs(ne_.getTabs());
        firstChild.setOffsets(ne_.getOffsets());
        getConf().setElements(ne_);
        return firstChild;
    }

    public final void removeLocalVars(PageEl _ip) {
        for (Block s: Classes.getDirectChildren(this)) {
            if (s instanceof InitVariable) {
                String var_ = ((InitVariable)s).getVariableName();
                _ip.getLocalVars().removeKey(var_);
            }
        }
    }

    @Override
    public void removeVarAndLoop(PageEl _ip) {
        _ip.removeLastBlock();
    }
}
