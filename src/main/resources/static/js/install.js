/**
 * <p>Description: 部署安装 JS</p>
 *
 * @author kuzan
 * @since  2018-04-18
 */

$(function () {

})

function sublimeData() {

    var obj = $('#installdata').serialize();
    $.ajax({
        url: "/install/execute",
        type: 'POST',
        data: obj,

        success: function (data) {
            debugger
            alert(data)
        },

        error: function (er) {
            alert(er)
            BackErr(er);
        }
    });
}

function get_memory() {

    var _host = $('#_host').val();
    var _username = $('#_username').val();
    var _password = $('#_password').val();

    if (_host != null && _host != "" && _username != null && _username != "" && _password != null && _password != "") {
        debugger
        $.ajax({
            url: "/install/getMemory",
            type: 'POST',
            timeout: 100000, //超时时间设置，单位毫秒
            data: {
                _host: _host,
                _username: _username,
                _password: _password
            },

            success: function (data) {
                debugger
                switch (data.status) {
                    case 200:
                        layer.msg('远程主机的可用内存大小 : ' + data.data, {icon: 0, shift: 6});
                        break;
                    case 411:
                        layer.msg(data.message, {icon: 0, shift: 6});
                        alert('连接信息出错,无法连接远程服务器');
                        break;
                    case 412:
                        layer.msg(data.message, {icon: 0, shift: 6});
                        alert('连接远程服务器成功,但无法获取服务器内存信息');
                        break;
                }
            },

            error: function (msg) {
                console.log(msg)
            }
        });

    } else {
        layer.msg('请填写完整远程主机的连接信息', {icon: 0, shift: 6});
    }

}



















