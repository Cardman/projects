package cards.consts;

import code.util.StringMap;

public final class RulesCommon {
    private MixCardsChoice mixedCards=MixCardsChoice.EACH_LAUNCHING;
    private int nbDeals;
    private StringMap<String> general=new StringMap<String>();
    private StringMap<String> specific=new StringMap<String>();
    public RulesCommon() {
    }
    public RulesCommon(RulesCommon _other) {
        setMixedCards(_other.getMixedCards());
        setNbDeals(_other.getNbDeals());
        setGeneral(_other.getGeneral());
        setSpecific(_other.getSpecific());
    }

    public MixCardsChoice getMixedCards() {
        return mixedCards;
    }

    public void setMixedCards(MixCardsChoice _m) {
        this.mixedCards = _m;
    }

    public int getNbDeals() {
        return nbDeals;
    }

    public void setNbDeals(int _nbDeals) {
        nbDeals = _nbDeals;
    }

    public StringMap<String> getGeneral() {
        return general;
    }

    public void setGeneral(StringMap<String> _general) {
        this.general = _general;
    }

    public StringMap<String> getSpecific() {
        return specific;
    }

    public void setSpecific(StringMap<String> _specific) {
        this.specific = _specific;
    }
}
