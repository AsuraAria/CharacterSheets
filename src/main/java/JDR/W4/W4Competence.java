package JDR.W4;

public class W4Competence
{

    public String name;
    public String type;
    public String desc;
    public Boolean basic;

    //CONSTRUCTOR
    public W4Competence()
    {
        this.name = null;
        this.type = null;
        this.desc = null;
        this.basic = false;
    }
    public W4Competence(String name, String type, String desc, Boolean basic)
    {
        this.name = name;
        this.type = type;
        this.desc = desc;
        this.basic = basic;
    }

    //SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void setBasic(Boolean basic) {
        this.basic = basic;
    }
    //GETTERS
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getDesc() {
        return desc;
    }
    public Boolean getBasic() {
        return basic;
    }

    //FUNCTION

    public Boolean isEmpty ()
    {
        if (this.getName()==null && this.getType()==null && this.getDesc()==null && this.basic==false)
        {
            return true;
        }
        else return false;
    }
}
