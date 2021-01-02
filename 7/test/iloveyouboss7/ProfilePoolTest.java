package iloveyouboss7;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.util.*;

import org.checkerframework.checker.units.qual.A;
import org.junit.*;
import scratch.*;

public class ProfilePoolTest {
    private ProfilePool pool;
    private Profile smeltInc;
    private Profile langrsoft;
    private BooleanQuestion doTheyReimburseTuition;

    @Before
    public void create() {
        pool = new ProfilePool();
        smeltInc = new Profile("Smelt Inc.");
        langrsoft = new Profile("Langrsoft");
        doTheyReimburseTuition = new BooleanQuestion(1, "Reimburses tuition?");
    }

    private Criteria soleNeed(Question question, int value, Weight weight) {
        Criteria criteria = new Criteria();
        criteria.add(new Criterion(new Answer(question, value), weight));
        return criteria;
    }

    // スコアの順に結果を返す
    @Test
    public void answersResultsInScoredOrder() {
        smeltInc.add(new Answer(doTheyReimburseTuition, Bool.FALSE));
        pool.add(smeltInc);
        langrsoft.add(new Answer(doTheyReimburseTuition, Bool.TRUE));
        pool.add(langrsoft);

        pool.score(soleNeed(doTheyReimburseTuition, Bool.TRUE, Weight.Important));
        List<Profile> ranked = pool.ranked();

        assertThat(ranked.toArray(), equalTo(new Profile[] { langrsoft, smeltInc }));
    }
}
