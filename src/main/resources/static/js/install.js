/**
 * <p>Description: 部署安装 JS</p>
 */

$(function () {

})

function sublimeData() {

    var obj = $('#installdata').serialize();
    console.log(obj)

    $.ajax({
        url: "/install/execute",
        type: 'POST',
        data: obj,

        success: function (data) {
            debugger
            switch (data.status) {
                case 200:
                    // TODO 安装成功后的提示
                    layer.msg('安装成功，请到启动页面启动应用');
                    break;
                case 320: // 参数信息不全，请求填写完整所有参数信息
                    layer.msg(data.message, {icon: 0, shift: 6});
                    break;
                case 330:  // 系统未创建集群应用
                    layer.msg(data.message, {icon: 0, shift: 6});
                    break;
                case 331: // 集群应用命名不合法
                    layer.msg(data.message, {icon: 0, shift: 6});
                    break;
                case 411: // 连接信息出错,无法连接远程服务器
                    layer.msg(data.message, {icon: 0, shift: 6});
                    break;
                case 420: // 远程主机的 http 端口本占用，请选择其他端口
                    layer.msg(data.message, {icon: 0, shift: 6});
                    break;
                case 421: // 远程主机的 tcp 端口被占用，请选择其他端口
                    layer.msg(data.message, {icon: 0, shift: 6});
                    break;
                case 423: // 远程主机的目录被占用
                    layer.msg(data.message, {icon: 0, shift: 6});
                    break;
            }
            console.log(data);
        },

        error: function (er) {
            console.log(er)
        }
    });
}

function get_memory() {

    var _host = $('#_host').val();
    var _username = $('#_username').val();
    var _password = $('#_password').val();

    if (_host != null && _host != "" && _username != null && _username != "" && _password != null && _password != "") {
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



















