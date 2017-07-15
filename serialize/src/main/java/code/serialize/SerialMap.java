package code.serialize;
import code.util.CustList;

final class SerialMap<V> {

    private final CustList<TemplateSerialValue<V>> list = new CustList<TemplateSerialValue<V>>();

    SerialMap() {
    }

    CustList<TemplateSerialValue<V>> getElements() {
        return list;
    }
}
