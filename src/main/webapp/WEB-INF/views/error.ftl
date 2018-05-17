<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head><title>错误页面</title></head>
<body>
    <#if ex?index_of("com.cs.core.exception.ValidateException") = 0>
        <input type="hidden" value="${ex?substring(0,ex?last_index_of(":"))}">
    　　${ex?substring(ex?last_index_of(":")+1,ex?length)}
    <#else>
        ${ex}
    </#if>
</body>
</html>