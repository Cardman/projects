package code.expressionlanguage.gui.unit;

import code.expressionlanguage.utilcompo.*;
import code.sml.util.*;
import code.util.*;

public final class MessagesCdmFullGui {
    public static final String KEY_FILE = "full_gui";
    public static final String TITLE = "0";
    public static final String FILE = "1";
    public static final String OPEN = "2";
    public static final String CONFIGURATION = "3";
    public static final String LAUNCH = "4";
    public static final String COVERAGE = "5";
    public static final String STOP = "6";
    private MessagesCdmFullGui() {
    }
    public static StringMap<String> valMessages(TranslationsLg _lg) {
        return FileInfos.getAppliTr(_lg).getMapping().getVal(KEY_FILE).getMapping();
    }
    public static TranslationsAppli updateEn(TranslationsAppli _a){
        _a.getMapping().addEntry(KEY_FILE,en());
        return _a;
    }
    public static TranslationsAppli updateFr(TranslationsAppli _a){
        _a.getMapping().addEntry(KEY_FILE,fr());
        return _a;
    }
    public static TranslationsFile en(){
        TranslationsFile en_ = new TranslationsFile();
        en_.add(TITLE,"Launcher");
        en_.add(FILE,"File");
        en_.add(OPEN,"Open");
        en_.add(CONFIGURATION,"Configuration");
        en_.add(LAUNCH,"Launch");
        en_.add(COVERAGE,"Coverage");
        en_.add(STOP,"Stop");
        return en_;
    }
    public static TranslationsFile fr(){
        TranslationsFile en_ = new TranslationsFile();
        en_.add(TITLE,"Lanceur");
        en_.add(FILE,"Fichier");
        en_.add(OPEN,"Ouvrir");
        en_.add(CONFIGURATION,"Configuration");
        en_.add(LAUNCH,"Lancer");
        en_.add(COVERAGE,"Couverture");
        en_.add(STOP,"Arrêter");
        return en_;
    }
}
