package cards.tarot.enumerations;

import code.util.IdList;

public enum EndDealTarot {
    ATTACK_LOOSE("1"), ATTACK_WIN("2"), ZERO("0");
    private final String st;

    EndDealTarot(String _s) {
        st = _s;
    }

    public String getSt() {
        return st;
    }
    public static IdList<EndDealTarot> all() {
        IdList<EndDealTarot> all_ = new IdList<EndDealTarot>();
        all_.add(ZERO);
        all_.add(ATTACK_LOOSE);
        all_.add(ATTACK_WIN);
        return all_;
    }
}
