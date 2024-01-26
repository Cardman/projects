package cards.president.enumerations;

import code.util.IdList;

public enum EqualtyPlaying {
    FORBIDDEN("0"),
    SKIP_ALWAYS_NEXT("1"),
    SKIP_DIFF_NEXT_STOP("2"),
    NO_SKIP("3");
    private final String st;

    EqualtyPlaying(String _s) {
        st = _s;
    }

    public String getSt() {
        return st;
    }
    public static IdList<EqualtyPlaying> all() {
        IdList<EqualtyPlaying> all_ = new IdList<EqualtyPlaying>();
        all_.add(FORBIDDEN);
        all_.add(SKIP_ALWAYS_NEXT);
        all_.add(SKIP_DIFF_NEXT_STOP);
        all_.add(NO_SKIP);
        return all_;
    }
}
