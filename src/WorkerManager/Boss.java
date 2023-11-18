package WorkerManager;

public class Boss extends Worker{
    public Boss(String name,String did,int age,int id)
    {
        this.Name = name;
        this.DeptID = did;
        this.Age = age;
        this.ID = id;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "Name='" + Name + '\'' +
                ", DeptID='" + DeptID + '\'' +
                ", Age=" + Age +
                ", ID=" + ID +
                '}';
    }

    @Override
    public String Showinfo() {
        return("编号："+this.ID+"\t"+"姓名："+this.Name+"\t"+"年龄："+this.Age+"\t"+"部门："+this.DeptID);
    }

    @Override
    public int GetID() {
        return this.ID;
    }
}
