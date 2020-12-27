package iloveyouboss6;

import iloveyouboss7.Question;

import java.util.*;
import java.util.stream.*;

public class Person {
   private List<iloveyouboss7.Question> characteristics = new ArrayList<>();

   public void add(iloveyouboss7.Question characteristic) {
      characteristics.add(characteristic);
   }

   public List<iloveyouboss7.Question> getCharacteristics() {
      return characteristics;
   }

   public List<Question> withCharacteristic(String questionPattern) {
      return characteristics.stream().filter(c -> c.getText().endsWith(questionPattern)).collect(Collectors.toList());
   }

}

/*
// your answer
// their answer
// how important is it to you

me very organized
you very organized
very important

me no
you no


irrelevant 0
little 1
10
50
mandatory 250

how much did other person satisfy?
      
      multiply scores
      take nth root
      
      .98 * .94 take sqrt (2 questions)
      
      (geometric mean)

*/
