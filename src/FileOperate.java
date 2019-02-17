import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOperate {
    private String fileName="Students.dat";
    private List<Student> list=new ArrayList<Student>();
    private File file;
    public FileOperate()
    {
        file=new File((this.fileName));
        if(!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public  void insert()
    {
        try {
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            System.out.print("请输入学号： ");
            String id = br.readLine();
            System.out.print("请输入姓名： ");
            String name=br.readLine();
            System.out.print("请输入年龄： ");
            String age=br.readLine();
            Student stu=new Student(id,name,Integer.parseInt(age));

            list=getAll();
            list.add(stu);
            writeList();
            System.out.println("新增成功！");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public  void  delete()
    {
        //请输入要删除的学号
        //接受用户输入的数据
        //list=getAll()
        //对集合进行遍历，比较每个学生的学号是否相等
        //相等即从集合删除
        //List是最新的数据，集合写入文件
        try {
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            System.out.print("请输入要删除的学号： ");
            String id = br.readLine();
            list=getAll();
            boolean flag=false;
            for(int i=0;i<list.size();i++)
            {
                if(list.get(i).getStuId().equals(id))
                {
                    flag=true;
                    list.remove(list.get(i));
                    writeList();
                }
            }
            if(flag)
            {
                System.out.println("删除成功！");
            }
            else {
                System.out.println("无此学号！");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public  void update()
    {
        try{
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            System.out.print("请输入学号： ");
            String id = br.readLine();
            System.out.print("请输入姓名： ");
            String name=br.readLine();
            System.out.print("请输入年龄： ");
            String age=br.readLine();
            Student stu=new Student(id,name,Integer.parseInt(age));
            list=getAll();
            boolean flag=false;
            for(int i=0;i<list.size();i++)
            {
                if(list.get(i).getStuId().equals(id))
                {
                    flag=true;
                    list.remove(list.get(i));
                    list.add(stu);
                    writeList();

                }
            }
            if(flag)
            {
                System.out.println("修改成功！");
            }
            else {
                System.out.println("无此学号！");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void search(String sid)
    {
        //sid=1：学号查询 sid=2： 姓名查询 sid=3： 年龄查询
        try {
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            switch (sid) {
                case "1":
                    System.out.print("请输入学号查询： ");
                    String id = br.readLine();
                    searchByIdNameAge(sid,id);
                case "2":
                    System.out.print("请输入姓名查询： ");
                    String name=br.readLine();
                    searchByIdNameAge(sid,name);
                case "3":
                    System.out.print("请输入年龄查询： ");
                    String age=br.readLine();
                    searchByIdNameAge(sid,age);
                case "4":
                    return;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void searchByIdNameAge(String sid,String type) throws Exception
    {
        list=getAll();
        List<Student> tmpList=new ArrayList<Student>();
        for(Student stu:list)
        {
            if(sid.equals("1"))
            {
                if(stu.getStuId().equals(type))
                {
                    tmpList.add(stu);
                }
            }
            else if(sid.equals("2"))
            {
                if(stu.getStuName().equals(type))
                {
                    tmpList.add(stu);
                }
            }
            else if(sid.equals("3"))
            {
                if(stu.getAge()==Integer.parseInt(type))
                {
                    tmpList.add(stu);
                }
            }
        }
        if(tmpList.size()!=0)
        {
            System.out.println("结果: \t学号\t姓名\t年龄");
            for(Student stu:tmpList)
            {
                System.out.println("   \t"+stu.getStuId()+"\t"+stu.getStuName()
                +"\t"+stu.getAge());
            }
        }else
        {
            System.out.println("\t查询无记录！");
        }
        System.out.println();

    }
    public  void showAll()
    {

        try {
            list = getAll();
            if(list.size()!=0)
            {
                System.out.println("结果:  学号 姓名 年龄");
                for(Student stu:list)
                {
                    System.out.println("\t"+stu.getStuId()+"\t"+stu.getStuName()
                            +"\t"+stu.getAge());
                }
            }else
            {
                System.out.println("\t无学生记录！");
            }
            System.out.println();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private List<Student> getAll() throws IOException,ClassNotFoundException
    {

        InputStream fis = new FileInputStream(this.fileName);
        ObjectInputStream ois=null;
        if(fis.available()!=0)
        {
            ois=new ObjectInputStream(fis);
            this.list=(List<Student>)ois.readObject();
            ois.close();
        }
        //项目中遇到的第一个问题
        //如果不加这一个条件判断语句程序会抛出java.io.EOFException异常
        //异常产生的原因是输入流到达了文件的末尾

        fis.close();
        return this.list;
    }
    private void writeList() throws Exception
    {
        OutputStream fos=new FileOutputStream(this.fileName);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(list);
        fos.close();
        oos.close();
    }
}
