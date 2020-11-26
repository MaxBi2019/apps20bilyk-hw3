package ua.edu.ucu;

import java.util.Arrays;
import ua.edu.ucu.functions.MyComparator;
import ua.edu.ucu.functions.MyFunction;
import ua.edu.ucu.functions.MyPredicate;
import ua.edu.ucu.smartarr.BaseArray;
import ua.edu.ucu.smartarr.DistinctDecorator;
import ua.edu.ucu.smartarr.FilterDecorator;
import ua.edu.ucu.smartarr.SmartArray;
import ua.edu.ucu.smartarr.SortDecorator;
import ua.edu.ucu.smartarr.MapDecorator;

public class SmartArrayApp {

    public static Integer[]
            filterPositiveIntegersSortAndMultiplyBy2(Integer[] integers) {
                
        MyPredicate pr = new MyPredicate() {
            @Override
            public boolean test(Object t) {
                return ((Integer) t) > 0;
            }
        };

        MyComparator cmp = new MyComparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Integer) o1) - ((Integer) o2);
            }
        };

        MyFunction func = new MyFunction() {
            @Override
            public Object apply(Object t) {
                return 2 * ((Integer) t);
            }
        };

        // Input: [-1, 2, 0, 1, -5, 3]
        SmartArray sa = new BaseArray(integers);

        sa = new FilterDecorator(sa, pr); // Result: [2, 1, 3];
        sa = new SortDecorator(sa, cmp); // Result: [1, 2, 3]
        sa = new MapDecorator(sa, func); // Result: [2, 4, 6]

        // Alternative
//        sa = new MapDecorator(
//                    new SortDecorator(
//                        new FilterDecorator(sa, pr),
//                    cmp),
//                func);
        Object[] result = sa.toArray();
        return Arrays.copyOf(result, result.length, Integer[].class);
    }

    static private int minimalGPA = 4;
    static private int yearOfStudy = 2;

    public static String[]
            findDistinctStudentNamesFrom2ndYearWithGPAgt4AndOrderedBySurname(Student[] students) {

        MyPredicate predicate = new MyPredicate() {
            @Override
            public boolean test(Object tested)
            {
                Student student = ((Student) tested);
                return student.getGPA() >= minimalGPA
                        && student.getYear() == yearOfStudy;
            }
        };

        MyComparator comparator = new MyComparator() {
            @Override
            public int compare(Object objA,
                               Object objB)
            {
                Student studentA = (Student) objA;
                Student studentB = (Student) objB;
                return (studentA.getSurname() + studentA.getName())
                        .compareTo(studentB.getSurname() + studentB.getName());
            }
        };

        SmartArray array = new BaseArray(students);
        array = new DistinctDecorator(array);
        array = new FilterDecorator(array, predicate);
        array = new SortDecorator(array, comparator);
        String[] result = new String[array.size()];
        int index = 0;
        Student cur;
        for (Object obj: array.toArray())
        {
            cur = (Student) obj;
            result[index++] = cur.getSurname() + " " + cur.getName();
        }
        return result;
    }
}
