package code.sml.util;

import code.util.StringMap;

public final class TranslationsLg {
    private final StringMap<TranslationsAppli> mapping = new StringMap<TranslationsAppli>();
    private final StringMap<int[][]> maxiCards = new StringMap<int[][]>();
    private final StringMap<int[][]> miniCardsDef = new StringMap<int[][]>();
    private final StringMap<int[][]> miniCardsSel = new StringMap<int[][]>();

    private final String key;
//    private Document treeCards;
    public TranslationsLg() {
        this("");
    }
    public TranslationsLg(String _k) {
        key = _k;
    }

    public String getKey() {
        return key;
    }

    public StringMap<TranslationsAppli> getMapping() {
        return mapping;
    }

    public StringMap<int[][]> getMaxiCards() {
        return maxiCards;
    }

    public StringMap<int[][]> getMiniCardsDef() {
        return miniCardsDef;
    }

    public StringMap<int[][]> getMiniCardsSel() {
        return miniCardsSel;
    }

//    public Document getTreeCards() {
//        return treeCards;
//    }
//
//    public void setTreeCards(Document _t) {
//        this.treeCards = _t;
//    }
}
