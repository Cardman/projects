package code.scripts.pages.cards;

import code.sml.Document;
import code.util.StringMap;

public final class PagesBelotes {
private PagesBelotes(){}
public static StringMap<Document> build(){
StringMap<Document> mb_ = new StringMap<Document>();
mb_.addEntry("resources_cards/classes/cards/belote/detailsresults.html",PageBeloteDetailsresults.build());
mb_.addEntry("resources_cards/classes/cards/belote/results.html",PageBeloteResults.build());
mb_.addEntry("resources_cards/classes/cards/belote/rules.html",PageBeloteRules.build());
return mb_;
}
}
