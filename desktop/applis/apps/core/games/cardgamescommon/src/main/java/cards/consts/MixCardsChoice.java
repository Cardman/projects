package cards.consts;

import code.util.IdList;

public enum MixCardsChoice {
EACH_DEAL("0"),EACH_LAUNCHING("2"),ONCE_ONLY("1"),NEVER("3");
    private final String mixSt;

    MixCardsChoice(String _m) {
        this.mixSt = _m;
    }

    public String getMixSt() {
        return mixSt;
    }
    public static IdList<MixCardsChoice> all() {
        IdList<MixCardsChoice> all_ = new IdList<MixCardsChoice>();
        all_.add(EACH_DEAL);
        all_.add(EACH_LAUNCHING);
        all_.add(ONCE_ONLY);
        all_.add(NEVER);
        return all_;
    }
}
