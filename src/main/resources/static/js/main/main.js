var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!',
        nowTime: '',
        items: []
    },
    methods: {
        doPunch: function () {
            var d = {"time": app.nowTime};
            axios({
                method: "POST",
                url: "/record",
                data: d
            }).then(function (res) {
                console.log(res.data);
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
        },
        queryRecords: function () {
            var this_ = this;
            axios({
                method: "POST",
                url: "/queryByDate",
                data: ""
            }).then(function (res) {
                this_.items = res.data.result;
            }).catch(function (err) {
                console.log(err);
            });
        }
    },
    created: function () {
        this.nowTime = this.formatDate();
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