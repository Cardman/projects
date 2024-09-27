package code.renders;

import code.expressionlanguage.utilcompo.FileInfos;
import code.sml.util.*;
import code.util.*;

public final class MessagesRenders {
    public static final String BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    public static final String APPS_RENDERS_SITES = "renders_sites";
    public static final String FRAME = "render_frame";
    public static final String FILE = "0";
    public static final String OPEN = "1";
    public static final String TITLE = "2";
    public static final String SEARCH = "3";
    private MessagesRenders() {
    }
    public static StringMap<String> valMessages(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(FRAME).getMapping();
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
        en_.add(FILE,"file");
        en_.add(OPEN,"open");
        en_.add(TITLE,"Local sites");
        en_.add(SEARCH,"search");
        return en_;
    }
    public static TranslationsFile fr(){
        TranslationsFile fr_ = new TranslationsFile();
        fr_.add(FILE,"fichiers");
        fr_.add(OPEN,"ouvrir");
        fr_.add(TITLE,"Sites locaux");
        fr_.add(SEARCH,"chercher");
        return fr_;
    }
}
