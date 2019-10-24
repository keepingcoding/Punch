var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!',
        nowTime: ''
    },
    methods: {
        doPunch: function () {
            var d = {"time": app.nowTime};
            axios({
                method: "POST",
                url: "/record",
                data: d
            }).then(function (res) {
                console.log(res);
            }).catch(function (err) {
                console.log(err);
            });
        },
        formatDate: function () {
            var _this = this;
            var now = new Date();
            var year = now.getFullYear();
            var month = now.getMonth() + 1;
            var day = now.getDate();
            var hours = now.getHours();
            var minutes = now.getMinutes();
            var seconds = now.getSeconds();
            return year + '-' + _this.fillZero(month) + '-' + _this.fillZero(day) + ' '
                + _this.fillZero(hours) + ':' + _this.fillZero(minutes) + ':' + _this.fillZero(seconds);
        },
        fillZero: function (num) {
            return num < 10 ? '0' + num : num;
        }
    },
    created: function () {
        var _this = this;
        _this.nowTime = _this.formatDate();
    },
    mounted: function () {
        var _this = this;
        this.timer = setInterval(function () {
            _this.nowTime = _this.formatDate();
        }, 1000);
    },
    beforeDestory: function () {
        if (this.timer) {
            clearInterval(this.timer);
        }
    }
})