package com.itlike.web;

import com.itlike.domain.AjaxRes;
import com.itlike.domain.Teacher;
import com.itlike.service.TeacherService;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class TeacherController {
    /*注入服务层*/
    @Autowired
    private TeacherService teacherService;
    @RequestMapping("/teacher")
    public String teacher(String name){
        System.out.println(name);
        //调用服务层
        teacherService.save(name);
        return "success";
    }


    /*下载模板*/
    @RequestMapping("downloadExcelTpl")
    @ResponseBody
    public void downloadExcelTpl(HttpServletRequest request, HttpServletResponse response){
        FileInputStream is = null;
        try {
            String fileName = new String("EmployeeTpl1.xls".getBytes("utf-8"), "iso8859-1");
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


    /*配置文件上传解析器  mvc配置当中*/
    @RequestMapping("/uploadExcelFile")
    @ResponseBody
    public AjaxRes uploadExcelFile(MultipartFile excel) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            ajaxRes.setMsg("导入成功");
            ajaxRes.setSuccess(true);
            HSSFWorkbook wb = new HSSFWorkbook(excel.getInputStream());
            HSSFSheet sheet = wb.getSheetAt(0);
            /*获取最大的行号*/
            int lastRowNum = sheet.getLastRowNum();
            Row teacherRow = null;
            for (int i = 1; i <= lastRowNum; i++) {
                teacherRow = sheet.getRow(i);
                Teacher teacher = new Teacher();
                System.out.println(getCellValue(teacherRow.getCell(0)));
                System.out.println(getCellValue(teacherRow.getCell(1)));


            }
        }catch(Exception e){
                e.printStackTrace();
                ajaxRes.setMsg("导入失败");
                ajaxRes.setSuccess(false);
            }


            return ajaxRes;
        }
        private Object getCellValue (Cell cell){
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getRichStringCellValue().getString();
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        return cell.getDateCellValue();
                    } else {
                        return cell.getNumericCellValue();
                    }
                case BOOLEAN:
                    return cell.getBooleanCellValue();
                case FORMULA:
                    return cell.getCellFormula();
            }
            return cell;
        }


}


