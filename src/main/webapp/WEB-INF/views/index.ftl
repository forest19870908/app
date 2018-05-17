<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>首页</title>
    <#include "shared/include.ftl">
    <link href="${public}/css/index.css" rel="stylesheet" type="text/css">
    <link href="${public}/css/app_list.css?now=20180504" rel="stylesheet" type="text/css">
    <script src="${public}/js/index.js?now=20180507" type="text/javascript"></script>
</head>
<body>
<div class="easyui-navpanel">
    <div class="easyui-tabs"
         data-options="tabHeight:60,fit:true,tabPosition:'bottom',border:false,pill:true,narrow:true,justified:true">
        <div style="padding:10px">
            <div class="panel-header tt-inner" data_id="0">
                待办
                <span class="m-badge" id="waitdonenumber">${waitdonenumber}</span>
            </div>
            <p>
                <div class="easyui-navpanel">
                    <header>
                        <div class="m-toolbar">
                            <span class="m-title">待办</span>
                        </div>
                    </header>
                    <ul class="m-list" id="ul_0">
                        <#list waitdonelist as map>
                            <li><a href="javascript:void(0)" class="datalist-link" data_title="${map.title}" data_type="0"  data_code="${map.code}">${map.title}
                                <#if map.number gt 0><div class="m-right"><span class="m-badge" style="margin-top:10px">${map.number}</span></div></#if>
                            </a>
                            </li>
                        </#list>
                    </ul>
                </div>
            </p>
        </div>
        <div style="padding:10px">
            <div class="panel-header tt-inner" data_id="1">
                已办
            </div>
            <p>
                <div class="easyui-navpanel">
                    <header>
                        <div class="m-toolbar">
                            <span class="m-title">已办</span>
                        </div>
                    </header>
                    <ul class="m-list" id="ul_1">
                    </ul>
                </div>
            </p>
        </div>
        <div style="padding:10px">
            <div class="panel-header tt-inner" data_id="2">
                我的
            </div>
            <p>
            <div class="m-toolbar">
                <div class="m-title">设置</div>
            </div>
            <div class="m-list">
                <li>名称：${Session["user"].nameF}</li>
                <li>职位：${Session["position"].nameF}</li>
                <li>账号：${Session["user"].codeF}</li>
                <div style="padding:20px"><a href="#" class="easyui-linkbutton c7" style="width:80px" id="unbinduser" data_name="${Session["user"].nameF}">解除绑定</a></div>
            </div>
            </p>
        </div>
    </div>
</div>
<#include "app_list.ftl">
<#include "app_detail.ftl">
</body>
</html>