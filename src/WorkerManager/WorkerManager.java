package WorkerManager;

import WorkerSystem.Function;
import WorkerSystem.IDComparaator2;
import WorkerSystem.IDComparator;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class WorkerManager implements Function {
    public WorkerManager()
    {
        //文件为空
        File file = new File(FilePath);
        if(file.exists() && file.length() == 0)
        {
            this.WorkerIsEmploy = true;
        }
        //文件不为空
        else
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(FilePath))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    // 根据空格分隔行数据
                    String[] tokens = line.split("\\s+");

                    int id = -1;
                    String name = "";
                    int age = -1;
                    String department = "";

                    // 遍历分隔后的数据，提取信息
                    for (String token : tokens) {
                        if (token.startsWith("编号：")) {
                            id = Integer.parseInt(token.substring(3)); // 去掉前缀"编号："
                        } else if (token.startsWith("姓名：")) {
                            name = token.substring(3); // 去掉前缀"姓名："
                        } else if (token.startsWith("年龄：")) {
                            age = Integer.parseInt(token.substring(3)); // 去掉前缀"年龄："
                        } else if (token.startsWith("部门：")) {
                            department = token.substring(3); // 去掉前缀"部门："
                        }
                    }

                    // 创建职员对象并添加到数组
                    Worker worker;
                    if (id != -1 && age != -1) {
                        if ("员工".equals(department)) {
                            worker = new Employee(name, department, age, id);
                        } else if ("经理".equals(department)) {
                            worker = new Manager(name, department, age, id);
                        } else if ("老板".equals(department)) {
                            worker = new Boss(name, department, age, id);
                        } else {
                            // 处理无效的部门信息
                            worker = new Employee(name, "员工", age, id);
                        }
                        // 将职员对象添加到数组
                        WorkerArray.add(worker);
                    }
                }
            } catch (IOException e) {
                System.out.println("读取文件失败：" + e.getMessage());
            }
        }
    }
    public void AddWorker()
    {
        Frame frame = new JFrame("职工信息管理系统");
        ((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int numToAdd = Integer.parseInt(JOptionPane.showInputDialog(frame, "请输入要添加的职工数量:"));
        if (numToAdd <= 0) {
            JOptionPane.showMessageDialog(frame, "输入有误，请输入大于0的数量。");
            return;
        }

        for (int i = 0; i < numToAdd; i++) {
            String name = JOptionPane.showInputDialog(frame, "请输入第" + (i + 1) + "个职工的姓名:");
            String did = JOptionPane.showInputDialog(frame, "请输入第" + (i + 1) + "个职工的部门:");
            int age = Integer.parseInt(JOptionPane.showInputDialog(frame, "请输入第" + (i + 1) + "个职工的年龄:"));
            int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "请输入第" + (i + 1) + "个职工的编号:"));

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
                JOptionPane.showMessageDialog(frame, "部门输入有误，使用默认部门“员工”。");
                Worker worker = new Employee(name, "员工", age, id);
                WorkerArray.add(worker);
            }
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.FilePath)))
        {
            for(int i = 0;i<WorkerArray.size();i++)
            {
                writer.write(WorkerArray.get(i).Showinfo());
                writer.newLine();
            }
            writer.close();
        }
        catch(IOException a)
        {
            JOptionPane.showMessageDialog(frame,"文件写入错误"+a.getMessage());
        }
        JOptionPane.showMessageDialog(frame, "添加成功！");
        this.WorkerIsEmploy = false;
    }
    public void DeleteWorker()
    {
        Frame frame = new JFrame("职工信息管理系统");
        ((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String name = JOptionPane.showInputDialog(frame,"请输入要删除的员工姓名：");
        int temp = this.Find(name);
        if(temp == -1)
        {
            JOptionPane.showMessageDialog(frame,"未找到该员工！");
        }
        else
        {
            WorkerArray.remove(temp);
            //保存
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.FilePath)))
            {
                for(int i = 0;i<WorkerArray.size();i++)
                {
                    writer.write(WorkerArray.get(i).Showinfo());
                    writer.newLine();
                }
                writer.close();
            }
            catch(IOException a)
            {
                JOptionPane.showMessageDialog(frame,"文件写入错误"+a.getMessage());
            }
            JOptionPane.showMessageDialog(frame, "删除成功！");
        }
    }
    public void ModWorker()
    {
        Frame frame = new JFrame("职工信息管理系统");
        ((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String name = JOptionPane.showInputDialog(frame,"请输入要修改的员工姓名");
        int temp = this.Find(name);
        if(temp == -1)
        {
            JOptionPane.showMessageDialog(frame,"员工不存在！");
        }
        else {
            String Name = JOptionPane.showInputDialog(frame,"请输入新的员工姓名");
            int Age = Integer.parseInt(JOptionPane.showInputDialog(frame,"请输入新的员工年龄"));
            int ID = Integer.parseInt(JOptionPane.showInputDialog(frame,"请输入新的员工编号"));
            String DID = JOptionPane.showInputDialog(frame,"请输入新的员工部门");
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
            //保存
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.FilePath)))
            {
                for(int i = 0;i<WorkerArray.size();i++)
                {
                    writer.write(WorkerArray.get(i).Showinfo());
                    writer.newLine();
                }
                writer.close();
            }
            catch(IOException a)
            {
                JOptionPane.showMessageDialog(frame,"文件写入错误"+a.getMessage());
            }
            JOptionPane.showMessageDialog(frame, "修改成功！");
        }
    }
    public void SearchWorker()
    {
        Frame frame = new JFrame("职工信息管理系统");
        ((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int select = 0;
        while(true) {
            select = Integer.parseInt(JOptionPane.showInputDialog(frame, "请选择查找的方式:" + "\n" + "1.按姓名查找" + "\n" + "2.按编号查找"));
            if(select == 1||select == 2)
                break;
            else JOptionPane.showMessageDialog(frame,"输入错误，请重新输入！");
        }
        if(select == 1)
        {
            String Name = JOptionPane.showInputDialog(frame,"请输入要查找的员工姓名");
            if(Find(Name) == -1)
            {
                JOptionPane.showMessageDialog(frame,"员工不存在");
            }
            else
            {
                // 创建新的窗口来显示职工信息
                JFrame infoFrame = new JFrame("找到员工");
                infoFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                // 创建文本区域来显示职工信息
                JTextArea infoTextArea = new JTextArea(10, 40);
                infoTextArea.setEditable(false); // 设置为不可编辑
                JScrollPane infoScrollPane = new JScrollPane(infoTextArea);
                // 添加文本区域到新窗口
                infoFrame.add(infoScrollPane);
                infoFrame.setSize(400, 300); // 设置新窗口大小
                // 在新窗口中显示职工信息
                infoTextArea.append(this.WorkerArray.get(Find(Name)).Showinfo() + "\n");
                // 设置新窗口可见
                infoFrame.setVisible(true);
            }
        }
        else
        {
            int ID = Integer.parseInt(JOptionPane.showInputDialog(frame,"请输入要查找的员工编号"));
            if(Find(ID) == -1)
            {
                JOptionPane.showMessageDialog(frame,"员工不存在");
            }
            else
            {
                // 创建新的窗口来显示职工信息
                JFrame infoFrame = new JFrame("找到员工");
                infoFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                // 创建文本区域来显示职工信息
                JTextArea infoTextArea = new JTextArea(10, 40);
                infoTextArea.setEditable(false); // 设置为不可编辑
                JScrollPane infoScrollPane = new JScrollPane(infoTextArea);
                // 添加文本区域到新窗口
                infoFrame.add(infoScrollPane);
                infoFrame.setSize(400, 300); // 设置新窗口大小
                // 在新窗口中显示职工信息
                infoTextArea.append(this.WorkerArray.get(Find(ID)).Showinfo() + "\n");
                // 设置新窗口可见
                infoFrame.setVisible(true);
            }
        }
    }

    public void SortWorker()
    {
        Frame frame = new JFrame("职工信息管理系统");
        ((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        IDComparator mycompare = new IDComparator();
        IDComparaator2 mycompare2 = new IDComparaator2();
        while(true) {
            int select = Integer.parseInt(JOptionPane.showInputDialog(frame, "请选择排序规则：\n" + "1.降序\n" + "2.升序"));
            if(select == 1)
            {
                Collections.sort(WorkerArray,mycompare2);  //降序
                // 创建新的窗口来显示职工信息
                JFrame infoFrame = new JFrame("排序成功！");
                infoFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                // 创建文本区域来显示职工信息
                JTextArea infoTextArea = new JTextArea(10, 40);
                infoTextArea.setEditable(false); // 设置为不可编辑
                JScrollPane infoScrollPane = new JScrollPane(infoTextArea);
                // 添加文本区域到新窗口
                infoFrame.add(infoScrollPane);
                infoFrame.setSize(400, 300); // 设置新窗口大小
                // 在新窗口中显示职工信息
                for(int i = 0;i<WorkerArray.size();i++) {
                    infoTextArea.append(this.WorkerArray.get(i).Showinfo() + "\n");
                }
                // 设置新窗口可见
                infoFrame.setVisible(true);
                break;
            }
            else if (select == 2) {
                Collections.sort(WorkerArray,mycompare);  //升序
                // 创建新的窗口来显示职工信息
                JFrame infoFrame = new JFrame("排序成功！");
                infoFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                // 创建文本区域来显示职工信息
                JTextArea infoTextArea = new JTextArea(10, 40);
                infoTextArea.setEditable(false); // 设置为不可编辑
                JScrollPane infoScrollPane = new JScrollPane(infoTextArea);
                // 添加文本区域到新窗口
                infoFrame.add(infoScrollPane);
                infoFrame.setSize(400, 300); // 设置新窗口大小
                // 在新窗口中显示职工信息
                for(int i = 0;i<WorkerArray.size();i++) {
                    infoTextArea.append(this.WorkerArray.get(i).Showinfo() + "\n");
                }
                // 设置新窗口可见
                infoFrame.setVisible(true);
                break;
            }
            else if(select != 1&&select != 2){
                JOptionPane.showMessageDialog(frame,"输入错误，请重新输入！");
            }
        }
    }
    public void ClearFile()
    {

    }

    @Override
    public void ShowWorker() {
        // 创建新的窗口来显示职工信息
        JFrame infoFrame = new JFrame("职工信息");
        infoFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // 创建文本区域来显示职工信息
        JTextArea infoTextArea = new JTextArea(10, 40);
        infoTextArea.setEditable(false); // 设置为不可编辑
        JScrollPane infoScrollPane = new JScrollPane(infoTextArea);
        // 添加文本区域到新窗口
        infoFrame.add(infoScrollPane);
        infoFrame.setSize(400, 300); // 设置新窗口大小
        // 在新窗口中显示职工信息
        for (int i = 0; i < WorkerArray.size(); i++) {
            infoTextArea.append(this.WorkerArray.get(i).Showinfo() + "\n");
        }
        // 设置新窗口可见
        infoFrame.setVisible(true);
    }

    public ArrayList<Worker> WorkerArray = new ArrayList<Worker>();

    public boolean WorkerIsEmploy;

    public String FilePath = "D:\\JavaIDEA\\Java-projects\\WorkerSystem\\src\\WorkerSystem\\Worker.txt";

    public int Find(String name)
    {
        int index = -1;
        for(int i = 0;i<WorkerArray.size();i++)
        {
            if(WorkerArray.get(i).Name.equals(name))  //比较字符串不可以使用==，==比较字符串对象的引用，应该使用equals
            {
                index = i;
                break;
            }
        }
        return index;

    }
    public int Find(int ID)
    {
        int index = -1;
        for(int i = 0;i<WorkerArray.size();i++)
        {
            if(WorkerArray.get(i).ID == ID)
            {
                index = i;
                break;
            }
        }
        return index;
    }
}
