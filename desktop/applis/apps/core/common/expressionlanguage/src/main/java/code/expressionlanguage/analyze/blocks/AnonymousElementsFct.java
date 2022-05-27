package code.expressionlanguage.analyze.blocks;

import code.util.CustList;

public final class AnonymousElementsFct {
    private final AnonymousElements elements = new AnonymousElements();
    private final CustList<RootBlock> reserved = new CustList<RootBlock>();

    public AnonymousElements getElements() {
        return elements;
    }

    public CustList<RootBlock> getReserved() {
        return reserved;
    }
}
