package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataItemsEvoitem {
    public static final String M_P_19_ITEM="item";
    private MessagesDataItemsEvoitem(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_19_ITEM,"The item allows to evolve the following pokemon by holding it on a pokemon then by growing one level:");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_19_ITEM,"L'objet permet de faire évoluer les pokemons suivants en le leur faisant porter et en leur montant d'un niveau:");
        return f_;
    }
}