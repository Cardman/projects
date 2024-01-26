package cards.tarot.enumerations;

import code.util.IdList;

public enum ModeTarot {
NORMAL("0"),NORMAL_WITH_MISERE("1"),NORMAL_WITH_ONE_FOR_ONE("2"),MISERE("3"),ONE_FOR_ONE("4");
    private final String st;

    ModeTarot(String _s) {
        this.st = _s;
    }

    public String getSt() {
        return st;
    }
    public static IdList<ModeTarot> all() {
        IdList<ModeTarot> all_ = new IdList<ModeTarot>();
        all_.add(NORMAL);
        all_.add(NORMAL_WITH_MISERE);
        all_.add(NORMAL_WITH_ONE_FOR_ONE);
        all_.add(MISERE);
        all_.add(ONE_FOR_ONE);
        return all_;
    }
}
