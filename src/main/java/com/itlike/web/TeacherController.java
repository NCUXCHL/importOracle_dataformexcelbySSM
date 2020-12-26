package com.itlike.web;




import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.itlike.domain.AjaxRes;
import com.itlike.domain.Teacher;
import com.itlike.service.TeacherService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Controller
public class TeacherController {
    /*注入服务层*/
    @Autowired
    private TeacherService teacherService;



    @RequestMapping("/teacher")
    public String teacher(String name) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        System.out.println(name);
        //调用服务层
       // teacherService.save(name);
        Map<String,Object> columnMap=new HashMap<>();
        columnMap.put("teacher_id",10);
        columnMap.put("teacher_name","xuechu");
        columnMap.put("course","数学");
        columnMap.put("age",18);


        Map<String,Object> map=new HashMap<>();
        map.put("columnMap",columnMap);
        map.put("table_name","TEACHER");

        teacherService.saveMap(map);
       /* Teacher teacher=new Teacher();
        teacher.setAge(12);
        teacher.setCourse("shux");
        teacher.setTeacherName("xue");
        teacher.setTeacherId(32);
        teacherService.save(teacher);*/
        return "success";
    }


    /*下载模板*/
    @RequestMapping("downloadExcelTpl")
    @ResponseBody
    public void downloadExcelTpl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FileInputStream is = null;


        try {


            System.out.println(123);
            String fileName = new String("EmployeeTpl.xls".getBytes("utf-8"), "iso8859-1");
            response.setHeader("content-Disposition","attachment;filename="+fileName);
            /*获取文件路径*/
            String realPath = request.getSession().getServletContext().getRealPath("static/EmployeeTpl.xls");
            System.out.println(realPath);
            is = new FileInputStream(realPath);
            IOUtils.copy(is,response.getOutputStream());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


  /*  *//*配置文件上传解析器  mvc配置当中*//*
    @RequestMapping("/uploadExcelFile")
    @ResponseBody
    public AjaxRes uploadExcelFile(MultipartFile excel) throws IOException {
        System.out.println(excel.getOriginalFilename());
        AjaxRes ajaxRes = new AjaxRes();
        Teacher teacher=new Teacher();
        int default_insert=0;
        try {
            ajaxRes.setMsg("--");
            ajaxRes.setSuccess(true);
            try{
                System.out.println("xls格式");
                HSSFWorkbook wb = new HSSFWorkbook(excel.getInputStream());
                HSSFSheet sheet = wb.getSheetAt(0);
                *//*获取最大的行号*//*
                int lastRowNum = sheet.getLastRowNum();
                System.out.println(getCellValue(sheet.getRow(0).getCell(0)));
                System.out.println(getCellValue(sheet.getRow(1).getCell(0)));
                System.out.println(lastRowNum);
                Row teacherRow = null;

                for (int i = 2; i <= lastRowNum; i++) {
                    teacherRow = sheet.getRow(i);
                    //Teacher teacher = new Teacher();
                    try{
                        System.out.println(getCellValue(teacherRow.getCell(0)));
                        teacher.setTeacherId((Integer)getCellValue(teacherRow.getCell(0)));
                        System.out.println(getCellValue(teacherRow.getCell(1)));
                        teacher.setTeacherName((String)getCellValue(teacherRow.getCell(1)));
                        System.out.println(getCellValue(teacherRow.getCell(2)));
                        teacher.setCourse((String)getCellValue(teacherRow.getCell(2)));
                        System.out.println(getCellValue(teacherRow.getCell(3)));
                        teacher.setAge((Integer)getCellValue(teacherRow.getCell(3)));
                        teacherService.save(teacher);
                        ajaxRes.setMsg(ajaxRes.getMsg()+"\n第"+(i-1)+"行导入成功");

                    }catch (NullPointerException e2){//判断teacherRow.getCell返回是否为空指针，如果是这有空单元格
                        System.out.println("xls,空指针，该行有空单元格");

                        ajaxRes.setMsg(ajaxRes.getMsg()+"\n第"+(i-1)+"行导入失败,有空单元格");
                        default_insert++;
                        //ajaxRes.setSuccess(false);
                    }catch (DuplicateKeyException e1){//复制键，违反唯一约束条件
                        default_insert++;
                        ajaxRes.setMsg(ajaxRes.getMsg()+"\n第"+(i-1)+"行导入失败,ID与之前的冲突");
                        // ajaxRes.setSuccess(false);
                    }

                }
                }catch(OfficeXmlFileException e){
                    System.out.println("xlsx格式");
                    *//*xlsx格式*//*
                    XSSFWorkbook wb1 = new XSSFWorkbook(excel.getInputStream());
                    XSSFSheet sheet = wb1.getSheetAt(0);
                    *//*获取最大的行号*//*
                    int lastRowNum = sheet.getLastRowNum();

                    System.out.println(lastRowNum);
                    Row teacherRow = null;
                    for (int i = 2; i <= lastRowNum; i++) {
                        teacherRow = sheet.getRow(i);
                        //Teacher teacher = new Teacher();
                       try{
                           System.out.println(getCellValue(teacherRow.getCell(0)));
                           teacher.setTeacherId((Integer)getCellValue(teacherRow.getCell(0)));
                           System.out.println(getCellValue(teacherRow.getCell(1)));
                           teacher.setTeacherName((String)getCellValue(teacherRow.getCell(1)));
                           System.out.println(getCellValue(teacherRow.getCell(2)));
                           teacher.setCourse((String)getCellValue(teacherRow.getCell(2)));
                           System.out.println(getCellValue(teacherRow.getCell(3)));
                           teacher.setAge((Integer)getCellValue(teacherRow.getCell(3)));
                           teacherService.save(teacher);
                           ajaxRes.setMsg(ajaxRes.getMsg()+"\n第"+(i-1)+"行导入成功");

                       }catch (NullPointerException e2){//判断teacherRow.getCell返回是否为空指针，如果是这有空单元格
                           System.out.println("xlsx,空指针，该行有空单元格");

                           ajaxRes.setMsg(ajaxRes.getMsg()+"\n第"+(i-1)+"行导入失败,有空单元格");
                           default_insert++;
                           //ajaxRes.setSuccess(false);
                       }catch (DuplicateKeyException e1){//复制键，违反唯一约束条件
                           default_insert++;
                           ajaxRes.setMsg(ajaxRes.getMsg()+"\n第"+(i-1)+"行导入失败,ID与之前的冲突");
                          // ajaxRes.setSuccess(false);
                       }
                    }
                    ajaxRes.setMsg("总共"+(lastRowNum-1)+"记录;有"+default_insert+"条保存记录失败:\n"+ajaxRes.getMsg());
                }
        }catch(Exception e){
            e.printStackTrace();
            ajaxRes.setMsg("导入失败");
            //default_insert++;
           // ajaxRes.setSuccess(false);
        }

        System.out.println(ajaxRes.getMsg());
        return ajaxRes;
    }


*/

    /*配置文件上传解析器  mvc配置当中*/
    @RequestMapping("/uploadExcelFile1")
    @ResponseBody
    public AjaxRes uploadExcelFile1(MultipartFile excel) throws IOException {
        System.out.println(excel.getOriginalFilename());
        AjaxRes ajaxRes = new AjaxRes();
        Teacher teacher=new Teacher();
        int default_insert=0;
        int i =0;
        int lastRowNum=0;
        Sheet sheet;
        Workbook wb;
        Map<String, Object> mapSheet = new HashMap<>();
        try {
            Properties properties = new Properties();
            //用相对路径
            File file = ResourceUtils.getFile("classpath:excel-db-mapper.properties");
            String content = FileUtils.readFileToString(file, String.valueOf(StandardCharsets.UTF_8));
            System.out.println(content);
            FileInputStream input =new FileInputStream(file);
            properties.load(new InputStreamReader(input,"utf-8"));
            ajaxRes.setMsg("--");
            ajaxRes.setSuccess(true);

            try {
                wb = new HSSFWorkbook(excel.getInputStream());
                System.out.println("xls格式");
                 sheet= wb.getSheetAt(0);
                /*获取最大的行号*/
                lastRowNum = sheet.getLastRowNum();
                System.out.println(lastRowNum);
            }catch(OfficeXmlFileException e){
                //e.printStackTrace();
                /*xlsx格式*/
                wb = new XSSFWorkbook(excel.getInputStream());
                System.out.println("xlsx格式");
                sheet = wb.getSheetAt(0);
                /*获取最大的行号*/
                lastRowNum = sheet.getLastRowNum();

                System.out.println(lastRowNum);
                }

                    /*获取表名*/
                    System.out.println(properties.getProperty((String) getCellValue(sheet.getRow(0).getCell(0))));
                    String table_name = properties.getProperty((String) getCellValue(sheet.getRow(0).getCell(0)));
                    /*获取最大的列号*/
                    int lastcolumn = sheet.getRow(1).getLastCellNum();
            System.out.println(lastcolumn);
                    /*获取字段名*/
                    //动态初始化数据
                    String field[] = new String[lastcolumn];

                    Row teacherRow = null;
                    Map<String, Object> columnMap = new HashMap<>();
                    Map<String, Object> map = new HashMap<>();

                    for (i = 2; i <= lastRowNum; i++) {
                        try {
                        for (int j = 0; j < lastcolumn; j++) {
                            columnMap.put(properties.getProperty((String) getCellValue(sheet.getRow(1).getCell(j))), getCellValue(sheet.getRow(i).getCell(j)));
                        }
                        //一定要再定义一个map用来传递参数
                        map.put("columnMap", columnMap);
                        map.put("table_name", table_name);

                        //把后定义的map作为sql的执行参数
                        teacherService.saveMap(map);
                        ajaxRes.setMsg(ajaxRes.getMsg() + "\n第" + (i - 1) + "行导入成功");


                } catch (NullPointerException e2) {//判断teacherRow.getCell返回是否为空指针，如果是这有空单元格
                    System.out.println("xls,空指针，该行有空单元格");

                    ajaxRes.setMsg(ajaxRes.getMsg() + "\n第" + (i - 1) + "行导入失败,有空单元格");
                    default_insert++;
                    //ajaxRes.setSuccess(false);
                } catch (DuplicateKeyException e1) {//复制键，违反唯一约束条件
                            System.out.println("212222222222222222");
                    default_insert++;
                    ajaxRes.setMsg(ajaxRes.getMsg() + "\n第" + (i - 1) + "行导入失败,ID与之前的冲突");
                    // ajaxRes.setSuccess(false);
                }
                    }



        }catch(Exception e){
            System.out.println("333333333333333333");
            e.printStackTrace();
            ajaxRes.setMsg("导入失败");
            //default_insert++;
            // ajaxRes.setSuccess(false);
        }

        System.out.println(ajaxRes.getMsg());
        return ajaxRes;
    }

    private Object getCellValue (Cell cell){
        String cellValue = "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getRichStringCellValue().getString();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    double doubleVal=cell.getNumericCellValue();
                    int longVal = (int)Math.round(doubleVal);
                    if(Double.parseDouble(longVal + ".0") == doubleVal)
                        return longVal;
                    else
                        return doubleVal;
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            default:
                return cellValue;
        }

    }


}


