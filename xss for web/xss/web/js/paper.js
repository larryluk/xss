var requestUrl = 'http://192.168.1.9:8082/getXsChapter';

var content = new Vue({
    el: '#xsContent',
    data: {
        xsContent: []
    }
});

var prev = new Vue({
    el: '#prev',
    data: {
        prevUrl: ''
    },
    methods: {
        getPrevPage: function () {
            loadPage(this.prevUrl);
        }
    }
});

var next = new Vue({
    el: '#next',
    data: {
        nextUrl: ''
    },
    methods: {
        getNextPage: function () {
            loadPage(this.nextUrl);
        }
    }
})

var title = new Vue({
    el: '#title',
    data: {
        title: ''
    }
});

var openPage = new Vue({
    el: '#openPage',
    data: {
        msg: '加载小说',
        openFlag: false
    },
    methods: {
        openNav: function () {
            this.openFlag = !this.openFlag;
            if(this.openFlag){
                $('#openBtn').removeClass().addClass("closeNav openNavAnimate");
                $('#nav').removeClass().addClass('nav_show fadeInNav');
                $('#btnImg').removeClass().addClass('openImg openImgAnim');
            }
            else {
                $('#openBtn').removeClass().addClass("openNav closeNavAnimate");
                $('#nav').removeClass().addClass('nav_hide fadeOutNav');
                $('#btnImg').removeClass().addClass('closeImg closeImgAnim');
            }

        },
        openPage: function () {
            open();
        }
    }
});

var loadPage = function (webpath) {
    var url = "url=" + webpath;
    $.ajax({
        url: requestUrl, data: url,
        headers: {
            'Accept': 'application/json;charset=UTF-8',
            'Access-Control-Allow-Origin': '*'
        },
        success: function (result) {
            if (result.code != '0') {
                layer.msg(result.msg);
                return;
            }

            content.$set(content.$data, "xsContent", result.data.content.split('\n'));
            content.$set(content.$data, "loadStatus", 'true');
            prev.$set(prev.$data, "prevUrl", result.data.prevUrl);
            next.$set(next.$data, "nextUrl", result.data.nextUrl);
            title.$set(title.$data, "title", result.data.title);

            $('body,html').animate({ scrollTop: 0 }, 500);
        }
    });
}

function open() {
    layer.prompt({title: '请输入小说章节地址', formType: 3}, function (webpath, index) {
        loadPage(webpath);
        layer.close(index);
    });
}

//移动端访问增大字体
if (/(iPhone|iPad|iPod|iOS|Android)/i.test(navigator.userAgent)) {
    $('p').css('font-size', '40px');
}

layer.alert('目前支持的网站有（www.biquzi.com）', {
    skin: 'layui-layer-lan'
    ,closeBtn: 0
}, function (i) {
    open();
    layer.close(i);
});

function testAnim() {
    $('p').each(function () {
        $(this).removeClass().addClass('animated fadeInDown');
    });
};
loadPage("http://www.biquzi.com/11_11850/7653490.html");