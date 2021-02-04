package cards.main;

import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import cards.president.enumerations.CardPresident;
import cards.tarot.enumerations.CardTarot;
import code.gui.initialize.AbstractGraphicListGenerator;

public final class CardFactories {
    private final AbstractGraphicListGenerator<CardBelote> geneBelote;
    private final AbstractGraphicListGenerator<CardPresident> genePresident;
    private final AbstractGraphicListGenerator<CardTarot> geneTarot;
    private final AbstractGraphicListGenerator<Suit> geneSuit;

    public CardFactories(AbstractGraphicListGenerator<CardBelote> _geneBelote, AbstractGraphicListGenerator<CardPresident> _genePresident, AbstractGraphicListGenerator<CardTarot> _geneTarot, AbstractGraphicListGenerator<Suit> _geneSuit) {
        geneBelote= _geneBelote;
        genePresident= _genePresident;
        geneTarot= _geneTarot;
        geneSuit= _geneSuit;
    }
    public AbstractGraphicListGenerator<CardBelote> getGeneBelote() {
        return geneBelote;
    }

    public AbstractGraphicListGenerator<CardPresident> getGenePresident() {
        return genePresident;
    }

    public AbstractGraphicListGenerator<CardTarot> getGeneTarot() {
        return geneTarot;
    }

    public AbstractGraphicListGenerator<Suit> getGeneSuit() {
        return geneSuit;
    }
}
