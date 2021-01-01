package iloveyouboss16bp.domain;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import org.junit.*;

public class StatCompilerTest {

    // responseByQuestionは質問の文字列と回答数を返す
    @Test
    public void responsesByQuestionAnswersCountsByQuestionText() {

        StatCompiler stats = new StatCompiler();
        List<BooleanAnswer> answers = new ArrayList<>();
        answers.add(new BooleanAnswer(1, true));
        answers.add(new BooleanAnswer(1, true));
        answers.add(new BooleanAnswer(1, true));
        answers.add(new BooleanAnswer(1, false));
        answers.add(new BooleanAnswer(2, true));
        answers.add(new BooleanAnswer(2, true));
        Map<Integer, String> questions = new HashMap<>();
        questions.put(1, "奨学制度はありますか?");
        questions.put(2, "転居時のサポートはありますか?");

        Map<String, Map<Boolean, AtomicInteger>> responses =
                stats.responsesByQuestion(answers, questions);

        assertThat(responses.get("奨学制度はありますか?").get(Boolean.TRUE).get(), equalTo(3));
        assertThat(responses.get("奨学制度はありますか?").get(Boolean.FALSE).get(), equalTo(1));
        assertThat(responses.get("転居時のサポートはありますか?").get(Boolean.TRUE).get(), equalTo(2));
        assertThat(responses.get("転居時のサポートはありますか?").get(Boolean.FALSE).get(), equalTo(0));
    }
}