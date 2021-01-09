package iloveyouboss16;

import java.util.Map;

public class MatchSet {
    private Map<String,Answer> answers;
    private int score = 0;
    private Criteria criteria;

    public MatchSet(Map<String,Answer> answers, Criteria criteria) {
        this.answers = answers;
        this.criteria = criteria;
        calculateScore();
    }

    public boolean matches() {
        if (doesNotMeetAnyMustMatchCriterion()) {
            return false;
        }
        return anyMatches();
    }

    // 必須の条件の中にマッチしないものがある
    private boolean doesNotMeetAnyMustMatchCriterion() {
        for (Criterion criterion: criteria) {
            boolean match = criterion.matches(answerMatching(criterion));

            if (!match && criterion.getWeight() == Weight.MustMatch) {
                return true;
            }
        }
        return false;
    }

    private boolean anyMatches() {
        boolean anyMatches = false;
        for (Criterion criterion: criteria) {
            anyMatches |= criterion.matches(answerMatching(criterion));
        }
        return anyMatches;
    }

    private void calculateScore() {
        for (Criterion criterion: criteria) {
            if (criterion.matches(answerMatching(criterion))) {
                score += criterion.getWeight().getValue();
            }
        }
    }

    private Answer answerMatching(Criterion criterion) {
        return answers.get(criterion.getAnswer().getQuestionText());
    }

    public int getScore() {
        return score;
    }
}
