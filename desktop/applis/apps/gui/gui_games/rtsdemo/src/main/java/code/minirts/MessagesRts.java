package code.minirts;

import code.sml.util.*;
import code.util.*;

public final class MessagesRts {
    public static final String APPS_RTS = "rts";
    public static final String FRAME = "0";
    public static final String ANIMATE = "0";
    public static final String PAUSE = "1";
    public static final String STOP = "2";
    public static final String ADD_SOLDIER = "3";

    private MessagesRts() {
    }

    public static TranslationsAppli initAppliTr(TranslationsLg _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getMapping().addEntry(APPS_RTS, a_);
        return a_;
    }
    public static TranslationsAppli getAppliTr(TranslationsLg _lgs) {
        return _lgs.getMapping().getVal(APPS_RTS);
    }
    public static StringMap<String> valMessages(TranslationsLg _lg) {
        return getAppliTr(_lg).getMapping().getVal(FRAME).getMapping();
    }
    public static TranslationsAppli updateEn(TranslationsAppli _a){
        _a.getMapping().addEntry(FRAME,en());
        return _a;
    }
    public static TranslationsAppli updateFr(TranslationsAppli _a){
        _a.getMapping().addEntry(FRAME,fr());
        return _a;
    }
    public static TranslationsFile en(){
        TranslationsFile en_ = new TranslationsFile();
        en_.add(ANIMATE,"Animate");
        en_.add(PAUSE,"Pause");
        en_.add(STOP,"Stop");
        en_.add(ADD_SOLDIER,"Add soldier");
        return en_;
    }
    public static TranslationsFile fr(){
        TranslationsFile fr_ = new TranslationsFile();
        fr_.add(ANIMATE,"Animer");
        fr_.add(PAUSE,"Pause");
        fr_.add(STOP,"Arrêter");
        fr_.add(ADD_SOLDIER,"Ajouter soldat");
        return fr_;
    }
}
