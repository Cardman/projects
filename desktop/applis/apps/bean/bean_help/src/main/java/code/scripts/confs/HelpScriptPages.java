package code.scripts.confs;

import code.bean.nat.analyze.NatConfigurationCore;
import code.util.CollCapacity;
import code.util.StringMap;

public final class HelpScriptPages {
    private HelpScriptPages() {

    }
    public static StringMap<NatConfigurationCore> cf(){
        StringMap<NatConfigurationCore> m = new StringMap<NatConfigurationCore>(new CollCapacity(71));
        m.addEntry("resources_cards/help/aide_generale",initConf("resources_cards/help/aide_generale"));
        m.addEntry("resources_cards/help/aide_generale/logiciel",initConf("resources_cards/help/aide_generale/logiciel"));
        m.addEntry("resources_cards/help/aide_generale/jeux",initConf("resources_cards/help/aide_generale/jeux"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus",initConf("resources_cards/help/aide_generale/logiciel/menus"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/fin_partie",initConf("resources_cards/help/aide_generale/logiciel/fin_partie"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote",initConf("resources_cards/help/aide_generale/jeux/belote"));
        m.addEntry("resources_cards/help/aide_generale/jeux/president",initConf("resources_cards/help/aide_generale/jeux/president"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot",initConf("resources_cards/help/aide_generale/jeux/tarot"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/fichier",initConf("resources_cards/help/aide_generale/logiciel/menus/fichier"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie",initConf("resources_cards/help/aide_generale/logiciel/menus/partie"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/aide",initConf("resources_cards/help/aide_generale/logiciel/menus/aide"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/cartes_utilisees",initConf("resources_cards/help/aide_generale/jeux/belote/cartes_utilisees"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/ordre_des_cartes",initConf("resources_cards/help/aide_generale/jeux/belote/ordre_des_cartes"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/distribution",initConf("resources_cards/help/aide_generale/jeux/belote/distribution"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/contrat",initConf("resources_cards/help/aide_generale/jeux/belote/contrat"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/annonces",initConf("resources_cards/help/aide_generale/jeux/belote/annonces"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/jeu_des_cartes",initConf("resources_cards/help/aide_generale/jeux/belote/jeu_des_cartes"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/fin_de_la_partie",initConf("resources_cards/help/aide_generale/jeux/belote/fin_de_la_partie"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/variantes",initConf("resources_cards/help/aide_generale/jeux/belote/variantes"));
        m.addEntry("resources_cards/help/aide_generale/jeux/president/cartes_utilisees",initConf("resources_cards/help/aide_generale/jeux/president/cartes_utilisees"));
        m.addEntry("resources_cards/help/aide_generale/jeux/president/ordre_des_cartes",initConf("resources_cards/help/aide_generale/jeux/president/ordre_des_cartes"));
        m.addEntry("resources_cards/help/aide_generale/jeux/president/distribution",initConf("resources_cards/help/aide_generale/jeux/president/distribution"));
        m.addEntry("resources_cards/help/aide_generale/jeux/president/jeu_des_cartes",initConf("resources_cards/help/aide_generale/jeux/president/jeu_des_cartes"));
        m.addEntry("resources_cards/help/aide_generale/jeux/president/variantes",initConf("resources_cards/help/aide_generale/jeux/president/variantes"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/cartes_utilisees",initConf("resources_cards/help/aide_generale/jeux/tarot/cartes_utilisees"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/ordre_des_cartes",initConf("resources_cards/help/aide_generale/jeux/tarot/ordre_des_cartes"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/distribution",initConf("resources_cards/help/aide_generale/jeux/tarot/distribution"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/contrat",initConf("resources_cards/help/aide_generale/jeux/tarot/contrat"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/chien",initConf("resources_cards/help/aide_generale/jeux/tarot/chien"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/annonces",initConf("resources_cards/help/aide_generale/jeux/tarot/annonces"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/jeu_des_cartes",initConf("resources_cards/help/aide_generale/jeux/tarot/jeu_des_cartes"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/fin_de_la_partie",initConf("resources_cards/help/aide_generale/jeux/tarot/fin_de_la_partie"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes",initConf("resources_cards/help/aide_generale/jeux/tarot/variantes"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/fichier/charger",initConf("resources_cards/help/aide_generale/logiciel/menus/fichier/charger"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/fichier/sauvegarder",initConf("resources_cards/help/aide_generale/logiciel/menus/fichier/sauvegarder"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/fichier/changer_jeu",initConf("resources_cards/help/aide_generale/logiciel/menus/fichier/changer_jeu"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/fichier/quitter",initConf("resources_cards/help/aide_generale/logiciel/menus/fichier/quitter"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/conseil",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/conseil"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/pause",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/pause"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/aide_au_jeu",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/aide_au_jeu"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/mains_plis",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/mains_plis"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/equipes",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/equipes"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/editer",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/editer"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/simulation",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/simulation"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/entrainement",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/entrainement"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/arret_multi",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/arret_multi"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/belote",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/belote"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/tarot",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/tarot"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/joueurs",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/joueurs"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/lancement",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/lancement"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/temporisation",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/temporisation"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/interaction",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/interaction"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/langue",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/langue"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/affichage",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/affichage"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/aide/aide_generale",initConf("resources_cards/help/aide_generale/logiciel/menus/aide/aide_generale"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/variantes/surcontrat",initConf("resources_cards/help/aide_generale/jeux/belote/variantes/surcontrat"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/variantes/fin_de_partie_differente",initConf("resources_cards/help/aide_generale/jeux/belote/variantes/fin_de_partie_differente"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/trois_joueurs",initConf("resources_cards/help/aide_generale/jeux/tarot/variantes/trois_joueurs"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/quatre_joueurs_rep",initConf("resources_cards/help/aide_generale/jeux/tarot/variantes/quatre_joueurs_rep"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/cinq_joueurs",initConf("resources_cards/help/aide_generale/jeux/tarot/variantes/cinq_joueurs"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/six_joueurs",initConf("resources_cards/help/aide_generale/jeux/tarot/variantes/six_joueurs"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/nouvelles_annonces",initConf("resources_cards/help/aide_generale/jeux/tarot/variantes/nouvelles_annonces"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/jeu_de_misere",initConf("resources_cards/help/aide_generale/jeux/tarot/variantes/jeu_de_misere"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/jeu_de_chacun_pour_soi",initConf("resources_cards/help/aide_generale/jeux/tarot/variantes/jeu_de_chacun_pour_soi"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/editer/belote",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/editer/belote"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/editer/tarot",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/editer/tarot"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/simulation/belote",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/simulation/belote"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/simulation/tarot",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/simulation/tarot"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/affichage/belote",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/affichage/belote"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/affichage/tarot",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/affichage/tarot"));
        return m;
    }
    static NatConfigurationCore initConf(String _url){
        NatConfigurationCore conf_ = new NatConfigurationCore();
        conf_.setFirstUrl(_url);
        return conf_;
    }


}
