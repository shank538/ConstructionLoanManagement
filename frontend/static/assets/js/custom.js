$(document).ready(function () {
    $(".dropdown-menu li a").click(function () {
        $(".dropdown-menu li").click(function () {
            $(this).parents(".dropdown").find('.btn').html(
                $(this).text() + " <span class=\"caret\"></span>");
        });
    });
});