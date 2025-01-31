package code.gui.document;

import code.sml.util.*;

public final class MessagesGameDifficulty {
    public static final String M_P_93_ALLOW_CATCHING_KO="allow_catching_ko";
    public static final String M_P_93_ALLOW_SWITCH_PLACES="allow_switch_places";
    public static final String M_P_93_CLOSING="closing";
    public static final String M_P_93_END_FIGHT="end_fight";
    public static final String M_P_93_FLEE="flee";
    public static final String M_P_93_IV_FOE="iv_foe";
    public static final String M_P_93_IV_PLAYER="iv_player";
    public static final String M_P_93_LAW_CHOICE_FOE="law_choice_foe";
    public static final String M_P_93_LAW_CHOICE_PLAYER="law_choice_player";
    public static final String M_P_93_RANDOM_WILD="random_wild";
    public static final String M_P_93_RATE_DAMAGE="rate_damage";
    public static final String M_P_93_RATE_DAMAGE_EV="rate_damage_ev";
    public static final String M_P_93_RATE_ISSUE="rate_issue";
    public static final String M_P_93_RATE_WIN_MONEY_BASE="rate_win_money_base";
    public static final String M_P_93_RATE_WIN_MONEY_LOOSE="rate_win_money_loose";
    public static final String M_P_93_RESTORED_MOVES="restored_moves";
    public static final String M_P_93_SHORT_ISSUE="short_issue";
    public static final String M_P_93_SKIP_LEARN="skip_learn";
    public static final String M_P_93_TITLE="title";
    public static final String M_P_93_WINNING_EXP_PTS_FIGHT="winning_exp_pts_fight";
    public static final String M_P_93_WIN_PTS="win_pts";
    public static final String M_P_93_WIN_TRAINER_EXP="win_trainer_exp";
    public static final String M_P_93_OK="ok";
    private MessagesGameDifficulty(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_93_ALLOW_CATCHING_KO,"Allow catching ko pokemon");
        e_.add(M_P_93_ALLOW_SWITCH_PLACES,"Allow swicthing places at the front of battle at the end of round");
        e_.add(M_P_93_CLOSING,"The moves wth single target can achieve any foe");
        e_.add(M_P_93_END_FIGHT,"End of fight if a team is ko.");
        e_.add(M_P_93_FLEE,"Flee always possible");
        e_.add(M_P_93_IV_FOE,"Iv of the pokemon of your foes");
        e_.add(M_P_93_IV_PLAYER,"Iv of your pokemon");
        e_.add(M_P_93_LAW_CHOICE_FOE,"Choix of averages of damage rate for the pokemon of your foes");
        e_.add(M_P_93_LAW_CHOICE_PLAYER,"Choice of averages of damage rate for your pokemon");
        e_.add(M_P_93_RANDOM_WILD,"Random appearing pokemon");
        e_.add(M_P_93_RATE_DAMAGE,"Probability");
        e_.add(M_P_93_RATE_DAMAGE_EV,"Rate");
        e_.add(M_P_93_RATE_ISSUE,"{0} is not a valid rate.");
        e_.add(M_P_93_RATE_WIN_MONEY_BASE,"Rate of won money between winning money while a victory and winning base");
        e_.add(M_P_93_RATE_WIN_MONEY_LOOSE,"Rate of lost money between loss while a defeat and winning money while a victory");
        e_.add(M_P_93_RESTORED_MOVES,"Healed moves of your pokemon at the end of fight");
        e_.add(M_P_93_SHORT_ISSUE,"{0} is not a valid integer.");
        e_.add(M_P_93_SKIP_LEARN,"Do not learnt the already known moves");
        e_.add(M_P_93_TITLE,"Difficulty choice");
        e_.add(M_P_93_WINNING_EXP_PTS_FIGHT,"Rate of winning experience points");
        e_.add(M_P_93_WIN_PTS,"Difficulty of winning points");
        e_.add(M_P_93_WIN_TRAINER_EXP,"Rate of winning experience points de gain de points while a fight against a foe");
        e_.add(M_P_93_OK,"OK");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_93_ALLOW_CATCHING_KO,"Autoriser la capture de pokemon ko");
        f_.add(M_P_93_ALLOW_SWITCH_PLACES,"Autoriser l''échange de places sur le terrain en fin de tour");
        f_.add(M_P_93_CLOSING,"Les attaques à cible unique peuvent toucher n''importe quel adversaire");
        f_.add(M_P_93_END_FIGHT,"Fin de combat si une équipe est ko.");
        f_.add(M_P_93_FLEE,"Fuite toujours possible");
        f_.add(M_P_93_IV_FOE,"Iv des pokemon de vos adversaires");
        f_.add(M_P_93_IV_PLAYER,"Iv de vos pokemon");
        f_.add(M_P_93_LAW_CHOICE_FOE,"Choix de la loi de probabilité du coefficient de dégâts pour les pokemon de vos adversaires");
        f_.add(M_P_93_LAW_CHOICE_PLAYER,"Choix de la loi de probabilité du coefficient de dégâts pour vos pokemon");
        f_.add(M_P_93_RANDOM_WILD,"Apparition des pokemon aléatoire");
        f_.add(M_P_93_RATE_DAMAGE,"Probabilité");
        f_.add(M_P_93_RATE_DAMAGE_EV,"Coefficient");
        f_.add(M_P_93_RATE_ISSUE,"{0} n''est pas un taux valide.");
        f_.add(M_P_93_RATE_WIN_MONEY_BASE,"Rapport d''argent gagné entre le gain lors d''une victoire et la base de gain");
        f_.add(M_P_93_RATE_WIN_MONEY_LOOSE,"Rapport d''argent perdu entre la perte lords d''une défaite et le gain lors d''une victoire");
        f_.add(M_P_93_RESTORED_MOVES,"Attaques de vos pokemon restaurées à la fin du combat");
        f_.add(M_P_93_SHORT_ISSUE,"{0} n''est pas un entier valide.");
        f_.add(M_P_93_SKIP_LEARN,"Ne pas apprendre les attaques déjà connues");
        f_.add(M_P_93_TITLE,"Choix de difficulté");
        f_.add(M_P_93_WINNING_EXP_PTS_FIGHT,"Coefficient de gain de points d''expérience");
        f_.add(M_P_93_WIN_PTS,"Difficulté de gain des points");
        f_.add(M_P_93_WIN_TRAINER_EXP,"Coefficient de gain de points d''expérience lors d''un combat contre un dresseur");
        f_.add(M_P_93_OK,"OK");
        return f_;
    }
}
