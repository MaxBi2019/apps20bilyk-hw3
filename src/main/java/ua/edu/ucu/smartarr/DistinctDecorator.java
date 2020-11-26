package ua.edu.ucu.smartarr;

import java.util.Arrays;

// Remove duplicates from SmartArray. Use method equals() to compare objects
public class DistinctDecorator extends SmartArrayDecorator
{
    private SmartArray array;

    public DistinctDecorator(SmartArray smartArray)
    {
        super(smartArray);
        makeUnique(smartArray);
    }


    private void makeUnique(SmartArray array)
    {
        Object[] objects = array.toArray();
        Object[] unique  = new Object[objects.length];
        boolean isUnique;
        int index = 0;
        for (int ind_1 = objects.length; ind_1 > 0; --ind_1)
        {
            isUnique = true;
            for (int ind_2 = ind_1-1; ind_2 > 0; --ind_2)
            {
                if (objects[ind_1-1].equals(objects[ind_2-1]))
                {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique)
            {
                unique[index++] = objects[ind_1-1];
            }
            this.array = new BaseArray(Arrays.copyOf(unique, index));
        }

    }

    @Override
    public Object[] toArray() {
        return array.toArray();
    }

    @Override
    public String operationDescription() {
        return null;
    }

    @Override
    public int size() {
        return array.size();
    }
}
