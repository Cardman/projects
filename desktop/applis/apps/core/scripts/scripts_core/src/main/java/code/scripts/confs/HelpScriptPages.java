package code.scripts.confs;

import code.sml.*;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringMap;

public final class HelpScriptPages {
    private HelpScriptPages() {

    }
    public static StringMap<Document> docs(){
        StringMap<Document> m_ = new StringMap<Document>(new CollCapacity(142));
        m_.addEntry("resources_cards/help/en/aide_generale.xml",init0());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel.xml",init1());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux.xml",init2());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus.xml",init3());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/fin_partie.xml",init4());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/belote.xml",init5());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/president.xml",init6());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/tarot.xml",init7());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/fichier.xml",init8());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/partie.xml",init9());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/parametres.xml",init10());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/aide.xml",init11());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/belote/cartes_utilisees.xml", HelpScriptPagesImgs.init12());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/belote/ordre_des_cartes.xml", HelpScriptPagesImgs.init13());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/belote/distribution.xml",init14());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/belote/contrat.xml",init15());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/belote/annonces.xml",init16());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/belote/jeu_des_cartes.xml",init17());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/belote/fin_de_la_partie.xml",init18());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/belote/variantes.xml",init19());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/president/cartes_utilisees.xml", HelpScriptPagesImgs.init20());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/president/ordre_des_cartes.xml", HelpScriptPagesImgs.init21());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/president/distribution.xml",init22());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/president/jeu_des_cartes.xml",init23());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/president/variantes.xml",init24());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/tarot/cartes_utilisees.xml", HelpScriptPagesImgs.init25());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/tarot/ordre_des_cartes.xml", HelpScriptPagesImgs.init26());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/tarot/distribution.xml",init27());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/tarot/contrat.xml",init28());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/tarot/chien.xml",init29());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/tarot/annonces.xml",init30());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/tarot/jeu_des_cartes.xml",init31());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/tarot/fin_de_la_partie.xml",init32());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/tarot/variantes.xml",init33());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/fichier/charger.xml",init34());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/fichier/sauvegarder.xml",init35());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/fichier/changer_jeu.xml",init36());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/fichier/quitter.xml",init37());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/partie/conseil.xml",init38());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/partie/pause.xml",init39());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/partie/aide_au_jeu.xml",init40());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/partie/mains_plis.xml",init41());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/partie/equipes.xml",init42());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/partie/editer.xml",init43());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/partie/simulation.xml",init44());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/partie/entrainement.xml",init45());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/partie/arret_multi.xml",init46());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/parametres/belote.xml",init47());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/parametres/tarot.xml",init48());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/parametres/joueurs.xml",init49());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/parametres/lancement.xml",init50());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/parametres/temporisation.xml",init51());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/parametres/interaction.xml",init52());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/parametres/langue.xml",init53());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/parametres/affichage.xml",init54());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/aide/aide_generale.xml",init55());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/belote/variantes/surcontrat.xml",init56());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/belote/variantes/fin_de_partie_differente.xml",init57());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/tarot/variantes/trois_joueurs.xml",init58());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/tarot/variantes/quatre_joueurs_rep.xml",init59());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/tarot/variantes/cinq_joueurs.xml",init60());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/tarot/variantes/six_joueurs.xml",init61());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/tarot/variantes/nouvelles_annonces.xml",init62());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/tarot/variantes/jeu_de_misere.xml",init63());
        m_.addEntry("resources_cards/help/en/aide_generale/jeux/tarot/variantes/jeu_de_chacun_pour_soi.xml",init64());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/partie/editer/belote.xml",init65());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/partie/editer/tarot.xml",init66());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/partie/simulation/belote.xml",init67());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/partie/simulation/tarot.xml",init68());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/parametres/affichage/belote.xml",init69());
        m_.addEntry("resources_cards/help/en/aide_generale/logiciel/menus/parametres/affichage/tarot.xml",init70());
        m_.addEntry("resources_cards/help/fr/aide_generale.xml",init71());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel.xml",init72());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux.xml",init73());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus.xml",init74());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/fin_partie.xml",init75());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/belote.xml",init76());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/president.xml",init77());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/tarot.xml",init78());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/fichier.xml",init79());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/partie.xml",init80());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/parametres.xml",init81());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/aide.xml",init82());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/belote/cartes_utilisees.xml", HelpScriptPagesImgs.init83());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/belote/ordre_des_cartes.xml", HelpScriptPagesImgs.init84());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/belote/distribution.xml",init85());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/belote/contrat.xml",init86());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/belote/annonces.xml",init87());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/belote/jeu_des_cartes.xml",init88());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/belote/fin_de_la_partie.xml",init89());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/belote/variantes.xml",init90());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/president/cartes_utilisees.xml", HelpScriptPagesImgs.init91());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/president/ordre_des_cartes.xml", HelpScriptPagesImgs.init92());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/president/distribution.xml",init93());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/president/jeu_des_cartes.xml",init94());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/president/variantes.xml",init95());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/tarot/cartes_utilisees.xml", HelpScriptPagesImgs.init96());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/tarot/ordre_des_cartes.xml", HelpScriptPagesImgs.init97());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/tarot/distribution.xml",init98());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/tarot/contrat.xml",init99());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/tarot/chien.xml",init100());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/tarot/annonces.xml",init101());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/tarot/jeu_des_cartes.xml",init102());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/tarot/fin_de_la_partie.xml",init103());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/tarot/variantes.xml",init104());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/fichier/charger.xml",init105());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/fichier/sauvegarder.xml",init106());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/fichier/changer_jeu.xml",init107());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/fichier/quitter.xml",init108());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/partie/conseil.xml",init109());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/partie/pause.xml",init110());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/partie/aide_au_jeu.xml",init111());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/partie/mains_plis.xml",init112());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/partie/equipes.xml",init113());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/partie/editer.xml",init114());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/partie/simulation.xml",init115());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/partie/entrainement.xml",init116());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/partie/arret_multi.xml",init117());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/parametres/belote.xml",init118());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/parametres/tarot.xml",init119());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/parametres/joueurs.xml",init120());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/parametres/lancement.xml",init121());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/parametres/temporisation.xml",init122());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/parametres/interaction.xml",init123());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/parametres/langue.xml",init124());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/parametres/affichage.xml",init125());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/aide/aide_generale.xml",init126());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/belote/variantes/surcontrat.xml",init127());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/belote/variantes/fin_de_partie_differente.xml",init128());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/tarot/variantes/trois_joueurs.xml",init129());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/tarot/variantes/quatre_joueurs_rep.xml",init130());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/tarot/variantes/cinq_joueurs.xml",init131());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/tarot/variantes/six_joueurs.xml",init132());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/tarot/variantes/nouvelles_annonces.xml",init133());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/tarot/variantes/jeu_de_misere.xml",init134());
        m_.addEntry("resources_cards/help/fr/aide_generale/jeux/tarot/variantes/jeu_de_chacun_pour_soi.xml",init135());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/partie/editer/belote.xml",init136());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/partie/editer/tarot.xml",init137());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/partie/simulation/belote.xml",init138());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/partie/simulation/tarot.xml",init139());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/parametres/affichage/belote.xml",init140());
        m_.addEntry("resources_cards/help/fr/aide_generale/logiciel/menus/parametres/affichage/tarot.xml",init141());
        return m_;
    }

    static Document init0(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init1(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init2(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init3(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init4(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/fin_partie.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init5(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/belote.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init6(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/president.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init7(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init8(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/fichier.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init9(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init10(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init11(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/aide.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }

    static Document init14(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/belote/distribution.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init15(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/belote/contrat.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init16(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/belote/annonces.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init17(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/belote/jeu_des_cartes.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init18(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/belote/fin_de_la_partie.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init19(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/belote/variantes.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }

    static Document init22(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/president/distribution.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init23(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/president/jeu_des_cartes.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init24(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/president/variantes.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }

    static Document init27(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/distribution.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init28(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/contrat.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init29(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/chien.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init30(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/annonces.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init31(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/jeu_des_cartes.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init32(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/fin_de_la_partie.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init33(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/variantes.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init34(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/fichier/charger.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init35(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/fichier/sauvegarder.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init36(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/fichier/changer_jeu.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init37(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/fichier/quitter.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init38(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/conseil.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init39(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/pause.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init40(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/aide_au_jeu.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init41(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/mains_plis.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init42(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/equipes.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init43(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/editer.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init44(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/simulation.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init45(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/entrainement.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init46(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/arret_multi.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init47(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/belote.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init48(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/tarot.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init49(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/joueurs.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init50(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/lancement.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init51(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/temporisation.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init52(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/interaction.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init53(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/langue.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init54(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/affichage.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init55(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/aide/aide_generale.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init56(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/belote/variantes/surcontrat.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init57(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/belote/variantes/fin_de_partie_differente.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init58(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/variantes/trois_joueurs.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init59(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/variantes/quatre_joueurs_rep.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init60(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/variantes/cinq_joueurs.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init61(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/variantes/six_joueurs.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init62(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/variantes/nouvelles_annonces.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init63(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/variantes/jeu_de_misere.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init64(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/variantes/jeu_de_chacun_pour_soi.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init65(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/editer/belote.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init66(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/editer/tarot.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init67(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/simulation/belote.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init68(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/simulation/tarot.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init69(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/affichage/belote.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init70(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/affichage/tarot.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init71(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init72(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init73(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init74(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init75(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/fin_partie.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init76(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/belote.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init77(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/president.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init78(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init79(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/fichier.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init80(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init81(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init82(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/aide.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }

    static Document init85(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/belote/distribution.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init86(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/belote/contrat.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init87(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/belote/annonces.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init88(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/belote/jeu_des_cartes.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init89(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/belote/fin_de_la_partie.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init90(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/belote/variantes.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }

    static Document init93(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/president/distribution.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init94(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/president/jeu_des_cartes.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init95(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/president/variantes.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }

    static Document init98(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/distribution.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init99(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/contrat.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init100(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/chien.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init101(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/annonces.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init102(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/jeu_des_cartes.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init103(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/fin_de_la_partie.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init104(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/variantes.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init105(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/fichier/charger.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init106(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/fichier/sauvegarder.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init107(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/fichier/changer_jeu.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init108(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/fichier/quitter.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init109(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/conseil.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init110(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/pause.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init111(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/aide_au_jeu.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init112(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/mains_plis.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init113(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/equipes.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init114(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/editer.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init115(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/simulation.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init116(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/entrainement.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init117(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/arret_multi.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init118(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/belote.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init119(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/tarot.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init120(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/joueurs.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init121(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/lancement.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init122(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/temporisation.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init123(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/interaction.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init124(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/langue.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init125(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/affichage.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init126(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/aide/aide_generale.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init127(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/belote/variantes/surcontrat.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init128(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/belote/variantes/fin_de_partie_differente.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init129(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/variantes/trois_joueurs.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init130(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/variantes/quatre_joueurs_rep.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init131(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/variantes/cinq_joueurs.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init132(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/variantes/six_joueurs.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init133(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/variantes/nouvelles_annonces.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init134(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/variantes/jeu_de_misere.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init135(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/jeux/tarot/variantes/jeu_de_chacun_pour_soi.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init136(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/editer/belote.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init137(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/editer/tarot.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init138(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/simulation/belote.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init139(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/partie/simulation/tarot.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init140(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/affichage/belote.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }
    static Document init141(){
        FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
        Element elt0_=doc_.createElement("cfg");
        CustList<Attr> attrs0_=new CustList<Attr>(new CollCapacity(0));
        elt0_.setAttributes(new NamedNodeMap(attrs0_));
        Element elt1_=doc_.createElement("java.lang.String");
        CustList<Attr> attrs1_=new CustList<Attr>(new CollCapacity(3));
        attrs1_.add(CoreDocument.createAttribute("class","cfg"));
        attrs1_.add(CoreDocument.createAttribute("field","firstUrl"));
        attrs1_.add(CoreDocument.createAttribute("value","resources_cards/help//aide_generale/logiciel/menus/parametres/affichage/tarot.html"));
        elt1_.setAttributes(new NamedNodeMap(attrs1_));
        elt0_.appendChild(elt1_);
        doc_.appendChild(elt0_);
        return doc_;
    }


}
