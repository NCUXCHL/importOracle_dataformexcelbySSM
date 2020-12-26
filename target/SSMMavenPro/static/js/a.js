$(function () {


    $("#excelUpload").dialog({
        width:260,
        height:180,
        title:"导入Excel",
        buttons:[{
            text:'保存',
            handler:function(){
                $("#uploadForm").form("submit",{
                    url:"uploadExcelFile1",
                    success:function (data) {
                        data = $.parseJSON(data);
                        if (data.success){
                            $.messager.alert("温馨提示1",data.msg);
                            /*关闭对话框 */
                            $("#excelUpload").dialog("close");
                            /*重新加载数据表格*/
                            $("#dg").datagrid("reload");
                        } else {
                            $.messager.alert("温馨提示",data.msg);

                        }
                    }
                })
            }
        },{
            text:'关闭',
            handler:function(){
                $("#excelUpload").dialog("close");
            }
        }],
        closed:true
    })

    $("#excelImpot").click(function () {
        $("#excelUpload").dialog("open");
    });


    $("#excelIn").click(function () {
        $("#excelUpload").dialog("open");
    });

    /*下载Excel模板*/
    $("#downloadTml").click(function () {
        window.open('/downloadExcelTpl')
    });



});