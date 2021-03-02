package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
public final class PageCards{
private PageCards(){}
public static StringMap<Document> build(){
StringMap<Document> m_ = new StringMap<Document>();
m_.addEntry("resources_cards/classes/cards/belote/detailsresults.html",PageBeloteDetailsresults.build());
m_.addEntry("resources_cards/classes/cards/belote/results.html",PageBeloteResults.build());
m_.addEntry("resources_cards/classes/cards/belote/rules.html",PageBeloteRules.build());

m_.addEntry("resources_cards/classes/cards/president/results.html",PagePresidentResults.build());
m_.addEntry("resources_cards/classes/cards/president/rules.html",PagePresidentRules.build());

m_.addEntry("resources_cards/classes/cards/tarot/detailsresults.html",PageTarotDetailsresults.build());
m_.addEntry("resources_cards/classes/cards/tarot/results.html",PageTarotResults.build());
m_.addEntry("resources_cards/classes/cards/tarot/rules.html",PageTarotRules.build());
return m_;
}
}