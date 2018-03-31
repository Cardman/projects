package aiki.beans.solution;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorWildPokemonDto;
import aiki.beans.facade.solution.dto.PlaceTrainerDto;
import aiki.beans.facade.solution.dto.StepDto;
import aiki.beans.facade.solution.dto.WildPokemonDto;
import aiki.comparators.ComparatorPlaceLevel;
import aiki.fight.pokemon.GenderName;
import aiki.map.Solution;
import aiki.map.Step;
import aiki.map.levels.Level;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.LevelLeague;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.map.util.PlaceLevel;
import aiki.util.Coords;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;
import code.util.TreeMap;
import code.util.opers.BaseSixtyFourUtil;

public class SolutionBean extends CommonBean {

    private Solution solution;
    private CustList<StepDto> steps = new CustList<StepDto>();

    @Override
    public void beforeDisplaying() {
        DataBase data_ = (DataBase) getDataBase();
        if (solution == null) {
            solution = data_.getMap().getSolution();
        }
        steps = new CustList<StepDto>();
        for (Step step_: solution.getSteps()) {
//                TreeMap<Pair<Short,Byte>, List<WildPokemonDto>> treeMap_ = new TreeMap<new>(new NaturalComparator<Pair<Short,Byte>>() {
//                    @Override
//                    public int compare(Pair<Short, Byte> _o1, Pair<Short, Byte> _o2) {
//                        int res_ = _o1.getFirst().compareTo(_o2.getFirst());
//                        if (res_ != 0) {
//                            return res_;
//                        }
//                        return _o1.getSecond().compareTo(_o2.getSecond());
//                    }
//                });
            TreeMap<PlaceLevel,CustList<WildPokemonDto>> treeMap_ = new TreeMap<PlaceLevel,CustList<WildPokemonDto>>(new ComparatorPlaceLevel());
            for (PlaceLevel key_: step_.getCaughtPokemonPlaceLevel().getKeys()) {
                CustList<WildPokemonDto> pokemon_ = new CustList<WildPokemonDto>();
                for (GenderName pk_: step_.getCaughtPokemonPlaceLevel().getVal(key_)) {
                    String name_ = data_.getTranslatedPokemon().getVal(getLanguage()).getVal(pk_.getName());
                    String image_ = BaseSixtyFourUtil.getStringByImage(data_.getMiniPk().getVal(pk_.getName()));
                    String gender_ = data_.getTranslatedGenders().getVal(getLanguage()).getVal(pk_.getGender());
                    pokemon_.add(new WildPokemonDto(image_, name_, gender_));
                }
                pokemon_.sortElts(new ComparatorWildPokemonDto());
                treeMap_.put(key_, pokemon_);
            }
            StepDto s_ = new StepDto(treeMap_);
            CustList<PlaceTrainerDto> places_ = new CustList<PlaceTrainerDto>();
            for (Coords cTrainer_: step_.getImportantsTrainers()) {
                Place pl_ = data_.getMap().getPlaces().getVal(cTrainer_.getNumberPlace());
                Level level_ = pl_.getLevelByCoords(cTrainer_);
                String trainerName_ = DataBase.EMPTY_STRING;
                if (level_ instanceof LevelIndoorGym) {
                    LevelIndoorGym lev_ = (LevelIndoorGym) level_;
                    trainerName_ = lev_.getGymLeader().getName();
                }
                if (level_ instanceof LevelLeague) {
                    League league_ = (League) pl_;
                    trainerName_ = league_.getRooms().last().getTrainer().getName();
                }
                if (level_ instanceof LevelWithWildPokemon) {
                    trainerName_ = data_.getMap().getTrainerName(cTrainer_);
                }
                places_.add(new PlaceTrainerDto(pl_.getName(), trainerName_));
            }
            s_.getNames().addAllElts(places_);
            steps.add(s_);
        }
    }
    public EqList<PlaceLevel> getPlaces(Long _indexStep) {
        StepDto step_ = steps.get(_indexStep.intValue());
        EqList<PlaceLevel> keys_ = new EqList<PlaceLevel>();
        keys_.addAllElts(step_.getPokemon().getKeys());
//        keys_.sort(new NaturalComparator<Pair<Short,Byte>>() {
//            @Override
//            public int compare(Pair<Short, Byte> _o1, Pair<Short, Byte> _o2) {
//                int res_ = _o1.getFirst().compareTo(_o2.getFirst());
//                if (res_ != 0) {
//                    return res_;
//                }
//                return _o1.getSecond().compareTo(_o2.getSecond());
//            }
//        });
        keys_.sortElts(new ComparatorPlaceLevel());
        return keys_;
    }
    public String getPlace(Long _indexStep, Long _indexPlace) {
        StepDto step_ = steps.get(_indexStep.intValue());
        EqList<PlaceLevel> keys_ = new EqList<PlaceLevel>();
        keys_.addAllElts(step_.getPokemon().getKeys());
//        keys_.sort(new NaturalComparator<Pair<Short,Byte>>() {
//            @Override
//            public int compare(Pair<Short, Byte> _o1, Pair<Short, Byte> _o2) {
//                int res_ = _o1.getFirst().compareTo(_o2.getFirst());
//                if (res_ != 0) {
//                    return res_;
//                }
//                return _o1.getSecond().compareTo(_o2.getSecond());
//            }
//        });
        keys_.sortElts(new ComparatorPlaceLevel());
        DataBase data_ = (DataBase) getDataBase();
        PlaceLevel key_ = keys_.get(_indexPlace.intValue());
        Place place_ = data_.getMap().getPlaces().getVal(key_.getPlace());
        String name_ = place_.getName();
        if (place_.getLevelsMap().size() == DataBase.ONE_POSSIBLE_CHOICE) {
            return name_;
        }
        return StringList.concat(name_,SPACE,Long.toString(key_.getLevel()));
    }
    public CustList<WildPokemonDto> getPokemonList(Long _indexStep, Long _indexLevelPlace) {
        StepDto step_ = steps.get(_indexStep.intValue());
        EqList<PlaceLevel> keys_ = new EqList<PlaceLevel>();
        keys_.addAllElts(step_.getPokemon().getKeys());
//        keys_.sort(new NaturalComparator<Pair<Short,Byte>>() {
//            @Override
//            public int compare(Pair<Short, Byte> _o1, Pair<Short, Byte> _o2) {
//                int res_ = _o1.getFirst().compareTo(_o2.getFirst());
//                if (res_ != 0) {
//                    return res_;
//                }
//                return _o1.getSecond().compareTo(_o2.getSecond());
//            }
//        });
        keys_.sortElts(new ComparatorPlaceLevel());
        return step_.getPokemon().getVal(keys_.get(_indexLevelPlace.intValue()));
    }
    public String getPokemonName(Long _indexStep, Long _indexLevelPlace, Long _indexPokemon) {
        Step step_ = solution.getSteps().get(_indexStep.intValue());
        EqList<PlaceLevel> keys_ = new EqList<PlaceLevel>();
        keys_.addAllElts(step_.getCaughtPokemonPlaceLevel().getKeys());
//        keys_.sort(new NaturalComparator<Pair<Short,Byte>>() {
//            @Override
//            public int compare(Pair<Short, Byte> _o1, Pair<Short, Byte> _o2) {
//                int res_ = _o1.getFirst().compareTo(_o2.getFirst());
//                if (res_ != 0) {
//                    return res_;
//                }
//                return _o1.getSecond().compareTo(_o2.getSecond());
//            }
//        });
        keys_.sortElts(new ComparatorPlaceLevel());
        return step_.getCaughtPokemonPlaceLevel().getVal(keys_.get(_indexLevelPlace.intValue())).get(_indexPokemon.intValue()).getName();
    }

    public CustList<StepDto> getSteps() {
        return steps;
    }
}