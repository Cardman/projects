package cards.consts;

public final class CouleurValeur {
    public static final int EXCUSE = 0;
    public static final int TRUMP_21 = 1;
    public static final int TRUMP_20 = 2;
    public static final int TRUMP_19 = 3;
    public static final int TRUMP_18 = 4;
    public static final int TRUMP_17 = 5;
    public static final int TRUMP_16 = 6;
    public static final int TRUMP_15 = 7;
    public static final int TRUMP_14 = 8;
    public static final int TRUMP_13 = 9;
    public static final int TRUMP_12 = 10;
    public static final int TRUMP_11 = 11;
    public static final int TRUMP_10 = 12;
    public static final int TRUMP_9 = 13;
    public static final int TRUMP_8 = 14;
    public static final int TRUMP_7 = 15;
    public static final int TRUMP_6 = 16;
    public static final int TRUMP_5 = 17;
    public static final int TRUMP_4 = 18;
    public static final int TRUMP_3 = 19;
    public static final int TRUMP_2 = 20;
    public static final int TRUMP_1 = 21;
    public static final int HEART_KING = 22;
    public static final int HEART_QUEEN = 23;
    public static final int HEART_KNIGHT = 24;
    public static final int HEART_JACK = 25;
    public static final int HEART_10 = 26;
    public static final int HEART_9 = 27;
    public static final int HEART_8 = 28;
    public static final int HEART_7 = 29;
    public static final int HEART_6 = 30;
    public static final int HEART_5 = 31;
    public static final int HEART_4 = 32;
    public static final int HEART_3 = 33;
    public static final int HEART_2 = 34;
    public static final int HEART_1 = 35;
    public static final int SPADE_KING = 36;
    public static final int SPADE_QUEEN = 37;
    public static final int SPADE_KNIGHT = 38;
    public static final int SPADE_JACK = 39;
    public static final int SPADE_10 = 40;
    public static final int SPADE_9 = 41;
    public static final int SPADE_8 = 42;
    public static final int SPADE_7 = 43;
    public static final int SPADE_6 = 44;
    public static final int SPADE_5 = 45;
    public static final int SPADE_4 = 46;
    public static final int SPADE_3 = 47;
    public static final int SPADE_2 = 48;
    public static final int SPADE_1 = 49;
    public static final int DIAMOND_KING = 50;
    public static final int DIAMOND_QUEEN = 51;
    public static final int DIAMOND_KNIGHT = 52;
    public static final int DIAMOND_JACK = 53;
    public static final int DIAMOND_10 = 54;
    public static final int DIAMOND_9 = 55;
    public static final int DIAMOND_8 = 56;
    public static final int DIAMOND_7 = 57;
    public static final int DIAMOND_6 = 58;
    public static final int DIAMOND_5 = 59;
    public static final int DIAMOND_4 = 60;
    public static final int DIAMOND_3 = 61;
    public static final int DIAMOND_2 = 62;
    public static final int DIAMOND_1 = 63;
    public static final int CLUB_KING = 64;
    public static final int CLUB_QUEEN = 65;
    public static final int CLUB_KNIGHT = 66;
    public static final int CLUB_JACK = 67;
    public static final int CLUB_10 = 68;
    public static final int CLUB_9 = 69;
    public static final int CLUB_8 = 70;
    public static final int CLUB_7 = 71;
    public static final int CLUB_6 = 72;
    public static final int CLUB_5 = 73;
    public static final int CLUB_4 = 74;
    public static final int CLUB_3 = 75;
    public static final int CLUB_2 = 76;
    public static final int CLUB_1 = 77;

    /**Numero de couleur de la carte (0: Excuse Tarot,1: Atout Tarot,2: Coeur,3: Pique,4: Carreau,5: Tr&egrave;fle)*/
    private final Suit couleur;
    /**Numero de valeur de la carte (Numeros pour les atouts du tarot, et pour les cartes chiffrees, position pour les figures avec Roi, Dame, Cavalier, Valet)*/
    private final byte valeur;

    private final CardChar nomFigure;
    private final boolean jouable;
    private final int no;

    public CouleurValeur(Suit _c, byte _v, CardChar _f, boolean _j) {
        this.couleur = _c;
        this.valeur = _v;
        this.nomFigure= _f;
        this.jouable = _j;
        this.no = nb();
    }

    public static int exc(){
        return new CouleurValeur(Suit.UNDEFINED,(byte)0,CardChar.EXCUSE,true).nb();
    }

    public static int trump(int _i){
        return new CouleurValeur(Suit.TRUMP,(byte)_i,CardChar.UNDEFINED,true).nb();
    }

    public static int suit(Suit _s,int _i){
        return new CouleurValeur(_s,(byte)_i,CardChar.UNDEFINED,true).nb();
    }

    public static int suit(Suit _s,CardChar _i){
        return new CouleurValeur(_s,(byte)0,_i,true).nb();
    }

    public int nb() {
        if (couleur == Suit.TRUMP) {
            return 21-valeur+1;
        }
        if (couleur == Suit.HEART) {
            return 35-valChar()+1;
        }
        if (couleur == Suit.SPADE) {
            return 49-valChar()+1;
        }
        if (couleur == Suit.DIAMOND) {
            return 63-valChar()+1;
        }
        if (couleur == Suit.CLUB) {
            return 77-valChar()+1;
        }
        return 0;
    }
    private int valChar() {
        if (nomFigure == CardChar.KING) {
            return 14;
        }
        if (nomFigure == CardChar.QUEEN) {
            return 13;
        }
        if (nomFigure == CardChar.KNIGHT) {
            return 12;
        }
        if (nomFigure == CardChar.JACK) {
            return 11;
        }
        return valeur;
    }

    public static boolean vientAvant(int _couleur1, int _valeur1,int _couleur2, int _valeur2) {
        if(_couleur1<_couleur2) {
            return true;
        }
        if(_couleur1==_couleur2) {
            return _valeur1<_valeur2;
        }
        return false;
    }

    public int getNo() {
        return no;
    }

    public Suit getCouleur() {
        return couleur;
    }

    public byte getValeur() {
        return valeur;
    }

    public CardChar getNomFigure() {
        return nomFigure;
    }

    public boolean isJouable() {
        return jouable;
    }
}
