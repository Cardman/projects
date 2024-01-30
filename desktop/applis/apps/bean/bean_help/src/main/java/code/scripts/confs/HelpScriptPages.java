package code.scripts.confs;

import code.bean.nat.analyze.NatConfigurationCore;
import code.util.CollCapacity;
import code.util.StringMap;

public final class HelpScriptPages {
    private HelpScriptPages() {

    }
    public static StringMap<NatConfigurationCore> cf(){
        StringMap<NatConfigurationCore> m = new StringMap<NatConfigurationCore>(new CollCapacity(71));
        m.addEntry("aide_generale",initConf("aide_generale"));
        m.addEntry("aide_generale/logiciel",initConf("aide_generale/logiciel"));
        m.addEntry("aide_generale/jeux",initConf("aide_generale/jeux"));
        m.addEntry("aide_generale/logiciel/menus",initConf("aide_generale/logiciel/menus"));
        m.addEntry("aide_generale/logiciel/fin_partie",initConf("aide_generale/logiciel/fin_partie"));
        m.addEntry("aide_generale/jeux/belote",initConf("aide_generale/jeux/belote"));
        m.addEntry("aide_generale/jeux/president",initConf("aide_generale/jeux/president"));
        m.addEntry("aide_generale/jeux/tarot",initConf("aide_generale/jeux/tarot"));
        m.addEntry("aide_generale/logiciel/menus/fichier",initConf("aide_generale/logiciel/menus/fichier"));
        m.addEntry("aide_generale/logiciel/menus/partie",initConf("aide_generale/logiciel/menus/partie"));
        m.addEntry("aide_generale/logiciel/menus/parametres",initConf("aide_generale/logiciel/menus/parametres"));
        m.addEntry("aide_generale/logiciel/menus/aide",initConf("aide_generale/logiciel/menus/aide"));
        m.addEntry("aide_generale/jeux/belote/cartes_utilisees",initConf("aide_generale/jeux/belote/cartes_utilisees"));
        m.addEntry("aide_generale/jeux/belote/ordre_des_cartes",initConf("aide_generale/jeux/belote/ordre_des_cartes"));
        m.addEntry("aide_generale/jeux/belote/distribution",initConf("aide_generale/jeux/belote/distribution"));
        m.addEntry("aide_generale/jeux/belote/contrat",initConf("aide_generale/jeux/belote/contrat"));
        m.addEntry("aide_generale/jeux/belote/annonces",initConf("aide_generale/jeux/belote/annonces"));
        m.addEntry("aide_generale/jeux/belote/jeu_des_cartes",initConf("aide_generale/jeux/belote/jeu_des_cartes"));
        m.addEntry("aide_generale/jeux/belote/fin_de_la_partie",initConf("aide_generale/jeux/belote/fin_de_la_partie"));
        m.addEntry("aide_generale/jeux/belote/variantes",initConf("aide_generale/jeux/belote/variantes"));
        m.addEntry("aide_generale/jeux/president/cartes_utilisees",initConf("aide_generale/jeux/president/cartes_utilisees"));
        m.addEntry("aide_generale/jeux/president/ordre_des_cartes",initConf("aide_generale/jeux/president/ordre_des_cartes"));
        m.addEntry("aide_generale/jeux/president/distribution",initConf("aide_generale/jeux/president/distribution"));
        m.addEntry("aide_generale/jeux/president/jeu_des_cartes",initConf("aide_generale/jeux/president/jeu_des_cartes"));
        m.addEntry("aide_generale/jeux/president/variantes",initConf("aide_generale/jeux/president/variantes"));
        m.addEntry("aide_generale/jeux/tarot/cartes_utilisees",initConf("aide_generale/jeux/tarot/cartes_utilisees"));
        m.addEntry("aide_generale/jeux/tarot/ordre_des_cartes",initConf("aide_generale/jeux/tarot/ordre_des_cartes"));
        m.addEntry("aide_generale/jeux/tarot/distribution",initConf("aide_generale/jeux/tarot/distribution"));
        m.addEntry("aide_generale/jeux/tarot/contrat",initConf("aide_generale/jeux/tarot/contrat"));
        m.addEntry("aide_generale/jeux/tarot/chien",initConf("aide_generale/jeux/tarot/chien"));
        m.addEntry("aide_generale/jeux/tarot/annonces",initConf("aide_generale/jeux/tarot/annonces"));
        m.addEntry("aide_generale/jeux/tarot/jeu_des_cartes",initConf("aide_generale/jeux/tarot/jeu_des_cartes"));
        m.addEntry("aide_generale/jeux/tarot/fin_de_la_partie",initConf("aide_generale/jeux/tarot/fin_de_la_partie"));
        m.addEntry("aide_generale/jeux/tarot/variantes",initConf("aide_generale/jeux/tarot/variantes"));
        m.addEntry("aide_generale/logiciel/menus/fichier/charger",initConf("aide_generale/logiciel/menus/fichier/charger"));
        m.addEntry("aide_generale/logiciel/menus/fichier/sauvegarder",initConf("aide_generale/logiciel/menus/fichier/sauvegarder"));
        m.addEntry("aide_generale/logiciel/menus/fichier/changer_jeu",initConf("aide_generale/logiciel/menus/fichier/changer_jeu"));
        m.addEntry("aide_generale/logiciel/menus/fichier/quitter",initConf("aide_generale/logiciel/menus/fichier/quitter"));
        m.addEntry("aide_generale/logiciel/menus/partie/conseil",initConf("aide_generale/logiciel/menus/partie/conseil"));
        m.addEntry("aide_generale/logiciel/menus/partie/pause",initConf("aide_generale/logiciel/menus/partie/pause"));
        m.addEntry("aide_generale/logiciel/menus/partie/aide_au_jeu",initConf("aide_generale/logiciel/menus/partie/aide_au_jeu"));
        m.addEntry("aide_generale/logiciel/menus/partie/mains_plis",initConf("aide_generale/logiciel/menus/partie/mains_plis"));
        m.addEntry("aide_generale/logiciel/menus/partie/equipes",initConf("aide_generale/logiciel/menus/partie/equipes"));
        m.addEntry("aide_generale/logiciel/menus/partie/editer",initConf("aide_generale/logiciel/menus/partie/editer"));
        m.addEntry("aide_generale/logiciel/menus/partie/simulation",initConf("aide_generale/logiciel/menus/partie/simulation"));
        m.addEntry("aide_generale/logiciel/menus/partie/entrainement",initConf("aide_generale/logiciel/menus/partie/entrainement"));
        m.addEntry("aide_generale/logiciel/menus/partie/arret_multi",initConf("aide_generale/logiciel/menus/partie/arret_multi"));
        m.addEntry("aide_generale/logiciel/menus/parametres/belote",initConf("aide_generale/logiciel/menus/parametres/belote"));
        m.addEntry("aide_generale/logiciel/menus/parametres/tarot",initConf("aide_generale/logiciel/menus/parametres/tarot"));
        m.addEntry("aide_generale/logiciel/menus/parametres/joueurs",initConf("aide_generale/logiciel/menus/parametres/joueurs"));
        m.addEntry("aide_generale/logiciel/menus/parametres/lancement",initConf("aide_generale/logiciel/menus/parametres/lancement"));
        m.addEntry("aide_generale/logiciel/menus/parametres/temporisation",initConf("aide_generale/logiciel/menus/parametres/temporisation"));
        m.addEntry("aide_generale/logiciel/menus/parametres/interaction",initConf("aide_generale/logiciel/menus/parametres/interaction"));
        m.addEntry("aide_generale/logiciel/menus/parametres/langue",initConf("aide_generale/logiciel/menus/parametres/langue"));
        m.addEntry("aide_generale/logiciel/menus/parametres/affichage",initConf("aide_generale/logiciel/menus/parametres/affichage"));
        m.addEntry("aide_generale/logiciel/menus/aide/aide_generale",initConf("aide_generale/logiciel/menus/aide/aide_generale"));
        m.addEntry("aide_generale/jeux/belote/variantes/surcontrat",initConf("aide_generale/jeux/belote/variantes/surcontrat"));
        m.addEntry("aide_generale/jeux/belote/variantes/fin_de_partie_differente",initConf("aide_generale/jeux/belote/variantes/fin_de_partie_differente"));
        m.addEntry("aide_generale/jeux/tarot/variantes/trois_joueurs",initConf("aide_generale/jeux/tarot/variantes/trois_joueurs"));
        m.addEntry("aide_generale/jeux/tarot/variantes/quatre_joueurs_rep",initConf("aide_generale/jeux/tarot/variantes/quatre_joueurs_rep"));
        m.addEntry("aide_generale/jeux/tarot/variantes/cinq_joueurs",initConf("aide_generale/jeux/tarot/variantes/cinq_joueurs"));
        m.addEntry("aide_generale/jeux/tarot/variantes/six_joueurs",initConf("aide_generale/jeux/tarot/variantes/six_joueurs"));
        m.addEntry("aide_generale/jeux/tarot/variantes/nouvelles_annonces",initConf("aide_generale/jeux/tarot/variantes/nouvelles_annonces"));
        m.addEntry("aide_generale/jeux/tarot/variantes/jeu_de_misere",initConf("aide_generale/jeux/tarot/variantes/jeu_de_misere"));
        m.addEntry("aide_generale/jeux/tarot/variantes/jeu_de_chacun_pour_soi",initConf("aide_generale/jeux/tarot/variantes/jeu_de_chacun_pour_soi"));
        m.addEntry("aide_generale/logiciel/menus/partie/editer/belote",initConf("aide_generale/logiciel/menus/partie/editer/belote"));
        m.addEntry("aide_generale/logiciel/menus/partie/editer/tarot",initConf("aide_generale/logiciel/menus/partie/editer/tarot"));
        m.addEntry("aide_generale/logiciel/menus/partie/simulation/belote",initConf("aide_generale/logiciel/menus/partie/simulation/belote"));
        m.addEntry("aide_generale/logiciel/menus/partie/simulation/tarot",initConf("aide_generale/logiciel/menus/partie/simulation/tarot"));
        m.addEntry("aide_generale/logiciel/menus/parametres/affichage/belote",initConf("aide_generale/logiciel/menus/parametres/affichage/belote"));
        m.addEntry("aide_generale/logiciel/menus/parametres/affichage/tarot",initConf("aide_generale/logiciel/menus/parametres/affichage/tarot"));
        return m;
    }
    static NatConfigurationCore initConf(String _url){
        NatConfigurationCore conf_ = new NatConfigurationCore();
        conf_.setFirstUrl(_url);
        return conf_;
    }


}
