window.onload=function(){
    $("#login_btn").on("click",doLogin);
}
function doLogin(){
    ajax({url:base+"/doLogin",data:$('#login_form').serialize(),type:"post"}, function (resdata) {
        window.location.href=base+"/app/index";
    });
}