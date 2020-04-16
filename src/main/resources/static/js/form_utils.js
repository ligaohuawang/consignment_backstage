function formToObject(form) {
    //1.创建一个对象用来封装表单的值
    var formObject = new Object();
    //2.得到表单数据的数组
    var array = form.serializeArray();
    for (var i =0;i<array.length;i++){
        //取出每一个元素的值
        var element = array[i];
        //给对象赋值
        formObject[element.name]=element.value;
    }
    return formObject;
}

function submit(url,form) {
    //1.先获取表单的数据
    var data = formToObject(form);
    //url是ajax提交的地址，data是表单的数据，function是回调函数,result是返回的结果
    $.post(url,data,function(result){


        // 1.先判断是否操作成功

        if(result.state=="SUCCESS"){

            layer.msg(result.msg, {icon: 1,time:1000},function(){
                // 获取弹出框的索引
                var index = parent.layer.getFrameIndex(window.name);
                // 关闭弹出框
                parent.layer.close(index);
            });
        }else{
            layer.msg(result.msg, {icon: 2,time:1000},function(){
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            });
        }
    });
}