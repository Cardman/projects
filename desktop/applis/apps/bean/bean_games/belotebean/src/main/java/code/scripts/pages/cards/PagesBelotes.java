package code.scripts.pages.cards;

import code.scripts.confs.BeloteScriptPages;
import code.sml.Document;
import code.util.StringMap;

public final class PagesBelotes {
private PagesBelotes(){}
public static StringMap<Document> buildRules(){
StringMap<Document> mb_ = new StringMap<Document>();
mb_.addEntry(BeloteScriptPages.RULES_BELOTE,PageBeloteRules.build());
return mb_;
}
public static StringMap<Document> buildDetails(){
StringMap<Document> mb_ = new StringMap<Document>();
mb_.addEntry(BeloteScriptPages.DET_BELOTE,PageBeloteDetailsresults.build());
return mb_;
}
public static StringMap<Document> build(){
StringMap<Document> mb_ = new StringMap<Document>();
mb_.addEntry(BeloteScriptPages.RES_BELOTE,PageBeloteResults.build());
return mb_;
}
}
