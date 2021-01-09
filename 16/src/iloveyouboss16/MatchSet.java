package iloveyouboss16;

import java.util.Map;

public class MatchSet {
    private Map<String,Answer> answers;
    private Criteria criteria;

    public MatchSet(Map<String,Answer> answers, Criteria criteria) {
        this.answers = answers;
        this.criteria = criteria;
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

    private Answer answerMatching(Criterion criterion) {
        return answers.get(criterion.getAnswer().getQuestionText());
    }

    public int getScore() {
        int score = 0;
        for (Criterion criterion: criteria) {
            if (criterion.matches(answerMatching(criterion))) {
                score += criterion.getWeight().getValue();
            }
        }
        return score;
    }
}
