package code.expressionlanguage.classes;

public class PickableList {

    private final GeneObjects list = new GeneObjects();

    public boolean removeAndExistAfter(int _i) {
        if (_i < list.size()) {
            list.remove(_i);
            return true;
        }
        return false;
    }

    public GeneObjects getList() {
        return list;
    }
}
