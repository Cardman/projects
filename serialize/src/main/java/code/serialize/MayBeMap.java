package code.serialize;

interface MayBeMap {

//    ComparatorSerial getCmpSerial();

    void setComponents(String _xml);

    boolean keysAllDifferent();

    boolean mapIsEmpty();
}
