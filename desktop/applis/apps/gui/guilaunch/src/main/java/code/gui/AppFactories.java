package code.gui;

import aiki.main.AikiFactory;
import cards.main.CardFactories;

public final class AppFactories {
    private final AikiFactory aikiFactory;
    private final CardFactories cardFactories;
    private final CdmFactory cdmFactory;

    public AppFactories(AikiFactory _a,CardFactories _cf, CdmFactory _cdm) {
        this.aikiFactory = _a;
        this.cardFactories = _cf;
        this.cdmFactory = _cdm;
    }

    public AikiFactory getAikiFactory() {
        return aikiFactory;
    }

    public CardFactories getCardFactories() {
        return cardFactories;
    }

    public CdmFactory getCdmFactory() {
        return cdmFactory;
    }
}
