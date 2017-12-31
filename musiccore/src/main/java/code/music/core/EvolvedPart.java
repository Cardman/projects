package code.music.core;
import java.util.Iterator;
import java.util.ListIterator;

import jm.music.data.Part;
import jm.music.data.Phrase;
import code.serialize.XmlTransientable;
import code.util.EqList;
import code.util.annot.RwXml;
import code.util.ints.Equallable;
import code.util.ints.Listable;
import code.util.ints.MidListable;

@RwXml
public final class EvolvedPart implements XmlTransientable, MidListable<EvolvedPhrase>, Equallable<EvolvedPart> {

    private static final String SEP_TWO = "/";

    private static final String SEP_ONE = ":";

    private static final String STRING = "";

    private transient Part part;

    private EqList<EvolvedPhrase> phrases = new EqList<EvolvedPhrase>();

    private int instrument;

    private int channel;

    private String name = STRING;

    private int numerator;

    private int denominator;

    @RwXml
    public EvolvedPart() {
        instrument = Part.DEFAULT_INSTRUMENT;
        channel = Part.DEFAULT_CHANNEL;
        numerator = Part.DEFAULT_NUMERATOR;
        denominator = Part.DEFAULT_DENOMINATOR;
        part = new Part(name, instrument, channel);
        part.setDenominator(denominator);
        part.setDenominator(numerator);
    }

    public EvolvedPart(Part _ph) {
        instrument = _ph.getInstrument();
        channel = _ph.getChannel();
        name = _ph.getTitle();
        numerator = _ph.getNumerator();
        denominator = _ph.getDenominator();
        part = new Part(name, instrument, channel);
        part.setDenominator(denominator);
        part.setDenominator(numerator);
        for (Phrase n: _ph.getPhraseArray()) {
            part.add(n);
            phrases.add(new EvolvedPhrase(n));
        }
    }

    public EqList<EvolvedPhrase> getPhrases() {
        return phrases;
    }

    public Part getPart() {
        return part;
    }

    public void updateData() {
        phrases.clear();
        for (Phrase n: part.getPhraseArray()) {
            phrases.add(new EvolvedPhrase(n));
        }
    }

    @Override
    public void beforeSave() {
    }

    @Override
    public void afterLoad() {
        if (instrument < Part.DEFAULT_INSTRUMENT) {
            instrument = Part.DEFAULT_INSTRUMENT;
        }
        part = new Part(name, instrument, channel);
        part.setDenominator(denominator);
        part.setNumerator(numerator);
        for (EvolvedPhrase e: phrases) {
            part.addPhrase(e.getPhrase());
        }
    }

    @Override
    public String toString() {
        StringBuilder str_ = new StringBuilder();
        str_.append(name);
        str_.append(SEP_ONE);
        str_.append(phrases.toString());
        str_.append(SEP_ONE);
        str_.append(instrument);
        str_.append(SEP_ONE);
        str_.append(channel);
        str_.append(SEP_ONE);
        str_.append(numerator);
        str_.append(SEP_TWO);
        str_.append(denominator);
        return str_.toString();
    }

    @Override
    public boolean eq(EvolvedPart _g) {
        return phrases.eq(_g.phrases);
    }
    @Override
    public int size() {
        return phrases.size();
    }

    @Override
    public boolean isEmpty() {
        return phrases.isEmpty();
    }

    @Override
    public boolean contains(EvolvedPhrase _o) {
        return phrases.containsObj(_o);
    }

    @Override
    public Iterator<EvolvedPhrase> iterator() {
        return phrases.iterator();
    }

    @Override
    public Object[] toArray() {
        return phrases.toArray();
    }

//    @Override
//    public <T> T[] toArray(T[] _a) {
//        return phrases.toArray(_a);
//    }

    @Override
    public void add(EvolvedPhrase _e) {
        part.add(_e.getPhrase());
        phrases.add(_e);
    }

    @Override
    public void remove(EvolvedPhrase _o) {
        part.removePhrase(_o.getPhrase());
        phrases.removeObj(_o);
    }

    @Override
    public void clear() {
        part.empty();
        phrases.clear();
    }

    @Override
    public EvolvedPhrase get(int _index) {
        return phrases.get(_index);
    }

    @Override
    public void set(int _index, EvolvedPhrase _element) {
        Phrase[] array_ = part.getPhraseArray();
        array_[_index] = _element.getPhrase();
        part.empty();
        part.addPhraseList(array_);
        phrases.set(_index, _element);
    }

    @Override
    public void remove(int _index) {
        part.removePhrase(_index);
        phrases.remove(_index);
    }

//    @Override
//    public int indexOf(Object _o) {
//        return phrases.indexOf(_o);
//    }
//
//    @Override
//    public int lastIndexOf(Object _o) {
//        return phrases.lastIndexOf(_o);
//    }

    @Override
    public ListIterator<EvolvedPhrase> listIterator() {
        return phrases.listIterator();
    }

    @Override
    public ListIterator<EvolvedPhrase> listIterator(int _index) {
        return phrases.listIterator(_index);
    }

//    @Override
//    public EqList<EvolvedPhrase> subList(int _fromIndex, int _toIndex) {
//        return phrases.subList(_fromIndex, _toIndex);
//    }

    @Override
    public Listable<EvolvedPhrase> sub(int _from, int _to) {
        return phrases.sub(_from, _to);
    }

    public int getInstrument() {
        return instrument;
    }

    public int getChannel() {
        return channel;
    }

    public String getName() {
        return name;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setInstrument(int _instrument) {
        instrument = _instrument;
    }

    public void setChannel(int _channel) {
        channel = _channel;
    }

    public void setName(String _name) {
        name = _name;
    }

    public void setNumerator(int _numerator) {
        numerator = _numerator;
    }

    public void setDenominator(int _denominator) {
        denominator = _denominator;
    }
}
