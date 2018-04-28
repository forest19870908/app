<#assign ctx = rc.contextPath>
<#assign public = ctx+"/public">

<input type="hidden" id="base" value="${ctx}"/>

<#-- js部分 -->
<script src= "${public}/easyui/jquery.min.js" type="text/javascript"></script>
<script src= "${public}/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src= "${public}/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${public}/easyui/jquery.easyui.mobile.js" type="text/javascript"></script>
<script src="${public}/js/common.js" type="text/javascript"></script>
<#-- css部分 -->
<link href="${public}/easyui/themes/metro/easyui.css" rel="stylesheet" type="text/css" />
<link href="${public}/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="${public}/easyui/themes/mobile.css" rel="stylesheet" type="text/css" />



