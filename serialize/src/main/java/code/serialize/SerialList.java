package code.serialize;
import code.util.CustList;


final class SerialList<T extends ElementsSerial> extends CustList<T> {

    SerialList() {
    }
    SerialList(SerialList<T> _other) {
        super(_other);
    }
}
