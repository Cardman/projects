package cards.belote.enumerations;
import cards.consts.CardChar;
import code.util.EnumList;

public enum DeclaresBelote {
    UNDEFINED,
    FOUR_1(1,6,100,true),
    FOUR_KING(CardChar.KING,6,100),
    FOUR_QUEEN(CardChar.QUEEN,6,100),
    FOUR_JACK(CardChar.JACK,8,200),
    FOUR_10(10,6,100,true),
    FOUR_9(9,7,150,true),
    FOUR_8(8,2,0,true),
    FOUR_7(7,2,0,true),
    HUNDRED(5,5,100,false),
    FIFTY(4,4,50,false),
    THIRTY(3,3,30,false);
    private final boolean constante;
    private final CardChar figure;
    private final byte valeur;
    private final int force;
    private final int points;
    private final boolean annoncable;
    private final byte taille;

    DeclaresBelote(){
        annoncable = false;
        figure = CardChar.UNDEFINED;
        constante = false;
        valeur = 0;
        force = 0;
        points = 0;
        taille = 0;
    }
    DeclaresBelote(CardChar _figure, int _force, int _points){
        annoncable = true;
        constante = true;
        figure = _figure;
        force = _force;
        points = _points;
        valeur = 0;
        taille = 0;
    }
    DeclaresBelote(int _valeur, int _force, int _points, boolean _constante){
        figure = CardChar.UNDEFINED;
        annoncable = true;
        constante = _constante;
        if(constante){
            taille = 0;
            valeur = (byte) _valeur;
        }else{
            taille = (byte) _valeur;
            valeur = 0;
        }
        force = _force;
        points = _points;
    }
    public boolean estAnnoncable(){
        return annoncable;
    }
    public boolean estConstante(){
        return constante;
    }
    private boolean estUneFigure(){
        return figure != CardChar.UNDEFINED;
    }
    public CardChar getFigure() {
        return figure;
    }
    public byte getValeur() {
        return valeur;
    }
    public int getForce(){
        return force;
    }
    public int getPoints() {
        return points;
    }

    public static EnumList<DeclaresBelote> annoncesValides(){
        EnumList<DeclaresBelote> liste_ = new EnumList<DeclaresBelote>();
        for(DeclaresBelote a: DeclaresBelote.values()){
            if(!a.estAnnoncable()){
                continue;
            }
            liste_.add(a);
        }
        return liste_;
    }
    public int nombreCartes(){
        int nb_ = 0;
        if(!constante){
            return taille;
        }
        if(estUneFigure()){
            for(CardBelote c:CardBelote.values()){
                if(!c.isPlayable()){
                    continue;
                }
                if(c.getNomFigure() == figure){
                    nb_++;
                }
            }
        }else{
            for(CardBelote c:CardBelote.values()){
                if(!c.isPlayable()){
                    continue;
                }
                if(c.valeur() == valeur){
                    nb_++;
                }
            }
        }
        return nb_;
    }
}
