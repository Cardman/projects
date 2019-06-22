package code.music.core;
import java.util.Iterator;

import jm.music.data.Part;
import jm.music.data.Score;
import jm.util.Play;
import code.util.EqList;
import code.util.ints.Displayable;
import code.util.ints.Listable;

public final class EvolvedScore implements MidListable<EvolvedPart>, Displayable {

    private static final String STRING = "";

    private Score score;

    private EqList<EvolvedPart> parts = new EqList<EvolvedPart>();

    private String name = STRING;

    public EvolvedScore() {
        score = new Score();
    }

    public EvolvedScore(Score _ph) {
        score = new Score();
        name = _ph.getTitle();
        for (Part n: _ph.getPartArray()) {
            score.add(n);
            parts.add(new EvolvedPart(n));
        }
    }

    public void play() {
        Play.midi(score);
    }

    public EqList<EvolvedPart> getParts() {
        return parts;
    }

    public Score getScore() {
        return score;
    }

    public void updateData() {
        parts.clear();
        for (Part n: score.getPartArray()) {
            parts.add(new EvolvedPart(n));
        }
    }

    public void afterLoad() {
        score = new Score(name);
        for (EvolvedPart e: parts) {
            score.addPart(e.getPart());
        }
    }

    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder(name);
        for (EvolvedPart e: parts) {
            str_.append(",");
            str_.append(e.display());
        }
        return str_.toString();
    }

    @Override
    public int size() {
        return parts.size();
    }

    @Override
    public boolean isEmpty() {
        return parts.isEmpty();
    }

    @Override
    public boolean contains(EvolvedPart _o) {
        return parts.containsObj(_o);
    }

    @Override
    public Iterator<EvolvedPart> iterator() {
        return parts.iterator();
    }

    @Override
    public void add(EvolvedPart _e) {
        score.add(_e.getPart());
        parts.add(_e);
    }

    @Override
    public void remove(EvolvedPart _o) {
        score.removePart(_o.getPart());
        parts.removeObj(_o);
    }

    @Override
    public void clear() {
        score.empty();
        parts.clear();
    }

    @Override
    public EvolvedPart get(int _index) {
        return parts.get(_index);
    }

    @Override
    public void set(int _index, EvolvedPart _element) {
        score.removePart(_index);
        score.insertPart(_element.getPart(), _index);
        parts.set(_index, _element);
    }

    @Override
    public void remove(int _index) {
        score.removePart(_index);
        parts.remove(_index);
    }

    @Override
    public Listable<EvolvedPart> sub(int _from, int _to) {
        return parts.sub(_from, _to);
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public void setParts(EqList<EvolvedPart> _parts) {
        parts = _parts;
    }
}
