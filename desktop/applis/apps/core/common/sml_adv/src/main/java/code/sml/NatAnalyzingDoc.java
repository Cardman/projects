package code.sml;

public final class NatAnalyzingDoc extends SetupableAnalyzingDoc {
    private RendKeyWordsGroup rendKeyWords = new RendKeyWordsGroup();

    public void setRendKeyWords(RendKeyWordsGroup _r) {
        this.rendKeyWords = _r;
    }

    public RendKeyWordsGroup getRendKeyWords() {
        return rendKeyWords;
    }
}
