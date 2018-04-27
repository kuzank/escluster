/**
 * DESCRIPTION :
 */
$(function () {
    $.ajax({
        url: "/operation/info",
        type: 'POST',

        success: function (data) {

            for (var i = 0; i < data.length; i++) { // 遍历一下json数据
                addRowData(data[i]);
            }
            console.log(data);
        },
        error: function (er) {
            console.log(er)
        }
    });

})

function addRowData(item) {

    $("#mytable tbody").append('<tr  style="font-size: 16px">' +
        '<td style="padding-top: 10px">' + item.nodeName + '</td>' +
        '<td style="padding-top: 10px">' + '运行情况' + '</td>' +
        '<td style="padding-top: 10px">' + item.tcpPort + '</td>' +
        '<td style="padding-top: 10px">' + item.httpPort + '</td>' +
        '<td style="padding-top: 10px">' + item.description + '</td>' +
        '<td><button id="run" class="btn" onclick="run(' + item.id + ')">RUN</button></td>' +
        '<td><button id="stop" class="btn" onclick="stop(' + item.id + ')">STOP</button></td>' +
        '</tr>');
}

function run(id) {
    alert("run   " + id);
}

function stop(id) {
    alert("stop   " + id);
}




