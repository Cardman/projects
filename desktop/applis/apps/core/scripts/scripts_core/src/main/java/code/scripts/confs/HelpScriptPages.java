package code.scripts.confs;

import code.formathtml.Configuration;
import code.util.CollCapacity;
import code.util.StringMap;

public final class HelpScriptPages {
    private HelpScriptPages() {

    }
    public static StringMap<Configuration> cf(){
        StringMap<Configuration> m = new StringMap<Configuration>(new CollCapacity(71));
        m.addEntry("resources_cards/help/aide_generale.xml",initConf("resources_cards/help/aide_generale.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel.xml",initConf("resources_cards/help/aide_generale/logiciel.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux.xml",initConf("resources_cards/help/aide_generale/jeux.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus.xml",initConf("resources_cards/help/aide_generale/logiciel/menus.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/fin_partie.xml",initConf("resources_cards/help/aide_generale/logiciel/fin_partie.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote.xml",initConf("resources_cards/help/aide_generale/jeux/belote.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/president.xml",initConf("resources_cards/help/aide_generale/jeux/president.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot.xml",initConf("resources_cards/help/aide_generale/jeux/tarot.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/fichier.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/fichier.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/partie.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/aide.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/aide.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/cartes_utilisees.xml",initConf("resources_cards/help/aide_generale/jeux/belote/cartes_utilisees.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/ordre_des_cartes.xml",initConf("resources_cards/help/aide_generale/jeux/belote/ordre_des_cartes.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/distribution.xml",initConf("resources_cards/help/aide_generale/jeux/belote/distribution.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/contrat.xml",initConf("resources_cards/help/aide_generale/jeux/belote/contrat.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/annonces.xml",initConf("resources_cards/help/aide_generale/jeux/belote/annonces.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/jeu_des_cartes.xml",initConf("resources_cards/help/aide_generale/jeux/belote/jeu_des_cartes.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/fin_de_la_partie.xml",initConf("resources_cards/help/aide_generale/jeux/belote/fin_de_la_partie.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/variantes.xml",initConf("resources_cards/help/aide_generale/jeux/belote/variantes.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/president/cartes_utilisees.xml",initConf("resources_cards/help/aide_generale/jeux/president/cartes_utilisees.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/president/ordre_des_cartes.xml",initConf("resources_cards/help/aide_generale/jeux/president/ordre_des_cartes.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/president/distribution.xml",initConf("resources_cards/help/aide_generale/jeux/president/distribution.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/president/jeu_des_cartes.xml",initConf("resources_cards/help/aide_generale/jeux/president/jeu_des_cartes.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/president/variantes.xml",initConf("resources_cards/help/aide_generale/jeux/president/variantes.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/cartes_utilisees.xml",initConf("resources_cards/help/aide_generale/jeux/tarot/cartes_utilisees.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/ordre_des_cartes.xml",initConf("resources_cards/help/aide_generale/jeux/tarot/ordre_des_cartes.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/distribution.xml",initConf("resources_cards/help/aide_generale/jeux/tarot/distribution.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/contrat.xml",initConf("resources_cards/help/aide_generale/jeux/tarot/contrat.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/chien.xml",initConf("resources_cards/help/aide_generale/jeux/tarot/chien.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/annonces.xml",initConf("resources_cards/help/aide_generale/jeux/tarot/annonces.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/jeu_des_cartes.xml",initConf("resources_cards/help/aide_generale/jeux/tarot/jeu_des_cartes.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/fin_de_la_partie.xml",initConf("resources_cards/help/aide_generale/jeux/tarot/fin_de_la_partie.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes.xml",initConf("resources_cards/help/aide_generale/jeux/tarot/variantes.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/fichier/charger.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/fichier/charger.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/fichier/sauvegarder.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/fichier/sauvegarder.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/fichier/changer_jeu.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/fichier/changer_jeu.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/fichier/quitter.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/fichier/quitter.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/conseil.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/conseil.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/pause.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/pause.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/aide_au_jeu.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/aide_au_jeu.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/mains_plis.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/mains_plis.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/equipes.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/equipes.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/editer.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/editer.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/simulation.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/simulation.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/entrainement.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/entrainement.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/arret_multi.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/arret_multi.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/belote.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/belote.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/tarot.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/tarot.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/joueurs.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/joueurs.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/lancement.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/lancement.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/temporisation.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/temporisation.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/interaction.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/interaction.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/langue.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/langue.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/affichage.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/affichage.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/aide/aide_generale.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/aide/aide_generale.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/variantes/surcontrat.xml",initConf("resources_cards/help/aide_generale/jeux/belote/variantes/surcontrat.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/belote/variantes/fin_de_partie_differente.xml",initConf("resources_cards/help/aide_generale/jeux/belote/variantes/fin_de_partie_differente.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/trois_joueurs.xml",initConf("resources_cards/help/aide_generale/jeux/tarot/variantes/trois_joueurs.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/quatre_joueurs_rep.xml",initConf("resources_cards/help/aide_generale/jeux/tarot/variantes/quatre_joueurs_rep.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/cinq_joueurs.xml",initConf("resources_cards/help/aide_generale/jeux/tarot/variantes/cinq_joueurs.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/six_joueurs.xml",initConf("resources_cards/help/aide_generale/jeux/tarot/variantes/six_joueurs.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/nouvelles_annonces.xml",initConf("resources_cards/help/aide_generale/jeux/tarot/variantes/nouvelles_annonces.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/jeu_de_misere.xml",initConf("resources_cards/help/aide_generale/jeux/tarot/variantes/jeu_de_misere.html"));
        m.addEntry("resources_cards/help/aide_generale/jeux/tarot/variantes/jeu_de_chacun_pour_soi.xml",initConf("resources_cards/help/aide_generale/jeux/tarot/variantes/jeu_de_chacun_pour_soi.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/editer/belote.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/editer/belote.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/editer/tarot.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/editer/tarot.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/simulation/belote.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/simulation/belote.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/partie/simulation/tarot.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/partie/simulation/tarot.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/affichage/belote.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/affichage/belote.html"));
        m.addEntry("resources_cards/help/aide_generale/logiciel/menus/parametres/affichage/tarot.xml",initConf("resources_cards/help/aide_generale/logiciel/menus/parametres/affichage/tarot.html"));
        return m;
    }
    static Configuration initConf(String _url){
        Configuration conf_ = new Configuration();
        conf_.setFirstUrl(_url);
        return conf_;
    }


}
