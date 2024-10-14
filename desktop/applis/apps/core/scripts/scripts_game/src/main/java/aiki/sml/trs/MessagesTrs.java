package aiki.sml.trs;
import code.sml.util.*;
public final class MessagesTrs{
public static final String SC_APP = "sc_app";
public static final String EN = "en";
public static final String FR = "fr";
public static final String TRANSLATION_CATEGORIES = "categories.txt";
public static final String TRANSLATION_GENDERS = "genders.txt";
public static final String TRANSLATION_ENVIRONMENTS = "environments.txt";
public static final String TRANSLATION_BOOLEANS = "booleans.txt";
public static final String TRANSLATION_WINPTS = "winpts.txt";
public static final String TRANSLATION_MODELLAW = "modellaw.txt";
public static final String TRANSLATION_STATISTICS = "statistics.txt";
public static final String TRANSLATION_TARGETS = "targets.txt";
public static final String TRANSLATION_TYPES = "types.txt";
public static final String TRANSLATION_POKEMON = "pokemon.txt";
public static final String TRANSLATION_MOVES = "moves.txt";
public static final String TRANSLATION_ITEMS = "items.txt";
public static final String TRANSLATION_ABILITIES = "abilities.txt";
public static final String TRANSLATION_STATUS = "status.txt";
public static final String TRANSLATION_MATH = "math.txt";
public static final String TRANSLATION_CLASSES = "classes.txt";
public static final String TRANSLATION_LITTERAL = "litteral.txt";
private MessagesTrs(){}
public static TranslationsAppli enPart(){
TranslationsAppli e=new TranslationsAppli(17);
e.getMapping().addEntry(TRANSLATION_ABILITIES,MessagesTrsAb.en());
e.getMapping().addEntry(TRANSLATION_BOOLEANS,MessagesTrsBooleans.en());
e.getMapping().addEntry(TRANSLATION_CATEGORIES,MessagesTrsCategories.en());
e.getMapping().addEntry(TRANSLATION_CLASSES,MessagesTrsClasses.en());
e.getMapping().addEntry(TRANSLATION_ENVIRONMENTS,MessagesTrsEnvironments.en());
e.getMapping().addEntry(TRANSLATION_GENDERS,MessagesTrsGenders.en());
e.getMapping().addEntry(TRANSLATION_ITEMS,MessagesTrsIt.en());
e.getMapping().addEntry(TRANSLATION_LITTERAL,MessagesTrsLiteral.en());
e.getMapping().addEntry(TRANSLATION_MATH,MessagesTrsMath.en());
e.getMapping().addEntry(TRANSLATION_MODELLAW,MessagesTrsModelLaw.en());
e.getMapping().addEntry(TRANSLATION_MOVES,MessagesTrsMv.en());
e.getMapping().addEntry(TRANSLATION_POKEMON,MessagesTrsPk.en());
e.getMapping().addEntry(TRANSLATION_STATISTICS,MessagesTrsStatistics.en());
e.getMapping().addEntry(TRANSLATION_STATUS,MessagesTrsSt.en());
e.getMapping().addEntry(TRANSLATION_TARGETS,MessagesTrsTargets.en());
e.getMapping().addEntry(TRANSLATION_TYPES,MessagesTrsTypes.en());
e.getMapping().addEntry(TRANSLATION_WINPTS,MessagesTrsWinPts.en());
return e;
}
public static TranslationsAppli frPart(){
TranslationsAppli f=new TranslationsAppli(17);
f.getMapping().addEntry(TRANSLATION_ABILITIES,MessagesTrsAb.fr());
f.getMapping().addEntry(TRANSLATION_BOOLEANS,MessagesTrsBooleans.fr());
f.getMapping().addEntry(TRANSLATION_CATEGORIES,MessagesTrsCategories.fr());
f.getMapping().addEntry(TRANSLATION_CLASSES,MessagesTrsClasses.fr());
f.getMapping().addEntry(TRANSLATION_ENVIRONMENTS,MessagesTrsEnvironments.fr());
f.getMapping().addEntry(TRANSLATION_GENDERS,MessagesTrsGenders.fr());
f.getMapping().addEntry(TRANSLATION_ITEMS,MessagesTrsIt.fr());
f.getMapping().addEntry(TRANSLATION_LITTERAL,MessagesTrsLiteral.fr());
f.getMapping().addEntry(TRANSLATION_MATH,MessagesTrsMath.fr());
f.getMapping().addEntry(TRANSLATION_MODELLAW,MessagesTrsModelLaw.fr());
f.getMapping().addEntry(TRANSLATION_MOVES,MessagesTrsMv.fr());
f.getMapping().addEntry(TRANSLATION_POKEMON,MessagesTrsPk.fr());
f.getMapping().addEntry(TRANSLATION_STATISTICS,MessagesTrsStatistics.fr());
f.getMapping().addEntry(TRANSLATION_STATUS,MessagesTrsSt.fr());
f.getMapping().addEntry(TRANSLATION_TARGETS,MessagesTrsTargets.fr());
f.getMapping().addEntry(TRANSLATION_TYPES,MessagesTrsTypes.fr());
f.getMapping().addEntry(TRANSLATION_WINPTS,MessagesTrsWinPts.fr());
return f;
}
}
