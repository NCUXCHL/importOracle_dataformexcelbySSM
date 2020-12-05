$(function () {

    /*对话框*/
    $("#dialog").dialog({
        width:350,
        height:400,
        closed:true,
        buttons:[{
            text:'保存',
            handler:function(){

                /*判断当前是添加 还是编辑*/
                var id = $("[name='id']").val();
                var url;
                if(id){
                    /*编辑*/
                    url = "updateEmployee";
                }else {
                    /*添加*/
                    url= "saveEmployee";
                }

                /*提交表单*/
                $("#employeeForm").form("submit",{
                    url:url,
                    onSubmit:function(param){
                        /*获取选中的角色*/
                        var values =  $("#role").combobox("getValues");
                        for(var i = 0; i < values.length; i++){
                            var rid  =  values[i];
                            param["roles["+i+"].rid"] = rid;
                        }
                    },
                    success:function (data) {
                        data = $.parseJSON(data);
                        if (data.success){
                            $.messager.alert("温馨提示",data.msg);
                            /*关闭对话框 */
                            $("#dialog").dialog("close");
                            /*重新加载数据表格*/
                            $("#dg").datagrid("reload");
                        } else {
                            $.messager.alert("温馨提示",data.msg);
                        }
                    }
                });
            }
        },{
            text:'关闭',
            handler:function(){
                $("#dialog").dialog("close");
            }
        }]
    });



    $("#excelUpload").dialog({
        width:260,
        height:180,
        title:"导入Excel",
        buttons:[{
            text:'保存',
            handler:function(){
                $("#uploadForm").form("submit",{
                    url:"uploadExcelFile",
                    success:function (data) {
                        data = $.parseJSON(data);
                        if (data.success){
                            $.messager.alert("温馨提示",data.msg);
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