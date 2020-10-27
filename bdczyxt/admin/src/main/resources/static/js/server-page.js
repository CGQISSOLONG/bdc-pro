$.ajaxSetup({headers: {'X-CSRF-TOKEN': $("#csrf_token").attr("content")}});
$(function () {
    setTimeout(function () {
        $('#dataTable').on('click', 'tr span', function () {
            var name = this.getAttribute("name");
            var type = 'PUT';
            var msg = "修改成功";

            var boolean = true;
            if (name == 'lock' || name == 'trash' || name == 'rePassword') {
                if (name == 'trash') {
                    type = 'DELETE';
                    if (!check()) {
                        boolean = false;
                        return;
                    }
                    msg = "删除成功";
                } else if (name == 'rePassword') {
                    type = 'POST';
                    if (!checkPassword()) {
                        boolean = false;
                        return;
                    }
                    msg = "重置成功";
                }
                if (boolean) {
                    $.ajax({
                        type: type,
                        url: $(this).attr("data"),
                        success: function (data) {
                            lay_win(msg);
                            setTimeout(function () {
                                window.location.reload();
                            },2000);
                        },
                        error: function (data) {
                            lay_win("操作失败,请重试！！！");
                        }
                    });
                }
            }
        });
    }, 500);
});

function lay_win(msg) {
    layer.open({
        title:['温馨提示','text-align: center;padding:0;'],
        type: 0,
        closeBtn: 0,
        btn: [],
        shade:0.4,
        time: 2000,
        content: '<p style="text-align: center">'+msg+'</p>'
    });
}