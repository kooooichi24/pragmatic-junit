package iloveyouboss6;

import iloveyouboss7.Criterion;

import java.util.*;

public class Criteria implements Iterable<iloveyouboss7.Criterion> {

   private List<iloveyouboss7.Criterion> criteria = new ArrayList<>();

   public void add(iloveyouboss7.Criterion criterion) {
      criteria.add(criterion);
   }

   @Override
   public Iterator<Criterion> iterator() {
      return criteria.iterator();
   }
   
   public int arithmeticMean() {
      return 0;
   }

   public double geometricMean(int[] numbers) {
      int totalProduct = Arrays.stream(numbers).reduce(1, (product, number) -> product * number);
      return Math.pow(totalProduct, 1.0 / numbers.length);
   }
}
