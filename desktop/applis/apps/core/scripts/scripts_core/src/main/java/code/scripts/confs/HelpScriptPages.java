package code.scripts.confs;

import code.sml.*;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringMap;

public final class HelpScriptPages {
    private HelpScriptPages() {

    }
    public static StringMap<Document> docs(){
        StringMap<Document> m_ = new StringMap<Document>(new CollCapacity(71));
        m_.addEntry("resources_cards/help/aide_generale.xml",init0());
        m_.addEntry("resources_cards/help/aide_generale/logiciel.xml",init1());
        m_.addEntry("resources_cards/help/aide_generale/jeux.xml",init2());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus.xml",init3());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/fin_partie.xml",init4());
        m_.addEntry("resources_cards/help/aide_generale/jeux/belote.xml",init5());
        m_.addEntry("resources_cards/help/aide_generale/jeux/president.xml",init6());
        m_.addEntry("resources_cards/help/aide_generale/jeux/tarot.xml",init7());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/fichier.xml",init8());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie.xml",init9());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres.xml",init10());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/aide.xml",init11());
        m_.addEntry("resources_cards/help/aide_generale/jeux/belote/cartes_utilisees.xml", HelpScriptPagesImgs.init12("resources_cards/help//aide_generale/jeux/belote/cartes_utilisees.html"));
        m_.addEntry("resources_cards/help/aide_generale/jeux/belote/ordre_des_cartes.xml", HelpScriptPagesImgs.init12("resources_cards/help//aide_generale/jeux/belote/ordre_des_cartes.html"));
        m_.addEntry("resources_cards/help/aide_generale/jeux/belote/distribution.xml",init14());
        m_.addEntry("resources_cards/help/aide_generale/jeux/belote/contrat.xml",init15());
        m_.addEntry("resources_cards/help/aide_generale/jeux/belote/annonces.xml",init16());
        m_.addEntry("resources_cards/help/aide_generale/jeux/belote/jeu_des_cartes.xml",init17());
        m_.addEntry("resources_cards/help/aide_generale/jeux/belote/fin_de_la_partie.xml",init18());
        m_.addEntry("resources_cards/help/aide_generale/jeux/belote/variantes.xml",init19());
        m_.addEntry("resources_cards/help/aide_generale/jeux/president/cartes_utilisees.xml", HelpScriptPagesImgs.init12("resources_cards/help//aide_generale/jeux/president/cartes_utilisees.html"));
        m_.addEntry("resources_cards/help/aide_generale/jeux/president/ordre_des_cartes.xml", HelpScriptPagesImgs.init12("resources_cards/help//aide_generale/jeux/president/ordre_des_cartes.html"));
        m_.addEntry("resources_cards/help/aide_generale/jeux/president/distribution.xml",init22());
        m_.addEntry("resources_cards/help/aide_generale/jeux/president/jeu_des_cartes.xml",init23());
        m_.addEntry("resources_cards/help/aide_generale/jeux/president/variantes.xml",init24());
        m_.addEntry("resources_cards/help/aide_generale/jeux/tarot/cartes_utilisees.xml", HelpScriptPagesImgs.init12("resources_cards/help//aide_generale/jeux/tarot/cartes_utilisees.html"));
        m_.addEntry("resources_cards/help/aide_generale/jeux/tarot/ordre_des_cartes.xml", HelpScriptPagesImgs.init12("resources_cards/help//aide_generale/jeux/tarot/ordre_des_cartes.html"));
        m_.addEntry("resources_cards/help/aide_generale/jeux/tarot/distribution.xml",init27());
        m_.addEntry("resources_cards/help/aide_generale/jeux/tarot/contrat.xml",init28());
        m_.addEntry("resources_cards/help/aide_generale/jeux/tarot/chien.xml",init29());
        m_.addEntry("resources_cards/help/aide_generale/jeux/tarot/annonces.xml",init30());
        m_.addEntry("resources_cards/help/aide_generale/jeux/tarot/jeu_des_cartes.xml",init31());
        m_.addEntry("resources_cards/help/aide_generale/jeux/tarot/fin_de_la_partie.xml",init32());
        m_.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes.xml",init33());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/fichier/charger.xml",init34());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/fichier/sauvegarder.xml",init35());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/fichier/changer_jeu.xml",init36());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/fichier/quitter.xml",init37());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/conseil.xml",init38());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/pause.xml",init39());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/aide_au_jeu.xml",init40());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/mains_plis.xml",init41());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/equipes.xml",init42());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/editer.xml",init43());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/simulation.xml",init44());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/entrainement.xml",init45());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/arret_multi.xml",init46());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/belote.xml",init47());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/tarot.xml",init48());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/joueurs.xml",init49());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/lancement.xml",init50());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/temporisation.xml",init51());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/interaction.xml",init52());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/langue.xml",init53());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/affichage.xml",init54());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/aide/aide_generale.xml",init55());
        m_.addEntry("resources_cards/help/aide_generale/jeux/belote/variantes/surcontrat.xml",init56());
        m_.addEntry("resources_cards/help/aide_generale/jeux/belote/variantes/fin_de_partie_differente.xml",init57());
        m_.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/trois_joueurs.xml",init58());
        m_.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/quatre_joueurs_rep.xml",init59());
        m_.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/cinq_joueurs.xml",init60());
        m_.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/six_joueurs.xml",init61());
        m_.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/nouvelles_annonces.xml",init62());
        m_.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/jeu_de_misere.xml",init63());
        m_.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/jeu_de_chacun_pour_soi.xml",init64());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/editer/belote.xml",init65());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/editer/tarot.xml",init66());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/simulation/belote.xml",init67());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/simulation/tarot.xml",init68());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/affichage/belote.xml",init69());
        m_.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/affichage/tarot.xml",init70());
        return m_;
    }

    static Document init0(){
        return common("resources_cards/help//aide_generale.html");
    }
    static Document init1(){
        return common("resources_cards/help//aide_generale/logiciel.html");
    }
    static Document init2(){
        return common("resources_cards/help//aide_generale/jeux.html");
    }
    static Document init3(){
        return common("resources_cards/help//aide_generale/logiciel/menus.html");
    }
    static Document init4(){
        return common("resources_cards/help//aide_generale/logiciel/fin_partie.html");
    }
    static Document init5(){
        return common("resources_cards/help//aide_generale/jeux/belote.html");
    }
    static Document init6(){
        return common("resources_cards/help//aide_generale/jeux/president.html");
    }
    static Document init7(){
        return common("resources_cards/help//aide_generale/jeux/tarot.html");
    }
    static Document init8(){
        return common("resources_cards/help//aide_generale/logiciel/menus/fichier.html");
    }
    static Document init9(){
        return common("resources_cards/help//aide_generale/logiciel/menus/partie.html");
    }
    static Document init10(){
        return common("resources_cards/help//aide_generale/logiciel/menus/parametres.html");
    }
    static Document init11(){
        return common("resources_cards/help//aide_generale/logiciel/menus/aide.html");
    }

    static Document init14(){
        return common("resources_cards/help//aide_generale/jeux/belote/distribution.html");
    }
    static Document init15(){
        return common("resources_cards/help//aide_generale/jeux/belote/contrat.html");
    }
    static Document init16(){
        return common("resources_cards/help//aide_generale/jeux/belote/annonces.html");
    }
    static Document init17(){
        return common("resources_cards/help//aide_generale/jeux/belote/jeu_des_cartes.html");
    }
    static Document init18(){
        return common("resources_cards/help//aide_generale/jeux/belote/fin_de_la_partie.html");
    }
    static Document init19(){
        return common("resources_cards/help//aide_generale/jeux/belote/variantes.html");
    }

    static Document init22(){
        return common("resources_cards/help//aide_generale/jeux/president/distribution.html");
    }
    static Document init23(){
        return common("resources_cards/help//aide_generale/jeux/president/jeu_des_cartes.html");
    }
    static Document init24(){
        return common("resources_cards/help//aide_generale/jeux/president/variantes.html");
    }

    static Document init27(){
        return common("resources_cards/help//aide_generale/jeux/tarot/distribution.html");
    }
    static Document init28(){
        return common("resources_cards/help//aide_generale/jeux/tarot/contrat.html");
    }
    static Document init29(){
        return common("resources_cards/help//aide_generale/jeux/tarot/chien.html");
    }
    static Document init30(){
        return common("resources_cards/help//aide_generale/jeux/tarot/annonces.html");
    }
    static Document init31(){
        return common("resources_cards/help//aide_generale/jeux/tarot/jeu_des_cartes.html");
    }
    static Document init32(){
        return common("resources_cards/help//aide_generale/jeux/tarot/fin_de_la_partie.html");
    }
    static Document init33(){
        return common("resources_cards/help//aide_generale/jeux/tarot/variantes.html");
    }
    static Document init34(){
        return common("resources_cards/help//aide_generale/logiciel/menus/fichier/charger.html");
    }
    static Document init35(){
        return common("resources_cards/help//aide_generale/logiciel/menus/fichier/sauvegarder.html");
    }
    static Document init36(){
        return common("resources_cards/help//aide_generale/logiciel/menus/fichier/changer_jeu.html");
    }
    static Document init37(){
        return common("resources_cards/help//aide_generale/logiciel/menus/fichier/quitter.html");
    }
    static Document init38(){
        return common("resources_cards/help//aide_generale/logiciel/menus/partie/conseil.html");
    }
    static Document init39(){
        return common("resources_cards/help//aide_generale/logiciel/menus/partie/pause.html");
    }
    static Document init40(){
        return common("resources_cards/help//aide_generale/logiciel/menus/partie/aide_au_jeu.html");
    }
    static Document init41(){
        return common("resources_cards/help//aide_generale/logiciel/menus/partie/mains_plis.html");
    }
    static Document init42(){
        return common("resources_cards/help//aide_generale/logiciel/menus/partie/equipes.html");
    }
    static Document init43(){
        return common("resources_cards/help//aide_generale/logiciel/menus/partie/editer.html");
    }
    static Document init44(){
        return common("resources_cards/help//aide_generale/logiciel/menus/partie/simulation.html");
    }
    static Document init45(){
        return common("resources_cards/help//aide_generale/logiciel/menus/partie/entrainement.html");
    }
    static Document init46(){
        return common("resources_cards/help//aide_generale/logiciel/menus/partie/arret_multi.html");
    }
    static Document init47(){
        return common("resources_cards/help//aide_generale/logiciel/menus/parametres/belote.html");
    }
    static Document init48(){
        return common("resources_cards/help//aide_generale/logiciel/menus/parametres/tarot.html");
    }
    static Document init49(){
        return common("resources_cards/help//aide_generale/logiciel/menus/parametres/joueurs.html");
    }
    static Document init50(){
        return common("resources_cards/help//aide_generale/logiciel/menus/parametres/lancement.html");
    }
    static Document init51(){
        return common("resources_cards/help//aide_generale/logiciel/menus/parametres/temporisation.html");
    }
    static Document init52(){
        return common("resources_cards/help//aide_generale/logiciel/menus/parametres/interaction.html");
    }
    static Document init53(){
        return common("resources_cards/help//aide_generale/logiciel/menus/parametres/langue.html");
    }
    static Document init54(){
        return common("resources_cards/help//aide_generale/logiciel/menus/parametres/affichage.html");
    }
    static Document init55(){
        return common("resources_cards/help//aide_generale/logiciel/menus/aide/aide_generale.html");
    }
    static Document init56(){
        return common("resources_cards/help//aide_generale/jeux/belote/variantes/surcontrat.html");
    }
    static Document init57(){
        return common("resources_cards/help//aide_generale/jeux/belote/variantes/fin_de_partie_differente.html");
    }
    static Document init58(){
        return common("resources_cards/help//aide_generale/jeux/tarot/variantes/trois_joueurs.html");
    }
    static Document init59(){
        return common("resources_cards/help//aide_generale/jeux/tarot/variantes/quatre_joueurs_rep.html");
    }
    static Document init60(){
        return common("resources_cards/help//aide_generale/jeux/tarot/variantes/cinq_joueurs.html");
    }
    static Document init61(){
        return common("resources_cards/help//aide_generale/jeux/tarot/variantes/six_joueurs.html");
    }
    static Document init62(){
        return common("resources_cards/help//aide_generale/jeux/tarot/variantes/nouvelles_annonces.html");
    }
    static Document init63(){
        return common("resources_cards/help//aide_generale/jeux/tarot/variantes/jeu_de_misere.html");
    }
    static Document init64(){
        return common("resources_cards/help//aide_generale/jeux/tarot/variantes/jeu_de_chacun_pour_soi.html");
    }
    static Document init65(){
        return common("resources_cards/help//aide_generale/logiciel/menus/partie/editer/belote.html");
    }
    static Document init66(){
        return common("resources_cards/help//aide_generale/logiciel/menus/partie/editer/tarot.html");
    }
    static Document init67(){
        return common("resources_cards/help//aide_generale/logiciel/menus/partie/simulation/belote.html");
    }
    static Document init68(){
        return common("resources_cards/help//aide_generale/logiciel/menus/partie/simulation/tarot.html");
    }
    static Document init69(){
        return common("resources_cards/help//aide_generale/logiciel/menus/parametres/affichage/belote.html");
    }
    static Document init70(){
        return common("resources_cards/help//aide_generale/logiciel/menus/parametres/affichage/tarot.html");
    }

    private static Document common(String _s) {
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_ = doc_.createElement("cfg");
        CustList<Attr> attrs0_ = new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_ = doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_ = new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class", "cfg"));
        attrs1_.add(CoreDocument.createAttribute("field", "firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value", _s));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }


}
