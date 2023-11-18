package WorkerManager;

public abstract class Worker {
    public Worker(String name,String did,int age,int id)
    {
        this.Name = name;
        this.DeptID = did;
        this.Age = age;
        this.ID = id;
    }
    public Worker()
    {

    }
    abstract public String Showinfo();
    abstract public int GetID();
    public String Name;
    protected String DeptID;
    protected int Age;
    protected int ID;
}
