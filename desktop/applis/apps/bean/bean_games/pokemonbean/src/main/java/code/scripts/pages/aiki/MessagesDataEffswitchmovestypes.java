package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffswitchmovestypes {
    public static final String M_P_62_CHANGING_TYPE="changing_type";
    public static final String M_P_62_CHANGING_TYPE_POSSIBLE="changing_type_possible";
    public static final String M_P_62_EFFECT="effect";
    public static final String M_P_62_NEW_TYPE="new_type";
    public static final String M_P_62_OLD_TYPE="old_type";
    public static final String M_P_62_REPLACING_TYPES="replacing_types";
    private MessagesDataEffswitchmovestypes(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_62_CHANGING_TYPE,"The types of the move are changed in the following way:");
        e_.add(M_P_62_CHANGING_TYPE_POSSIBLE,"The types of the move are changed in the following way after possible replacement of types:");
        e_.add(M_P_62_EFFECT,"The effect changes the types of a move of a target not having acted yet.");
        e_.add(M_P_62_NEW_TYPE,"New type");
        e_.add(M_P_62_OLD_TYPE,"Old type");
        e_.add(M_P_62_REPLACING_TYPES,"The types of the move are replaced by the following types:");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_62_CHANGING_TYPE,"Les types de l'attaque sont convertis de la manière suivante:");
        f_.add(M_P_62_CHANGING_TYPE_POSSIBLE,"Les types de l'attaque sont convertis de la manière suivante aprè éventuel remplacement de types:");
        f_.add(M_P_62_EFFECT,"L'effet change les types d'une attaque d'une cible n'ayant pas encore joué.");
        f_.add(M_P_62_NEW_TYPE,"Nouveau type");
        f_.add(M_P_62_OLD_TYPE,"Ancien type");
        f_.add(M_P_62_REPLACING_TYPES,"Les types de l'attaque sont remplacés par les types suivants:");
        return f_;
    }
}