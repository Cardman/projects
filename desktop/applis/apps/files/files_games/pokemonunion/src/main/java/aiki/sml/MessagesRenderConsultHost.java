package aiki.sml;

import code.sml.util.TranslationsFile;

public final class MessagesRenderConsultHost {
    public static final String TITLE = "0";
    public static final String STEPS = "1";
    public static final String FREE = "2";
    private MessagesRenderConsultHost() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(TITLE, "Hosting details");
        e_.add(STEPS, "Remaining steps before laying an egg: {0}");
        e_.add(FREE, "This hosting is free.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(TITLE, "Détail de la pension");
        f_.add(STEPS, "Pas restants avant une ponte d''oeuf: {0}");
        f_.add(FREE, "Cette pension est libre.");
        return f_;
    }
}
