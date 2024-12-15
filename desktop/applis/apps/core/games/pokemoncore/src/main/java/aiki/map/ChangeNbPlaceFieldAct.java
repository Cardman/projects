package aiki.map;

public interface ChangeNbPlaceFieldAct {
    int place();
    void place(int _p);
    boolean matchLevel(int _p, int _l);
    int decr(int _l,int _v);
}
