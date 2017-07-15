package code.music.enums;
import jm.constants.Pitches;
import jm.music.data.Note;

public enum Gamme {
    DO(Note.C, Pitches.C0),
    RE(Note.D, Pitches.D0),
    MI(Note.E, Pitches.E0),
    FA(Note.F, Pitches.F0),
    SOL(Note.G, Pitches.G0),
    LA(Note.A, Pitches.A0),
    SI(Note.B, Pitches.B0);
    private final String equivalent;
    private final int pitch;
    Gamme(String _equivalent, int _pitch) {
        equivalent = _equivalent;
        pitch = _pitch;
    }

    public static Gamme getGammeByPitch(int _pitch) {
        for (Gamme g: values()) {
            if (g.pitch == _pitch) {
                return g;
            }
        }
        return null;
    }

    public String getEquivalent() {
        return equivalent;
    }

    public int getPitch() {
        return pitch;
    }
}
