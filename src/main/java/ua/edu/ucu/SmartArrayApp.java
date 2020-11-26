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

public class SmartArrayApp
{
    private static final int MINIMAL_GPA = 4;
    private static final int STUDY_YEAR = 2;

    public static Integer[]
    filterPositiveIntegersSortAndMultiplyBy2(Integer[] integers)
    {
                
        MyPredicate pr = new MyPredicate() {
            @Override
            public boolean test(Object tested) {
                return ((Integer) tested) > 0;
            }
        };

        MyComparator cmp = new MyComparator() {
            @Override
            public int compare(Object objA, Object objB)
            {
                return ((Integer) objA) - ((Integer) objB);
            }
        };

        MyFunction func = new MyFunction() {
            @Override
            public Object apply(Object obj)
            {
                return 2 * ((Integer) obj);
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

    public static String[]
    findDistinctStudentNamesFrom2ndYearWithGPAgt4AndOrderedBySurname(Student[] students)
    {

        MyPredicate predicate = new MyPredicate() {
            @Override
            public boolean test(Object tested)
            {
                Student student = ((Student) tested);
                return student.getGPA() >= MINIMAL_GPA
                        && student.getYear() == STUDY_YEAR;
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
