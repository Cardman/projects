package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataItemsEvostone {
    public static final String M_P_20_ITEM="item";
    private MessagesDataItemsEvostone(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_20_ITEM,"The item allows to evolve the following pokemon by affecting them out of fight:");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_20_ITEM,"L'objet permet de faire évoluer les pokemons suivants en le leur affectant hors combat:");
        return f_;
    }
}