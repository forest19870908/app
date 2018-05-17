window.onload = function() {
    $(".tt-inner").on('click',function(){
        if($(this).attr("data_id")==0){//待办
            $("#ul_0").empty();
            ajax({url:base+"/app/index/waitdone",type:"post"}, function (resdata) {
                if(resdata.waitdonelist) {
                    $.each(resdata.waitdonelist, function (i, value) {
                        var content = "<li><a href=\"javascript:void(0)\" class=\"datalist-link\" data_title=\""+value.title+"\" data_type=\"0\"  data_code=\""+value.code+"\">" + value.title + "\n";
                        if (value.number > 0) {
                            content += "                                <div class=\"m-right\"><span class=\"m-badge\" style=\"margin-top:10px\">" + value.number + "</span></div>\n" +
                                "                            </a>\n";
                        }
                        content += "                            </li>";
                        $("#ul_0").append(content);
                    });
                    $(".datalist-link").on('click',openapp_list);
                }
                if(resdata.waitdonenumber){
                    $("#waitdonenumber").html(resdata.waitdonenumber);
                }
            });
        }else if($(this).attr("data_id")==1){//已办
            $("#ul_1").empty();
            ajax({url:base+"/app/index/done",type:"post"}, function (resdata) {
                if(resdata.waitdonelist) {
                    $.each(resdata.waitdonelist, function (i, value) {
                        var content = "<li><a href=\"javascript:void(0)\" class=\"datalist-link\" data_title=\""+value.title+"\" data_type=\"1\"  data_code=\""+value.code+"\">" + value.title + "\n"+
                        "                            </li>";
                        $("#ul_1").append(content);
                    });
                    $(".datalist-link").on('click',openapp_list);
                }
            });
        }else if($(this).attr("data_id")==2){//设置

        }
    });

    /**
     * 解除绑定
     */
    function unbinduser() {
        $.messager.confirm('解除绑定', '是否确认解除绑定当前账号：'+$(this).attr("data_name")+'\n' +
            '解除后需绑定一个账号后方可查看审批项目。', function(r){
            if (r){
                ajax({url:base+"/unbinduser",type:"post"}, function (resdata) {
                    window.location.href=base+"/login";
                });
            }
        });
    }
    $("#unbinduser").on('click',unbinduser);

    function openapp_list(){
        var text = $(this).attr("data_title");
        $('#app_list-title').html(text);
        var type = $(this).attr("data_type");
        var code = $(this).attr("data_code");
        $('#queryform').empty();
        var content="<input type=\"hidden\" name=\"type\" id=\"type\" value=\""+type+"\"/>\n" +
            "            <input type=\"hidden\" name=\"code\" id=\"code\" value=\""+code+"\"/>";
        $('#queryform').append(content);
        $('#ul_app_list').empty();
        ajax({url:base+"/app/query/list",data:$('#queryform').serialize(),type:"post"}, function (resdata) {
            var list_name=resdata.list_name[0];
            if(resdata.list_value) {
                var list_value=resdata.list_value;
                content="";
                $.each(list_value, function (i, value) {
                    content += "<li><a href=\"javascript:void(0)\" class=\"datadetail-link\" data_title=\""+text+"\" data_type=\""+type+"\"  data_code=\""+code+"\""
                    var index=0;
                    for (var key in value) {
                        if(key=='uuid_f'){
                            content += " data_uuid=\""+value[key]+"\">";
                            continue;
                        }
                        if(index==2){
                            content += "<br/>";
                            index=0;
                        }
                        if (key == 'status_f') {
                            content += "&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;"+value[key] + " ";
                        } else {
                            content += "&nbsp;" + list_name[key] + "&nbsp;:&nbsp;" + value[key] + "&nbsp;";
                        }
                        index++;
                    }
                    content += "</a></li>";
                });
                $('#ul_app_list').append(content);
                $(".datadetail-link").on('click',openapp_detail);
            }
        });
        $.mobile.go('#app_list');
    }
    $(".datalist-link").on('click',openapp_list);

   function openapp_detail() {
       var text = $(this).attr("data_title");
       var type = $(this).attr("data_type");// 0 审批 1 详情
       if(type==0){
           text=text+"审批";
       }else if(type==1){
           text=text+"审批";
       }else if(type==2){
           text=text+"详情";
       }
       var code = $(this).attr("data_code");
       var uuid = $(this).attr("data_uuid");
       $('#app_detail-title').html(text);
       $("#main_list").empty();
       $("#detail_list").empty();
       ajax({url:base+"/app/query/detail",data:{code:code,uuid:uuid},type:"post"}, function (resdata) {
           var detail_name=resdata.detail_name[0];
           if(resdata.detail_value) {
               var detail_value=resdata.detail_value;
               var content ="";
               $.each(detail_value, function (i, value) {
                   for (var key in value) {
                       if(key=='uuid_f'){
                           continue;
                       }
                       content+="<tr><td>"+detail_name[key]+"</td><td>" +value[key] + "</td></tr>"
                   }
               });
               $("#main_list").append(content);
           }

           var detail_list_name=resdata.detail_list_name;
           var content ="";
           if(detail_list_name){
               content+="<tr>"
               $.each(detail_list_name, function (i, value) {
                   for (var key in value) {
                       content+="<td>" +value[key] + "</td>"
                   }
               });
               content+="</tr>"
           }
           if(resdata.detail_list_value) {
               var detail_list_value=resdata.detail_list_value;
               $.each(detail_list_value, function (i, value) {
                   content+="<tr>";
                   for (var key in value) {
                       content+="<td>" +value[key] + "</td>"
                   }
                   content+="</tr>";
               });
           }
           $("#detail_list").append(content);
       });
       $.mobile.go('#app_detail');
    }

}