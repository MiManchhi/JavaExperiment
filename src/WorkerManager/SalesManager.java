package WorkerManager;

public class SalesManager extends Worker{
    public SalesManager(String name,String did,int age,int id,String gender,String position,Double salary)
    {
        this.Name = name;
        this.DeptID = did;
        this.Age = age;
        this.ID = id;
        this.Gender = gender;
        this.Position = position;
        this.Salary = salary;
        count++;
    }
    @Override
    public String Showinfo()
    {
        return("编号："+this.ID+"\t"+"姓名："+this.Name+"\t"+"性别："+this.Gender+"\t"+"年龄："+this.Age+"\t"+"级别："+this.Position+"\t"+"部门："+this.DeptID+"\t"+"工资"+this.Salary);
    }
    @Override
    public int GetID()
    {
        return this.ID;
    }

    @Override
    public String Writerinfo() {
        return (this.ID+" "+this.Name+" "+this.Gender+" "+this.Age+" "+this.Position+" "+this.DeptID+" "+this.Salary);
    }
    public static int count = 0;
}
