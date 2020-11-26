package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyPredicate;

// Tests every element and removes it if it doesn't satisfy MyPredicate
public class FilterDecorator extends SmartArrayDecorator
{
    private SmartArray array;

    public FilterDecorator(SmartArray smartArray, MyPredicate predicate)
    {
        super(smartArray);
        filter(smartArray, predicate);
    }

    private void filter(SmartArray array, MyPredicate predicate)
    {
        Object[] objects = array.toArray();
        int num = 0;
        Object[] filtered;
        for (int ind = objects.length; ind > 0; --ind)
        {
            if (predicate.test(objects[ind-1]))
            {
                num += 1;
            }
            else
            {
                objects[ind-1] = null;
            }
        }
        filtered = new Object[num];
        num = 0;
        for (Object obj: objects)
        {
            if (obj != null)
            {
                filtered[num++] = obj;
            }
        }
        this.array = new BaseArray(filtered);
    }

    @Override
    public Object[] toArray() {
        return array.toArray();
    }

    @Override
    public String operationDescription() {
        return "Filter element applying predicate function";
    }

    @Override
    public int size() {
        return array.size();
    }
}
