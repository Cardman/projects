package code.netw;

import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;

public final class NetWork {
    public static final String APPS_NETWORK = "network";
    public static final String COMMON = "common";
    private NetWork() {
    }

    public static TranslationsAppli initAppliTr(TranslationsLg _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getMapping().addEntry(APPS_NETWORK, a_);
        return a_;
    }

    public static TranslationsAppli getAppliTr(TranslationsLg _lgs) {
        return _lgs.getMapping().getVal(APPS_NETWORK);
    }

    public static void enTr(TranslationsAppli _lgs) {
        append(_lgs, MessagesNetWork.en());
    }
    public static void frTr(TranslationsAppli _lgs) {
        append(_lgs, MessagesNetWork.fr());
    }
    public static void append(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(COMMON,_f);
    }
    public static TranslationsFile getMessages(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(COMMON);
    }
}
