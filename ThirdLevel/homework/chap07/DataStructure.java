import java.util.function.Function;
import java.util.function.Predicate;

public class DataStructure {

    // 创建一个数组
    private final static int SIZE = 15;
    private int[] arrayOfInts = new int[SIZE];

    public DataStructure() {
        // 填充数值，从0开始
        for (int i = 0; i < SIZE; i++) {
            arrayOfInts[i] = i;
        }
    }

    public void printEven() {

        // 只打印偶数
        DataStructureIterator iterator = this.new EvenIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    interface DataStructureIterator extends java.util.Iterator<Integer> { }


    private class EvenIterator implements DataStructureIterator {

        // 从0位置开始，0是偶数
        private int nextIndex = 0;

        public boolean hasNext() {

            // 检查当前元素是否越界
            return (nextIndex <= SIZE - 1);
        }

        public Integer next() {

            // 获取数组当前位置的元素值
            Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);

            // 获取下一个偶数
            nextIndex += 2;
            return retValue;
        }
    }

    //请补充一个方法
    //print(java.util.Function<Integer, Boolean> iterator)
    //要求实现的功能和print(DataStructureIterator iterator) 一样。
    void print(Function<Integer, Boolean> iterator) {
        for (Integer i:arrayOfInts) {
            if (iterator.apply(i)) System.out.print(i + " ");
        }
        System.out.println();
    }

    //请补充一个方法isEvenIndex
    //输入一个整数位置，输出布尔，偶数位置是true，奇数是false
    Predicate<Integer> isEven = index -> index % 2 == 0 ? true : false;
    boolean isEvenIndex(int index) {
        return isEven.test(index);
//        if (index % 2 == 0) return true;
//        else return false;
    }

    //请补充一个方法isOddIndex
    //输入一个整数位置，输出布尔，偶数位置是false，奇数是true
    boolean isOddIndex(int index) {
        return !isEven.test(index);
//        if (index % 2 == 1) return true;
//        else return false;
    }

    public static void main(String s[]) {

        // 输出所有的偶数
        DataStructure ds = new DataStructure();
        ds.printEven();

        //请补充两个调用语句
        //分别调用print(java.util.Function<Integer, Boolean> iterator)
        //输出偶数和奇数
        Function<Integer, Boolean> evenIterator = index -> index % 2 == 0;
        ds.print(evenIterator);
        Function<Integer, Boolean> oddIterator = index -> index % 2 == 1;
        ds.print(oddIterator);

        //请补充使用print和isEvenIndex方法引用，输出偶数
        ds.print(ds::isEvenIndex);

        //请补充使用print和isOddIndex方法引用，输出奇数
        ds.print(ds::isOddIndex);
    }
}