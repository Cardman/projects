package cards.gui;

import cards.consts.CouleurValeur;
import code.threads.IntCallable;
import code.util.CollCapacity;
import code.util.StringMap;

public final class MiniCardsSampleGene implements IntCallable<StringMap<StringMap<int[][]>>> {
    @Override
    public StringMap<StringMap<int[][]>> call() {
        StringMap<StringMap<int[][]>> s_ = new StringMap<StringMap<int[][]>>();
        StringMap<int[][]> map_ = def();
        s_.addEntry("en", map_);
        s_.addEntry("fr", map_);
        return s_;
    }

    static StringMap<int[][]> def(){
        StringMap<int[][]> out_ = new StringMap<int[][]>(new CollCapacity(78));
        defTrs(out_);
        out_.addEntry(""+CouleurValeur.HEART_KING,new int[1][1]);
        out_.addEntry(""+CouleurValeur.HEART_QUEEN,new int[1][1]);
        out_.addEntry(""+CouleurValeur.HEART_KNIGHT,new int[1][1]);
        out_.addEntry(""+CouleurValeur.HEART_JACK,new int[1][1]);
        defHeart(out_);
        out_.addEntry(""+CouleurValeur.SPADE_KING,new int[1][1]);
        out_.addEntry(""+CouleurValeur.SPADE_QUEEN,new int[1][1]);
        out_.addEntry(""+CouleurValeur.SPADE_KNIGHT,new int[1][1]);
        out_.addEntry(""+CouleurValeur.SPADE_JACK,new int[1][1]);
        defSpade(out_);
        out_.addEntry(""+CouleurValeur.DIAMOND_KING,new int[1][1]);
        out_.addEntry(""+CouleurValeur.DIAMOND_QUEEN,new int[1][1]);
        out_.addEntry(""+CouleurValeur.DIAMOND_KNIGHT,new int[1][1]);
        out_.addEntry(""+CouleurValeur.DIAMOND_JACK,new int[1][1]);
        defDiamond(out_);
        out_.addEntry(""+CouleurValeur.CLUB_KING,new int[1][1]);
        out_.addEntry(""+CouleurValeur.CLUB_QUEEN,new int[1][1]);
        out_.addEntry(""+CouleurValeur.CLUB_KNIGHT,new int[1][1]);
        out_.addEntry(""+CouleurValeur.CLUB_JACK,new int[1][1]);
        defClub(out_);
        return out_;
    }

    private static void defTrs(StringMap<int[][]> _out) {
        _out.addEntry(""+CouleurValeur.EXCUSE,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_21,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_20,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_19,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_18,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_17,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_16,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_15,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_14,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_13,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_12,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_11,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_10,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_9,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_8,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_7,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_6,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_5,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_4,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_3,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_2,new int[1][1]);
        _out.addEntry(""+CouleurValeur.TRUMP_1,new int[1][1]);
    }

    private static void defHeart(StringMap<int[][]> _out) {
        _out.addEntry(""+CouleurValeur.HEART_10,new int[1][1]);
        _out.addEntry(""+CouleurValeur.HEART_9,new int[1][1]);
        _out.addEntry(""+CouleurValeur.HEART_8,new int[1][1]);
        _out.addEntry(""+CouleurValeur.HEART_7,new int[1][1]);
        _out.addEntry(""+CouleurValeur.HEART_6,new int[1][1]);
        _out.addEntry(""+CouleurValeur.HEART_5,new int[1][1]);
        _out.addEntry(""+CouleurValeur.HEART_4,new int[1][1]);
        _out.addEntry(""+CouleurValeur.HEART_3,new int[1][1]);
        _out.addEntry(""+CouleurValeur.HEART_2,new int[1][1]);
        _out.addEntry(""+CouleurValeur.HEART_1,new int[1][1]);
    }

    private static void defSpade(StringMap<int[][]> _out) {
        _out.addEntry(""+CouleurValeur.SPADE_10,new int[1][1]);
        _out.addEntry(""+CouleurValeur.SPADE_9,new int[1][1]);
        _out.addEntry(""+CouleurValeur.SPADE_8,new int[1][1]);
        _out.addEntry(""+CouleurValeur.SPADE_7,new int[1][1]);
        _out.addEntry(""+CouleurValeur.SPADE_6,new int[1][1]);
        _out.addEntry(""+CouleurValeur.SPADE_5,new int[1][1]);
        _out.addEntry(""+CouleurValeur.SPADE_4,new int[1][1]);
        _out.addEntry(""+CouleurValeur.SPADE_3,new int[1][1]);
        _out.addEntry(""+CouleurValeur.SPADE_2,new int[1][1]);
        _out.addEntry(""+CouleurValeur.SPADE_1,new int[1][1]);
    }

    private static void defDiamond(StringMap<int[][]> _out) {
        _out.addEntry(""+CouleurValeur.DIAMOND_10,new int[1][1]);
        _out.addEntry(""+CouleurValeur.DIAMOND_9,new int[1][1]);
        _out.addEntry(""+CouleurValeur.DIAMOND_8,new int[1][1]);
        _out.addEntry(""+CouleurValeur.DIAMOND_7,new int[1][1]);
        _out.addEntry(""+CouleurValeur.DIAMOND_6,new int[1][1]);
        _out.addEntry(""+CouleurValeur.DIAMOND_5,new int[1][1]);
        _out.addEntry(""+CouleurValeur.DIAMOND_4,new int[1][1]);
        _out.addEntry(""+CouleurValeur.DIAMOND_3,new int[1][1]);
        _out.addEntry(""+CouleurValeur.DIAMOND_2,new int[1][1]);
        _out.addEntry(""+CouleurValeur.DIAMOND_1,new int[1][1]);
    }

    private static void defClub(StringMap<int[][]> _out) {
        _out.addEntry(""+CouleurValeur.CLUB_10,new int[1][1]);
        _out.addEntry(""+CouleurValeur.CLUB_9,new int[1][1]);
        _out.addEntry(""+CouleurValeur.CLUB_8,new int[1][1]);
        _out.addEntry(""+CouleurValeur.CLUB_7,new int[1][1]);
        _out.addEntry(""+CouleurValeur.CLUB_6,new int[1][1]);
        _out.addEntry(""+CouleurValeur.CLUB_5,new int[1][1]);
        _out.addEntry(""+CouleurValeur.CLUB_4,new int[1][1]);
        _out.addEntry(""+CouleurValeur.CLUB_3,new int[1][1]);
        _out.addEntry(""+CouleurValeur.CLUB_2,new int[1][1]);
        _out.addEntry(""+CouleurValeur.CLUB_1,new int[1][1]);
    }

}
