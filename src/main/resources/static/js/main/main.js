let app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!',
        nowTime: new Date()
    },
    methods: {
        doPunch: () => {
            axios({
                method: "POST",
                url: "/record",
                data: ""
            }).then((res) => {
                console.log(res);
            }).catch((err) => {
                console.log(err);
            });
        }
    },
    mounted: () => {
        let _this = this;
        this.timer = setInterval(() => {
            _this.nowTime = new Date();
        }, 1000);
    },
    beforeDestory: () => { //清除定时器
        if (this.timer) {
            clearInterval(this.timer);
        }
    }
})