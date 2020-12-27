package iloveyouboss6;

import iloveyouboss7.Answer;
import iloveyouboss7.Scoreable;
import iloveyouboss7.Weight;

public class Criterion implements Scoreable {
   private iloveyouboss7.Weight weight;
   private iloveyouboss7.Answer answer;
   private int score;

   public Criterion(iloveyouboss7.Answer answer, iloveyouboss7.Weight weight) {
      this.answer = answer;
      this.weight = weight;
   }
   
   public Answer getAnswer() { return answer; }
   public Weight getWeight() { return weight; }
   
   public void setScore(int score) { this.score = score; }
   public int getScore() { return score; }
}
