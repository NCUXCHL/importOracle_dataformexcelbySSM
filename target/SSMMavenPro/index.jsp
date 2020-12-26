<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/a.js"></script>
</head>

<body>
<div id="excelUpload">
    <form method="post" enctype="multipart/form-data" id="uploadForm">
        <tabel>
            <tr>
                <td><input type="file" name="excel" style="width: 180px; margin-top: 20px; margin-left: 5px;"></td>
                <td><a href="javascript:void(0);"  id="downloadTml">下载模板-----</a></td>

            </tr>
        </tabel>
    </form>
</div>


<a class="easyui-linkbutton" iconCls="icon-edit" id="excelIn">Excel导入</a>


<h2>----------------------------------------------------</h2>
<h2>Hello World!-----itlike</h2>
<form action="${pageContext.request.contextPath}/teacher">
    姓名: <input type="text" name="name"> <br>
    <input type="submit" value="提交 ">

</form>
</body>
</html>
