package iloveyouboss6;

import iloveyouboss7.Answer;
import iloveyouboss7.Criteria;
import iloveyouboss7.Criterion;
import iloveyouboss7.Weight;

import java.util.*;

public class Profile { 
   private Map<String, iloveyouboss7.Answer> answers = new HashMap<>();
   private int score;
   private String name;

   public Profile(String name) {
      this.name = name;
   }
   
   public String getName() {
      return name;
   }

   public void add(iloveyouboss7.Answer answer) {
      answers.put(answer.getQuestionText(), answer);
   }
   
   public boolean matches(Criteria criteria) {
      score = 0;
      
      boolean kill = false;
      boolean anyMatches = false; 
      for (Criterion criterion: criteria) {
         Answer answer = answers.get(
               criterion.getAnswer().getQuestionText()); 
         boolean match = 
               criterion.getWeight() == iloveyouboss7.Weight.DontCare ||
               answer.match(criterion.getAnswer());

         if (!match && criterion.getWeight() == Weight.MustMatch) {
            kill = true;
         }
         if (match) {         
            score += criterion.getWeight().getValue();
         }
         anyMatches |= match;  
      }
      if (kill)       
         return false;
      return anyMatches; 
   }

   public int score() {
      return score;
   }
}
