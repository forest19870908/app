<div id="app_detail" class="easyui-navpanel">
    <header>
        <div class="m-toolbar">
            <span id="app_detail-title" class="m-title"></span>
            <div class="m-left">
                <a href="javascript:void(0)" class="easyui-linkbutton m-back" plain="true" outline="true"
                   onclick="$.mobile.back()">Back</a>
            </div>
        </div>
        <form id="queryform">
            <input type="hidden" name="type" id="type"/>
            <input type="hidden" name="code" id="code"/>
        </form>
    </header>
    <table id="main_list" border="1" cellspacing="0" cellpadding="0" width="100%" align="center">

    </table>
    <table id="detail_list" border="1" cellspacing="0" cellpadding="0" width="100%" align="center">

    </table>
    <table id="approval_list" border="1" cellspacing="0" cellpadding="0" width="100%" align="center">

    </table>
</div>