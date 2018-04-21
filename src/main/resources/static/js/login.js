/**
 * <p>Description: </p>
 *
 * @author kuzan
 * @since  2018-04-21
 */

$(function () {
    toastr.options = {
        "closeButton": false,
        "debug": false,
        "positionClass": "toast-bottom-right",
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    }
})

/*
1  toastr.success('');
2  toastr.error('');
3  toastr.warning('');
4  toastr.info('');
*/

function sublimeLogin() {

    var account = $('#account').val();
    var password = $('#password').val();

    if (account == null || account === '' || account === 'null' ||
        password == null || password === '' || password === 'null') {
        toastr.warning('用户名或者密码为空！', 'warning')
    } else {
        $.ajax({
            url: "/user/login",
            type: 'POST',
            data: {
                account: account,
                password: password
            },

            success: function (data) {
                debugger
                switch (data.status) {
                    case 200:
                        toastr.info('操作成功','info')
                        window.location.href = '/index';
                        break;
                    case 405:
                        toastr.error('登陆失败，密码错误或用户名错误', 'error')
                        break;
                }
            },

            error: function (er) {
                toastr.error(er, 'error')
            }
        });
    }
}