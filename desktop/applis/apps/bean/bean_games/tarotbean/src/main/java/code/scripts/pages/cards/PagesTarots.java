package code.scripts.pages.cards;

import code.sml.Document;
import code.util.StringMap;

public final class PagesTarots {
private PagesTarots(){}
public static StringMap<Document> buildRules(){
StringMap<Document> mt_ = new StringMap<Document>();
mt_.addEntry("resources_cards/classes/cards/tarot/rules.html",PageTarotRules.build());
return mt_;
}
public static StringMap<Document> buildDetails(){
StringMap<Document> mt_ = new StringMap<Document>();
mt_.addEntry("resources_cards/classes/cards/tarot/detailsresults.html",PageTarotDetailsresults.build());
return mt_;
}
public static StringMap<Document> build(){
StringMap<Document> mt_ = new StringMap<Document>();
mt_.addEntry("resources_cards/classes/cards/tarot/results.html",PageTarotResults.build());
return mt_;
}
}
