package code.scripts.pages.cards;

import code.scripts.confs.PresidentScriptPages;
import code.sml.Document;
import code.util.StringMap;

public final class PagesPresidents {
private PagesPresidents(){}
public static StringMap<Document> buildRules(){
StringMap<Document> mp_ = new StringMap<Document>();
mp_.addEntry(PresidentScriptPages.RULES_PRESIDENT,PagePresidentRules.build());
return mp_;
}
public static StringMap<Document> build(){
StringMap<Document> mp_ = new StringMap<Document>();
mp_.addEntry(PresidentScriptPages.RES_PRESIDENT,PagePresidentResults.build());
return mp_;
}
}
