package code.scripts.confs;

import cards.consts.*;
import code.bean.nat.*;
import code.scripts.imgs.cards.*;
import code.scripts.pages.cards.*;
import code.util.*;

public final class HelpScriptPagesImgs {
    private HelpScriptPagesImgs() {
    }
    public static StringMap<NatDualConfigurationContext> ct(){
        StringMap<NatDualConfigurationContext> m = new StringMap<NatDualConfigurationContext>(new CollCapacity(71));
        m.addEntry(HelpCards.AIDE_GENERALE,initAnaData(HelpCards.AIDE_GENERALE));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX,initAnaData(HelpCards.AIDE_GENERALE_JEUX));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_FIN_PARTIE,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_FIN_PARTIE));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_BELOTE,initAnaData(HelpCards.AIDE_GENERALE_JEUX_BELOTE));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_PRESIDENT,initAnaData(HelpCards.AIDE_GENERALE_JEUX_PRESIDENT));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_TAROT,initAnaData(HelpCards.AIDE_GENERALE_JEUX_TAROT));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_FICHIER,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_FICHIER));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_AIDE,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_AIDE));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_BELOTE_CARTES_UTILISEES,initAnaData(HelpCards.AIDE_GENERALE_JEUX_BELOTE_CARTES_UTILISEES));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_BELOTE_ORDRE_DES_CARTES,initAnaData(HelpCards.AIDE_GENERALE_JEUX_BELOTE_ORDRE_DES_CARTES));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_BELOTE_DISTRIBUTION,initAnaData(HelpCards.AIDE_GENERALE_JEUX_BELOTE_DISTRIBUTION));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_BELOTE_CONTRAT,initAnaData(HelpCards.AIDE_GENERALE_JEUX_BELOTE_CONTRAT));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_BELOTE_ANNONCES,initAnaData(HelpCards.AIDE_GENERALE_JEUX_BELOTE_ANNONCES));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_BELOTE_JEU_DES_CARTES,initAnaData(HelpCards.AIDE_GENERALE_JEUX_BELOTE_JEU_DES_CARTES));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_BELOTE_FIN_DE_LA_PARTIE,initAnaData(HelpCards.AIDE_GENERALE_JEUX_BELOTE_FIN_DE_LA_PARTIE));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_BELOTE_VARIANTES,initAnaData(HelpCards.AIDE_GENERALE_JEUX_BELOTE_VARIANTES));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_PRESIDENT_CARTES_UTILISEES,initAnaData(HelpCards.AIDE_GENERALE_JEUX_PRESIDENT_CARTES_UTILISEES));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_PRESIDENT_ORDRE_DES_CARTES,initAnaData(HelpCards.AIDE_GENERALE_JEUX_PRESIDENT_ORDRE_DES_CARTES));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_PRESIDENT_DISTRIBUTION,initAnaData(HelpCards.AIDE_GENERALE_JEUX_PRESIDENT_DISTRIBUTION));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_PRESIDENT_JEU_DES_CARTES,initAnaData(HelpCards.AIDE_GENERALE_JEUX_PRESIDENT_JEU_DES_CARTES));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_PRESIDENT_VARIANTES,initAnaData(HelpCards.AIDE_GENERALE_JEUX_PRESIDENT_VARIANTES));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_TAROT_CARTES_UTILISEES,initAnaData(HelpCards.AIDE_GENERALE_JEUX_TAROT_CARTES_UTILISEES));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_TAROT_ORDRE_DES_CARTES,initAnaData(HelpCards.AIDE_GENERALE_JEUX_TAROT_ORDRE_DES_CARTES));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_TAROT_DISTRIBUTION,initAnaData(HelpCards.AIDE_GENERALE_JEUX_TAROT_DISTRIBUTION));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_TAROT_CONTRAT,initAnaData(HelpCards.AIDE_GENERALE_JEUX_TAROT_CONTRAT));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_TAROT_CHIEN,initAnaData(HelpCards.AIDE_GENERALE_JEUX_TAROT_CHIEN));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_TAROT_ANNONCES,initAnaData(HelpCards.AIDE_GENERALE_JEUX_TAROT_ANNONCES));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_TAROT_JEU_DES_CARTES,initAnaData(HelpCards.AIDE_GENERALE_JEUX_TAROT_JEU_DES_CARTES));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_TAROT_FIN_DE_LA_PARTIE,initAnaData(HelpCards.AIDE_GENERALE_JEUX_TAROT_FIN_DE_LA_PARTIE));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_TAROT_VARIANTES,initAnaData(HelpCards.AIDE_GENERALE_JEUX_TAROT_VARIANTES));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_FICHIER_CHARGER,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_FICHIER_CHARGER));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_FICHIER_SAUVEGARDER,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_FICHIER_SAUVEGARDER));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_FICHIER_CHANGER_JEU,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_FICHIER_CHANGER_JEU));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_FICHIER_QUITTER,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_FICHIER_QUITTER));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_CONSEIL,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_CONSEIL));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_PAUSE,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_PAUSE));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_AIDE_AU_JEU,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_AIDE_AU_JEU));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_MAINS_PLIS,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_MAINS_PLIS));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_EQUIPES,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_EQUIPES));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_EDITER,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_EDITER));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_SIMULATION,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_SIMULATION));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_ENTRAINEMENT,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_ENTRAINEMENT));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_ARRET_MULTI,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_ARRET_MULTI));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_BELOTE,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_BELOTE));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_TAROT,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_TAROT));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_JOUEURS,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_JOUEURS));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_LANCEMENT,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_LANCEMENT));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_TEMPORISATION,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_TEMPORISATION));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_INTERACTION,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_INTERACTION));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_LANGUE,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_LANGUE));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_AFFICHAGE,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_AFFICHAGE));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_AIDE_AIDE_GENERALE,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_AIDE_AIDE_GENERALE));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_BELOTE_VARIANTES_SURCONTRAT,initAnaData(HelpCards.AIDE_GENERALE_JEUX_BELOTE_VARIANTES_SURCONTRAT));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_BELOTE_VARIANTES_FIN_DE_PARTIE_DIFFERENTE,initAnaData(HelpCards.AIDE_GENERALE_JEUX_BELOTE_VARIANTES_FIN_DE_PARTIE_DIFFERENTE));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_TAROT_VARIANTES_TROIS_JOUEURS,initAnaData(HelpCards.AIDE_GENERALE_JEUX_TAROT_VARIANTES_TROIS_JOUEURS));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_TAROT_VARIANTES_QUATRE_JOUEURS_REP,initAnaData(HelpCards.AIDE_GENERALE_JEUX_TAROT_VARIANTES_QUATRE_JOUEURS_REP));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_TAROT_VARIANTES_CINQ_JOUEURS,initAnaData(HelpCards.AIDE_GENERALE_JEUX_TAROT_VARIANTES_CINQ_JOUEURS));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_TAROT_VARIANTES_SIX_JOUEURS,initAnaData(HelpCards.AIDE_GENERALE_JEUX_TAROT_VARIANTES_SIX_JOUEURS));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_TAROT_VARIANTES_NOUVELLES_ANNONCES,initAnaData(HelpCards.AIDE_GENERALE_JEUX_TAROT_VARIANTES_NOUVELLES_ANNONCES));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_TAROT_VARIANTES_JEU_DE_MISERE,initAnaData(HelpCards.AIDE_GENERALE_JEUX_TAROT_VARIANTES_JEU_DE_MISERE));
        m.addEntry(HelpCards.AIDE_GENERALE_JEUX_TAROT_VARIANTES_JEU_DE_CHACUN_POUR_SOI,initAnaData(HelpCards.AIDE_GENERALE_JEUX_TAROT_VARIANTES_JEU_DE_CHACUN_POUR_SOI));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_EDITER_BELOTE,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_EDITER_BELOTE));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_EDITER_TAROT,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_EDITER_TAROT));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_SIMULATION_BELOTE,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_SIMULATION_BELOTE));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_SIMULATION_TAROT,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARTIE_SIMULATION_TAROT));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_AFFICHAGE_BELOTE,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_AFFICHAGE_BELOTE));
        m.addEntry(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_AFFICHAGE_TAROT,initAnaData(HelpCards.AIDE_GENERALE_LOGICIEL_MENUS_PARAMETRES_AFFICHAGE_TAROT));
        return m;
    }
    public static NatDualConfigurationContext initAnaData(String _prop){
        NatDualConfigurationContext d = new NatDualConfigurationContext();
        d.setMessagesFolder("");
        StringMap<String> p = new StringMap<String>(new CollCapacity(1));
        p.addEntry("msg",_prop);
        d.setProperties(p);
        StringList a = new StringList(new CollCapacity(78));
        a.add(CardsInit.FOLDER+CouleurValeur.EXCUSE+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_21+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_20+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_19+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_18+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_17+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_16+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_15+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_14+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_13+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_12+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_11+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_10+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_9+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_8+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_7+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_6+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_5+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_4+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_3+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_2+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.TRUMP_1+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.HEART_KING+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.HEART_QUEEN+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.HEART_KNIGHT+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.HEART_JACK+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.HEART_10+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.HEART_9+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.HEART_8+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.HEART_7+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.HEART_6+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.HEART_5+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.HEART_4+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.HEART_3+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.HEART_2+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.HEART_1+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.SPADE_KING+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.SPADE_QUEEN+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.SPADE_KNIGHT+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.SPADE_JACK+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.SPADE_10+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.SPADE_9+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.SPADE_8+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.SPADE_7+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.SPADE_6+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.SPADE_5+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.SPADE_4+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.SPADE_3+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.SPADE_2+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.SPADE_1+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.DIAMOND_KING+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.DIAMOND_QUEEN+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.DIAMOND_KNIGHT+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.DIAMOND_JACK+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.DIAMOND_10+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.DIAMOND_9+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.DIAMOND_8+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.DIAMOND_7+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.DIAMOND_6+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.DIAMOND_5+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.DIAMOND_4+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.DIAMOND_3+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.DIAMOND_2+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.DIAMOND_1+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.CLUB_KING+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.CLUB_QUEEN+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.CLUB_KNIGHT+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.CLUB_JACK+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.CLUB_10+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.CLUB_9+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.CLUB_8+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.CLUB_7+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.CLUB_6+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.CLUB_5+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.CLUB_4+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.CLUB_3+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.CLUB_2+CardsInit.EXT);
        a.add(CardsInit.FOLDER+CouleurValeur.CLUB_1+CardsInit.EXT);
        d.setAddedFiles(a);
        return d;
    }

}
