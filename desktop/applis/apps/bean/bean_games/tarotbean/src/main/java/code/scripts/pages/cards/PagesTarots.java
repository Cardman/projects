package code.scripts.pages.cards;

import code.scripts.confs.TarotScriptPages;
import code.sml.Document;
import code.util.StringMap;

public final class PagesTarots {
private PagesTarots(){}
public static StringMap<Document> buildRules(){
StringMap<Document> mt_ = new StringMap<Document>();
mt_.addEntry(TarotScriptPages.RULES_TAROT,PageTarotRules.build());
return mt_;
}
public static StringMap<Document> buildDetails(){
StringMap<Document> mt_ = new StringMap<Document>();
mt_.addEntry(TarotScriptPages.DET_TAROT,PageTarotDetailsresults.build());
return mt_;
}
public static StringMap<Document> build(){
StringMap<Document> mt_ = new StringMap<Document>();
mt_.addEntry(TarotScriptPages.RES_TAROT,PageTarotResults.build());
return mt_;
}
}
