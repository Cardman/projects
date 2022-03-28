package code.scripts.pages.cards;

import code.sml.Document;
import code.util.StringMap;

public class PagesPresidents {
private PagesPresidents(){}
public static StringMap<Document> build(){
StringMap<Document> m_ = new StringMap<Document>();

m_.addEntry("resources_cards/classes/cards/president/results.html",PagePresidentResults.build());
m_.addEntry("resources_cards/classes/cards/president/rules.html",PagePresidentRules.build());

return m_;
}
}
