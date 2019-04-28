package code.sml;


public final class FullDocument extends CoreDocument {

    protected FullDocument(int _tabWidth) {
        super(_tabWidth);
    }

    @Override
    public Element createElement(String _tagName) {
        Element element_ = new FullElement(this);
        element_.setTagName(_tagName);
        return element_;
    }

    @Override
    public void renameNode(Node _node, String _name) {
        if (!(_node instanceof Element)) {
            return;
        }
        Element elt_ = (Element)_node;
        elt_.setTagName(_name);
    }

}
