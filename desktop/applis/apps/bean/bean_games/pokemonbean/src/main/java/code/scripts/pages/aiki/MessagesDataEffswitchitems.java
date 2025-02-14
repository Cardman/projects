package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffswitchitems {
    public static final String M_P_61_DELETE_BERRY="delete_berry";
    public static final String M_P_61_EFFECT="effect";
    public static final String M_P_61_GIVE_TO_TARGET="give_to_target";
    public static final String M_P_61_REMOVE_ITEM="remove_item";
    public static final String M_P_61_REUSE_ITEM="reuse_item";
    public static final String M_P_61_SWITCH_ITEMS="switch_items";
    public static final String M_P_61_TAKE_ITEM="take_item";
    public static final String M_P_61_USE_ITEM_IF_POSSIBLE="use_item_if_possible";
    private MessagesDataEffswitchitems(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_61_DELETE_BERRY,"The berry of the fighter is definitively deleted.");
        e_.add(M_P_61_EFFECT,"This effect changes the owner of an item at least.");
        e_.add(M_P_61_GIVE_TO_TARGET,"The user gives its item to the target.");
        e_.add(M_P_61_REMOVE_ITEM,"The target looses its item. The user does not take the lost item of the target.");
        e_.add(M_P_61_REUSE_ITEM,"The user gets the last item that the user used.");
        e_.add(M_P_61_SWITCH_ITEMS,"The user and the target switch one each other the items.");
        e_.add(M_P_61_TAKE_ITEM,"The user takes the item of the target.");
        e_.add(M_P_61_USE_ITEM_IF_POSSIBLE,"If possible, the user uses the item of the target.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_61_DELETE_BERRY,"La baie du combattant est supprimé définitivement.");
        f_.add(M_P_61_EFFECT,"Cet effet change le possesseur d''au moins un objet.");
        f_.add(M_P_61_GIVE_TO_TARGET,"Le lanceur donne son objet à la cible.");
        f_.add(M_P_61_REMOVE_ITEM,"La cible perd son objet. Le lanceur ne prend pas l''objet perdu par la cible.");
        f_.add(M_P_61_REUSE_ITEM,"Le lanceur récupère le dernier objet qu''il a utilisé.");
        f_.add(M_P_61_SWITCH_ITEMS,"Le lanceur et la cible s''échangent les objets.");
        f_.add(M_P_61_TAKE_ITEM,"Le lanceur prend l''objet de la cible.");
        f_.add(M_P_61_USE_ITEM_IF_POSSIBLE,"Si possible, le lanceur utilise l''objet de la cible.");
        return f_;
    }
}