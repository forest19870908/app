<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>审批列表</title>
    <#include "shared/include.ftl">
</head>
<body>
<div class="easyui-tabs" data-options="fit:true,border:false,tabWidth:80,tabHeight:35">
    <div title="待审批" style="padding:10px">
        <p>Java is a general-purpose, concurrent, class-based, object-oriented computer programming language that is specifically designed to have as few implementation dependencies as possible.</p>
        <p>Java applications are typically compiled to bytecode (class file) that can run on any Java virtual machine (JVM) regardless of computer architecture.</p>
    </div>
    <div title="已审批" style="padding:10px">
        <p>Fortran (previously FORTRAN) is a general-purpose, imperative programming language that is especially suited to numeric computation and scientific computing. Originally developed by IBM at their campus in south San Jose, California[1] in the 1950s for scientific and engineering applications.</p>
    </div>
    <div title="Perl" style="padding:10px">
        <p>Perl is a family of high-level, general-purpose, interpreted, dynamic programming languages. The languages in this family include Perl 5 and Perl 6.</p>
        <p>Though Perl is not officially an acronym, there are various backronyms in use, such as: Practical Extraction and Reporting Language. Perl was originally developed by Larry Wall in 1987 as a general-purpose Unix scripting language to make report processing easier. Since then, it has undergone many changes and revisions.</p>
    </div>
</div>
<style scoped>
    p{
        line-height:150%;
    }
</style>
</body>
</html>