$(document).ready(function () {
    waterfall();
    window.onscroll = function () {
        if (check()) {
            var jsonData;
            $.ajax({
                url: "/waterfall/getImage",
                dataType:"json",
                async: false,
                success:function(data) {
                    console.log(data);
                    jsonData = data;
                }
            });

            $(jsonData.data).each(function (index, value) {
                var $addpin = $("<div class='pin'>").appendTo( $( "#main" ) );
                var $addbox = $('<div class="box">').appendTo( $addpin );     //换用append用法图片会出现重叠？？
                $('<img>').attr('src','./image/waterfall/' + $( value).attr( 'src') ).appendTo($addbox);
            });
            waterfall();
        };
    }
});

function check() { //检查是否满足加载要求
    var $pins = $('#main>div');
    var lastpin = $pins.last().get(0).offsetTop + Math.floor($pins.last().height() / 2);//get(0)??
    var scrollTop = $(window).scrollTop(); //document.documentElement.scrollTop||document.body.scrollTop;
    var documentHeight = $(document).height(); //页面高度
    console.log(lastpin + "," + scrollTop + "," + documentHeight)
    return (lastpin < scrollTop + documentHeight) ? true : false; //到达指定高度后 返回true，触发waterfall()函数
}

function waterfall() {
    var $pins = $('#main>div');
    var pinwidth = $pins.eq(0).outerWidth(); // 一个块框pin的宽
    var num = Math.floor($(window).width() / pinwidth); //每行中能容纳的pin个数【窗口宽度除以一个块框宽度】
    $("#main").css({
        'width': pinwidth * num,
        'margin': '0 auto',
    });
    var pinheights = []; //用于存储 每列中的所有块框相加的高度。
    $pins.each(function (index, value) {
        if (index < num) {
            pinheights[index] = $pins.eq(index).height();
        } else {
            var minH = Math.min.apply(null, pinheights); //数组pinHArr中的最小值minH
            var minHindex = $.inArray(minH, pinheights); //数组pinHArr中的最小值为minH的索引
            $(value).css({
                "position": "absolute",
                "top": minH,
                "left": $pins.eq(minHindex).position().left,
            });
            pinheights[minHindex] = pinheights[minHindex] + $pins.eq(index).height(); //更新添加了块框后的列高
        }

    });

}