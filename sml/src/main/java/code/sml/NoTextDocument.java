package code.sml;


public final class NoTextDocument extends CoreDocument {

    protected NoTextDocument(int _tabWidth) {
        super(_tabWidth);
    }
    @Override
    public NotTextElement createElement(String _tagName) {
        NotTextElement element_ = new NotTextElement(this);
        element_.setTagName(_tagName);
        return element_;
    }

}
