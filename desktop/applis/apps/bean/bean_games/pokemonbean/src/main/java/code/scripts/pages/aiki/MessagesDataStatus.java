package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataStatus {
    public static final String M_P_88_AUTO_DAMAGE="auto_damage";
    public static final String M_P_88_CATCHING_RATE="catching_rate";
    public static final String M_P_88_DAMAGED_FOES="damaged_foes";
    public static final String M_P_88_DAMAGE_INCREMENTED_FALSE="damage_incremented_false";
    public static final String M_P_88_DAMAGE_INCREMENTED_TRUE="damage_incremented_true";
    public static final String M_P_88_DISABLED_EFF_IF_SWITCH="disabled_eff_if_switch";
    public static final String M_P_88_ENDROUND="endRound";
    public static final String M_P_88_FORMULA="formula";
    public static final String M_P_88_HEAL_HP="heal_hp";
    public static final String M_P_88_INCREMENTING_END_ROUND_FALSE="incrementing_end_round_false";
    public static final String M_P_88_INCREMENTING_END_ROUND_TRUE="incrementing_end_round_true";
    public static final String M_P_88_INCREMENT_END_ROUND="increment_end_round";
    public static final String M_P_88_MULT_STAT="mult_stat";
    public static final String M_P_88_MULT_STAT_KEY="mult_stat_key";
    public static final String M_P_88_MULT_STAT_VALUE="mult_stat_value";
    public static final String M_P_88_NOT_ATTACK="not_attack";
    public static final String M_P_88_NOT_ATTACK_FOE="not_attack_foe";
    public static final String M_P_88_RATE_HEAL_MOVE="rate_heal_move";
    public static final String M_P_88_RATE_USE_MOVE="rate_use_move";
    public static final String M_P_88_RATE_USE_MOVE_FOE="rate_use_move_foe";
    public static final String M_P_88_RATE_USE_MOVE_ROUND="rate_use_move_round";
    public static final String M_P_88_RATE_USE_MOVE_ROUND_KEY="rate_use_move_round_key";
    public static final String M_P_88_RATE_USE_MOVE_ROUND_RATE="rate_use_move_round_rate";
    public static final String M_P_88_REASONS="reasons";
    public static final String M_P_88_RELATION="relation";
    public static final String M_P_88_SINGLE="single";
    public static final String M_P_88_STATUS="status";
    public static final String M_P_88_TITLE="title";
    public static final String M_P_88_WEDDING="wedding";
    private MessagesDataStatus(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_88_AUTO_DAMAGE,"The user inflicts itself damage of a move with a base power being {0} based for the attack on the value of the statistic {1} and for the defense on the value of the statistic {2}.");
        e_.add(M_P_88_CATCHING_RATE,"The catching rate is multiplied by {0} for each owned status by the user.");
        e_.add(M_P_88_DAMAGED_FOES,"If the owner loves a partner, then damage are multiplied by {0} for each partner that it loves.");
        e_.add(M_P_88_DAMAGE_INCREMENTED_FALSE,"Damage is not multiplied by the number of elapsed rounds.");
        e_.add(M_P_88_DAMAGE_INCREMENTED_TRUE,"Damage is multiplied by the number of elapsed rounds.");
        e_.add(M_P_88_DISABLED_EFF_IF_SWITCH,"The effect is stopped while the owner exit from the front of battle.");
        e_.add(M_P_88_ENDROUND,"See the effect of end of round");
        e_.add(M_P_88_FORMULA,"{0}");
        e_.add(M_P_88_HEAL_HP,"In the case when a move achieves a loved partner, the rate of restored life of this partner is {0}.");
        e_.add(M_P_88_INCREMENTING_END_ROUND_FALSE,"The incrementing of the number of rounds of the effect happens at the beginning of round.");
        e_.add(M_P_88_INCREMENTING_END_ROUND_TRUE,"The incrementing of the number of rounds of the effect happens at the end of round.");
        e_.add(M_P_88_INCREMENT_END_ROUND,"The rank of incrementing is {0}.");
        e_.add(M_P_88_MULT_STAT,"The statistics of the owner are multiplied in the following way:");
        e_.add(M_P_88_MULT_STAT_KEY,"Statistic");
        e_.add(M_P_88_MULT_STAT_VALUE,"Rate");
        e_.add(M_P_88_NOT_ATTACK,"The owner cannot act its round.");
        e_.add(M_P_88_NOT_ATTACK_FOE,"The owner cannot use a move against a target in relation with the owner.");
        e_.add(M_P_88_RATE_HEAL_MOVE,"The probability that the owner is healed from the status is {0} knowing that the owner uses a move.");
        e_.add(M_P_88_RATE_USE_MOVE,"The probability that the owner can act its round is {0}.");
        e_.add(M_P_88_RATE_USE_MOVE_FOE,"The probability that the owner can use a move against a target in relation with the owner is {0}.");
        e_.add(M_P_88_RATE_USE_MOVE_ROUND,"Here is the law of averages in fonction by the number of rounds:");
        e_.add(M_P_88_RATE_USE_MOVE_ROUND_KEY,"Number of rounds");
        e_.add(M_P_88_RATE_USE_MOVE_ROUND_RATE,"Probability");
        e_.add(M_P_88_REASONS,"The status is not affected to the fighter at the end of round if and only if one of the conditions is checked:");
        e_.add(M_P_88_RELATION,"The status depends on the user and the target.");
        e_.add(M_P_88_SINGLE,"The status depends on the target.");
        e_.add(M_P_88_STATUS,"Status");
        e_.add(M_P_88_TITLE,"Data of the status {0}");
        e_.add(M_P_88_WEDDING,"If the status is triggered by a partner of the target and if the target has an opposite gender at its partner, then the target falls in love with the partner even if the target is protected.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_88_AUTO_DAMAGE,"Le lanceur s''autoinflige les dégâts d''une attaque de puissance de base valant {0} se basant pour l''attaque sur la valeur de la statistique {1} et pour la défense sur la valeur de la statistique {2}.");
        f_.add(M_P_88_CATCHING_RATE,"Le coefficient de capture est multiplié par {0} pour chaque statut possédé par le porteur.");
        f_.add(M_P_88_DAMAGED_FOES,"Si le porteur aime un partenaire, alors les dégâts sont multipliés par {0} pour chaque partenaire qu''il aime.");
        f_.add(M_P_88_DAMAGE_INCREMENTED_FALSE,"Les dégâts ne sont pas multipliés par le nombre de tours écoulé.");
        f_.add(M_P_88_DAMAGE_INCREMENTED_TRUE,"Les dégâts sont multipliés par le nombre de tours écoulé.");
        f_.add(M_P_88_DISABLED_EFF_IF_SWITCH,"L''effet s''arrête lorsque le porteur sort du terrain.");
        f_.add(M_P_88_ENDROUND,"Voir l''effet de fin de tour");
        f_.add(M_P_88_FORMULA,"{0}");
        f_.add(M_P_88_HEAL_HP,"En cas d''attaque touchant un partenaire aimé, le taux de vie restaurés de ce partenaire vaut {0}.");
        f_.add(M_P_88_INCREMENTING_END_ROUND_FALSE,"L''incrémentation du nombre de tours de l''effet a lieu en début de tour.");
        f_.add(M_P_88_INCREMENTING_END_ROUND_TRUE,"L''incrémentation du nombre de tours de l''effet a lieu en fin de tour.");
        f_.add(M_P_88_INCREMENT_END_ROUND,"Le rang d''incrémentation vaut {0}.");
        f_.add(M_P_88_MULT_STAT,"Les statistiques du porteur sont multipliées de la façon suivante:");
        f_.add(M_P_88_MULT_STAT_KEY,"Statistique");
        f_.add(M_P_88_MULT_STAT_VALUE,"Coefficient");
        f_.add(M_P_88_NOT_ATTACK,"Le porteur ne peut pas entamer son tour.");
        f_.add(M_P_88_NOT_ATTACK_FOE,"Le porteur ne peut pas utiliser une attaque sur une cible en relation avec le porteur.");
        f_.add(M_P_88_RATE_HEAL_MOVE,"La probabilité que le porteur soit soigné du statut est de {0} sachant que le porteur attaque.");
        f_.add(M_P_88_RATE_USE_MOVE,"La probabilité que le porteur puisse entamer son tour est de {0}.");
        f_.add(M_P_88_RATE_USE_MOVE_FOE,"La probabilité que le porteur puisse utiliser une attaque sur une cible en relation avec le porteur est de {0}.");
        f_.add(M_P_88_RATE_USE_MOVE_ROUND,"Voici le tableau des probabilités en fonction du nombre de tour:");
        f_.add(M_P_88_RATE_USE_MOVE_ROUND_KEY,"Nombre de tours");
        f_.add(M_P_88_RATE_USE_MOVE_ROUND_RATE,"Probabilité");
        f_.add(M_P_88_REASONS,"Le statut n''est pas affecté en fin de tour si et seulement une des conditions est vérifiée:");
        f_.add(M_P_88_RELATION,"Le statut dépend du lanceur et de la cible.");
        f_.add(M_P_88_SINGLE,"Le statut dépend de la cible.");
        f_.add(M_P_88_STATUS,"Statuts");
        f_.add(M_P_88_TITLE,"Données sur le statut {0}");
        f_.add(M_P_88_WEDDING,"Si le statut est provoqué par un partenaire de la cible et si la cible est du genre opposé à son partenaire, alors la cible tombe amoureuse du partenaire même si la cible est immunisée.");
        return f_;
    }
}