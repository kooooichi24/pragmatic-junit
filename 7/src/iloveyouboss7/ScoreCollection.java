package iloveyouboss7;

import java.util.ArrayList;
import java.util.List;

public class ScoreCollection {
   private List<iloveyouboss7.Scoreable> scores = new ArrayList<>();
   
   public void add(iloveyouboss7.Scoreable scoreable) {
      scores.add(scoreable);
   }
   
   public int arithmeticMean() {
      int total = scores.stream().mapToInt(Scoreable::getScore).sum();
      return total / scores.size();
   }
}