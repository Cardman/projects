package cards.president.enumerations;

import code.util.IdList;
import code.util.core.StringUtil;

public enum Playing {
    CAN_PLAY("0"),SKIPPED("1"),HAS_TO_EQUAL("2"),PASS("3"),FINISH("4"),DO_NOT_EQUAL("5");

    private final String play;

    Playing(String _p) {
        this.play = _p;
    }

    public String getPlay() {
        return play;
    }
    public static Playing retrieve(String _s) {
        for (Playing e: Playing.all()) {
            if (StringUtil.quickEq(e.getPlay(),_s)) {
                return e;
            }
        }
        return Playing.CAN_PLAY;
    }
    public static IdList<Playing> all() {
        IdList<Playing> l_ = new IdList<Playing>();
        l_.add(CAN_PLAY);
        l_.add(SKIPPED);
        l_.add(HAS_TO_EQUAL);
        l_.add(PASS);
        l_.add(FINISH);
        l_.add(DO_NOT_EQUAL);
        return l_;
    }
}
