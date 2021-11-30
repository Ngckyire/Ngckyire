#### 越野机器人实验室web组第二次考核

考核介绍：

使用`Java`语言完成一个实验室成员管理系统。

考核目的：

在完成项目的过程中体验Java面向对象的思想，熟练使用Java的基础语法，加深对ArrayList,Map等常用集合类的理解，深入体会IO流的用法

考核要求：

1. 完成成员类的构建，一个实验室成员的基本属性有学号，姓名，学院，专业，性别，历次考核成绩，需要进行封装
2. 完成管理系统类的构建，管理系统应有以下功能：
   + 新成员的添加
   + 全体成员信息的遍历预览
   + 根据学号进行成员的查询，随后可有下列操作：1. 进行成员信息的预览；2.进行成员信息的修改（得选择具体属性）3. 成员信息的删除
   + 根据成员的历次考核的平均成绩进行排序，并展示全体成员信息
   + 成员信息可以通过一个txt文件进行读取与存储
3. 可有创新项，贴近实验室的实际需求有创新的进行加分；进度快使用数据库，h5等技术的加分；有计算机行业其他特长的可以展现，酌情加分

分数说明：

考核1占20%，考核2占80%，创新项算额外分

考核说明：

本次考核如独立（可搜索资料）完美完成，你们将拥有不逊色大多数大二学生的基础编程能力，

在本次考核后，将进行h5，css，js技术的学习，并且会进行小组分组，学期末还会有一次小组成果展示。

web组不对大家的发展路线进行很死板的框定，如果对视觉，控制，算法，机器学习等方面感兴趣的，主动反映，近年来电赛等比赛对于视觉的考核越来越多，也会有机器学习的知识，如果感兴趣进行一定程度的训练是有很大机会拿到国家级奖项的。大家在实验室关键是要取得进步，得到成果，学到技术，希望大家牢记初心，冲冲冲！







```java
package LaboratoryManager;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.Comparator;
import static kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames.list;

public class LaboratoryManager {
    public static void main(String[] args) throws IOException {
        List<Members> array = new ArrayList<Members>();
        while (true) {
            System.out.println("-------欢迎来到实验室成员管理系统-------");
            System.out.println("1 添加成员信息");
            System.out.println("2 删除成员信息");
            System.out.println("3 修改成员信息");
            System.out.println("4 查看所有成员信息");
            System.out.println("5 退出");
            System.out.println("6 根据成员的历次考核的平均成绩进行排序，并展示全体成员信息");
            System.out.println("7 使用txt存储");
            System.out.println("8 使用txt读取");
            System.out.println("9 打开txt文档");
            System.out.println("请输入数字以使用系统:");
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            switch (line) {
                case ("1"):{
                    //System.out.println("添加成员信息");
                    addLaboratoryMembers(array);
                    break;
                }
                case ("2"):{
                    //System.out.println("删除成员信息");
                    deleteLaboratoryMembers(array);
                    break;
                }
                case ("3"):{
                    //System.out.println("修改成员信息");
                    upDateLaboratoryMembers(array);
                    break;
                }
                case("4"):{
                    //System.out.println("查看所有成员信息");
                    lookAllLaboratoryMembers(array);
                    break;
                }
                case ("5"):{
                    System.out.println("退出成功");
                    System.exit(0);
                }
                case ("6"):{
                    // System.out.println("根据成员的历次考核的平均成绩进行排序，并展示全体成员信息");
//                  Collections.sort(array);
//                    System.out.println("排序后");
//                    for(Members m : array){
//                        System.out.println(m.getSid()+" "+m.getSex()+" "+m.getName()+" "+m.getCollege()+" "+m.getMajor()+" "+m.getScore());
//                    }

                    sortLaboratoryMembers(array);
                    break;
                }
                case ("7"):{
                    txt(array);
                    break;
                }
                case("8"):{
                    // System.out.println(readFileContent("实验室成员管理系统.txt"));
                    // readFileContent("实验室成员管理系统.txt");
                    //System.out.println(readTxtFile("实验室成员管理系统.txt"));
                    readTxt("实验室成员管理系统.txt",array);
                    break;
                }
                case ("9"):{
                    openTxt();
                    break;
                }
            }
        }
    }
    public static void addLaboratoryMembers(List<Members>arr){

        Scanner sc = new Scanner(System.in);
        Members m = new Members();
        String sid;
        while (true){
            System.out.println("请输入成员学号");
            sid = sc.nextLine();
            boolean flag = isUsed(arr,sid);
            if (flag == false){
                System.out.println("该学号已经存在！");
            }
            else {
                break;
            }
        }
        System.out.println("请输入成员姓名");
        String name = sc.nextLine();
        System.out.println("请输入成员性别");
        String sex = sc.nextLine();
        System.out.println("请输入成员年龄");
        String age = sc.nextLine();
        //System.out.println("请输入学生学号");
        //String sid = sc.nextLine();
        System.out.println("请输入成员学院");
        String college = sc.nextLine();
        System.out.println("请输入成员专业");
        String major = sc.nextLine();
        List<Integer>array = new ArrayList<>();
//        System.out.println("请输入分数:");
//        String score1 = sc.nextLine();
        System.out.println("您想输入___次考核成绩？");
        int n = sc.nextInt();
        for (int i=0;i<n;i++){
            System.out.println("请输入第"+(i+1)+"次考核成绩");
            int score = sc.nextInt();
            array.add(score);
        }
        m.setAverage(Members.average(array));
        m.setName(name);
        m.setSex(sex);
        m.setAge(age);
        m.setSid(sid);
        m.setCollege(college);
        m.setMajor(major);
        m.setScore(array);
//        m.setScore1(score1);
        m.setScore(array);
        arr.add(m);
    }
    public static void deleteLaboratoryMembers(List<Members>arr){
        System.out.println("请输入你想删除的成员的学号：");
        Scanner sc = new Scanner(System.in);
        String sid = sc.nextLine();
        int index = -1;
        for(int i=0;i<arr.size();i++) {
            Members m = arr.get(i);
            if (sid.equals(m.getSid())) {
                arr.remove(m);
                System.out.println("删除成功！");
                index = 1;
                break;
            }
        }
        if(index == -1) {
            System.out.println("该成员信息不存在，删除失败");
        }
    }
    public static void lookAllLaboratoryMembers(List<Members>arr){
        for(int i=0;i<arr.size();i++){
            Members m = new Members();
            m = arr.get(i);
            System.out.println(m.getSid()+" "+m.getName()+" "+m.getSex()+" "+m.getAge()+m.getCollege()+" "+m.getMajor()+" "+m.getScore()+" ");
        }
        if(arr.size()==0){
            System.out.println("无学生信息，请添加学生信息！");
        }
    }
    public static void upDateLaboratoryMembers(List<Members>arr) {
        Scanner sc = new Scanner(System.in);
        String sid;
        while (true) {
            System.out.println("请输入你想修改信息的成员的学号");
            sid = sc.nextLine();
            boolean flag = isUsed(arr, sid);
            if (flag == false) {
                System.out.println("成员信息存在，请继续操作");
                break;
            } else {
                System.out.println("该成员信息不存在! ");
            }
        }
        System.out.println("sid 修改学生的学号");
        System.out.println("name 修改学生姓名");
        System.out.println("sex 修改学生性别");
        System.out.println("age 修改学生年龄");
        System.out.println("college 修改学生学院");
        System.out.println("major 修改学生专业");
        System.out.println("score 修改学生分数");
        System.out.println("exit 退出修改");
        String index = sc.nextLine();
        switch(index) {
            case ("sid"): {
                System.out.println("请输入新的学号");
                String newSid = sc.nextLine();
                int temp = 1;
                for (int i = 0; i < arr.size(); i++) {
                    Members m = arr.get(i);
                    if (m.getSid().equals(newSid)) {
                        System.out.println("该学号已经存在，请重新输入！");
                        temp = 0;
                        break;
                    } else {
                        temp = 1;
                    }
                }
                if (temp == 1) {
                    for (int i = 0; i < arr.size(); i++) {
                        Members m = arr.get(i);
                        if (m.getSid().equals(sid)) {
                            m.setSid(newSid);
                        }
                    }
                }
                break;
            }
            case ("name"): {
                System.out.println("请输入新的姓名");
                String newName = sc.nextLine();
                for (int i = 0; i < arr.size(); i++) {
                    Members m = arr.get(i);
                    if (m.getSid().equals(sid)) {
                        m.setName(newName);
                    }
                }
                break;
            }
            case ("sex"): {
                System.out.println("请输入新的性别");
                String newSex = sc.nextLine();
                for (int i = 0; i < arr.size(); i++) {
                    Members m = arr.get(i);
                    if (m.getSid().equals(sid)) {
                        m.setSex(newSex);
                    }
                }
                break;
            }
            case ("age"): {
                System.out.println("请输入新的年龄");
                String newAge = sc.nextLine();
                for (int i = 0; i < arr.size(); i++) {
                    Members m = m = arr.get(i);
                    if (m.getSid().equals(sid)) {
                        m.setAge(newAge);
                    }
                }
                break;
            }
            case ("college"): {
                System.out.println("请输入新的学院");
                String newCollege = sc.nextLine();
                for (int i = 0; i < arr.size(); i++) {
                    Members m = arr.get(i);
                    if (m.getSid().equals(sid)) {
                        m.setCollege(newCollege);
                    }
                }
                break;
            }
            case ("major"): {
                System.out.println("请输入新的专业");
                String newMajor = sc.nextLine();
                for (int i = 0; i < arr.size(); i++) {
                    Members m = arr.get(i);
                    if (m.getSid().equals(sid)) {
                        m.setCollege(newMajor);
                    }
                }
                break;
            }
            case ("score"): {
                System.out.println("请输入修改后的成员历次考核成绩");
                List<Integer> newScore = new ArrayList<>();
                for (int i = 0; i < arr.size(); i++) {
                    Members m = arr.get(i);
                    if (m.getSid().equals(sid)) {
                        for (int j = 0; j < m.getScore().size(); j++) {
                            int newScores = sc.nextInt();
                            newScore.add(newScores);
                        }
                    }
                }
//                System.out.println("请输入新成绩:");
//                String newScore1 = new String();
//                for (int i = 0; i < arr.size(); i++) {
//                    Members m = arr.get(i);
//                    if (m.getSid().equals(sid)) {
//                        m.setSex(newScore1);
//                    }
//                }
                break;
            }
        }
    }
    public static void sortLaboratoryMembers(List<Members>arr){

//        for(int i=0;i<arr.size();i++) {
//            Members m = new Members();
//            m.setAverage(Members.average(m.getScore()));
//        }
        Collections.sort(arr);
        System.out.println("排序后");
        System.out.println(arr.toString());
        if(arr.size()==0){
            System.out.println("暂无成员信息！");
        }
    }
    public static void openTxt(){
        Process p = null;//Process代表一个进程对象
        try {
            p = Runtime.getRuntime().exec("notepad.exe 实验室成员管理系统.txt");
            //参数为操作系统的一个进程的命令，而不是传一个文件
            //启动记事本程序打开java文件
            Thread.sleep(5000);//眠5秒后销毁该进程。
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            p.destroy();//销毁该进程
        }
    }
    public static void txt(List<Members>arr){
        try {
            FileWriter writer = new FileWriter("实验室成员管理系统.txt",true);
            FileReader reader = new FileReader("实验室成员管理系统.txt");
            BufferedWriter bw = new BufferedWriter(writer);
            BufferedReader br = new BufferedReader(reader);
            // bw.write("学号 姓名 性别 年龄 学院 专业    考核成绩   平均成绩\n\n");
            for(int i=0;i<arr.size();i++){
                String s =arr.get(i).toString();
                /*if(s==br.readLine()){
                    continue;
                }*/
                bw.write(s);
                // bw.newLine();  //换行
                //   bw.flush();    //缓冲
            }
            System.out.println("储存成功" );
            bw.close();
        }catch(Exception e){
            System.err.format("IOException: %s%n",e);
        }
        //openTxt();
    }
    private static boolean isUsed(List<Members> arr, String sid) {
        boolean flag = true;
        for(int i=0;i<arr.size();i++){
            Members m = arr.get(i);
            if(sid.equals(m.getSid())){
                flag = false;
            }
        }
        return flag;
    }
    public static void readTxt(String fileName,List<Members>arr) throws IOException {
        /*
        //创建字符缓冲流对象
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        //创建ArrayList集合对象
        ArrayList<String> array = new ArrayList<String>();
        String line;
        while((line = br.readLine()) != null){
            //把读取到的字符串存储到集合中
            array.add(line);
        }
        br.close();
        for(String s : array){
            System.out.println(s);
        }

         */

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = null;
        try {
            while ((line = br.readLine()) != null ) {
                if(line.equals((""))){
                    continue;
                }
                //分割字符串
                String[] strArray = line.split("\\s");
                Members m = new Members();
                m.setSid(strArray[0]);
                m.setName(strArray[1]);
                m.setSex(strArray[2]);
                m.setAge(strArray[3]);
                m.setCollege(strArray[4]);
                m.setMajor(strArray[5]);
                String s = new String();
                List<Integer>array = new ArrayList<>();
                for(int j=0;j<strArray[6].length();j++){
                    if(strArray[6].charAt(j)!='[' && strArray[6].charAt(j)!=']'){
                        s += strArray[6].charAt(j);
                    }
                    if(strArray[6].charAt(j)==']'){
                        break;
                    }
                }
                String[] fg = s.split(",") ;
                for(int k=0;k<fg.length;k++){
                    array.add(Integer.parseInt(fg[k]));
                }
                m.setScore(array);
//                m.setScore1(strArray[6]);
//                int i = Integer.parseInt(strArray[7]);
//                m.setAverage(i);
                arr.add(m);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        br.close();

    }
}

```

```java
package LaboratoryManager;

import java.util.List;

public class Members implements Comparable<Members> {
    private String name;
    private String sex;
    private String age;
    private String sid;
    private String college;
    private String major;
    private List<Integer> score;
    private String score1;
    private int average ;
    public Members() {
    }

    public Members(String name, String sex, String age, String sid, String college, String major, List<Integer> score) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.sid = sid;
        this.college = college;
        this.major = major;
        this.score = score;
    }

    public String getScore1() {
        return score1;
    }

    public void setScore1(String score1) {
        this.score1 = score1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<Integer> getScore() {
        return score;
    }

    public void setScore(List<Integer> score) {
        this.score = score;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }


//    public static String information(){};

    public static int average(List<Integer>score){
        int num=0;
        for(int i=0;i<score.size();i++){
            num += score.get(i);
        }
        int average = num/score.size();
        return average;
    }
    public static int average(String score1){
        return  Integer.parseInt(score1);
    }
    @Override
    public int compareTo(Members m){
        if( m.average - this.average!=0){
            return m.average - this.average;
        }
        else return 1;
//        return 0; //元素重复不添加
//        return 1;  //升序存储
//        return -1;  //降序存储

    }
    @Override
    public String toString(){
        /*return '\n'+sid+" "+name+" "+sex+" "+age+" "+college+" "+major+" "+
                score
                +'\n';*/
        String s1='\n'+sid+" "+name+" "+sex+" "+age+" "+college+" "+major+" ";
        String s2="[";
        for(int s:score){
            s2+=s+",";
        }
        s2+="]"+'\n';
        String ss = new String();
        for(int i=0;i<s2.length();i++){
            if(i==s2.length()-3){
                continue;
            }
            ss+=s2.charAt(i);
        }
        return s1+ss;
    }
    /*public int compare(Members m1,Members m2){
        int i= m1.getAverage()-m2.getAverage();
        if(i==0){
            return 1;
        }
        return i;
    }

     */
   /* public void getAverage(){
        this.average = average;
    }

    */
}
```

