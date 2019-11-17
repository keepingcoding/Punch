var app = new Vue({
    el: '#mainApp',
    data: {
        message: 'Hello Vue!',
        timer1: null,
        timer2: null,
        nowTime: '',
        punchType: null,
        punchTypeStr: '打卡',
        items: []
    },
    methods: {
        getPunchType: function () {
            var _this = this;
            axios({
                method: 'POST',
                url: '/getPunchType',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: _this.nowTime
            }).then(function (res) {
                if (res.data.success && res.data.resultCode == 200) {
                    _this.punchType = res.data.result;
                    _this.punchTypeStr = _this.punchType == 0 ? '上班' : '下班';
                }
            }).catch(function (err) {
                console.log(err);
            });
        },
        doPunch: function () {
            var d = {'time': app.nowTime, 'punchType': app.punchType};
            axios({
                method: 'POST',
                url: '/record',
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
            var _this = this;
            axios({
                method: 'POST',
                url: '/queryByDate',
                data: ''
            }).then(function (res) {
                _this.items = res.data.result;
            }).catch(function (err) {
                console.log(err);
            });
        }
    },
    created: function () {
        this.nowTime = this.formatDate();
        this.getPunchType();
    },
    mounted: function () {
        var _this = this;
        this.timer1 = setInterval(function () {
            _this.nowTime = _this.formatDate();
        }, 1000);

        this.timer2 = setInterval(function () {
            var now = new Date();
            var seconds = now.getSeconds();
            if (seconds == 0) {
                _this.getPunchType();
            }
        }, 1000);
    },
    beforeDestory: function () {
        if (this.timer1) {
            clearInterval(this.timer1);
        }
        if (this.timer2) {
            clearInterval(this.timer2);
        }
    }
})