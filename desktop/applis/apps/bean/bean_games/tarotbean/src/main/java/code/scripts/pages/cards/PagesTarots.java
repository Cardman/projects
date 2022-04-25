package code.scripts.pages.cards;

import code.sml.Document;
import code.util.StringMap;

public final class PagesTarots {
private PagesTarots(){}
public static StringMap<Document> build(){
StringMap<Document> mp_ = new StringMap<Document>();

mp_.addEntry("resources_cards/classes/cards/tarot/detailsresults.html",PageTarotDetailsresults.build());
mp_.addEntry("resources_cards/classes/cards/tarot/results.html",PageTarotResults.build());
mp_.addEntry("resources_cards/classes/cards/tarot/rules.html",PageTarotRules.build());

return mp_;
}
}
