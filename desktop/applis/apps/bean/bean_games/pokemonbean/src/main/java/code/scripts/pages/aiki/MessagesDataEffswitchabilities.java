package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffswitchabilities {
    public static final String M_P_60_EFFECT="effect";
    public static final String M_P_60_GIVE_CONST="give_const";
    public static final String M_P_60_GIVE_CONST_EMPTY="give_const_empty";
    public static final String M_P_60_GIVE_TO_TARGET="give_to_target";
    public static final String M_P_60_GIVE_TO_USER="give_to_user";
    public static final String M_P_60_SWICTH_ABILITIES="swicth_abilities";
    private MessagesDataEffswitchabilities(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_60_EFFECT,"This effect changes an ability at least.");
        e_.add(M_P_60_GIVE_CONST,"The target takes the ability:");
        e_.add(M_P_60_GIVE_CONST_EMPTY,"The ability of the target has not effect anymore.");
        e_.add(M_P_60_GIVE_TO_TARGET,"The target takes the ability of the user.If the ability of the user was without effect, then the ability of the target will be without effect.");
        e_.add(M_P_60_GIVE_TO_USER,"The user takes the ability of the target.If the ability of the target was without effect, then the ability of the user will be without effect.");
        e_.add(M_P_60_SWICTH_ABILITIES,"The user and the target switch one each other the abilities.If the ability of the user was without effect, then the ability of the target will be without effect.If the ability of the target was without effect, then the ability of the user will be without effect.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_60_EFFECT,"Cet effet change au moins une capacité.");
        f_.add(M_P_60_GIVE_CONST,"La cible prend la capacité:");
        f_.add(M_P_60_GIVE_CONST_EMPTY,"La capacité de la cible n''a plus d''effet.");
        f_.add(M_P_60_GIVE_TO_TARGET,"La cible prend la capacité du lanceur.Si la capacité du lanceur était sans effet, alors la capacité de la cible sera sans effet.");
        f_.add(M_P_60_GIVE_TO_USER,"Le lanceur prend la capacité de la cible.Si la capacité de la cible était sans effet, alors la capacité du lanceur sera sans effet.");
        f_.add(M_P_60_SWICTH_ABILITIES,"Le lanceur et la cible s''échangent les capacités.Si la capacité du lanceur était sans effet, alors la capacité de la cible sera sans effet.Si la capacité de la cible était sans effet, alors la capacité du lanceur sera sans effet.");
        return f_;
    }
}