package code.scripts.pages.cards;

import code.sml.Document;
import code.util.StringMap;

public final class PagesPresidents {
private PagesPresidents(){}
public static StringMap<Document> buildRules(){
StringMap<Document> mp_ = new StringMap<Document>();
mp_.addEntry("resources_cards/classes/cards/president/rules.html",PagePresidentRules.build());
return mp_;
}
public static StringMap<Document> build(){
StringMap<Document> mp_ = new StringMap<Document>();
mp_.addEntry("resources_cards/classes/cards/president/results.html",PagePresidentResults.build());
return mp_;
}
}
