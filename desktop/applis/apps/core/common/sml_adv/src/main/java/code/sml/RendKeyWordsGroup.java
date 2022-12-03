package code.sml;

public final class RendKeyWordsGroup {
    private final RendKeyWordsTags keyWordsTags;
    private final RendKeyWordsAttrs keyWordsAttrs;
    private final RendKeyWordsValues keyWordsValues;
    private final RendKeyWordsStyles keyWordsStyles;
    public RendKeyWordsGroup(){
        this(new RendKeyWordsTags(),new RendKeyWordsAttrs(),new RendKeyWordsValues(),new RendKeyWordsStyles());
    }
    public RendKeyWordsGroup(RendKeyWordsTags _t, RendKeyWordsAttrs _a, RendKeyWordsValues _v, RendKeyWordsStyles _s){
        keyWordsTags = _t;
        keyWordsAttrs = _a;
        keyWordsValues = _v;
        keyWordsStyles = _s;
    }

    public RendKeyWordsAttrs getKeyWordsAttrs() {
        return keyWordsAttrs;
    }

    public RendKeyWordsStyles getKeyWordsStyles() {
        return keyWordsStyles;
    }

    public RendKeyWordsTags getKeyWordsTags() {
        return keyWordsTags;
    }

    public RendKeyWordsValues getKeyWordsValues() {
        return keyWordsValues;
    }
}
