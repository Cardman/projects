package cards.consts;

public final class RulesCommon {
    private MixCardsChoice mixedCards=MixCardsChoice.EACH_LAUNCHING;
    private int nbDeals;
    private String general="";
    private String specific="";
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

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String _general) {
        this.general = _general;
    }

    public String getSpecific() {
        return specific;
    }

    public void setSpecific(String _specific) {
        this.specific = _specific;
    }
}
