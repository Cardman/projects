package code.scripts.pages.cards;

import code.sml.Document;
import code.util.StringMap;

public class PagesTarots {
private PagesTarots(){}
public static StringMap<Document> build(){
StringMap<Document> m_ = new StringMap<Document>();

m_.addEntry("resources_cards/classes/cards/tarot/detailsresults.html",PageTarotDetailsresults.build());
m_.addEntry("resources_cards/classes/cards/tarot/results.html",PageTarotResults.build());
m_.addEntry("resources_cards/classes/cards/tarot/rules.html",PageTarotRules.build());

return m_;
}
}
