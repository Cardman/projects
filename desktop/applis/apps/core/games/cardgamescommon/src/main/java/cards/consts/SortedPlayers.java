package cards.consts;

import code.util.Ints;
import code.util.core.IndexConstants;

public final class SortedPlayers {
    private final int nombreJoueurs;

    public SortedPlayers(int _nb) {
        this.nombreJoueurs = _nb;
    }

    public static void nextPlayers(Ints _joueursRepartitionConnueMemo, Ints _joueursRepartitionInconnue, int _nbPlayers) {
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nbPlayers; joueur_++) {
            if (!_joueursRepartitionConnueMemo.containsObj(joueur_)) {
                _joueursRepartitionInconnue.add(joueur_);
            }
        }
    }

    public static void shift(Ints _joueursRepartitionConnue, Ints _joueursRepartitionConnue2, Ints _joueursRepartitionInconnue) {
        _joueursRepartitionInconnue.clear();
        _joueursRepartitionConnue.clear();
        _joueursRepartitionConnue.addAllElts(_joueursRepartitionConnue2);
        _joueursRepartitionConnue2.clear();
    }

    public int getNombreJoueurs() {
        return nombreJoueurs;
    }
    public static Ints intersectionJoueurs(Ints _joueurs1, Ints _joueurs2) {
        Ints joueurs_ = new Ints();
        for (int j : _joueurs1) {
            if(!_joueurs2.containsObj(j)) {
                continue;
            }
            joueurs_.add(j);
        }
        return joueurs_;
    }

    public static Ints autresJoueurs(Ints _joueurs,
                                      int _nombreJoueurs) {
        Ints joueurs_ = new Ints();
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            if (!_joueurs.containsObj(joueur_)) {
                joueurs_.add(joueur_);
            }
        }
        return joueurs_;
    }
    public int getNextPlayer(int _player) {
        int next_ = _player;
        next_++;
        return next_%getNombreJoueurs();
    }
    public int index(int _joueur, int _entameur, int _total) {
        if(_total<nombreJoueurs) {
            //Pli en_ cours_
            if(_joueur>=_entameur) {
                return _joueur-_entameur;
            }
            return _joueur+nombreJoueurs-_entameur;
        }
        //Pli non_ separe_
        if(_joueur>=_entameur) {
            return _joueur-_entameur;
        }
        return _joueur-_entameur+nombreJoueurs;
    }

    public Ints joueursAyantJoue(int _starter, int _total) {
        Ints joueurs_=new Ints();
        for(int j : getSortedPlayers(_starter)) {
            if (aJoue(j, _total, _starter)) {
                joueurs_.add(j);
            }
        }
        return joueurs_;
    }
    public Ints joueursAyantJoueAvant(int _pnumero, int _starter, int _total){
        Ints joueurs_=new Ints();
        for(int j : getSortedPlayers(_starter)) {
            if (aJoue(j, _total, _starter)) {
                if (j == _pnumero) {
                    break;
                }
                joueurs_.add(j);
            }
        }
        return joueurs_;
    }
    public boolean aJoue(int _joueur, int _total, int _entameur) {
        if(_total<nombreJoueurs) {
            //Pli en_ cours_
            if(_joueur>=_entameur) {
                return _joueur - _entameur < _total;
            }
            return _joueur - _entameur + nombreJoueurs < _total;
        }
        //Pli non_ separe_
        return true;
    }
    public Ints getSortedPlayers(int _player) {
        Ints players_ = new Ints();
        int next_ = _player % getNombreJoueurs();
        while (players_.size() < getNombreJoueurs()) {
            players_.add(next_);
            next_ = getNextPlayer(next_);
        }
        return players_;
    }

    public Ints getSortedPlayersAfter(int _player) {
        Ints players_ = new Ints();
        int next_ = _player;
        next_++;
        next_ = next_%getNombreJoueurs();
        while (players_.size() < getNombreJoueurs()) {
            players_.add(next_);
            next_ = getNextPlayer(next_);
        }
        return players_;
    }
}
