
function initMenuTree(){
    // layer_show("菜单树","application/menu/menuTree.jsp",300,400);

    var htmlStr = "";
    htmlStr += '<div style="width:200px;height:400px" >';
    htmlStr += '<div style=" line-height:30px; text-indent:10px; margin-bottom:20px; background-color:#eee; position:relative;">组织</div>';
    htmlStr += '<div style="display:block; padding-bottom:5px;" align="center"  >';
    htmlStr += '<div><table><tr><td><input type="hidden" id="tmp_menuId" width="50px"/><input type="text" id="tmp_menuName" width="50px"><button onclick="selectParentMenu();">确定</button></td></tr></table></div>';
    htmlStr += '<div id="treeDemo" class="ztree"></div>';
    htmlStr += '</div>';
    htmlStr += '</div>';


    // 1.弹一个框出来
    layer.open({
        type: 1,
        title: false,
        closeBtn: 1,
        shadeClose: true,
        skin: 'yourclass',
        content: htmlStr
    });

    // 2.树的初始化
    initZtree();
}

function initZtree(){

    var zTreeObj;
    // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
    var setting = {
        async: {
            enable: true,
            url: "menu/getMenuTreeById",
            autoParam: ["id"] // 点击树传递的参数
        },

        callback: {
            //用户点击触发。
            onClick: zTreeOnClick
        }
    };
    // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
    var zNodes = [];
    $(document).ready(function(){

        zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    });
}

//treeNode:当前节点的对象，对象有id，name
function zTreeOnClick(event, treeId, treeNode){
    $("#tmp_menuName").val(treeNode.name);
    $("#tmp_menuId").val(treeNode.id);
}

function selectParentMenu(){
    // 1.先要把用户选择的菜单获取到,准备将这个值传到另一个jsp页面
    var pname = $("#tmp_menuName").val();
    var pid = $("#tmp_menuId").val();

    // 2.关闭框
    layer.close(layer.index);

    // 3.======给表单中的属性赋值
    $("#menupname").val(pname);
    $("#pid").val(pid);
}


function format(json){
    var ret = [], o = {};

    function add(arr, data){
        var obj = {
            "id": data.id,
            "pId": data.pid,
            "name":data.name,
            "open":true,
            "children": [],
            "checked":data.checked
        };
        o[data.id] = obj;
        arr.push(obj);
    }

    json.forEach(x =>{
        if(o[x.pid]){
        add(o[x.pid].children, x);
    }else{
        add(ret, x);
    }
});
    return ret;
}

