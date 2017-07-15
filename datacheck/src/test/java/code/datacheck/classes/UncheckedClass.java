package code.datacheck.classes;
import java.util.ArrayList;

public class UncheckedClass extends CheckedClass {

    private ArrayList<Integer> list;

    public ArrayList<Integer> getList() {
        return list;
    }

    public void setList(ArrayList<Integer> _list) {
        list = _list;
    }
}
