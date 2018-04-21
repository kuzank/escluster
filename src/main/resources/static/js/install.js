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

function sublimeCheck() {

}