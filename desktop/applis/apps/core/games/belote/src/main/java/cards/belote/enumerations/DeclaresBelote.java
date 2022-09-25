package cards.belote.enumerations;
import cards.belote.HandBelote;
import cards.consts.CardChar;
import code.util.IdList;

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
    private final byte taille;

    DeclaresBelote(){
        figure = CardChar.UNDEFINED;
        constante = false;
        valeur = 0;
        force = 0;
        points = 0;
        taille = 0;
    }
    DeclaresBelote(CardChar _figure, int _force, int _points){
        constante = true;
        figure = _figure;
        force = _force;
        points = _points;
        valeur = 0;
        taille = 0;
    }
    DeclaresBelote(int _valeur, int _force, int _points, boolean _constante){
        figure = CardChar.UNDEFINED;
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

    public static IdList<DeclaresBelote> annoncesValides(){
        IdList<DeclaresBelote> liste_ = new IdList<DeclaresBelote>();
        liste_.add(FOUR_1);
        liste_.add(FOUR_KING);
        liste_.add(FOUR_QUEEN);
        liste_.add(FOUR_JACK);
        liste_.add(FOUR_10);
        liste_.add(FOUR_9);
        liste_.add(FOUR_8);
        liste_.add(FOUR_7);
        liste_.add(HUNDRED);
        liste_.add(FIFTY);
        liste_.add(THIRTY);
        return liste_;
    }
    public int nombreCartes(){
        int nb_ = 0;
        if(!constante){
            return taille;
        }
        if(estUneFigure()){
            for(CardBelote c: HandBelote.pileBase()){
                if(c.getId().getNomFigure() == figure){
                    nb_++;
                }
            }
        }else{
            for(CardBelote c:HandBelote.pileBase()){
                if(c.getId().getValeur() == valeur){
                    nb_++;
                }
            }
        }
        return nb_;
    }
}
