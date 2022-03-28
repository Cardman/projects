package code.scripts.pages.cards;

import code.sml.Document;
import code.util.StringMap;

public class PagesBelotes {
private PagesBelotes(){}
public static StringMap<Document> build(){
StringMap<Document> m_ = new StringMap<Document>();
m_.addEntry("resources_cards/classes/cards/belote/detailsresults.html",PageBeloteDetailsresults.build());
m_.addEntry("resources_cards/classes/cards/belote/results.html",PageBeloteResults.build());
m_.addEntry("resources_cards/classes/cards/belote/rules.html",PageBeloteRules.build());
return m_;
}
}
