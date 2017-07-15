package code.expressionlanguage.classes;

import code.expressionlanguage.AccEl;

public class ArrayContainer {

    @AccEl
    private int[] array = new int[]{5};

    @AccEl
    private ArrayBis[] compo = new ArrayBis[]{new ArrayBis()};

    public int[] getArray() {
        return array;
    }

    public ArrayBis[] getCompo() {
        return compo;
    }
}
