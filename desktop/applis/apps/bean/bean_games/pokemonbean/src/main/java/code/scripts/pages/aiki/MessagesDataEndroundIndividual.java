package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEndroundIndividual {
    public static final String M_P_6_DELETE_ALL_STATUS="delete_all_status";
    public static final String M_P_6_DELETE_ALL_STATUS_INTRO="delete_all_status_intro";
    public static final String M_P_6_EFFECT="effect";
    public static final String M_P_6_HEAL_HP="heal_hp";
    public static final String M_P_6_HEAL_HP_INTRO="heal_hp_intro";
    public static final String M_P_6_HEAL_HP_BY_OWNER_TYPES="heal_hp_by_owner_types";
    public static final String M_P_6_HEAL_HP_BY_OWNER_TYPES_KEY="heal_hp_by_owner_types_key";
    public static final String M_P_6_HEAL_HP_BY_OWNER_TYPES_OTHER="heal_hp_by_owner_types_other";
    public static final String M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE="heal_hp_by_owner_types_value";
    public static final String M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE_L="heal_hp_by_owner_types_value_l";
    public static final String M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE_W="heal_hp_by_owner_types_value_w";
    public static final String M_P_6_MULT_DAMAGE_STATUS="mult_damage_status";
    public static final String M_P_6_MULT_DAMAGE_STATUS_KEY="mult_damage_status_key";
    public static final String M_P_6_MULT_DAMAGE_STATUS_VALUE="mult_damage_status_value";
    public static final String M_P_6_RECOIL_DAMAGE="recoil_damage";
    public static final String M_P_6_RECOIL_DAMAGE_INTRO="recoil_damage_intro";
    public static final String M_P_6_USER_STATUS="user_status";
    private MessagesDataEndroundIndividual(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_6_DELETE_ALL_STATUS,"The rate that the status are deleted is {0}.");
        e_.add(M_P_6_DELETE_ALL_STATUS_INTRO,"The rate that the status are deleted is:");
        e_.add(M_P_6_EFFECT,"This effect regards one fighter.");
        e_.add(M_P_6_HEAL_HP,"The fighter restores {0} of its full life.");
        e_.add(M_P_6_HEAL_HP_INTRO,"Rate of restored full life of the fighter:");
        e_.add(M_P_6_HEAL_HP_BY_OWNER_TYPES,"Here is the variation of the hp of the fighter in function by the owned types,the variations of life are added in function by owned types:");
        e_.add(M_P_6_HEAL_HP_BY_OWNER_TYPES_KEY,"Owned types");
        e_.add(M_P_6_HEAL_HP_BY_OWNER_TYPES_OTHER,"Other owned type(s)");
        e_.add(M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE,"Variation of the rate of the hp of the fighter");
        e_.add(M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE_L,"Loss of {0} of the full hp");
        e_.add(M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE_W,"Win of {0} of the full hp");
        e_.add(M_P_6_MULT_DAMAGE_STATUS,"Here is the rate of restored hp in function by the status of the fighter (the rates are multiplied if the fighter owns some status.):");
        e_.add(M_P_6_MULT_DAMAGE_STATUS_KEY,"Owned status");
        e_.add(M_P_6_MULT_DAMAGE_STATUS_VALUE,"Rate of restored health points by the target");
        e_.add(M_P_6_RECOIL_DAMAGE,"The rate of recoil damage for the fighter is {0}.");
        e_.add(M_P_6_RECOIL_DAMAGE_INTRO,"The rate of recoil damage for the fighter is:");
        e_.add(M_P_6_USER_STATUS,"If this is not already done, the fighter is affected by the status:");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_6_DELETE_ALL_STATUS,"La probabilité que les statuts du combattant soient supprimés est de {0}.");
        f_.add(M_P_6_DELETE_ALL_STATUS_INTRO,"La probabilité que les statuts du combattant soient supprimés est de:");
        f_.add(M_P_6_EFFECT,"Cet effet concerne un seul combattant.");
        f_.add(M_P_6_HEAL_HP,"Le combattant restaure {0} de sa vie totale.");
        f_.add(M_P_6_HEAL_HP_INTRO,"Taux de vie restaurée totale du combattant:");
        f_.add(M_P_6_HEAL_HP_BY_OWNER_TYPES,"Voici la variation des pv du combattant en fonction des types possédés,les variations de vie sont cumulés en fonction des types possédés:");
        f_.add(M_P_6_HEAL_HP_BY_OWNER_TYPES_KEY,"Types possédés");
        f_.add(M_P_6_HEAL_HP_BY_OWNER_TYPES_OTHER,"Autre(s) type(s) possédé(s)");
        f_.add(M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE,"Variation du taux de pv du combattant");
        f_.add(M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE_L,"Perte de {0} du total des pv");
        f_.add(M_P_6_HEAL_HP_BY_OWNER_TYPES_VALUE_W,"Gain de {0} du total des pv");
        f_.add(M_P_6_MULT_DAMAGE_STATUS,"Voici le taux de pv restaurés en fonction du statut du combattant (les coefficients sont mutlipliés si le combattant possède plusieurs statuts.):");
        f_.add(M_P_6_MULT_DAMAGE_STATUS_KEY,"Statut possédé");
        f_.add(M_P_6_MULT_DAMAGE_STATUS_VALUE,"Taux de vie restaurée par la cible");
        f_.add(M_P_6_RECOIL_DAMAGE,"Le taux de dégâts de recul pour le combattant est de {0}.");
        f_.add(M_P_6_RECOIL_DAMAGE_INTRO,"Le taux de dégâts de recul pour le combattant est de:");
        f_.add(M_P_6_USER_STATUS,"Si ce n'est pas déjà fait, le combattant prend le statut:");
        return f_;
    }
}