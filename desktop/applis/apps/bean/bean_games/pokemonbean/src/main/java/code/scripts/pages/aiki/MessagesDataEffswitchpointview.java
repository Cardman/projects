package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffswitchpointview {
    public static final String M_P_63_ATTRACT="attract";
    public static final String M_P_63_EFFECT="effect";
    public static final String M_P_63_MIRROR="mirror";
    public static final String M_P_63_THIEF="thief";
    private MessagesDataEffswitchpointview(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_63_ATTRACT,"The user of this move suffers damage of moves with single choice instead of its partners.");
        e_.add(M_P_63_EFFECT,"This effect changes the view point of fighters for a move.");
        e_.add(M_P_63_MIRROR,"All sent fighter using a move successful against the user of this move is target of its own \"counterable\" move and the user of the move {0} becomes user of each used move against it.");
        e_.add(M_P_63_THIEF,"The user of this move waits that its target use a \"thievable\" move.While the target uses a move, the user of the move {0} thieves the positives effects of the move that the target has chosen without being able to use it.The user of this move invokes also the move of the target withour using pp and the target cannot use its move during a round.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_63_ATTRACT,"Le lanceur de cette attaque subit les dégâts des attaques à cible unique au lieu de ses partenaires.");
        f_.add(M_P_63_EFFECT,"Cet effet change le point de vue des combattants pour une attaque.");
        f_.add(M_P_63_MIRROR,"Tout combattant attaquant le lanceur de cette attaque réussie se retrouve cible de sa propre attaque \"retournable\" et le lanceur de l''attaque {0} devient lanceur de chaque attaque lancée contre lui.");
        f_.add(M_P_63_THIEF,"Le lanceur de cette attaque attend que sa cible lance une attaque \"saisissable\".Lorsque le tour de la cible vient, le lanceur de l''attaque {0} vole les effets positifs de l''attaque que la cible a choisie sans pouvoir la lancer.Le lanceur de cette attaque invoque aussi l''attaque de la cible sans utiliser de pp et la cible ne peut pas lancer son attaque pendant son tour.");
        return f_;
    }
}