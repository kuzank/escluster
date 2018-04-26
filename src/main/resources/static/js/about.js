

$(function () {

    $.ajax({
        url: "/app/info",
        type: 'POST',

        success: function (data) {
            debugger
            switch (data.status) {
                case 200:
                    $('#msg').text("系统已创建集群应用 : " + data.data);
                    break;
                case 400:
                    $('#msg').text("系统未创建集群应用,请先创建集群应用");
                    break;
            }
        },

        error: function (er) {
            console.log(er);
        }
    });

})



