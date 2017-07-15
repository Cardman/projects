package code.expressionlanguage.classes;
import code.expressionlanguage.AccEl;
import code.util.CustList;
import code.util.Numbers;

public class BeanThree {

    @AccEl
    private Numbers<Integer> numbers = new Numbers<Integer>();

    @AccEl
    private int index = CustList.INDEX_NOT_FOUND_ELT;

    @AccEl
    private Numbers<Integer> numbersTwo = new Numbers<Integer>();

    @AccEl
    private int indexTwo = CustList.INDEX_NOT_FOUND_ELT;

    public int getIndex() {
        return index;
    }

    public Numbers<Integer> getNumbers() {
        return numbers;
    }

    public void setIndex(int _index) {
        index = _index;
    }

    public int getIndexTwo() {
        return indexTwo;
    }

    public Numbers<Integer> getNumbersTwo() {
        return numbersTwo;
    }

    public void setIndexTwo(int _indexTwo) {
        indexTwo = _indexTwo;
    }
}
