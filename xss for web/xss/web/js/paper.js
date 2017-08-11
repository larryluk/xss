/**
 *
 * Created by larryluk on 2017/8/11.
 */

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

var loadPage = function (webpath) {
    var requestUrl = 'http://localhost:8081/getXsChapter';
    var url = "url=" + webpath;
    $.ajax({url: requestUrl, data: url,
        headers: {
            'Accept': 'application/json;charset=UTF-8',
            'Access-Control-Allow-Origin' : '*'
        },
        success: function (result) {
            console.log(result.data.content);
            content.$set(content.$data, "xsContent", result.data.content.split('\n'));
            prev.$set(prev.$data, "prevUrl", result.data.prevUrl);
            next.$set(next.$data, "nextUrl", result.data.nextUrl);
        }
    });
}

loadPage('http://www.biquzi.com/11_11850/7644114.html');



