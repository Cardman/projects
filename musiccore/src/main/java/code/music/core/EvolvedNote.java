package code.music.core;
import code.music.enums.Gamme;
import code.util.CustList;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;
import jm.constants.Pitches;
import jm.music.data.Note;

import java.math.BigDecimal;
import java.math.MathContext;

public final class EvolvedNote implements Equallable<EvolvedNote>, Displayable {

    private static final String SEPARATOR = "/";
    private static final String SEPARATOR_TIME = ",";
    private static final String EMPTY_STRING = "";
    private static final String SYMBOL_PAUSE = "_";
    private static final String SYMBOL_DIESE = "#";
    private static final int DELTA = 12;
    private static boolean _displayDoubleValue_;
    private Note note;
    private Gamme value;
    private int level;
    private boolean diese;
    private boolean pause;
    private int durationNum;
    private int durationDen;
    private int dynamic = Note.DEFAULT_DYNAMIC;

    public EvolvedNote() {
    }

    public EvolvedNote(Gamme _value, int _level, double _duration, int _dynamic) {
        this(_value, _level, false, _duration, _dynamic);
    }

    public EvolvedNote(Gamme _value, int _level, boolean _diese, double _duration, int _dynamic) {
        note = new Note(_value.getPitch() + DELTA * _level + diese(_diese), _duration, _dynamic);
        note.setDuration(_duration);
        value = _value;
        dynamic = _dynamic;
    }

    public EvolvedNote(double _duration, int _dynamic) {
        note = new Note(Pitches.REST, _duration, _dynamic);
        note.setDuration(_duration);
        pause = true;
        dynamic = _dynamic;
    }

    public EvolvedNote(int _durationNum, int _durationDen, int _dynamic) {
        durationNum = _durationNum;
        durationDen = _durationDen;
        dynamic = _dynamic;
        pause = true;
        afterLoad();
    }

    public EvolvedNote(Gamme _value, int _level, int _durationNum, int _durationDen, int _dynamic) {
        this(_value, _level, false, _durationNum, _durationDen, _dynamic);
    }

    public EvolvedNote(Gamme _value, int _level, boolean _diese, int _durationNum, int _durationDen, int _dynamic) {
        level = _level;
        diese = _diese;
        value = _value;
        durationNum = _durationNum;
        durationDen = _durationDen;
        dynamic = _dynamic;
        afterLoad();
    }

    public EvolvedNote(Note _note) {
        note = _note;
        pause = note.isRest();
        diese = note.isSharp();
        dynamic = note.getDynamic();
        int v_ = _note.getPitch();
        v_ -= diese(diese);
        int level_ = v_ / DELTA;
        level_--;
        int gammePitch_ = v_ % DELTA;
        gammePitch_ += DELTA;
        level = level_;
        double duration_ = note.getDuration();
        BigDecimal durationCopy_ = BigDecimal.valueOf(duration_);
        durationDen = 1;
        while (durationCopy_.remainder(BigDecimal.ONE).signum() != CustList.SIZE_EMPTY) {
            durationCopy_ = durationCopy_.multiply(BigDecimal.valueOf(2));
            durationDen *= 2;
        }
        int durationCopyInt_ = durationCopy_.intValue();
        durationNum = durationCopyInt_ / durationDen;
        value = Gamme.getGammeByPitch(gammePitch_);
        dynamic = _note.getDynamic();
    }

    public EvolvedNote(EvolvedNote _note) {
        note = _note.getNote();
        diese = _note.isDiese();
        pause = _note.isPause();
        level = _note.getLevel();
        durationDen = _note.getDurationDen();
        durationNum = _note.getDurationNum();
        value = _note.getValue();
        dynamic = _note.getDynamic();
    }

    private static int diese(boolean _d) {
        if (_d) {
            return 1;
        }
        return 0;
    }

    private static double getDouble(int _n, int _d) {
        return (double)_n/(double)_d;
    }

    public static boolean isDisplayDoubleValue() {
        return _displayDoubleValue_;
    }

    public static void setDisplayDoubleValue(boolean _displayDoubleValue) {
        _displayDoubleValue_ = _displayDoubleValue;
    }

    public Gamme getValue() {
        return value;
    }

    public Note getNote() {
        return note;
    }

    public int getLevel() {
        return level;
    }

    public boolean isDiese() {
        return diese;
    }

    public boolean isPause() {
        return pause;
    }

    public int getDurationNum() {
        return durationNum;
    }

    public int getDurationDen() {
        return durationDen;
    }

    public int getDynamic() {
        return dynamic;
    }

    public void setValue(Gamme _value) {
        value = _value;
    }

    public void setLevel(int _level) {
        level = _level;
    }

    public void setDiese(boolean _diese) {
        diese = _diese;
    }

    public void setPause(boolean _pause) {
        pause = _pause;
    }

    public void setDurationNum(int _durationNum) {
        durationNum = _durationNum;
    }

    public void setDurationDen(int _durationDen) {
        durationDen = _durationDen;
    }

    public void setDynamic(int _dynamic) {
        dynamic = _dynamic;
    }

    @Override
    public boolean eq(EvolvedNote _obj) {
        if (durationDen != _obj.durationDen) {
            return false;
        }
        if (durationNum != _obj.durationNum) {
            return false;
        }
        if (dynamic != _obj.dynamic) {
            return false;
        }
        if (pause && _obj.pause || !pause && !_obj.pause) {
            return true;
        }
        if (!diese && _obj.diese || diese && !_obj.diese) {
            return false;
        }
        if (level != _obj.level) {
            return false;
        }
        return value == _obj.value;
    }

    @Override
    public String display() {
        String time_;
        if (_displayDoubleValue_) {
            time_ = Double.toString(getDouble(durationNum, durationDen));
        } else {
            time_ = StringList.concat(Integer.toString(durationNum),SEPARATOR,Integer.toString(durationDen));
        }
        String dyn_ = Integer.toString(dynamic);
        if (pause) {
            return StringList.concat(SYMBOL_PAUSE,time_,SEPARATOR_TIME,dyn_);
        }
        String diese_;
        if (diese) {
            diese_ = SYMBOL_DIESE;
        } else {
            diese_ = EMPTY_STRING;
        }
        return StringList.concat(value.name(),diese_,Integer.toString(level),SEPARATOR_TIME,time_,SEPARATOR_TIME,dyn_);
    }

    public void afterLoad() {
        //pitch < MIN_PITCH && pitch > REST + 2
        int pitch_;
        if (pause) {
            pitch_ = Pitches.REST;
        } else {
            pitch_ = value.getPitch() + DELTA * level + diese(diese);
        }
        if (!isValidPitch(pitch_)) {
            pitch_ = Pitches.REST;
        }
        note = new Note(pitch_, getDouble(durationNum, durationDen));
        note.setDuration(getDouble(durationNum, durationDen));
        note.setDynamic(dynamic);
    }

    private static boolean isValidPitch(int _pitch) {
        return _pitch >= Note.MIN_PITCH || _pitch <= Note.REST + 2;
    }
}
