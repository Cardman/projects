package code.sml;

import code.sml.util.TranslationsAppli;
import code.util.StringMap;

public final class NatAnalyzingDoc extends SetupableAnalyzingDoc {
    private RendKeyWordsGroup rendKeyWords = new RendKeyWordsGroup();
    private final StringMap<TranslationsAppli> applis = new StringMap<TranslationsAppli>();

    public StringMap<TranslationsAppli> getApplis() {
        return applis;
    }
    public void setRendKeyWords(RendKeyWordsGroup _r) {
        this.rendKeyWords = _r;
    }

    public RendKeyWordsGroup getRendKeyWords() {
        return rendKeyWords;
    }
}
