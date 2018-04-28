<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>移动审批登录</title>
    <#include "shared/include.ftl">
    <script src="${public}/js/login.js" type="text/javascript"></script>
</head>
<body>
<div class="easyui-navpanel">
    <header>
        <div class="m-toolbar">
            <span class="m-title">登录</span>
        </div>
    </header>
    <div style="margin:20px auto;width:100px;height:100px;border-radius:100px;overflow:hidden">
        <img src="/public/images/login1.jpg" style="margin:0;width:100%;height:100%;">
    </div>
    <form id="login_form">
        <input type="hidden" name="code" value="${code}"/>
        <input type="hidden" name="state" value="${state}"/>
        <div style="padding:0 20px">
            <div style="margin-bottom:10px">
                <input name="username" class="easyui-textbox" data-options="prompt:'Type username',iconCls:'icon-man'" style="width:100%;height:38px">
            </div>
            <div>
                <input name="password" class="easyui-passwordbox" data-options="prompt:'Type password'" style="width:100%;height:38px">
            </div>
            <div style="text-align:center;margin-top:30px">
                <a  id="login_btn" href="#" class="easyui-linkbutton" style="width:100%;height:40px"><span style="font-size:16px">Login</span></a>
            </div>
        </div>
    </form>
</div>
</body>
</html>