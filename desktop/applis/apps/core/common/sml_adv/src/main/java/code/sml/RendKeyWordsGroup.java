package code.sml;

public final class RendKeyWordsGroup {
    private final RendKeyWordsTags keyWordsTags;
    private final RendKeyWordsAttrs keyWordsAttrs;
    private final RendKeyWordsValues keyWordsValues;
    private final RendKeyWordsStyles keyWordsStyles;
    private final RendKeyWordsDefs keyWordsDefs;
    public RendKeyWordsGroup(){
        this(MessagesRendKeyWordsTags.init(),MessagesRendKeyWordsAttrs.init(),MessagesRendKeyWordsValues.init(),MessagesRendKeyWordsStyles.init(), MessagesRendKeyWordsDefs.init());
    }
    public RendKeyWordsGroup(RendKeyWordsTags _t, RendKeyWordsAttrs _a, RendKeyWordsValues _v, RendKeyWordsStyles _s, RendKeyWordsDefs _d){
        keyWordsTags = _t;
        keyWordsAttrs = _a;
        keyWordsValues = _v;
        keyWordsStyles = _s;
        keyWordsDefs = _d;
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

    public RendKeyWordsDefs getKeyWordsDefs() {
        return keyWordsDefs;
    }
}
