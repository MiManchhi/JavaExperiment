package WorkerManager;

import WorkerSystem.Function;
import WorkerSystem.IDComparaator2;
import WorkerSystem.IDComparator;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
                    //空格分隔行数据
                    String[] tokens = line.split(" ");

                    int id = Integer.parseInt(tokens[0]);
                    String name = tokens[1];
                    String gender = tokens[2];
                    int age = Integer.parseInt(tokens[3]);
                    String position = tokens[4];
                    String department = tokens[5];
                    Double salary = Double.parseDouble(tokens[6]);
                    // 创建职员对象并添加到数组
                    if ("经理".equals(position)) {
                        Worker worker = new Manager(name, department, age, id,gender,position,salary);
                        WorkerArray.add(worker);
                    } else if ("技术人员".equals(position)) {
                        Worker worker = new TechnicalWorker(name, department, age, id,gender,position,salary);
                        WorkerArray.add(worker);
                    } else if ("销售人员".equals(position)) {
                        Worker worker = new Salesperson(name, department, age, id,gender,position,salary);
                        WorkerArray.add(worker);
                    } else if("销售经理".equals(position)){
                        Worker worker = new SalesManager(name, department, age, id,gender,position,salary);
                        WorkerArray.add(worker);
                    }
                    }
                }catch (IOException e) {
                System.out.println("读取文件失败：" + e.getMessage());
            }
            this.WorkerIsEmploy = false;
        }
    }
    public void AddWorker()
    {
        Frame frame = new JFrame("职工信息管理系统");
        ((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7,2));
        //文本输入框
        JTextField IDField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField salaryField = new JTextField();
        //标签
        JLabel IDLabel = new JLabel("编号:");
        JLabel nameLabel = new JLabel("姓名:");
        JLabel genderLabel = new JLabel("性别:");
        JLabel ageLabel = new JLabel("年龄:");
        JLabel positionLabel =new JLabel("级别:");
        JLabel departmentLabel = new JLabel("部门:");
        JLabel salaryLabel = new JLabel("工资");
        //选择输入框
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"男","女"});
        JComboBox<String> positionComboBox = new JComboBox<>(new String[]{"经理", "技术人员", "销售人员","销售经理"});
        JComboBox<String> departmentComboBox = new JComboBox<>(new String[]{"管理部门", "技术部门", "销售部门"});
        // 添加标签和组件到面板
        panel.add(IDLabel);
        panel.add(IDField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(genderLabel);
        panel.add(genderComboBox);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(positionLabel);
        panel.add(positionComboBox);
        panel.add(departmentLabel);
        panel.add(departmentComboBox);
        panel.add(salaryLabel);
        panel.add(salaryField);
        // 创建添加按钮
        JButton addButton = new JButton("添加");
        // 显示对话框，获取用户输入
        int result = JOptionPane.showConfirmDialog(null, panel, "添加员工", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // 用户点击了确定按钮，获取输入的值
            int id = Integer.parseInt(IDField.getText());
            String name = nameField.getText();
            String gender = (String) genderComboBox.getSelectedItem();
            int age = Integer.parseInt(ageField.getText());
            String position = (String) positionComboBox.getSelectedItem();
            String department = (String) departmentComboBox.getSelectedItem();
            Double salary = Double.parseDouble(salaryField.getText());
            // 在这里可以进行进一步处理，比如调用添加逻辑
            int temp = Find(id);
            if(temp != -1) {
                if ("经理".equals(position)) {
                    Worker worker = new Manager(name, department, age, id, gender, position, salary);
                    WorkerArray.add(worker);
                } else if ("技术人员".equals(position)) {
                    Worker worker = new TechnicalWorker(name, department, age, id, gender, position, salary);
                    WorkerArray.add(worker);
                } else if ("销售人员".equals(position)) {
                    Worker worker = new Salesperson(name, department, age, id, gender, position, salary);
                    WorkerArray.add(worker);
                } else if ("销售经理".equals(position)) {
                    Worker worker = new SalesManager(name, department, age, id, gender, position, salary);
                    WorkerArray.add(worker);
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.FilePath))) {
                    for (int i = 0; i < WorkerArray.size(); i++) {
                        writer.write(WorkerArray.get(i).Writerinfo());
                        writer.newLine();
                    }
                    writer.close();
                } catch (IOException a) {
                    JOptionPane.showMessageDialog(frame, "文件写入错误" + a.getMessage());
                }
                JOptionPane.showMessageDialog(frame, "添加成功！");
                this.WorkerIsEmploy = false;
            }else{
                JOptionPane.showMessageDialog(null,"编号已存在，添加失败！");
            }
        }
    }
    public void DeleteWorker()
    {
        Frame frame = new JFrame("职工信息管理系统");
        ((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if(WorkerArray.size() != 0) {
            String name = JOptionPane.showInputDialog(frame, "请输入要删除的员工姓名：");
            int temp = this.Find(name);
            if (temp == -1) {
                JOptionPane.showMessageDialog(frame, "未找到该员工！");
            } else {
                WorkerArray.remove(temp);
                //保存
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.FilePath))) {
                    for (int i = 0; i < WorkerArray.size(); i++) {
                        writer.write(WorkerArray.get(i).Writerinfo());
                        writer.newLine();
                    }
                    writer.close();
                } catch (IOException a) {
                    JOptionPane.showMessageDialog(frame, "文件写入错误" + a.getMessage());
                }
                JOptionPane.showMessageDialog(frame, "删除成功！");
            }
        }else{
            JOptionPane.showMessageDialog(frame,"文件为空！");
        }
    }
    public void ModWorker()
    {
        Frame frame = new JFrame("职工信息管理系统");
        ((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if(this.WorkerArray.size() != 0) {
            String name = JOptionPane.showInputDialog(frame, "请输入要修改的员工姓名");
            int temp = this.Find(name);
            if (temp == -1) {
                JOptionPane.showMessageDialog(frame, "员工不存在！");
            } else {
                JPanel panel = new JPanel(new GridLayout(7, 2));
                //文本输入框
                JTextField IDField = new JTextField();
                JTextField nameField = new JTextField();
                JTextField ageField = new JTextField();
                JTextField salaryField = new JTextField();
                //标签
                JLabel IDLabel = new JLabel("编号:");
                JLabel nameLabel = new JLabel("姓名:");
                JLabel genderLabel = new JLabel("性别:");
                JLabel ageLabel = new JLabel("年龄:");
                JLabel positionLabel = new JLabel("级别:");
                JLabel departmentLabel = new JLabel("部门:");
                JLabel salaryLabel = new JLabel("工资");
                //选择输入框
                JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"男", "女"});
                JComboBox<String> positionComboBox = new JComboBox<>(new String[]{"经理", "技术人员", "销售人员", "销售经理"});
                JComboBox<String> departmentComboBox = new JComboBox<>(new String[]{"管理部门", "技术部门", "销售部门"});
                // 添加标签和组件到面板
                panel.add(IDLabel);
                panel.add(IDField);
                panel.add(nameLabel);
                panel.add(nameField);
                panel.add(genderLabel);
                panel.add(genderComboBox);
                panel.add(ageLabel);
                panel.add(ageField);
                panel.add(positionLabel);
                panel.add(positionComboBox);
                panel.add(departmentLabel);
                panel.add(departmentComboBox);
                panel.add(salaryLabel);
                panel.add(salaryField);
                // 创建添加按钮
                JButton addButton = new JButton("修改");
                // 显示对话框，获取用户输入
                int result = JOptionPane.showConfirmDialog(null, panel, "修改员工信息", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    // 用户点击了确定按钮，获取输入的值
                    int id = Integer.parseInt(IDField.getText());
                    String name1 = nameField.getText();
                    String gender = (String) genderComboBox.getSelectedItem();
                    int age = Integer.parseInt(ageField.getText());
                    String position = (String) positionComboBox.getSelectedItem();
                    String department = (String) departmentComboBox.getSelectedItem();
                    Double salary = Double.parseDouble(salaryField.getText());

                    if ("经理".equals(position)) {
                        Worker worker = new Manager(name, department, age, id, gender, position, salary);
                        WorkerArray.add(worker);
                    } else if ("技术人员".equals(position)) {
                        Worker worker = new TechnicalWorker(name, department, age, id, gender, position, salary);
                        WorkerArray.add(worker);
                    } else if ("销售人员".equals(position)) {
                        Worker worker = new Salesperson(name, department, age, id, gender, position, salary);
                        WorkerArray.add(worker);
                    } else if ("销售经理".equals(position)) {
                        Worker worker = new SalesManager(name, department, age, id, gender, position, salary);
                        WorkerArray.add(worker);
                    }
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.FilePath))) {
                        for (int i = 0; i < WorkerArray.size(); i++) {
                            writer.write(WorkerArray.get(i).Writerinfo());
                            writer.newLine();
                        }
                        writer.close();
                    } catch (IOException a) {
                        JOptionPane.showMessageDialog(frame, "文件写入错误" + a.getMessage());
                    }
                    JOptionPane.showMessageDialog(frame, "修改成功！");
                    this.WorkerIsEmploy = false;
                    JOptionPane.showMessageDialog(frame, "修改成功！");
                }
            }
        }else{
            JOptionPane.showMessageDialog(frame,"文件为空！");
        }
    }
    public void SearchWorker()
    {
        Frame frame = new JFrame("职工信息管理系统");
        ((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if(this.WorkerArray.size() != 0) {
            int select = 0;
            while (true) {
                select = Integer.parseInt(JOptionPane.showInputDialog(frame, "请选择查找的方式:" + "\n" + "1.按姓名查找" + "\n" + "2.按编号查找"));
                if (select == 1 || select == 2)
                    break;
                else JOptionPane.showMessageDialog(frame, "输入错误，请重新输入！");
            }
            if (select == 1) {
                String Name = JOptionPane.showInputDialog(frame, "请输入要查找的员工姓名");
                if (Find(Name) == -1) {
                    JOptionPane.showMessageDialog(frame, "员工不存在");
                } else {
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
                    List<Integer> temp = AllFind(Name);
                    for(int i = 0;i<temp.size();i++) {
                        infoTextArea.append(this.WorkerArray.get(temp.get(i)).Showinfo() + "\n");
                    }
                    // 设置新窗口可见
                    infoFrame.setVisible(true);
                }
            } else {
                int ID = Integer.parseInt(JOptionPane.showInputDialog(frame, "请输入要查找的员工编号"));
                if (Find(ID) == -1) {
                    JOptionPane.showMessageDialog(frame, "员工不存在");
                } else {
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
        }else {
            JOptionPane.showMessageDialog(frame,"文件为空！");
        }
    }

    public void SortWorker()
    {
        Frame frame = new JFrame("职工信息管理系统");
        ((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if(this.WorkerArray.size() != 0) {
            IDComparator mycompare = new IDComparator();
            IDComparaator2 mycompare2 = new IDComparaator2();
            while (true) {
                int select = Integer.parseInt(JOptionPane.showInputDialog(frame, "请选择排序规则：\n" + "1.降序\n" + "2.升序"));
                if (select == 1) {
                    Collections.sort(WorkerArray, mycompare2);  //降序
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
                    for (int i = 0; i < WorkerArray.size(); i++) {
                        infoTextArea.append(this.WorkerArray.get(i).Showinfo() + "\n");
                    }
                    // 设置新窗口可见
                    infoFrame.setVisible(true);
                    break;
                } else if (select == 2) {
                    Collections.sort(WorkerArray, mycompare);  //升序
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
                    for (int i = 0; i < WorkerArray.size(); i++) {
                        infoTextArea.append(this.WorkerArray.get(i).Showinfo() + "\n");
                    }
                    // 设置新窗口可见
                    infoFrame.setVisible(true);
                    break;
                } else if (select != 1 && select != 2) {
                    JOptionPane.showMessageDialog(frame, "输入错误，请重新输入！");
                }
            }
        }else{
            JOptionPane.showMessageDialog(frame,"文件为空！");
        }
    }
    public void ClearFile(String Password)
    {
        Frame frame = new JFrame("职工信息管理系统");
        ((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String password = JOptionPane.showInputDialog(frame,"请输入密码");
        if(password.equals(Password))
        {
            try (FileWriter fileWriter = new FileWriter(FilePath,false))
            {
                WorkerArray.clear();
                JOptionPane.showMessageDialog(frame,"文件清空成功！");
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(frame,"密码错误！");
        }
    }

    @Override
    public void ShowWorker() {
        if(this.WorkerArray.size() != 0) {
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
            infoFrame.setLocationRelativeTo(null);
            // 在新窗口中显示职工信息
            for (int i = 0; i < WorkerArray.size(); i++) {
                infoTextArea.append(this.WorkerArray.get(i).Showinfo() + "\n");
            }
            infoTextArea.append("\n");
            infoTextArea.append("经理人数："+Manager.count+" "+"技术人员人数："+TechnicalWorker.count+" "+"销售人员人数："+Salesperson.count
            +" "+"销售经理人数："+SalesManager.count+" "+"共计："+this.WorkerArray.size());
            // 设置新窗口可见
            infoFrame.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null,"文件为空！");
        }
    }

    public ArrayList<Worker> WorkerArray = new ArrayList<Worker>();

    public boolean WorkerIsEmploy;

    public String FilePath = "D:\\JavaIDEA\\Java-projects\\WorkerSystem\\src\\WorkerSystem\\Worker.txt";

    public List<Integer> AllFind(String name)
    {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0;i<WorkerArray.size();i++)
        {
            if(WorkerArray.get(i).Name.equals(name))
            {
                indices.add(i);
            }
        }
        return indices;
    }

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
