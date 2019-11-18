var app = new Vue({
    el: '#mainApp',
    data: {
        message: 'Hello Vue!',
        timer1: null,
        timer2: null,
        nowTime: '',
        punchType: null,
        punchTypeStr: '打卡',
        items: [],
        punchShow: ''
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
                    _this.punchType = res.data.result.type;
                    _this.punchTypeStr = _this.punchType == 0 ? '上班' : '下班';
                    if (res.data.result.data) {
                        var onWorkTimeShow = res.data.result.data.punchOnTime;
                        var offWorkTimeShow = res.data.result.data.punchOffTime;
                        if (_this.punchType != 0) {
                            _this.punchShow = "上班时间: " + onWorkTimeShow;
                            if (offWorkTimeShow) {
                                _this.punchShow = _this.punchShow + "<br>下班时间: " + offWorkTimeShow;
                            }
                        }
                    }
                }
            }).catch(function (err) {
                console.log(err);
            });
        },
        doPunch: function () {
            $('#jeTimeInput').trigger("click");
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
                headers: {
                    'Content-Type': 'application/json'
                },
                data: $('#jeQueryMonth').val()
            }).then(function (res) {
                _this.items = res.data.result;
                $('#tbRecordData').show();
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

        // 日期控件
        jeDate("#jeTimeInput", {
            format: "hh:mm:ss",
            donefun: function(obj){
                var inputDate = jeDate.nowDate(0, "YYYY-MM-DD");
                var time = inputDate + ' ' + obj.val;
                var d = {'time': time, 'punchType': _this.punchType};
                axios({
                    method: 'POST',
                    url: '/record',
                    data: d
                }).then(function (res) {
                    if (res.data.success) {
                        alert(_this.punchTypeStr + "打卡成功");
                    } else {
                        alert(_this.punchTypeStr + "打卡失败<br/>" + res.data.resultMsg);
                    }
                }).catch(function (err) {
                    console.log(err);
                });
            }
        });

        jeDate("#jeQueryMonth", {
            format: "YYYY-MM",
            maxDate: jeDate.nowDate(0, "YYYY-MM"),
            onClose: true,
            isinitVal: true
        });
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