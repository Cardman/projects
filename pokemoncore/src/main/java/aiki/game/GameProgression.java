package aiki.game;
import aiki.db.DataBase;
import aiki.fight.pokemon.TrainerPlaceNames;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.places.Campaign;
import aiki.map.places.Place;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import aiki.util.Coords;
import code.maths.LgInt;
import code.util.CustList;
import code.util.EqList;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;

public final class GameProgression {

    private final boolean finishedGame;
    private final String nickname;
    private final StringMap<EqList<StringList>> notAtAllFamiliesBase;
    private final StringMap<EqList<StringList>> partialFamiliesBaseCaught;
    private final StringMap<EqList<StringList>> partialFamiliesBaseNotCaught;
    private final StringMap<EqList<StringList>> fullFamiliesBase;
    private final EqList<TrainerPlaceNames> beatenImportantTrainers;
    private final EqList<TrainerPlaceNames> unBeatenImportantTrainers;
    private final ShortMap<Integer> remainingOtherTrainerPlaces;
    private final StringList visitedPlaces;
    private final StringList unVisitedPlaces;
    private final LgInt money;
    private final int remainStepsRepel;
    private final int nbRemainingEggs;
    private final int nbRemainingNotMaxLevel;
    private final int nbRemainingNotMaxHappiness;

    public GameProgression(DataBase _data, Game _game) {
        finishedGame = _game.endGame(_data);
        nickname = _game.getPlayer().getNickname();
        money = _game.getPlayer().getMoney();
        remainStepsRepel = _game.getPlayer().getRemainingRepelSteps();
        notAtAllFamiliesBase = new StringMap<EqList<StringList>>();
        partialFamiliesBaseCaught = new StringMap<EqList<StringList>>();
        partialFamiliesBaseNotCaught = new StringMap<EqList<StringList>>();
        fullFamiliesBase = new StringMap<EqList<StringList>>();
        for (String b: _data.getFamilies().getKeys()) {
            EqList<StringList> caughtPokemonStages_ = new EqList<StringList>();
            EqList<StringList> uncaughtPokemonStages_ = new EqList<StringList>();
            StringList caughtPokemon_ = new StringList();
            StringList uncaughtPokemon_ = new StringList();
            for (StringList s: _data.getFamilies().getVal(b).getStages()) {
                StringList caughtPokemonStage_ = new StringList();
                StringList uncaughtPokemonStage_ = new StringList();
                for (String e: s) {
                    if (_game.getPlayer().estAttrape(e)) {
                        caughtPokemon_.add(e);
                        caughtPokemonStage_.add(e);
                    } else {
                        uncaughtPokemon_.add(e);
                        uncaughtPokemonStage_.add(e);
                    }
                }
                caughtPokemonStages_.add(caughtPokemonStage_);
                uncaughtPokemonStages_.add(uncaughtPokemonStage_);
            }
            if (caughtPokemon_.isEmpty()) {
                notAtAllFamiliesBase.put(b,uncaughtPokemonStages_);
                continue;
            }
            if (uncaughtPokemon_.isEmpty()) {
                fullFamiliesBase.put(b,caughtPokemonStages_);
                continue;
            }
            partialFamiliesBaseCaught.put(b, caughtPokemonStages_);
            partialFamiliesBaseNotCaught.put(b, uncaughtPokemonStages_);
        }
        beatenImportantTrainers = new EqList<TrainerPlaceNames>();
        for (Coords c: _game.getBeatenGymLeader()) {
            Place pl_ = _data.getMap().getPlaces().getVal(c.getNumberPlace());
            beatenImportantTrainers.add(new TrainerPlaceNames(_data.getMap().getTrainerName(c), pl_.getName()));
        }
        unBeatenImportantTrainers = new EqList<TrainerPlaceNames>();
        for (Coords c: _game.getUnBeatenGymLeader()) {
            Place pl_ = _data.getMap().getPlaces().getVal(c.getNumberPlace());
            unBeatenImportantTrainers.add(new TrainerPlaceNames(_data.getMap().getTrainerName(c), pl_.getName()));
        }
        remainingOtherTrainerPlaces = new ShortMap<Integer>();
        for (NbFightCoords k: _game.getBeatTrainer().getKeys()) {
            Coords coords_ = k.getCoords();
            Campaign place_ = (Campaign) _data.getMap().getPlaces().getVal(coords_.getNumberPlace());
            LevelWithWildPokemon level_ = place_.getLevelCompaignByCoords(coords_);
            TrainerMultiFights trainer_ = (TrainerMultiFights) level_.getCharacters().getVal(coords_.getLevel().getPoint());
            if (k.getNbFight() < trainer_.getTeamsRewards().getLastIndex()) {
                continue;
            }
            boolean beaten_ = _game.getBeatTrainer().getVal(k);
            if (beaten_) {
                continue;
            }
            int rem_ = 0;
            if (remainingOtherTrainerPlaces.contains(coords_.getNumberPlace())) {
                rem_ = remainingOtherTrainerPlaces.getVal(coords_.getNumberPlace());
            }
            rem_++;
            remainingOtherTrainerPlaces.put(coords_.getNumberPlace(), rem_);
        }
        visitedPlaces = new StringList();
        unVisitedPlaces = new StringList();
        for (Coords c: _game.getVisited()) {
            Place pl_ = _data.getMap().getPlaces().getVal(c.getNumberPlace());
            visitedPlaces.add(pl_.getName());
        }
        for (Coords c: _game.getUnVisited()) {
            Place pl_ = _data.getMap().getPlaces().getVal(c.getNumberPlace());
            unVisitedPlaces.add(pl_.getName());
        }
        int nbRemainingEggs_ = _game.getPlayer().getEggsList().size();
        CustList<PokemonPlayer> pks_ = new CustList<PokemonPlayer>();
        pks_.addAllElts(_game.getPlayer().getPokemonPlayerList().values());
        for (UsablePokemon u: _game.getPlayer().getBox()) {
            if (u instanceof Egg) {
                nbRemainingEggs_++;
                continue;
            }
            PokemonPlayer pk_ = (PokemonPlayer) u;
            pks_.add(pk_);
        }
        nbRemainingEggs = nbRemainingEggs_;
        int nbRemainingNotMaxHappiness_ = 0;
        int nbRemainingNotMaxLevel_ = 0;
        for (PokemonPlayer p: pks_) {
            if (p.getLevel() < _data.getMaxLevel()) {
                nbRemainingNotMaxLevel_++;
            }
            if (p.getHappiness() < _data.getHappinessMax()) {
                nbRemainingNotMaxHappiness_++;
            }
        }
        nbRemainingNotMaxHappiness = nbRemainingNotMaxHappiness_;
        nbRemainingNotMaxLevel = nbRemainingNotMaxLevel_;
    }

    public boolean isFinishedGame() {
        return finishedGame;
    }

    public String getNickname() {
        return nickname;
    }

    public StringMap<EqList<StringList>> getNotAtAllFamiliesBase() {
        return notAtAllFamiliesBase;
    }

    public StringMap<EqList<StringList>> getPartialFamiliesBaseCaught() {
        return partialFamiliesBaseCaught;
    }

    public StringMap<EqList<StringList>> getPartialFamiliesBaseNotCaught() {
        return partialFamiliesBaseNotCaught;
    }

    public StringMap<EqList<StringList>> getFullFamiliesBase() {
        return fullFamiliesBase;
    }

    public EqList<TrainerPlaceNames> getBeatenImportantTrainers() {
        return beatenImportantTrainers;
    }

    public EqList<TrainerPlaceNames> getUnBeatenImportantTrainers() {
        return unBeatenImportantTrainers;
    }

    public ShortMap<Integer> getRemainingOtherTrainerPlaces() {
        return remainingOtherTrainerPlaces;
    }

    public StringList getVisitedPlaces() {
        return visitedPlaces;
    }

    public StringList getUnVisitedPlaces() {
        return unVisitedPlaces;
    }

    public LgInt getMoney() {
        return money;
    }

    public int getRemainStepsRepel() {
        return remainStepsRepel;
    }

    public int getNbRemainingEggs() {
        return nbRemainingEggs;
    }

    public int getNbRemainingNotMaxLevel() {
        return nbRemainingNotMaxLevel;
    }

    public int getNbRemainingNotMaxHappiness() {
        return nbRemainingNotMaxHappiness;
    }
}
