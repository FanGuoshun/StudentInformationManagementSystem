public class Menu {
    public static void showMenu()
    {
        System.out.println("学生信息管理系统");
        System.out.println("******************");
        System.out.println("[1]增加");
        System.out.println("[2]删除");
        System.out.println("[3]修改");
        System.out.println("[4]查询");
        System.out.println("[5]浏览");
        System.out.println("[0]退出");
        System.out.println("*****************");
        System.out.print("请选择操作： ");
    }
    public static void subMenu()
    {
        System.out.println("[1]按学号查询");
        System.out.println("[2]按姓名查询");
        System.out.println("[3]按年龄查询");
        System.out.println("[4]退出");
        System.out.print(" 请选择查询编号: ");
    }
}
