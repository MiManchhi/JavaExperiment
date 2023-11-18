package TEST;

import WorkerManager.Worker;

import java.util.Scanner;

public class testmain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        test t = new test();
        while(true)
        {
            t.Menu();
            System.out.println("请输入操作：");
            int select = scanner.nextInt();
            scanner.nextLine();
            switch(select)
            {
                //增加职工信息
                case 1:
                {
                    t.AddWorker();
                    System.out.println("请按任意键继续...");
                    scanner.nextLine();
                    break;
                }
                //显示职工信息
                case 2:
                {
                    t.ShowWorker();
                    System.out.println("请按任意键继续...");
                    scanner.nextLine();
                    break;
                }
                //删除离职职工
                case 3:
                {
                    t.DeleteWorker();
                    System.out.println("请按任意键继续...");
                    scanner.nextLine();
                    break;
                }
                //修改职工信息
                case 4:
                {
                    t.ModWorker();
                    System.out.println("请按任意键继续...");
                    scanner.nextLine();
                    break;
                }
                //查找职工信息
                case 5:
                {
                    t.SearchWorker();
                    System.out.println("请按任意键继续...");
                    scanner.nextLine();
                    break;
                }
                //按照编号排序
                case 6:
                {
                    t.SortWorker();
                    System.out.println("请按任意键继续...");
                    scanner.nextLine();
                    break;
                }
                //清空所有文档
                case 7:
                {

                    System.out.println("请按任意键继续...");
                    scanner.nextLine();
                    break;
                }
                //退出管理程序
                case 0:
                {
                    System.exit(0);
                }
            }
        }
    }
}
