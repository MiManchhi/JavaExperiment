package TEST;

import WorkerManager.Boss;
import WorkerManager.Employee;
import WorkerManager.Manager;
import WorkerManager.Worker;
import WorkerSystem.Function;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Scanner;

public class test implements Function {
    public test()
    {

    }
    public void Menu()
    {
        System.out.println("1.增加职工信息");
        System.out.println("2.显示职工信息");
        System.out.println("3.删除离职职工");
        System.out.println("4.修改职工信息");
        System.out.println("5.查找职工信息");
        System.out.println("6.按照编号排序");
        //System.out.println("7.清空所有文档");
        System.out.println("0.退出管理程序");
    }
    public void AddWorker()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要添加的数量");
        int numToAdd = scanner.nextInt();
        scanner.nextLine();
        if (numToAdd <= 0) {
            System.out.println("输入有误，请输入大于0的数量。");
            return;
        }

        for (int i = 0; i < numToAdd; i++) {
            System.out.println("请输入第" + (i + 1) + "个职工的姓名:");
            String name = scanner.nextLine();
            System.out.println("请输入第" + (i + 1) + "个职工的部门:");
            String did = scanner.nextLine();
            System.out.println("请输入第" + (i + 1) + "个职工的年龄:");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.println("请输入第" + (i + 1) + "个职工的编号:");
            int id = scanner.nextInt();
            scanner.nextLine();

            if ("员工".equals(did)) {
                Worker worker = new Employee(name, did, age, id);
                WorkerArray.add(worker);
            } else if ("经理".equals(did)) {
                Worker worker = new Manager(name, did, age, id);
                WorkerArray.add(worker);
            } else if ("老板".equals(did)) {
                Worker worker = new Boss(name, did, age, id);
                WorkerArray.add(worker);
            } else {
                System.out.println("部门输入有误，使用默认部门“员工”。");
                Worker worker = new Employee(name, "员工", age, id);
                WorkerArray.add(worker);
            }
        }
        System.out.println("添加成功！");
    }
    public void DeleteWorker()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要删除的员工姓名：");
        String name = scanner.nextLine();
        int temp = this.Find(name);
        if(temp == -1)
        {
            System.out.println("未找到该员工！");
        }
        else
        {
            WorkerArray.remove(temp);
            System.out.println("删除成功！");
        }
    }
    public void ModWorker()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要修改的员工姓名");
        String name = scanner.nextLine();
        int temp = this.Find(name);
        if(temp == -1)
        {
            System.out.println("员工不存在！");
        }
        else {
            System.out.println("请输入新的员工姓名");
            String Name = scanner.nextLine();
            System.out.println("请输入新的员工年龄");
            int Age = scanner.nextInt();
            scanner.nextLine();
            System.out.println("请输入新的员工编号");
            int ID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("请输入新的员工部门");
            String DID = scanner.nextLine();
            if("员工".equals(DID))
            {
                Worker worker = new Employee(Name,DID,Age,ID);
                WorkerArray.set(temp,worker);
            }
            if("经理".equals(DID))
            {
                Worker worker = new Manager(Name,DID,Age,ID);
                WorkerArray.set(temp,worker);
            }
            if("老板".equals(DID))
            {
                Worker worker = new Boss(Name,DID,Age,ID);
                WorkerArray.set(temp,worker);
            }
            System.out.println("修改成功！");
        }
    }

    public void SearchWorker()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查找的员工姓名：");
        String name = scanner.nextLine();
        int temp = Find(name);
        if(temp == -1)
        {
            System.out.println("员工不存在！");
        }
        else {
            System.out.println("找到该员工：");
            System.out.println(WorkerArray.get(temp).Showinfo());
        }
    }
    public void ShowWorker()
    {
        for(int i = 0;i<WorkerArray.size();i++)
        {
            System.out.println(WorkerArray.get(i).Showinfo());
        }
    }

    @Override
    public void SortWorker()
    {
        for(int i = 0;i<WorkerArray.size()-1;i++)
        {
            for(int j = 0;j<WorkerArray.size()-1-i;j++)
            {
                if(WorkerArray.get(j).GetID() > WorkerArray.get(j+1).GetID())
                {
                    Worker temp = WorkerArray.get(j);
                    WorkerArray.set(j,WorkerArray.get(j+1));
                    WorkerArray.set(j+1,temp);
                }
            }
        }
        System.out.println("排序成功！");
        for(Worker val : WorkerArray)
        {
            System.out.println(val);
        }
    }

    public int Find(String name)
    {
        int index = -1;
        for(int i = 0;i<WorkerArray.size();i++)
        {
            if(WorkerArray.get(i).Name.equals(name))
            {
                index = i;
                break;
            }
        }
        return index;
    }
    public void ClearFile(String Password)
    {

    }
    public ArrayList<Worker> WorkerArray = new ArrayList<Worker>();
}