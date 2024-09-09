import java.util.*;

/*
* a class that defines the Item type
* @author Owen McGrath
* @version 9/9/2024
*/
public class Item
{
    private Integer weight;
	private Integer value;
    private String descriptor;

   /*
    * a setter method for the Item class.
    * @return -> weight, value, descriptor
    */
    public Item(Integer weight, Integer value, String descriptor)
    {
        this.weight = weight;
        this.value = value;
        this.descriptor = descriptor;
    }

    public Integer getWeight()
    {
		return weight;
	}

	public Integer getValue()
	{
		return value;
	}

	public String getDescriptor()
	{
		return descriptor;
	}
}
