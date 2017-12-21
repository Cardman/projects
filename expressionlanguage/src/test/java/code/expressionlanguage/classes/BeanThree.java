package code.expressionlanguage.classes;
import code.util.CustList;
import code.util.Numbers;

public class BeanThree {


    private Numbers<Integer> numbers = new Numbers<Integer>();


    private int index = CustList.INDEX_NOT_FOUND_ELT;


    private Numbers<Integer> numbersTwo = new Numbers<Integer>();


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
