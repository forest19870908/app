var base=$("#base").val();
/**
 * 表单提交成功后返回错误信息提示
 * @param result
 * @returns {boolean}
 */
function errResult(result) {
    if (result.status) {
        if (result.status == 500) {
            result=result.error.replace('\n','<br/>');
            $.messager.alert('错误', result);
            return true;
        }
        if (result.status == 600) {
            window.location.href=base+"/login";
            return true;
        }
    }
    return false;
}
var urlMap=new Map();
/**
 * 封装ajax
 * // 调用方式
 * var obj = {
 *   url: 'xxxx',//必传 发送请求的地址
 *   data: xxx,//非必传 发送到服务器的数据
 *   type: 'get'//非必传，请求方式("POST" 或 "GET")， 默认为 "GET"
 *   dataType: 'json',非必传，预期服务器返回的数据类型，常用的如：xml、html、json、text,默认为json
 * }
 * ajax(obj, function (res) {
 *    //成功后执行的方法
 * },function (err) {
 *  //失败执行方法
 * })
 * @param {*} obj 参数
 * @param {*} successfn 成功回调函数
 * @param {*} errorfn 失败回调函数
 */
function ajax(obj, successfn, errorfn) {
    var key=obj.url+JSON.stringify(obj.data);
    if(urlMap.get(key)!=undefined){
        return;
    }
    urlMap.set(key,"1");
    $.ajax({
        url: obj.url,
        type: obj.type || 'get',
        dataType: obj.dataType || 'json',
        data: obj.data || {},
        async: obj.async,
        success: function (d) {
            urlMap.delete(key);
            if(!errResult(d)){
                successfn(d);
            }
        },
        error: function (e) {
            //公用的错误处理
            urlMap.delete(key);
            // var errorfn  = errorfn ? errorfn : function(){};
            if(!errorfn) {
                if(e.responseText==""){
                    $.messager.alert('错误',"服务器关闭，请联系管理员");
                }else if(e.responseText.indexOf("com.cs.core.exception.ValidateException")>0){
                    $.messager.alert('提示',e.responseText);
                }else{
                    $.messager.alert('错误',e.responseText);
                }
            }else{
                errorfn(e);
            }


        }
    });
}