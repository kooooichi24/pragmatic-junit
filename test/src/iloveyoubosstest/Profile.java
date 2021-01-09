/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyoubosstest;

public class Profile {
   private Answer answer;

   public boolean matches(Criterion criterion) {
      return answer != null && 
         answer.match(criterion.getAnswer());
   }
   // ...

   public void add(Answer answer) {
      this.answer = answer;
   }
}
