package code.util.pagination;
import static code.util.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import code.util.CustList;
import code.util.EnumMap;
import code.util.StringMap;
import code.util.classestest.PaginationPerson;
import code.util.classestest.Person;
import code.util.classestest.Sex;
import code.util.classestest.SortingPerson;
import code.util.pagination.SearchingMode;

@SuppressWarnings("static-method")
public class PaginationPersonTest {

    private static final StringMap<EnumMap<Sex,String>> TRANSLATIONS = new StringMap<EnumMap<Sex,String>>();

    private static final String LANGUAGE = "language";

    @BeforeClass
    public static void initDataBase() {
        EnumMap<Sex,String> words_ = new EnumMap<Sex,String>();
        words_.put(Sex.GIRL, Sex.GIRL.name());
        words_.put(Sex.BOY, Sex.BOY.name());
        TRANSLATIONS.put(LANGUAGE, words_);
    }

    @Test
    public void calculateRendered1Test() {
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setNbResultsPerPage(2);
        SortingPerson sorting_;
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        sorting_ = toSorting(person_, 1);
        pagination_.getResults().put(sorting_, person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        sorting_ = toSorting(person_, 0);
        pagination_.getResults().put(sorting_, person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        sorting_ = toSorting(person_, 3);
        pagination_.getResults().put(sorting_, person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        sorting_ = toSorting(person_, 2);
        pagination_.getResults().put(sorting_, person_);
        pagination_.sort();
        pagination_.calculateRendered();
        CustList<SortingPerson> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(2, sorted_.size());
        assertEq("TATA", sorted_.get(0).getFirstName());
        assertEq("M", sorted_.get(0).getLastName());
        assertEq(Sex.GIRL, sorted_.get(0).getSex());
        assertEq(10, sorted_.get(0).getAge());
        assertEq(0, sorted_.get(0).getIndex());
        assertEq("TOTO", sorted_.get(1).getFirstName());
        assertEq("P", sorted_.get(1).getLastName());
        assertEq(Sex.BOY, sorted_.get(1).getSex());
        assertEq(12, sorted_.get(1).getAge());
        assertEq(1, sorted_.get(1).getIndex());
    }

    @Test
    public void calculateRendered2Test() {
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setNbResultsPerPage(3);
        SortingPerson sorting_;
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        sorting_ = toSorting(person_, 1);
        pagination_.getResults().put(sorting_, person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        sorting_ = toSorting(person_, 0);
        pagination_.getResults().put(sorting_, person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        sorting_ = toSorting(person_, 3);
        pagination_.getResults().put(sorting_, person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        sorting_ = toSorting(person_, 2);
        pagination_.getResults().put(sorting_, person_);
        pagination_.sort();
        pagination_.setNumberPage(1);
        pagination_.calculateRendered();
        CustList<SortingPerson> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(1, sorted_.size());
        assertEq("TITI", sorted_.get(0).getFirstName());
        assertEq("T", sorted_.get(0).getLastName());
        assertEq(Sex.BOY, sorted_.get(0).getSex());
        assertEq(8, sorted_.get(0).getAge());
        assertEq(3, sorted_.get(0).getIndex());
    }

    @Test
    public void calculateRendered3Test() {
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setNbResultsPerPage(2);
        pagination_.sort();
        pagination_.setNumberPage(1);
        pagination_.calculateRendered();
        CustList<SortingPerson> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(0, sorted_.size());
    }

    @Test
    public void pages1Test() {
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setNbResultsPerPage(1);
        assertEq(0, pagination_.pages());
    }

    @Test
    public void pages2Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setNbResultsPerPage(5);
        pagination_.search(people_);
        pagination_.sort();
        pagination_.calculateRendered();
        assertEq(1, pagination_.pages());
    }

    @Test
    public void pages3Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setNbResultsPerPage(3);
        pagination_.search(people_);
        assertEq(2, pagination_.pages());
    }

    @Test
    public void pages4Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        assertEq(2, pagination_.pages());
    }

    @Test
    public void checkLine1Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        pagination_.checkLine(1);
        assertEq(1, pagination_.getLine());
    }

    @Test
    public void checkLine2Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        pagination_.checkLine(1);
        pagination_.checkLine(1);
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void checkLine3Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        pagination_.checkLine(1);
        pagination_.checkLine(2);
        assertEq(2, pagination_.getLine());
    }

    @Test
    public void changePage1Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        pagination_.changePage(1);
        CustList<SortingPerson> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(2, sorted_.size());
        assertEq(1, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void changePage2Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        pagination_.checkLine(0);
        pagination_.changePage(1);
        assertEq(4, pagination_.getResults().size());
        CustList<SortingPerson> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(2, sorted_.size());
        assertEq(1, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void changePage3Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        pagination_.checkLine(0);
        pagination_.changePage(0);
        assertEq(4, pagination_.getResults().size());
        CustList<SortingPerson> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(2, sorted_.size());
        assertEq(0, pagination_.getNumberPage());
        assertEq(0, pagination_.getLine());
    }

    @Test
    public void currentIndex1Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        assertEq(-1, pagination_.currentIndex());
    }

    @Test
    public void currentIndex2Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        pagination_.checkLine(0);
        assertEq(0, pagination_.currentIndex());
    }

    @Test
    public void currentIndex3Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        pagination_.checkLine(1);
        assertEq(1, pagination_.currentIndex());
    }

    @Test
    public void currentObject1Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        assertNull(pagination_.currentObject());
    }

    @Test
    public void currentObject2Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        SortingPerson sorting_;
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        pagination_.checkLine(0);
        sorting_ = new CustList<SortingPerson>(pagination_.getResults().getKeys()).first();
        assertSame(pagination_.getResults().getVal(sorting_), pagination_.currentObject());
    }

    @Test
    public void enabledPrevious1Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        assertTrue(!pagination_.enabledPrevious());
    }

    @Test
    public void enabledPrevious2Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        pagination_.changePage(1);
        assertTrue(pagination_.enabledPrevious());
    }

    @Test
    public void enabledPrevious3Test() {
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        assertTrue(!pagination_.enabledPrevious());
    }

    @Test
    public void enabledNext1Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        assertTrue(pagination_.enabledNext());
    }

    @Test
    public void enabledNext2Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        pagination_.changePage(1);
        assertTrue(!pagination_.enabledNext());
    }

    @Test
    public void enabledNext3Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(4);
        pagination_.search(people_);
        assertTrue(!pagination_.enabledNext());
    }

    @Test
    public void next1Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        pagination_.next();
        assertEq(1, pagination_.getNumberPage());
    }

    @Test
    public void previous1Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        pagination_.next();
        pagination_.previous();
        assertEq(0, pagination_.getNumberPage());
    }

    @Test
    public void begin1Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        pagination_.next();
        pagination_.next();
        pagination_.begin();
        assertEq(0, pagination_.getNumberPage());
    }

    @Test
    public void end1Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.search(people_);
        pagination_.end();
        assertEq(1, pagination_.getNumberPage());
    }

    @Test
    public void nextDelta1Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(1);
        pagination_.search(people_);
        pagination_.setDelta(3);
        pagination_.nextDelta();
        assertEq(3, pagination_.getNumberPage());
    }

    @Test
    public void nextDelta2Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(2);
        pagination_.setDelta(3);
        pagination_.search(people_);
        pagination_.nextDelta();
        assertEq(1, pagination_.getNumberPage());
    }

    @Test
    public void previousDelta1Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(1);
        pagination_.setDelta(2);
        pagination_.search(people_);
        pagination_.nextDelta();
        pagination_.nextDelta();
        pagination_.previousDelta();
        assertEq(1, pagination_.getNumberPage());
    }

    @Test
    public void previousDelta2Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.setNbResultsPerPage(1);
        pagination_.setDelta(2);
        pagination_.search(people_);
        pagination_.nextDelta();
        pagination_.nextDelta();
        pagination_.previousDelta();
        pagination_.previousDelta();
        assertEq(0, pagination_.getNumberPage());
    }

    @Test
    public void changeNbResultsPerPage1Test() {
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.changeNbResultsPerPage(3);
        assertEq(1, pagination_.getDelta());
        assertEq(3, pagination_.getNbResultsPerPage());
        assertEq(-1, pagination_.getLine());
        assertEq(-1, pagination_.getNumberPage());
        assertEq(0, pagination_.getResults().size());
        assertEq(0, pagination_.getRendered().size());
    }

    @Test
    public void changeNbResultsPerPage2Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.changeNbResultsPerPage(3);
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.search(people_);
        assertEq(1, pagination_.getDelta());
        assertEq(3, pagination_.getNbResultsPerPage());
        assertEq(-1, pagination_.getLine());
        assertEq(0, pagination_.getNumberPage());
        assertEq(4, pagination_.getResults().size());
        assertEq(3, pagination_.getRendered().size());
    }

    @Test
    public void changeNbResultsPerPage3Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.changeNbResultsPerPage(1);
        pagination_.search(people_);
        pagination_.next();
        pagination_.next();
        pagination_.changeNbResultsPerPage(3);
        assertEq(1, pagination_.getDelta());
        assertEq(3, pagination_.getNbResultsPerPage());
        assertEq(-1, pagination_.getLine());
        assertEq(0, pagination_.getNumberPage());
        assertEq(4, pagination_.getResults().size());
        assertEq(3, pagination_.getRendered().size());
    }

    @Test
    public void newSearch1Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.changeNbResultsPerPage(2);
        pagination_.search(people_);
        pagination_.getCriteria().setMaxAge(9);
        pagination_.newSearch();
        assertEq(2, pagination_.getResults().size());
        CustList<SortingPerson> sorted_;
        sorted_ = new CustList<SortingPerson>(pagination_.getResults().getKeys());
        person_ = pagination_.getResults().getVal(sorted_.get(0));
        assertEq("TITI", person_.getFirstName());
        assertEq("T", person_.getLastName());
        assertEq(Sex.BOY, person_.getSex());
        assertEq(8, person_.getAge());
        assertEq(2, sorted_.get(0).getIndex());
        person_ = pagination_.getResults().getVal(sorted_.get(1));
        assertEq("TETE", person_.getFirstName());
        assertEq("E", person_.getLastName());
        assertEq(Sex.GIRL, person_.getSex());
        assertEq(8, person_.getAge());
        assertEq(3, sorted_.get(1).getIndex());
        sorted_ = pagination_.getRendered();
        assertEq(2, sorted_.size());
        assertEq("TITI", sorted_.get(0).getFirstName());
        assertEq("T", sorted_.get(0).getLastName());
        assertEq(Sex.BOY, sorted_.get(0).getSex());
        assertEq(8, sorted_.get(0).getAge());
        assertEq(2, sorted_.get(0).getIndex());
        assertEq("TETE", sorted_.get(1).getFirstName());
        assertEq("E", sorted_.get(1).getLastName());
        assertEq(Sex.GIRL, sorted_.get(1).getSex());
        assertEq(8, sorted_.get(1).getAge());
        assertEq(3, sorted_.get(1).getIndex());
        assertEq(0, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void newSearch2Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.changeNbResultsPerPage(2);
        pagination_.search(people_);
        pagination_.getCriteria().setContentOfFirstName("*L*");
        pagination_.getCriteria().setSearchModeFirstName(SearchingMode.META_CHARACTER);
        pagination_.newSearch();
        assertEq(0, pagination_.getResults().size());
        CustList<SortingPerson> sorted_;
        sorted_ = pagination_.getRendered();
        assertEq(0, sorted_.size());
        assertEq(-1, pagination_.getNumberPage());
        assertEq(-1, pagination_.getLine());
    }

    @Test
    public void clear1Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.changeNbResultsPerPage(2);
        pagination_.setDelta(3);
        pagination_.search(people_);
        pagination_.clear();
        assertEq(0, pagination_.getResults().size());
        assertEq(0, pagination_.getRendered().size());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getNumberPage());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getLine());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.currentIndex());
    }

    @Test
    public void clear2Test() {
        CustList<Person> people_ = new CustList<Person>();
        Person person_;
        person_ = newPerson("TOTO", "P", Sex.BOY, 12);
        people_.add(person_);
        person_ = newPerson("TATA", "M", Sex.GIRL, 10);
        people_.add(person_);
        person_ = newPerson("TITI", "T", Sex.BOY, 8);
        people_.add(person_);
        person_ = newPerson("TETE", "E", Sex.GIRL, 8);
        people_.add(person_);
        PaginationPerson pagination_;
        pagination_ = new PaginationPerson();
        pagination_.setTranslation(TRANSLATIONS, LANGUAGE);
        pagination_.changeNbResultsPerPage(3);
        pagination_.setDelta(1);
        pagination_.getCriteria().setContentOfFirstName("L*");
        pagination_.getCriteria().setSearchModeFirstName(SearchingMode.META_CHARACTER);
        pagination_.search(people_);
        pagination_.clear();
        assertEq(0, pagination_.getResults().size());
        assertEq(0, pagination_.getRendered().size());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getNumberPage());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.getLine());
        assertEq(CustList.INDEX_NOT_FOUND_ELT, pagination_.currentIndex());
    }

    private static SortingPerson toSorting(
            Person _person, int _index) {
        SortingPerson sorting_;
        sorting_ = new SortingPerson();
        sorting_.setFirstName(_person.getFirstName());
        sorting_.setLastName(_person.getLastName());
        sorting_.setSex(_person.getSex());
        sorting_.setAge(_person.getAge());
        sorting_.setIndex(_index);
        return sorting_;
    }

    private static Person newPerson(
            String _firstName,
            String _lastName,
            Sex _sex,
            int _age) {
        Person person_ = new Person();
        person_.setFirstName(_firstName);
        person_.setLastName(_lastName);
        person_.setSex(_sex);
        person_.setAge(_age);
        return person_;
    }
}
