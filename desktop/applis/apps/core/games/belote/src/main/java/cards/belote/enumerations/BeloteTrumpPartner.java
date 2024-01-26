package cards.belote.enumerations;

import code.util.IdList;

public enum BeloteTrumpPartner {
NO_UNDERTRUMP_NO_OVERTRUMP("0"),OVERTRUMP_ONLY("1"),UNDERTRUMP_ONLY("2"),UNDERTRUMP_OVERTRUMP("3");
    private final String beloteTrSt;

    BeloteTrumpPartner(String _b) {
        this.beloteTrSt = _b;
    }

    public String getBeloteTrSt() {
        return beloteTrSt;
    }
    public static IdList<BeloteTrumpPartner> all() {
        IdList<BeloteTrumpPartner> all_ = new IdList<BeloteTrumpPartner>();
        all_.add(NO_UNDERTRUMP_NO_OVERTRUMP);
        all_.add(OVERTRUMP_ONLY);
        all_.add(UNDERTRUMP_ONLY);
        all_.add(UNDERTRUMP_OVERTRUMP);
        return all_;
    }
}
