/*
* A class that defines the KnapsackItem type
* @author Owen McGrath
* @version 9/9/2024
*/
public class KnapsackItem
{
    private final int weight;
    private final int value;
    private final String descriptor;

    public KnapsackItem(int weight, int value, String descriptor)
    {
        this.weight = weight;
        this.value = value;
        this.descriptor = descriptor;
    }

    public int getWeight()
    {
        return weight;
    }

    public int getValue()
    {
        return value;
    }

    public String getDescriptor()
    {
        return descriptor;
    }
}
