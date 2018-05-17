<div id="app_list" class="easyui-navpanel">
    <header>
        <div class="m-toolbar">
            <span id="app_list-title" class="m-title"></span>
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
    <ul id="ul_app_list" class="m-list">
    </ul>
</div>