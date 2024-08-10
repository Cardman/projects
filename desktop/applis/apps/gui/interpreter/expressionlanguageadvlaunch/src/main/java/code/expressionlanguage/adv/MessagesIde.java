package code.expressionlanguage.adv;

import code.expressionlanguage.utilcompo.*;
import code.sml.util.*;
import code.util.*;

public final class MessagesIde {
    public static final String IDE_INIT_CHOOSE_PRO = "0";
    public static final String IDE_INIT_PRO = "0";
    public static final String IDE_CHOOSE_PRO = "1";
    private MessagesIde(){
    }
    public static StringMap<String> valInitChoose(TranslationsLg _lg) {
        return appli(_lg).getMapping().getVal(IDE_INIT_CHOOSE_PRO).getMapping();
    }
    public static TranslationsAppli appli(TranslationsLg _lg) {
        return _lg.getMapping().getVal(FileInfos.CDM);
    }
    public static TranslationsAppli updateEn(TranslationsAppli _a){
        appendInitChoose(_a, enInitChoose());
        return _a;
    }
    public static TranslationsAppli updateFr(TranslationsAppli _a){
        appendInitChoose(_a, frInitChoose());
        return _a;
    }
    public static void appendInitChoose(TranslationsAppli _a, TranslationsFile _v) {
        _a.getMapping().addEntry(IDE_INIT_CHOOSE_PRO, _v);
    }
    public static TranslationsFile enInitChoose(){
        TranslationsFile t_ = new TranslationsFile(2);
        t_.add(IDE_INIT_PRO,"Finished initializing project");
        t_.add(IDE_CHOOSE_PRO,"Finished loading project");
        return t_;
    }
    public static TranslationsFile frInitChoose(){
        TranslationsFile t_ = new TranslationsFile(2);
        t_.add(IDE_INIT_PRO,"Initialisation finie du projet");
        t_.add(IDE_CHOOSE_PRO,"Chargement fini du projet");
        return t_;
    }
}
