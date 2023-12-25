package WorkerManager;

public abstract class Worker {
    public Worker(String name,String did,int age,int id,String gender,String position,Double salary)
    {
        this.Name = name;
        this.DeptID = did;
        this.Age = age;
        this.ID = id;
        this.Gender = gender;
        this.Position = position;
        this.Salary = salary;
    }
    public Worker()
    {

    }
    abstract public String Showinfo();
    abstract public int GetID();
    abstract public String Writerinfo();
    //姓名
    public String Name;
    //性别
    protected String Gender;
    //部门
    protected String DeptID;
    //年龄
    protected int Age;
    //编号
    protected int ID;
    //级别
    protected String Position;
    //工资
    protected Double Salary;
}
