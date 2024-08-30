package applications.code.gui;

import aiki.main.AikiFactory;
import cards.main.CardFactories;
import code.gui.CdmFactory;

public final class AppFactories {
    private final AikiFactory aikiFactory;
    private final CardFactories cardFactories;
    private final CdmFactory cdmFactory;
    private final String tmpFolder;

    public AppFactories(AikiFactory _a, CardFactories _cf, CdmFactory _cdm, String _tmpApp) {
        this.aikiFactory = _a;
        this.cardFactories = _cf;
        this.cdmFactory = _cdm;
        this.tmpFolder = _tmpApp;
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

    public String getTmpFolder() {
        return tmpFolder;
    }
}
