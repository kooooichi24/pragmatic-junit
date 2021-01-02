package iloveyouboss16bp.controller;

import static org.junit.Assert.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;
import iloveyouboss16bp.domain.*;
import org.junit.*;

public class QuestionControllerTest {

    private QuestionController controller;

//    @Before
//    public void create() {
//        controller = new QuestionController();
//        controller.deleteAll();
//    }

    // getCreateTimestampは追加された時刻を返す
    @Test
    public void questionAnswersDateAdded() {
        Instant now = new Date().toInstant();
        System.out.println(now);
    }
}