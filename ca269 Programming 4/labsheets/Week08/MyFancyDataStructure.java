import java.util.*;

public class MyFancyDataStructure {

    public static void main(String[] args) {
        List<Value> list = Arrays.asList(
            new Value(2),
            new Value(3),
            new Value(1)
        );

       
       Value.setSortLower();
       Collections.sort(list);
       System.out.println(list);
       // OUTPUT: [1, 2, 3]
       
       Value.setSortHigher();
       Collections.sort(list);
       System.out.println(list);
       // OUTPUT: [3, 2, 1]

        List<Value> list2 = Arrays.asList(
            new Value(2), new Value(3), new Value(1));

        list2.sort(new PreferLowerValues());
        System.out.println(list2);
        // OUTPUT: [1, 2, 3]

        list2.sort(new PreferHigherValues());
        System.out.println(list2);
        // OUTPUT: [3, 2, 1]
        
    }
}

class Value implements Comparable<Value>{

    private final int value;
    private static boolean SORT_LOWER;


    public Value(int value){
        this.value = value;
    }


    @Override
    public int compareTo(Value othervalue){
        if (SORT_LOWER){
            return Integer.compare(value, othervalue.getValue());
        }
        return Integer.compare(othervalue.getValue(), value);
    }


    public int getValue() {
        return this.value;
    }

    public String toString(){
        return Integer.toString(this.value);
    }

    public static void setSortLower(){
        SORT_LOWER = true;
    }
    public static void setSortHigher(){
        SORT_LOWER = false;
    }

    public boolean isSortLower(){
        return SORT_LOWER;
    }
    public boolean isSortHigher(){
        return !SORT_LOWER;
    }

}

class PreferLowerValues implements Comparator<Value> {
    public int compare(Value a, Value b) {
        return Integer.compare(a.getValue(), b.getValue());
    }
}

class PreferHigherValues implements Comparator<Value> {
    public int compare(Value a, Value b) {
        return Integer.compare(b.getValue(), a.getValue());
    }
}