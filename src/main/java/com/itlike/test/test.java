package com.itlike.test;

import com.itlike.domain.Teacher;
import com.itlike.service.TeacherService;
import com.itlike.service.impl.TeacherServiceImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class test {
    @Autowired
    private TeacherService teacherService1;
   // private TeacherService teacherService1=new TeacherServiceImpl();
   //private test_1 test_101=new test_10();
    @Test
    public void test0(){
       //TeacherService teacherService1=new TeacherServiceImpl();
       // System.out.println(teacherService1.findFields1("teacher").toString());
    }

    @Test
    public void test1(){
        Properties properties = new Properties();
        try {
          //  properties = PropertiesLoaderUtils.loadAllProperties("code.properties");
            Properties p = new Properties();
            Reader inStream = new InputStreamReader(new FileInputStream("D:\\NewC_Disk\\Desktop\\Java_project_team\\SSMMavenPro_oracle_excel\\src\\main\\resources\\code.properties"), "UTF-8");
            p.load(inStream);
            System.out.println(new String(p.getProperty("123")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test2() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
           Class cz=Class.forName("com.itlike.service.impl.TeacherServiceImpl");
        Method m = cz.getMethod("saveMap", Map.class);
        System.out.println("11111");
        Method m1=cz.getMethod("save", Teacher.class);
        System.out.println("22222");
        TeacherService object=ac.getBean(TeacherService.class);
        System.out.println("333333");

        Map<String,Object> map=new HashMap<>();
        map.put("teacher_id",2);
        map.put("teacher_name","xuechu");
        map.put("course","数学");
        map.put("age",18);

        Teacher teacher=new Teacher();
        teacher.setAge(12);
        teacher.setCourse("shux");
        teacher.setTeacherName("xue");
        teacher.setTeacherId(32);

        System.out.println(object.toString());
        m1.invoke(object,teacher);
      //  m.invoke(object,map);


    }

    @Test
    public void write2Test(){

            try{
                Properties properties=new Properties();
                //用绝对路径
               // InputStream input=new BufferedInputStream(new FileInputStream("D:\\NewC_Disk\\Desktop\\Java_project_team\\SSMMavenPro_oracle_excel\\src\\main\\resources\\excel-db-mapper.properties"));
              //  properties.load(new InputStreamReader(input,"utf-8"));
              //  properties.load(this.getClass().getClassLoader().getResourceAsStream("excel-db-mapper.properties"));
                //多添加几个值。
                //properties.setProperty("name","两个蝴蝶飞");
               // properties.setProperty("sex","男");
                //获取文件的URL
                File file = ResourceUtils.getFile("classpath:excel-db-mapper.properties");
                String content = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
                System.out.println(content);
                FileInputStream input =new FileInputStream(file);
                properties.load(new InputStreamReader(input,"utf-8"));

                System.out.println(properties.getProperty("教师基本信息表"));
                System.out.println(properties.getProperty("书籍基本信息表"));
                System.out.println(properties.getProperty("教师编号"));
                System.out.println(properties.getProperty("教师姓名"));
              //  OutputStream output=new FileOutputStream("D:\\NewC_Disk\\Desktop\\Java_project_team\\SSMMavenPro_oracle_excel\\src\\main\\resources\\excel-db-mapper.properties");
              //  OutputStreamWriter out=new OutputStreamWriter(output,"utf-8");
               // properties.store(out,"填充数据");

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    @Test
    public void test4() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        TeacherService teacherService=ac.getBean(TeacherService.class);
        System.out.println("333333");

        Map<String,Object> columnMap=new HashMap<>();
        columnMap.put("teacher_id",9);
        columnMap.put("teacher_name","xuechu");
        columnMap.put("course","数学");
        columnMap.put("age",18);

        Map<String,Object> map=new HashMap<>();
        map.put("columnMap",columnMap);

        Teacher teacher=new Teacher();
        teacher.setAge(19);
        teacher.setCourse("shux");
        teacher.setTeacherName("xue");
        teacher.setTeacherId(31);

        teacherService.save(teacher);



    }




}





