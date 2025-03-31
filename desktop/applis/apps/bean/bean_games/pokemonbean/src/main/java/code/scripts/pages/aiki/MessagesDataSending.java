package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataSending {
    public static final String M_P_84_COPY_AB="copy_ab";
    public static final String M_P_84_DISABLE_WEATHER="disable_weather";
    public static final String M_P_84_DISABLE_WEATHER_2="disable_weather_2";
    public static final String M_P_84_DISABLE_WEATHER_3="disable_weather_3";
    public static final String M_P_84_DISABLE_WEATHER_4="disable_weather_4";
    public static final String M_P_84_EFFECT="effect";
    public static final String M_P_84_PLATE="plate";
    public static final String M_P_84_WEATHER="weather";
    public static final String M_P_84_WEIGHT="weight";
    private MessagesDataSending(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_84_COPY_AB,"The owner copies the ability of the closest foe to the owner (in front of is possible).");
        e_.add(M_P_84_DISABLE_WEATHER,"The weathers are disabled while the presence of the sent fighter on the front battle.");
        e_.add(M_P_84_DISABLE_WEATHER_2,"If the owner is knocked out, looses its ability or exit from the front battle, then all disabled weathers at sending of the owner become enabled on condition that the number of rounds is positive.");
        e_.add(M_P_84_DISABLE_WEATHER_3,"If a fighter with an item or an ability invoking one of the weathers is sent on the front battle and if it exists an item or an ability disabling the weathers, then the weather is not enabled.");
        e_.add(M_P_84_DISABLE_WEATHER_4,"In contrary, if a move invoking a weather is directly used, then the move does not fail by the disability of the weathers but the weather only will become enabled if no other incompatible weather is invoked.");
        e_.add(M_P_84_EFFECT,"This effect happens after the effects of the moves acting while the sending of a foe on the front battle.");
        e_.add(M_P_84_PLATE,"Le type of the owner becomes the type of the owned plate, else the type of the owner is the base type (on the associated data).");
        e_.add(M_P_84_WEATHER,"While the owner is sent on the front battle, the following weather is enabled:");
        e_.add(M_P_84_WEIGHT,"The weight of the owner is multiplied by {0} while its sending.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_84_COPY_AB,"Le porteur copie la capacité de l'adversaire le plus proche de lui (en face si possible).");
        f_.add(M_P_84_DISABLE_WEATHER,"Les climats sont désactivés pendant la présence du combattant entré sur le terrain.");
        f_.add(M_P_84_DISABLE_WEATHER_2,"Si le porteur tombe KO, perd sa capacité ou sort du terrain, alors tous les climats désactivés à l'entrée du porteur deviennent actifs à condition que le nombre de tours soit positif.");
        f_.add(M_P_84_DISABLE_WEATHER_3,"Si un combattant avec un objet ou une capacité invoquant un des climats entre sur le terrain et s'il existe une capacité ou un objet désactivant les climats, alors le climat n'est pas activé.");
        f_.add(M_P_84_DISABLE_WEATHER_4,"En revanche, si une attaque invoquant un climat est directement utilisée, alors l'attaque n'échoue pas à cause de l'inactivité des climats mais le climat ne deviendra actif que si aucun autre climat incompatible n'était invoqué.");
        f_.add(M_P_84_EFFECT,"Cet effet a lieu après les effets des attaques agissant lors de l'entrée d'un adversaire sur le terrain.");
        f_.add(M_P_84_PLATE,"Le type du porteur devient le type de la plaque possédée, sinon le type du porteur est celui de base (sur la fiche associée dans les données).");
        f_.add(M_P_84_WEATHER,"Lorsque le possesseur entre sur le terrain, le climat suivant est actif:");
        f_.add(M_P_84_WEIGHT,"La masse du porteur est multiplié par {0} lors de son entrée.");
        return f_;
    }
}