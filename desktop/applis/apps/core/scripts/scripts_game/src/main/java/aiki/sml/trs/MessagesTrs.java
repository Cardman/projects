package aiki.sml.trs;
import aiki.db.*;
import code.sml.util.*;
public final class MessagesTrs{
public static final String EN = "en";
public static final String FR = "fr";
private MessagesTrs(){}
public static TranslationsAppli enPart(){
TranslationsAppli e=new TranslationsAppli(17);
e.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_ABILITIES,MessagesTrsAb.en());
e.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_BOOLEANS,MessagesTrsBooleans.en());
e.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_CATEGORIES,MessagesTrsCategories.en());
e.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_CLASSES,MessagesTrsClasses.en());
e.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_ENVIRONMENTS,MessagesTrsEnvironments.en());
e.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_GENDERS,MessagesTrsGenders.en());
e.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_ITEMS,MessagesTrsIt.en());
e.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_LITTERAL,MessagesTrsLiteral.en());
e.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_MATH,MessagesTrsMath.en());
e.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_MODELLAW,MessagesTrsModelLaw.en());
e.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_MOVES,MessagesTrsMv.en());
e.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_POKEMON,MessagesTrsPk.en());
e.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_STATISTICS,MessagesTrsStatistics.en());
e.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_STATUS,MessagesTrsSt.en());
e.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_TARGETS,MessagesTrsTargets.en());
e.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_TYPES,MessagesTrsTypes.en());
e.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_WINPTS,MessagesTrsWinPts.en());
return e;
}
public static TranslationsAppli frPart(){
TranslationsAppli f=new TranslationsAppli(17);
f.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_ABILITIES,MessagesTrsAb.fr());
f.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_BOOLEANS,MessagesTrsBooleans.fr());
f.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_CATEGORIES,MessagesTrsCategories.fr());
f.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_CLASSES,MessagesTrsClasses.fr());
f.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_ENVIRONMENTS,MessagesTrsEnvironments.fr());
f.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_GENDERS,MessagesTrsGenders.fr());
f.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_ITEMS,MessagesTrsIt.fr());
f.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_LITTERAL,MessagesTrsLiteral.fr());
f.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_MATH,MessagesTrsMath.fr());
f.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_MODELLAW,MessagesTrsModelLaw.fr());
f.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_MOVES,MessagesTrsMv.fr());
f.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_POKEMON,MessagesTrsPk.fr());
f.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_STATISTICS,MessagesTrsStatistics.fr());
f.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_STATUS,MessagesTrsSt.fr());
f.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_TARGETS,MessagesTrsTargets.fr());
f.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_TYPES,MessagesTrsTypes.fr());
f.getMapping().addEntry(MessagesDataBaseConstants.TRANSLATION_WINPTS,MessagesTrsWinPts.fr());
return f;
}
}
