package Utility;

public class TableViewContent
{
    private final String name;
    private final String number;
    private final String cost;



    public TableViewContent(String name, String number, String cost)
    {
        this.name = name;
        this.number = number;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getCost() {
        return cost;
    }
}
