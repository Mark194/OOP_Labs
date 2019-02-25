package sorting;

import java.util.ArrayList;
import java.util.List;

public class Sorting {

   public List sortBySimpleInsert(List<Double> list){
       double temp;
       int j;
       for (int i = 0; i < list.size() - 1; i++) {
           if (list.get(i) > list.get(i+1)){
               temp = list.get(i+1);
               list.set(i+1, list.get(i));
               j = i;
               while (j > 0 && temp < list.get(j-1)) {
                   list.set(j, list.get(j-1));
                   j--;
               }
               list.set(j, temp);
           }
       }
       return list;
   }

    public List simpleSorting(List<Double> list){
       for (int i = 0; i < list.size()-1; i++){
           int index = i;
           for (int j = i + 1; j < list.size(); j++){
               if (list.get(j) < list.get(index))
                   index = j;
           }
           double temp = list.get(i);
           list.set(i, list.get(index));
           list.set(index, temp);
       }
       return list;
    }

    public List sortDirectExchange(List<Double> list){
       for (int i = 0; i < list.size() - 1; i++){
           for (int j = (list.size() - 1); j > i; j--){
               if (list.get(j-1) > list.get(j)) {
                   double temp = list.get(j-1);
                   list.set(j-1,list.get(j));
                   list.set(j,temp);
               }
           }
       }
       return list;
    }

    public List sortingShell(List<Double> list){
       int j = 0, step;
       double temp;
       for (step = list.size()/2; step > 0; step/= 2)
           for (int i = step; i < list.size(); i++) {
               temp = list.get(i);
               for (j = i; j >= step; j -= step) {
                   if (temp < list.get(j - step))
                       list.set(j, list.get(j - step));
                   else
                       break;
               }
               list.set(j, temp);
           }
       return list;
    }


    public static double MAX_SAFE_VALUE = Math.pow(2, sun.misc.DoubleConsts.SIGNIFICAND_WIDTH) - 1;

    private Integer[] nums; //Коллекция "номерков"
    private Double[] values; //Колекция выбранных переменных

    public List methodQuadraticSampling(List<Double> list){
       int numGroup = (int)Math.sqrt(list.size()), numElem = list.size()/numGroup;
       nums = new Integer[numElem];
       values = new Double[numElem];
       List<Double> copy = list;
       List<Double> res = new ArrayList<>();
       int why; // индекс переменной, которую запишем в массив
       for (int i = 0; i < numGroup; i++)
           findMinElemToSquart( numElem, copy, i);
       for (int i = 0; i < list.size(); i++){
           why = findIndexOfMinValue(numGroup);
           res.add(i, values[why]);
           copy.set(why*numElem+nums[why], 10000.0);
           values[why]=10000.0;
           findMinElemToSquart(numElem, copy, why);
       }
       return res;
    }

    private void findMinElemToSquart(int numElem, List<Double> copy, int e) {
        double min = MAX_SAFE_VALUE;
        for (int i = 0; i < numElem; i++) {
            if ((min > copy.get(e*numElem+i)) && (copy.get(e*numElem+i) !=  10000.0)){
                min = copy.get(e*numElem+i);
                nums[e] = i ;
            }
            values[e] = copy.get(e*numElem+nums[e]);
        }
    }

    private int findIndexOfMinValue(int size){
        double min = MAX_SAFE_VALUE;
        int temp = 0;
        for (int i = 0; i < size; i++) {
            if (min > values[i] && (values[i] !=  10000.0)){
                min = values[i];
                temp = i;
            }
        }
        return temp;
    }

    public List<Double> algorithmQuickSort(List<Double> list, int low, int high){
        int i=low, j=high, t;
        double k = list.get(low);
        while (low < high) {
            while ((list.get(high) >= k) && (low < high)) high--;
            if (low != high) {
                list.set(low, list.get(high));
                low++;
            }
            while ((list.get(low) <= k) && (low < high)) low++;
            if (low != high) {
                list.set(high, list.get(low));
                high--;
            }
        }
        list.set(low, k);
        t = low;
        low = i;
        high = j;
        if (low < t)
            algorithmQuickSort(list, low, t - 1);
        if (high > t)
            algorithmQuickSort(list, t + 1, high);
        return list;

    }

    public List<Double> mergesortInner(List<Double> buffer1, List<Double> buffer2,
                                       int startIndex, int endIndex) {
        if (startIndex >= endIndex - 1) {
            return buffer1;
        }
        // уже отсортирован.
        int middle = startIndex + (endIndex - startIndex) / 2;
        List<Double> sorted1 = mergesortInner(buffer1, buffer2, startIndex, middle);
        List<Double> sorted2 = mergesortInner(buffer1, buffer2, middle, endIndex);

        // Слияние
        int index1 = startIndex;
        int index2 = middle;
        int destIndex = startIndex;
        List<Double> result = sorted1 == buffer1 ? buffer2 : buffer1;
        while (index1 < middle && index2 < endIndex) {
            result.set(destIndex++, sorted1.get(index1) < sorted2.get(index2)
                    ? sorted1.get(index1++) : sorted2.get(index2++));
        }
        while (index1 < middle) {
            result.set(destIndex++, sorted1.get(index1++));
        }
        while (index2 < endIndex) {
            result.set(destIndex++, sorted2.get(index2++));
        }
        return result;
    }
}
