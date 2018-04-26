/**
 * <p>Description: 首页的 JavaScript</p>
 *
 * @author kuzan
 * @since  2018-04-11
 */

$("#menu-toggle").click(function (e) {
    e.preventDefault();
    debugger
    $("#wrapper").toggleClass("toggled");
});
$("#menu-toggle-2").click(function (e) {
    debugger
    e.preventDefault();
    $("#wrapper").toggleClass("toggled-2");
    //$('#menu ul').hide();
});

function initMenu() {

    $('#menu ul').hide();
    $('#menu ul').children('.current').parent().show();
    //$('#menu ul:first').show();
    $('#menu li a').click(
        function () {
            var checkElement = $(this).next();
            if ((checkElement.is('ul')) && (checkElement.is(':visible'))) {
                return false;
            }
            if ((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
                $('#menu ul:visible').slideUp('normal');
                checkElement.slideDown('normal');
                return false;
            }
        }
    );


    $("#tabContainer").tabs({
        data: [{
            id: 'home',
            text: '主页',
            url: "/app/about",
            closeable: false
        }],
        showIndex: 0, // 显示的页面下标
        loadAll: false
    })
}

$(function () {

    initMenu();

});


/**
 * 封装 tab 页面的功能
 * @type {{addPageTab: PageContent.addPageTab}}
 */
var PageContent = {

    addPageTab: function (id, url, name) {

        var tabExist = false;
        var tabExistIndex;
        var tabLength = $("#myTab li").length;

        for (var i = 0; i < tabLength; i++) {

            var tabName = $("#myTab li a span")[i].innerText;
            if ($.trim(name) == $.trim(tabName)) {
                tabExist = true;
                tabExistIndex = i;
                break
            }
        }

        if (tabExist) {
            $("#tabContainer").data("tabs").showTab(id);
        } else {
            $("#tabContainer").data("tabs").addTab({
                id: id,
                text: name,
                url: url,
                closeable: true
            })
        }
    }
}
